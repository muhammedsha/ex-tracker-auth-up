/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notix.clients;

import com.notix.model.Employee;
import com.notix.utils.UserContext;
import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author jibin
 */
@Component
public class AuthRestTemplateClient {
    @Autowired
    OAuth2RestTemplate restTemplate;

    /**
     *
     * @param principal
     * @return 
     */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(AuthRestTemplateClient.class);

    public Employee getOrganization(String empId){
        logger.debug("In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());

        ResponseEntity<Employee> restExchange =
                restTemplate.exchange(
                        "http://zuulsrvr:5555/authserver/auth/rest/hello",
                        HttpMethod.GET,
                        null, Employee.class, empId);

        return restExchange.getBody();
    }
    
}
