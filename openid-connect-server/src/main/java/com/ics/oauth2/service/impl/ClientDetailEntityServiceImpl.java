package com.ics.oauth2.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.ics.oauth2.AppType;
import com.ics.oauth2.GrantType;
import com.ics.oauth2.TokenEndPointAuthMethod;
import com.ics.oauth2.ValidationException;
import com.ics.oauth2.repository.ClientDetailEntityRepository;
import com.ics.oauth2.service.ClientDetailEntityService;
import com.ics.oauth2.models.ClientDetailsEntity;
import com.ics.oauth2.service.ReservedScopeService;
import com.ics.openid.connect.service.ApprovedSiteService;
import com.ics.openid.connect.service.BlacklistedSiteService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.*;

@Service
@Transactional
@SuppressWarnings("deprecation")
public class ClientDetailEntityServiceImpl implements ClientDetailEntityService {

    public static final String INVALID_CLIENT_METADATA = "invalid_client_metadata";

    private final ClientDetailEntityRepository clientDetailEntityRepository;

    private final BlacklistedSiteService blacklistedSiteService;

    private final ApprovedSiteService approvedSiteService;

    private final ReservedScopeService reservedScopeService;

    @Autowired
    public ClientDetailEntityServiceImpl(ClientDetailEntityRepository clientDetailEntityRepository,
                                         BlacklistedSiteService blacklistedSiteService,
                                         ApprovedSiteService approvedSiteService,
                                         ReservedScopeService reservedScopeService) {

        this.clientDetailEntityRepository = clientDetailEntityRepository;
        this.blacklistedSiteService = blacklistedSiteService;
        this.approvedSiteService = approvedSiteService;
        this.reservedScopeService = reservedScopeService;
    }

    @PostConstruct
    public void postConstruct(){
        ClientDetailsEntity clientDetails = new ClientDetailsEntity();
        clientDetails.setClientId("client");
        generateClientSecret(clientDetails);
        clientDetails.setApplicationType(AppType.WEB);
        clientDetails.setScope(ImmutableSet.of("READ","WRITE"));
        clientDetails.setGrantTypes(ImmutableSet.of("authorization_code"));
        clientDetails.setRegisteredRedirectUri(ImmutableSet.of("www.google.com"));
        clientDetailEntityRepository.save(clientDetails);
    }

    @Override
    public ClientDetailsEntity saveNewClient(ClientDetailsEntity clientDetails) {

        if(clientDetails.getId() != null){
            throw new IllegalArgumentException(" Can't save new client with existing ID "+ clientDetails.getId());
        }


        if(Strings.isNullOrEmpty(clientDetails.getClientId())){
           clientDetails = generateClientId(clientDetails);
        }

        if(clientDetails.getResponseTypes() == null || clientDetails.getResponseTypes().isEmpty()){
            clientDetails.setResponseTypes(Sets.newHashSet());
        }


        try{
            validateRedirectUri(clientDetails);
            clientDetails = validateClientAuth(clientDetails);
        }
        catch (ValidationException e){
            e.printStackTrace();
        }


        clientDetails.setDynamicallyRegistered(true);

        clientDetailEntityRepository.save(clientDetails);

        return clientDetails;
    }

    @Override
    public ClientDetailsEntity getClientById(Long id) {
        return clientDetailEntityRepository.getById(id);
    }

    @Override
    public ClientDetailsEntity updateClient(ClientDetailsEntity oldClient, ClientDetailsEntity newClient) {
        return null;
    }

    @Override
    public ClientDetailsEntity loadClientByClientId(String clientId) throws OAuth2Exception, IllegalArgumentException {

        if(!Strings.isNullOrEmpty(clientId)) {

            return clientDetailEntityRepository.findByClientId(clientId)
                    .orElseThrow(
                            () -> new InvalidClientException("Client with id "+ clientId + "does not present!")
                    );
        }

        throw new IllegalArgumentException("Client Id must not be empty");
    }

    @Override
    public ClientDetailsEntity generateClientId(ClientDetailsEntity clientDetails) {
        clientDetails.setClientId(UUID.randomUUID().toString());
        return clientDetails;
    }

    @Override
    public ClientDetailsEntity generateClientSecret(ClientDetailsEntity clientDetails) {
        clientDetails.setClientSecret(Base64.encodeBase64URLSafeString(new BigInteger(512, new Random()).toByteArray()));
        return clientDetails;
    }

    @Override
    public Collection<ClientDetailsEntity> getAllClients() {
        return Collections.emptyList();
    }


    /**
     * @param clientDetails
     * check for request uri is blacklisted or URI is valid.
     * @return clientDetails
     */
    private void validateRedirectUri(ClientDetailsEntity clientDetails) throws ValidationException{
        if(clientDetails.getGrantTypes().contains(GrantType.AUTHORIZATION_CODE.getValue())){

            if (clientDetails.getRegisteredRedirectUri() == null  ||
                    clientDetails.getRegisteredRedirectUri().isEmpty()){
                throw new ValidationException(INVALID_CLIENT_METADATA,"Clients using a redirect-based grant type must register at least one redirect URI.",HttpStatus.BAD_REQUEST);
            }

            for (String uri:clientDetails.getRegisteredRedirectUri()){
                if(blacklistedSiteService.isBlacklisted(uri)){
                    throw new ValidationException(INVALID_CLIENT_METADATA,"Redirect URI is not allowed "+uri,HttpStatus.BAD_REQUEST);
                }
                if(uri.contains("#")){
                    throw new ValidationException(INVALID_CLIENT_METADATA,"Redirect URI can not have fragment "+uri,HttpStatus.BAD_REQUEST);
                }
            }

        }

    }

    /**
     *
     * @param clientDetails
     * @return
     */
    private ClientDetailsEntity validateClientAuth(ClientDetailsEntity clientDetails) throws ValidationException {

        if(clientDetails.getTokenEndPointAuthMethod()==null){
            clientDetails.setTokenEndPointAuthMethod(TokenEndPointAuthMethod.CLIENT_SECRET_BASIC);
        }

        if((clientDetails.getTokenEndPointAuthMethod() == TokenEndPointAuthMethod.CLIENT_SECRET_BASIC) ||
                (clientDetails.getTokenEndPointAuthMethod() == TokenEndPointAuthMethod.CLIENT_SECRET_JWT) ||
                (clientDetails.getTokenEndPointAuthMethod() == TokenEndPointAuthMethod.CLIENT_SECRET_POST)){
            if(Strings.isNullOrEmpty(clientDetails.getClientSecret())){
                clientDetails = generateClientSecret(clientDetails);
            }
        }

        else if(clientDetails.getTokenEndPointAuthMethod() == TokenEndPointAuthMethod.PRIVATE_KEY_JWT){
            // We need JWKs URI to use PRIVATE_KEY_JWT authentication

            if(clientDetails.getJwkSet() == null && Strings.isNullOrEmpty(clientDetails.getJwksUri())){
                throw new IllegalArgumentException();
            }

        }

        else if(clientDetails.getTokenEndPointAuthMethod() == TokenEndPointAuthMethod.NONE){
            clientDetails.setClientSecret(null); // No need to generate the client secret
        }

        else{
            throw new ValidationException(INVALID_CLIENT_METADATA, "Unknown authentication method", HttpStatus.BAD_REQUEST);
        }

        return clientDetails;
    }

    private ClientDetailsEntity validateGrantType(ClientDetailsEntity clientDetails) throws ValidationException{
        if (clientDetails.getGrantTypes() == null || clientDetails.getGrantTypes().isEmpty()){
            if(clientDetails.getScope().contains("offline_access")){
                clientDetails.setGrantTypes(Sets.newHashSet(GrantType.AUTHORIZATION_CODE.getValue(),GrantType.REFRESH_TOKEN.getValue()));
            }
            else{
                clientDetails.setGrantTypes(Sets.newHashSet(GrantType.AUTHORIZATION_CODE.getValue()));
            }
        }

        return clientDetails;
    }



    private ClientDetailsEntity validateScope(ClientDetailsEntity clientDetails){

        return null;
    }


}
