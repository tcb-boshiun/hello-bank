package com.rick.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class HelloService {

    public String hello() {
        return "Hello, here is Hello-Bank!";
    }

    public String echo(@RequestBody String message) {
        return "You sent: " + message;
    }
}
