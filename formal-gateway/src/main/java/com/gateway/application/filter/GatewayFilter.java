package com.gateway.application.filter;

import com.gateway.application.route.RouteContants;
import com.gateway.application.utils.ResponseProvider;
import com.gateway.application.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 拦截器
 *
 * @Author: zhanyang
 * @Date: 2022/1/17 11:45
 */
@Component
@Slf4j
public class GatewayFilter implements GlobalFilter {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 全局拦截器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse resp = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        PathContainer pathContainer = request.getPath().pathWithinApplication();
        //if white route
        if (isSkip(pathContainer.toString())) {
            ServerHttpRequest.Builder mutate = request.mutate();
            mutate.header("ggp-token", UUID.randomUUID().toString());
            return chain.filter(exchange.mutate().request(mutate.build()).build());
        }
        String token = request.getHeaders().getFirst(RouteContants.AUTH_KEY);
        if (ObjectUtils.isEmpty(token)) {
            return unAuth(resp, "缺失令牌,鉴权失败");
        }
        String realToken = token.replace("bearer ", "");
        Claims claims = TokenUtils.parseJWT(realToken);
        if(ObjectUtils.isEmpty(realToken) || ObjectUtils.isEmpty(claims)){
            return unAuth(resp,"请求未授权");
        }
        if(ObjectUtils.isEmpty(claims.getExpiration()) || TokenUtils.isTokenExpired(claims.getExpiration())){
            return unAuth(resp,"令牌已失效");
        }
        // 添加gatewayKey，防止下游接口直接被访问
        ServerHttpRequest.Builder mutate = request.mutate();
        mutate.header("ggp-token", UUID.randomUUID().toString());
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private boolean isSkip(String path) {
        return RouteContants.getDefaultWhiteRoute().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
    }

    private Mono<Void> unAuth(ServerHttpResponse resp, String msg) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String result = "";
        result = ResponseProvider.unAuth(msg).toString();
        DataBuffer buffer = resp.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }
}
