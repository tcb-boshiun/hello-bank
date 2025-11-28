package com.rick.backend.untils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PwdUtil {

    public static String hash(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    public static boolean match(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }
}
