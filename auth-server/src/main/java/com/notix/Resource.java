package com.notix;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/rest/hello")
public class Resource {
    @GetMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }
    @GetMapping()
    public String hello(){
        return "Hello";
    }
   
}
