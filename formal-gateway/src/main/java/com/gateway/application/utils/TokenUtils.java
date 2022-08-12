package com.gateway.application.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Author: zy
 * @Date: 2022/2/25 16:39
 */
@Slf4j
public class TokenUtils {

    /**
     * token密钥暂时写死在这
     * @return
     */
    private static String getSecret() {
        return "secret";
    }

    /**
     * 验证access_token
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        try {
            return Jwts.parser().setSigningKey(getSecret().getBytes()).parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 验证token是否过期失效
     *
     * @param expirationTime
     * @return
     */
    public static boolean isTokenExpired(Date expirationTime) {
        return expirationTime.before(new Date());
    }
}
