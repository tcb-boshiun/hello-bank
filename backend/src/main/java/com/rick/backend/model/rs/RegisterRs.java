package com.rick.backend.model.rs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRs {

    private String userId;
    private String userName;
    private String userAct;
    private String userPhone;
    private String userMail;
    private String userAddress;
}
