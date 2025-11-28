package com.rick.backend.model.rs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRs {

    private String token;

    private String userId;     // 身分證字號
    private String userName;
    private String userAct;
    private String userPhone;
    private String userMail;
    private String userAddress;
}
