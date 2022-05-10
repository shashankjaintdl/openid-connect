//package com.ics.auth.server.web;
//
//import com.ics.oauth2.service.ClientDetailEntityService;
//import com.ics.auth.server.processor.ClientDetailEntityJsonProcessor;
//import com.ics.oauth2.models.ClientDetailsEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping(ClientDetailEndpoint.ENDPOINT)
//public class ClientDetailEndpoint {
//
//    public static final String ENDPOINT = "/client";
//
//    private final ClientDetailEntityService clientDetailEntityService;
//
//    @Autowired
//    public ClientDetailEndpoint(ClientDetailEntityService clientDetailEntityService) {
//        this.clientDetailEntityService = clientDetailEntityService;
//    }
//
//    @PostMapping(value = "/register",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
//    public String registerClient(@RequestBody String jsonData, Model m){
//        ClientDetailsEntity clientDetails = ClientDetailEntityJsonProcessor.parse(jsonData);
//
//        clientDetailEntityService.saveNewClient(clientDetails);
//        return null;
//    }
//}
