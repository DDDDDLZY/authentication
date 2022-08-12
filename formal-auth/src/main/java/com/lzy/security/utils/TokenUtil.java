/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package com.lzy.security.utils;

import java.util.Calendar;

/**
 * 认证工具类
 *
 * @author Chill
 */
public class TokenUtil {

    public final static String ACCESS_TOKEN = "access_token";
    public final static String REFRESH_TOKEN = "refresh_token";
    public final static String TOKEN_TYPE = "token_type";
    public final static String EXPIRES_IN = "expires_in";
    public final static String ACCOUNT = "account";
    public final static String USER_NAME = "user_name";
    public final static String NICK_NAME = "nick_name";

    /**
     * 获取token过期时间(次日凌晨3点)
     *
     * @return expire
     */
    public static int getTokenValiditySecond() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 3);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    /**
     * 获取refreshToken过期时间
     *
     * @return expire
     */
    public static int getRefreshTokenValiditySeconds() {
        return 60 * 60 * 24 * 15;
    }
}
