package com.rick.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rick.backend.untils.PwdUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Table(name = "TB_USER") // 避免與保留字衝突
public class User {

    /**
     * 系統主鍵（Auto Increment）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 身分證字號（唯一但不是 PK）
     */
    @NotBlank
    @Column(nullable = false, length = 20, unique = true)
    private String userId;

    /**
     * 姓名
     */
    @NotBlank
    @Column(nullable = false, length = 50)
    private String userName;

    /**
     * 使用者代號（帳號）
     */
    @NotBlank
    @Column(nullable = false, length = 50, unique = true)
    private String userAct;

    /**
     * 使用者密碼（不回傳給前端）
     */
    @JsonIgnore
    @NotBlank
    @Setter(AccessLevel.NONE)
    @Column(name = "USER_PWD", nullable = false, length = 100)
    private String userPwd;

    /**
     * 手機（09 開頭，共 10 碼）
     */
    @NotBlank
    @Pattern(regexp = "09\\d{8}", message = "手機號碼格式需為 09 開頭共 10 碼")
    @Column(length = 20)
    private String userPhone;

    /**
     * 電子郵件
     */
    @NotBlank
    @Email
    @Column(length = 100)
    private String userMail;

    /**
     * 地址（可為空）
     */
    @Column(length = 200)
    private String userAddress;

    /**
     * 設定密碼時自動做雜湊（BCrypt / 其他）
     */
    public void setUserPwd(String userPwd) {
        if (userPwd == null || userPwd.isBlank()) {
            return; // 也可以丟 IllegalArgumentException，看你策略
        }
        this.userPwd = PwdUtil.hash(userPwd);
    }
}
