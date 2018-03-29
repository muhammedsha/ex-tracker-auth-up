/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jibin
 */
@RestController
@RequestMapping(value="/auth/user")
public class HelloWorldController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!!!!!!";
    }
}
