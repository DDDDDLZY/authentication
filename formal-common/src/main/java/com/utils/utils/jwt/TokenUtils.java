package com.utils.utils.jwt;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import com.utils.entity.Result;
import com.utils.utils.redis.service.RedisService;
import com.utils.utils.MyApplicationContextUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

/**
 * @Author: lzy
 * @Date: 2022/2/18 11:50
 */
@Slf4j
public class TokenUtils {

    private final static String TOKEN_REDIS = "token_";

    private static final String TOKEN_LOGIN_DATE = "login_date";

    private static final String TOKEN_SECRET = "secret";

    public static Result parseToken(String token) {
        JSONObject jsonObjToken = null;
        Object objRedisToken = null;
        Result result = Result.error("验证失败");
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.decode(TOKEN_SECRET))
                    .parseClaimsJws(token).getBody();
            //如果token不为空
            if (null != claims) {
                log.info("解析后的token:{}", claims.toString());
                Iterator iterator = claims.keySet().iterator();
                jsonObjToken = new JSONObject();
                while (iterator.hasNext()) {
                    String strKey = iterator.next().toString();
                    jsonObjToken.put(strKey, claims.get(strKey));
                }
                //获取redis操作类
                RedisService redisService =
                        (RedisService) MyApplicationContextUtil.getContext().getBean("RedisService");
                objRedisToken = redisService.getCacheObject(TOKEN_REDIS + Long.valueOf(jsonObjToken.get("userId").toString()) + "_" + (jsonObjToken.get("platform_id") == null ? "" : jsonObjToken.get("platform_id").toString()));
                if (objRedisToken == null) {
                    log.warn("redisToken不存在过期或已登出：jsonToken:{},redisToken:{}"
                            , jsonObjToken.get(TOKEN_LOGIN_DATE).toString(), "0");
                    result = Result.error("Token不存在过期或已登出");
                } else {
                    if (!jsonObjToken.get(TOKEN_LOGIN_DATE).toString().equals(objRedisToken.toString())) {
                        result = Result.error("Token不存在过期或已登出");
                    } else {
                        result = Result.success(jsonObjToken.toJSONString());
                    }
                }
            } else {
                log.debug("token验证失败:jsonWebToken:{}", token);
            }
        } catch (io.jsonwebtoken.SignatureException | ExpiredJwtException e) {
            log.warn("解析token失败（过期）：token:{},e:{}", token, e.getClass());
            result = Result.error("未登录或过期");
        } catch (Exception ex) {
            log.warn("解析token发生异常：token:{},e:{}", token, ex.getClass(), ex);
            result = Result.error();
        }
        return result;
    }
}
