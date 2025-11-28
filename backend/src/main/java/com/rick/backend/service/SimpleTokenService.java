package com.rick.backend.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SimpleTokenService {

    // token → userAct
    private final Map<String, String> tokenStore = new ConcurrentHashMap<>();

    /**
     * 產生登入 token
     */
    public String generateToken(String userAct) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, userAct);
        return token;
    }

    /**
     * 驗證 token 並回傳 userAct
     */
    public String verify(String token) {
        return tokenStore.get(token);
    }

    /**
     * 登出（可選）
     */
    public void invalidate(String token) {
        tokenStore.remove(token);
    }
}
