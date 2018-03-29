package com.notix.controller;

import com.notix.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vinu Sagar
 * 27/02/18
 */

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration config;
    @GetMapping("/limits")
    public LimitConfiguration retrieveLimits(){
        return new LimitConfiguration(config.getMinimum(),config.getMaximum());
    }
}
