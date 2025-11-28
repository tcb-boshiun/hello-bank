package com.rick.backend.controller;

import com.rick.backend.db.dao.UserRepository;
import com.rick.backend.db.entity.User;
import com.rick.backend.model.rq.UpdateUserRq;
import com.rick.backend.model.rs.UserInfoRs;
import com.rick.backend.service.SimpleTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final SimpleTokenService tokenService;

    /**
     * 從 Authorization header 抽 token → 驗證是否為本人 → 回傳 User
     */
    private User getUserFromToken(String authHeader, String pathUserAct) {

        // 沒帶或格式錯誤
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登入或缺少 Token");
        }

        String token = authHeader.substring(7);
        String tokenUserAct = tokenService.verify(token);

        // token 無效
        if (tokenUserAct == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token 無效，請重新登入");
        }

        // Token 的 userAct 必須跟 path 一致，避免亂查別人資料
        if (!tokenUserAct.equals(pathUserAct)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "無權限存取此使用者資料");
        }

        return userRepository.findByUserAct(tokenUserAct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "使用者不存在"));
    }

    /**
     * getUserInfo：依 userAct 取得使用者資訊（需登入 & 只能查自己的）
     */
    @GetMapping("/{userAct}")
    public UserInfoRs getUserInfo(@PathVariable String userAct,
                                  @RequestHeader(value = "Authorization", required = false) String authHeader) {

        User user = getUserFromToken(authHeader, userAct);

        return new UserInfoRs(
                user.getUserId(),
                user.getUserName(),
                user.getUserAct(),
                user.getUserPhone(),
                user.getUserMail(),
                user.getUserAddress()
        );
    }

    /**
     * updateUserInfo：更新使用者資訊（需登入 & 只能改自己的）
     */
    @PutMapping("/{userAct}")
    public UserInfoRs updateUserInfo(@PathVariable String userAct,
                                     @Valid @RequestBody UpdateUserRq request,
                                     @RequestHeader(value = "Authorization", required = false) String authHeader) {

        User user = getUserFromToken(authHeader, userAct);

        user.setUserName(request.getUserName());
        user.setUserPhone(request.getUserPhone());
        user.setUserMail(request.getUserMail());
        user.setUserAddress(request.getUserAddress());

        // 如果有帶 newUserPwd，才更新密碼（會走 Entity 內部的 hash）
        Optional.ofNullable(request.getNewUserPwd())
                .filter(pwd -> !pwd.isBlank())
                .ifPresent(user::setUserPwd);

        User saved = userRepository.save(user);

        return new UserInfoRs(
                saved.getUserId(),
                saved.getUserName(),
                saved.getUserAct(),
                saved.getUserPhone(),
                saved.getUserMail(),
                saved.getUserAddress()
        );
    }
}
