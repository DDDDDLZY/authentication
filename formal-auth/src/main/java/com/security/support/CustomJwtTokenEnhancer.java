package com.security.support;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * token增强
 * @Author: zhanyang
 * @Date: 2022/1/13 16:05
 */
public class CustomJwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        //获取客户端Client信息
//        Map<String, String> requestParameters = authentication.getOAuth2Request().getRequestParameters();
        Map<Object,Object> user = JSONObject.parseObject(JSONObject.toJSONString(authentication.getUserAuthentication().getPrincipal()), Map.class);
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("user_name", user.get("username"));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
