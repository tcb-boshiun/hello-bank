package com.rick.backend.controller;

import com.rick.backend.db.dao.UserRepository;
import com.rick.backend.db.entity.User;
import com.rick.backend.service.HelloService;
import com.rick.backend.service.SimpleTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;
    private final SimpleTokenService tokenService;
    private final UserRepository userRepository;

    /**
     * 從 Authorization header 抽 token → 找 userAct → 找 User
     * 回傳 Optional<User>，方便後面用 Optional 流水線處理
     */
    private Optional<User> getUser(String authHeader) {
        return Optional.ofNullable(authHeader)
                .filter(h -> h.startsWith("Bearer "))
                .map(h -> h.substring(7))          // 取出 token
                .map(tokenService::verify)         // token → userAct（可能為 null）
                .flatMap(userRepository::findByUserAct); // userAct → Optional<User>
    }

    /**
     * 共用工具：若有登入，就用 template 套入 name + baseMessage；否則回 baseMessage
     * template 例如："Hello, %s! %s"、"%s said: %s"
     */
    private String formatWithUser(Optional<User> userOpt,
                                  String baseMessage,
                                  String template) {
        return userOpt
                .map(User::getUserName)
                .map(name -> String.format(template, name, baseMessage))
                .orElse(baseMessage);
    }

    // ----------------------------------------------------
    // GET /api/hello
    // 未登入：回 helloService.hello()
    // 已登入：回 "Hello, {name}! {helloService.hello()}"
    // ----------------------------------------------------
    @GetMapping("/hello")
    public String hello(@RequestHeader(value = "Authorization", required = false) String auth) {

        String base = helloService.hello();      // 原本的 hello 文案
        Optional<User> userOpt = getUser(auth);  // Optional<User>

        return formatWithUser(userOpt, base, "Hello, %s! %s");
    }

    // ----------------------------------------------------
    // POST /api/echo
    // body 可為空、可不傳
    // 未登入：直接回 echo(message)
    // 已登入：回 "{name} said: {echo(message)}"
    // ----------------------------------------------------
//    @PostMapping("/echo")
//    public String echo(
//            @RequestHeader(value = "Authorization", required = false) String auth,
//            @RequestBody(required = false) String body) {
//
//        String message = Optional.ofNullable(body).orElse(""); // null → ""
//        String base = helloService.echo(message);
//        Optional<User> userOpt = getUser(auth);
//
//        return formatWithUser(userOpt, base, "%s said: %s");
//    }

    // ----------------------------------------------------
    // POST /api/echo/v2
    // 同樣允許空 body
    // 未登入：直接回 echo_v2(message)（或 echo，看你 service 實作）
    // 已登入：回 "{name} said(v2): {echo_v2(message)}"
    // ----------------------------------------------------
    @PostMapping("/echo/v2")
    public String echoV2(
            @RequestHeader(value = "Authorization", required = false) String auth,
            @RequestBody(required = false) String body) {

        String message = Optional.ofNullable(body).orElse(""); // null → ""
        String base = helloService.echo(message);           // 若你方法叫 echo，就改成 echo(message)
        Optional<User> userOpt = getUser(auth);

        return formatWithUser(userOpt, base, "%s said(v2): %s");
    }
}
