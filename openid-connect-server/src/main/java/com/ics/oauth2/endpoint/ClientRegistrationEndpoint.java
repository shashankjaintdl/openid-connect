package com.ics.oauth2.endpoint;

import com.google.gson.JsonSyntaxException;
import com.ics.exception.ValidationException;
import com.ics.oauth2.models.ClientDetailsEntity;
import com.ics.oauth2.service.ClientDetailEntityService;
import com.ics.processor.ClientDetailEntityJsonProcessor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(ClientRegistrationEndpoint.ENDPOINT)
public class ClientRegistrationEndpoint {

    public static final String ENDPOINT = "client";

    private final ClientDetailEntityService clientDetailService;

    @Autowired
    public ClientRegistrationEndpoint(ClientDetailEntityService clientDetailService) {
        this.clientDetailService = clientDetailService;
    }


    @PostMapping(
            value = "/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String registerClient(@RequestBody String jsonData){
        ClientDetailsEntity client = new ClientDetailsEntity();
        try {
             client = ClientDetailEntityJsonProcessor.parse(jsonData);
        }
        catch (JsonSyntaxException ex){
            ex.printStackTrace();
        }

        Objects.requireNonNull(client).setClientId(Strings.EMPTY);
        Objects.requireNonNull(client).setClientSecret(Strings.EMPTY);


        ClientDetailsEntity clientDetails =clientDetailService.saveNewClient(client);


        return "Hello";
    }


    @GetMapping
    public String index(){

        return "index.html";
    }


    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteClient(@PathVariable("id") String clientId){

        return null;
    }

}
