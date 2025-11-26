package com.rick.backend.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hello() {
        return "Hello, here is Hello-Bank!";
    }

    public String echo(String message) {
        return "You sent: " + message;
    }
}
