package com.ics.oauth2.service.impl;

import com.ics.oauth2.repository.ReservedScopeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservedScopeServiceTest {

    @Mock
    private ReservedScopeRepository reservedScopeRepository;

    @InjectMocks
    private DefaultReservedScopeServiceImpl reservedScopeService;


    @Test
    void testSaveReservedScope() throws Exception{


    }

}
