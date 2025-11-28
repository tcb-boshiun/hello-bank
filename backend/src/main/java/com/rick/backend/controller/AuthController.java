package com.rick.backend.controller;

import com.rick.backend.db.dao.UserRepository;
import com.rick.backend.db.entity.User;
import com.rick.backend.model.rq.LoginRq;
import com.rick.backend.model.rq.RegisterRq;
import com.rick.backend.model.rs.LoginRs;
import com.rick.backend.service.SimpleTokenService;
import com.rick.backend.untils.PwdUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final SimpleTokenService tokenService;

    /**
     * 註冊 (register)
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginRs register(@Valid @RequestBody RegisterRq request) {

        // 檢查 userAct / userId 是否重複
        if (userRepository.existsByUserAct(request.getUserAct())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號已存在");
        }
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "身分證字號已存在");
        }

        User user = new User();
        user.setUserId(request.getUserId());
        user.setUserName(request.getUserName());
        user.setUserAct(request.getUserAct());
        // 這裡傳明碼，Entity 內部會呼叫 PwdUtil.hash()
        user.setUserPwd(request.getUserPwd());
        user.setUserPhone(request.getUserPhone());
        user.setUserMail(request.getUserMail());
        user.setUserAddress(request.getUserAddress());

        User saved = userRepository.save(user);

        // 🔹 註冊成功就發一顆 token（等於自動登入）
        String token = tokenService.generateToken(saved.getUserAct());

        return new LoginRs(
                token,
                saved.getUserId(),
                saved.getUserName(),
                saved.getUserAct(),
                saved.getUserPhone(),
                saved.getUserMail(),
                saved.getUserAddress()
        );
    }

    /**
     * 登入 (login)
     */
    @PostMapping("/login")
    public LoginRs login(@Valid @RequestBody LoginRq request) {

        User user = userRepository.findByUserAct(request.getUserAct())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "帳號或密碼錯誤"));

        // 驗證密碼（明碼 vs DB 雜湊）
        if (!PwdUtil.match(request.getUserPwd(), user.getUserPwd())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "帳號或密碼錯誤");
        }

        // 🔹 登入成功發 token
        String token = tokenService.generateToken(user.getUserAct());

        // 回傳使用者資訊 + token
        return new LoginRs(
                token,
                user.getUserId(),
                user.getUserName(),
                user.getUserAct(),
                user.getUserPhone(),
                user.getUserMail(),
                user.getUserAddress()
        );
    }
}
