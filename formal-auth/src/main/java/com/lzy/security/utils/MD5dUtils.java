package com.lzy.security.utils;

import org.springframework.util.DigestUtils;

/**
 * md5密码工具类 逐渐完善
 *
 * @Author: lzy
 * @Date: 2022/1/12 13:21
 */
public class MD5dUtils {

    /**
     * 对密码进行md5编码
     *
     * @param code
     * @return
     */
    public static String encode(String code) {
        return DigestUtils.md5DigestAsHex(code.getBytes());
    }

    /**
     * 验证MD5密码 验证成功返回true
     *
     * @param text
     * @param md5
     * @return
     */
    public static boolean verify(String text, String md5) {
        //根据传入的密钥进行验证
        String md5Password = encode(text);
        if (md5Password.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }
}
