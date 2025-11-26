package com.rick.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // 開發環境先全部放行，之後可再收緊
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Hello-Bank backend!";
    }

    @PostMapping("/echo")
    public String echo(@RequestBody String message) {
        return "You sent: " + message;
    }
}