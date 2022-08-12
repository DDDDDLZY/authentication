package com.gateway.application.route;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单路径
 *
 * @Author: zhanyang
 * @Date: 2022/2/25 15:20
 */
public class RouteContants {

    public static final String AUTH_KEY = "Authorization";

    private static final List<String> DEFAULT_SKIP_URL = new ArrayList<>();

    static {
        //TODO 暂时放通全部访问
        DEFAULT_SKIP_URL.add("/**");
    }

    public static List<String> getDefaultWhiteRoute() {
        return DEFAULT_SKIP_URL;
    }
}
