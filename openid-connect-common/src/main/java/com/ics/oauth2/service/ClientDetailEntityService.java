package com.ics.oauth2.service;

import com.ics.oauth2.models.ClientDetailsEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import java.util.Collection;

@SuppressWarnings("deprecation")
public interface ClientDetailEntityService extends ClientDetailsService {

    ClientDetailsEntity saveNewClient(ClientDetailsEntity clientDetails);

    ClientDetailsEntity getClientById(Long id);

    ClientDetailsEntity updateClient(ClientDetailsEntity oldClient, ClientDetailsEntity newClient);

    @Override
    ClientDetailsEntity loadClientByClientId(String clientId) throws OAuth2Exception;

    ClientDetailsEntity generateClientId(ClientDetailsEntity clientDetails);

    ClientDetailsEntity generateClientSecret(ClientDetailsEntity clientDetails);

    Collection<ClientDetailsEntity> getAllClients();
}
