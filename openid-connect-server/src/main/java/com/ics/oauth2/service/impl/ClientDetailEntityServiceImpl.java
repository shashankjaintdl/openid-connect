package com.ics.oauth2.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.ics.oauth2.AppType;
import com.ics.oauth2.GrantType;
import com.ics.oauth2.TokenEndPointAuthMethod;
import com.ics.oauth2.repository.ClientDetailEntityRepository;
import com.ics.oauth2.service.ClientDetailEntityService;
import com.ics.oauth2.models.ClientDetailsEntity;
import com.ics.openid.connect.service.BlacklistedSiteService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ClientDetailEntityRepository clientDetailEntityRepository;

    private final BlacklistedSiteService blacklistedSiteService;

    @Autowired
    public ClientDetailEntityServiceImpl(ClientDetailEntityRepository clientDetailEntityRepository, BlacklistedSiteService blacklistedSiteService) {
        this.clientDetailEntityRepository = clientDetailEntityRepository;
        this.blacklistedSiteService = blacklistedSiteService;
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

//        if(!clientDetails.getRegisteredRedirectUri().isEmpty()){
//            for(String uri:clientDetails.getRegisteredRedirectUri()){
//               if(Boolean.TRUE.equals(blacklistedSiteService.isBlacklisted(uri))){
//                   throw new IllegalArgumentException("Can't registered Blacklisted site :"+uri);
//               }
//            }
//        }

        validateRedirectUri(clientDetails);
        clientDetails = validateClientAuth(clientDetails);


        if(Strings.isNullOrEmpty(clientDetails.getClientId())){
           clientDetails = generateClientId(clientDetails);
        }


        if(Strings.isNullOrEmpty(clientDetails.getClientSecret())){
            clientDetails = generateClientSecret(clientDetails);
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
    private void validateRedirectUri(ClientDetailsEntity clientDetails){
        if(clientDetails.getGrantTypes().contains(GrantType.AUTHORIZATION_CODE.getValue())){

            if (clientDetails.getRegisteredRedirectUri() == null  || clientDetails.getRegisteredRedirectUri().isEmpty()){
                throw new IllegalArgumentException("");
            }

            for (String uri:clientDetails.getRegisteredRedirectUri()){
                if(blacklistedSiteService.isBlacklisted(uri)){
                    throw new IllegalArgumentException();
                }
                if(uri.contains("#")){
                    throw new IllegalArgumentException();
                }
            }

        }

    }

    /**
     *
     * @param clientDetails
     * @return
     */
    private ClientDetailsEntity validateClientAuth(ClientDetailsEntity clientDetails){

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
            throw new IllegalArgumentException("invalid_client_metadata, Unknown authentication method");
        }

        return clientDetails;
    }

}
