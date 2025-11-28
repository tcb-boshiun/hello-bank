package com.rick.backend.service;

import com.rick.backend.facade.HelloFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HelloService implements HelloFacade {

    @Override
    public String hello() {
        return "This is Hello-Bank!";
    }

    @Override
    public String echo(String message) {
        return Optional.ofNullable(message)
                .filter(s -> !s.isBlank())
                .orElse("您有權保持緘默!");
    }
}
