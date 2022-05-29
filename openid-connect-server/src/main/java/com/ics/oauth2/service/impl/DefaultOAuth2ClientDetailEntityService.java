package com.ics.oauth2.service.impl;

import com.google.common.base.Strings;
import com.ics.common.specs.oauth2.AppType;
import com.ics.common.specs.oauth2.GrantType;
import com.ics.common.specs.openid.Scope;
import com.ics.exception.ValidationException;
import com.ics.oauth2.models.ClientDetailsEntity;
import com.ics.oauth2.models.RegisteredClientDetails;
import com.ics.oauth2.repository.ClientDetailEntityRepository;
import com.ics.oauth2.service.ClientDetailEntityService;
import com.ics.openid.connect.service.BlacklistedSiteService;
import com.ics.utils.ClientDetailEntityValidator;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.*;

@Service
@Transactional
@SuppressWarnings("deprecation")
public class DefaultOAuth2ClientDetailEntityService extends ClientDetailEntityValidator implements ClientDetailEntityService {

    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultOAuth2ClientDetailEntityService.class);

    private final ClientDetailEntityRepository clientDetailEntityRepository;
    private final BlacklistedSiteService blacklistedSiteService;

    public DefaultOAuth2ClientDetailEntityService(ClientDetailEntityRepository clientDetailEntityRepository,
                                                  BlacklistedSiteService blacklistedSiteService) {
        super();
        this.clientDetailEntityRepository = clientDetailEntityRepository;
        this.blacklistedSiteService = blacklistedSiteService;
    }

    @PostConstruct
    public void postConstruct(){
        ClientDetailsEntity clientDetails = new ClientDetailsEntity();
        clientDetails.setClientId("client");
        clientDetails.setApplicationType(AppType.WEB);
        clientDetails.setResponseTypes(new HashSet<>(List.of("id_token")));
        clientDetails.setScope(Set.of(Scope.OPENID.getValue(),Scope.PROFILE.getValue()));
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        clientDetails.setAuthorities(grantedAuthorities);
        clientDetails.setGrantTypes(Set.of(GrantType.AUTHORIZATION_CODE.getValue()));
        clientDetails.setRegisteredRedirectUri(Set.of("www.google.com"));
        saveNewClient(clientDetails);

    }

    @Override
    public ClientDetailsEntity saveNewClient(ClientDetailsEntity clientDetails) {

        if (clientDetails.getId()!=null ){
            throw new IllegalArgumentException("");
        }

        if (Strings.isNullOrEmpty(clientDetails.getClientId())){
            generateClientId(clientDetails);
        }

        try {

            validateSoftwareStatement(clientDetails);
            validateGrantType(clientDetails);
            validateResponseType(clientDetails);
            validateRedirectUri(clientDetails);
            validateAuthMethod(clientDetails);

        }
        catch (ValidationException e){
            e.printStackTrace();
        }

        ClientDetailsEntity savedClientDetails = clientDetailEntityRepository.save(clientDetails);



        RegisteredClientDetails registeredClientDetails = new RegisteredClientDetails(savedClientDetails,"","");

       LOGGER.info(registeredClientDetails.getClientDescription());

        return null;
    }

    @Override
    public ClientDetailsEntity loadClientByClientId(String clientId) throws ClientRegistrationException, IllegalArgumentException {

        if(!Strings.isNullOrEmpty(clientId)){

            return clientDetailEntityRepository.findByClientId(clientId)
                    .orElseThrow(
                            () -> new InvalidClientException("Client with id "+ clientId + "does not present!")
                    );

        }

        throw new IllegalArgumentException("Client id must be null or empty!");
    }

    @Override
    public ClientDetailsEntity getClientById(Long id) {
        return clientDetailEntityRepository.findById(id).orElseThrow(
                ()->new IllegalStateException("Client detail does not found with Id " + id)
        );
    }

    @Override
    public ClientDetailsEntity updateClient(ClientDetailsEntity clientDetails) {
        return clientDetails;
    }

    @Override
    public ClientDetailsEntity generateClientId(ClientDetailsEntity clientDetails) {
        clientDetails.setClientId(UUID.randomUUID().toString());
        return clientDetails;
    }

    @Override
    public ClientDetailsEntity generateClientSecret(ClientDetailsEntity clientDetails) {
        clientDetails.setClientSecret(Base64.encodeBase64URLSafeString(new BigInteger(256,new Random()).toByteArray()));
        return clientDetails;
    }

    @Override
    public Collection<ClientDetailsEntity> getAllClients() {
        return clientDetailEntityRepository.findAll();
    }



    private void validateRedirectUri(ClientDetailsEntity clientDetails) throws ValidationException{

        if(clientDetails.getGrantTypes().contains(GrantType.AUTHORIZATION_CODE.getValue()) || clientDetails.getGrantTypes().contains(GrantType.IMPLICIT.getValue())) {

            if (clientDetails.getRegisteredRedirectUri().isEmpty()){
                throw new ValidationException("invalid_client_metadata","Clients using a redirect-based grant type must register at least one redirect URI.",HttpStatus.BAD_REQUEST);
            }

            for (String uri : clientDetails.getRegisteredRedirectUri()) {

                if (blacklistedSiteService.isBlacklisted(uri)) {
                    throw new ValidationException("invalid_client_metadata", "Redirect URI does not allowed "+uri, HttpStatus.BAD_REQUEST);
                }

                // checking if URI contain fragments
                if (uri.contains("#")){
                    throw new ValidationException("invalid_client_metadata", "Redirect must have fragment", HttpStatus.BAD_REQUEST);
                }
            }
        }
    }



}
