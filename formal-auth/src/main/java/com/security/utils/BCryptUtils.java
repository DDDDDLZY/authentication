package com.security.utils;

import cn.hutool.crypto.digest.BCrypt;

/**
 * @Author: zhanyang
 * @Date: 2022/1/12 13:58
 */
public class BCryptUtils {

    public String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(rawPassword.toString());
    }


    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}
