package com.ics.oauth2.service.impl;


import com.ics.oauth2.repository.ClientDetailEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientDetailServiceImplTest {

    @Mock
    private ClientDetailEntityRepository repository;

    @InjectMocks
    private ClientDetailEntityServiceImpl clientDetailEntityService;


    @Test
    void runTest(){

    }


}
