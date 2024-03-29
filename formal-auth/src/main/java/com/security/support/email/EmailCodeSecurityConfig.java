package com.security.support.email;

import com.security.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * 将验证逻辑添加至spring容器,可写在WebSecurityConfig
 * @Author: zhanyang
 * @Date: 2022/8/12 10:18
 */
@Component
public class EmailCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userService;

    /**
     * 覆写方法
     * @param builder
     */
    @Override
    public void configure(HttpSecurity builder) {
        EmailAuthenticationProvider mobileAuthenticationProvider = new EmailAuthenticationProvider(userService,passwordEncoder);
        builder.authenticationProvider(mobileAuthenticationProvider);
    }
}
