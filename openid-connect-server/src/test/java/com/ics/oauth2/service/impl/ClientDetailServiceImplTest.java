package com.ics.oauth2.service.impl;

import com.google.common.collect.Sets;
import com.ics.common.specs.oauth2.TokenEndPointAuthMethod;
import com.ics.oauth2.models.ClientDetailsEntity;
import com.ics.oauth2.repository.ClientDetailEntityRepository;
import com.ics.openid.connect.service.BlacklistedSiteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientDetailServiceImplTest {

    @Mock
    private ClientDetailEntityRepository repository;

    @Mock
    private BlacklistedSiteService blacklistedSiteService;

    @InjectMocks
    private ClientDetailEntityServiceImpl clientDetailEntityService;



    @Test
    void testSaveNewClientWithBadId() throws Exception{

        ClientDetailsEntity clientDetails = Mockito.mock(ClientDetailsEntity.class);
        Mockito.when(clientDetails.getId()).thenReturn(1234L);
        Assertions.assertThrows(IllegalArgumentException.class,()->clientDetailEntityService.saveNewClient(clientDetails));

    }


//    @Test
//    void testSaveNewWithBlacklistedSites() throws Exception{
//
//        ClientDetailsEntity clientDetails = Mockito.mock(ClientDetailsEntity.class);
//        Mockito.when(clientDetails.getId()).thenReturn(null);
//
//        String badUri = "badplace.xxx";
//
//        Mockito.when(blacklistedSiteService.isBlacklisted(badUri)).thenReturn(true);
//        Mockito.when(clientDetails.getRegisteredRedirectUri()).thenReturn(Sets.newHashSet(badUri));
//
//        Assertions.assertThrows(IllegalArgumentException.class,()->clientDetailEntityService.saveNewClient(clientDetails));
//
//    }


    @Test
    void testIfClientDynamicallyRegistered() throws Exception{
        ClientDetailsEntity clientDetails = Mockito.mock(ClientDetailsEntity.class);
        Mockito.when(clientDetails.getId()).thenReturn(null);
        Mockito.when(clientDetails.isDynamicallyRegistered()).thenReturn(true);
        clientDetails.setTokenEndPointAuthMethod(TokenEndPointAuthMethod.CLIENT_SECRET_BASIC);

        clientDetailEntityService.saveNewClient(clientDetails);

        Assertions.assertTrue(clientDetails.isDynamicallyRegistered());


    }

}
