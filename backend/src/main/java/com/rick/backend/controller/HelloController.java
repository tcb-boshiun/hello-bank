package com.rick.backend.controller;

import com.rick.backend.service.HelloService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HelloController {

    @Autowired
    private HelloService service;

    @GetMapping("/hello")
    public String hello() {
        return this.service.hello();
    }

    @PostMapping("/echo")
    public String echo(@RequestBody @NotBlank String message) {
        return this.service.echo(message);
    }
}