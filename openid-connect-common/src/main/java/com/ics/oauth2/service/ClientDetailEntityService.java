package com.ics.oauth2.service;

import com.ics.oauth2.models.ClientDetailsEntity;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import java.util.Collection;

@SuppressWarnings("deprecation")
public interface ClientDetailEntityService extends ClientDetailsService {

    ClientDetailsEntity saveNewClient(ClientDetailsEntity clientDetails);

    ClientDetailsEntity getClientById(Long id);

    ClientDetailsEntity updateClient(ClientDetailsEntity clientDetails);

    @Override
    ClientDetailsEntity loadClientByClientId(String clientId) throws ClientRegistrationException;

    ClientDetailsEntity generateClientId(ClientDetailsEntity clientDetails);

    ClientDetailsEntity generateClientSecret(ClientDetailsEntity clientDetails);

    Collection<ClientDetailsEntity> getAllClients();
}
