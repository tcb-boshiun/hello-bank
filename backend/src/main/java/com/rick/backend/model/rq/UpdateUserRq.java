package com.rick.backend.model.rq;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateUserRq {

    @NotBlank
    private String userName;

    @NotBlank
    @Pattern(regexp = "09\\d{8}", message = "手機號碼格式需為 09 開頭共 10 碼")
    private String userPhone;

    @NotBlank
    @Email
    private String userMail;

    private String userAddress;

    // 可選：如果有值就代表要改密碼，沒給就不改
    private String newUserPwd;
}
