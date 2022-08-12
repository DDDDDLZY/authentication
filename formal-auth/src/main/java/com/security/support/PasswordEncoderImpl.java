package com.security.support;

import cn.hutool.crypto.digest.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @deprecated
 */
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        if (rawPassword.equals(encodedPassword)) return true;
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}
