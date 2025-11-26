package com.rick.backend.service;

import com.rick.backend.facade.HelloFacade;
import org.springframework.stereotype.Service;

@Service
public class HelloService implements HelloFacade {

    public String hello() {
        return "Hello, here is Hello-Bank!";
    }

    public String echo(String message) {
        return "You sent: " + message;
    }
}
