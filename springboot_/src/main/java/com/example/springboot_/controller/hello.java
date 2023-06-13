package com.example.springboot_.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('admin')")
    public String hellos(){
        return "eee";
    }
}
