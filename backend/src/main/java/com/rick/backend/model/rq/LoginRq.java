package com.rick.backend.model.rq;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRq {

    @NotBlank
    private String userAct;   // 帳號

    @NotBlank
    private String userPwd;   // 明碼密碼
}
