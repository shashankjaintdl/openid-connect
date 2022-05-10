package com.ics.oauth2.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.ics.oauth2.AppType;
import com.ics.oauth2.repository.ClientDetailEntityRepository;
import com.ics.oauth2.service.ClientDetailEntityService;
import com.ics.oauth2.models.ClientDetailsEntity;
import com.ics.openid.connect.service.BlacklistedSiteService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
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

        if(!clientDetails.getRegisteredRedirectUri().isEmpty()){
            for(String uri:clientDetails.getRegisteredRedirectUri()){
               if(Boolean.TRUE.equals(blacklistedSiteService.isBlacklisted(uri))){
                   throw new IllegalArgumentException("Can't registered Blacklisted site :"+uri);
               }
            }
        }

        if(Strings.isNullOrEmpty(clientDetails.getClientId())){
           clientDetails = generateClientId(clientDetails);
        }

        if(Strings.isNullOrEmpty(clientDetails.getClientSecret())){
            clientDetails = generateClientSecret(clientDetails);
        }


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
        return null;
    }


}
