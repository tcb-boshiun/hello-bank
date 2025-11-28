package com.rick.backend.model.rq;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRq {

    @NotBlank
    private String userId;     // 身分證字號

    @NotBlank
    private String userName;

    @NotBlank
    private String userAct;    // 帳號

    @NotBlank
    private String userPwd;    // 明碼密碼

    @NotBlank
    @Pattern(regexp = "09\\d{8}", message = "手機號碼格式需為 09 開頭共 10 碼")
    private String userPhone;

    @NotBlank
    @Email
    private String userMail;

    private String userAddress;
}
