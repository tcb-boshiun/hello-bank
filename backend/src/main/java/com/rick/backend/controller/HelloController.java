package com.rick.backend.controller;

import com.rick.backend.facade.HelloFacade;
import com.rick.backend.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HelloController implements HelloFacade {

    @Autowired
    private HelloService service;

    @GetMapping("/hello")
    public String hello() {
        return this.service.hello();
    }

    @PostMapping("/echo")
    public String echo(@RequestBody String message) {
        return this.service.echo(message);
    }
}