//package com.gateway.application.filter;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * 请求拦截，避免服务绕过接口被直接访问
// */
//@Component
//@WebFilter(filterName = "BaseFilter",urlPatterns = {"/user/**"})
//public class BaseFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("进入过滤器========");
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        String gateway = request.getHeader("gatewayKey");
//        if(gateway == null || gateway.equals("") || !gateway.equals("key")){
//            return;
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//}
