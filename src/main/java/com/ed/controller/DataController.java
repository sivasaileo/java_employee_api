package com.ed.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getData() {
        return "This is data accessible to USER role.";
    }
}