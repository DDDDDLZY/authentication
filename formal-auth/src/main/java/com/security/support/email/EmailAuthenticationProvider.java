package com.security.support.email;

import com.security.auth.service.UserDetailsServiceImpl;
import com.utils.common.StringUtils;
import com.utils.common.exception.ServiceException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: zhanyang
 * @Date: 2022/8/12 9:11
 */
public class EmailAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    public EmailAuthenticationProvider(UserDetailsServiceImpl userService1, PasswordEncoder passwordEncoder) {
        this.userService = userService1;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 覆写验证逻辑
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailAuthenticationToken mobileAuthenticationToken = (EmailAuthenticationToken) authentication;
        String mobile = (String) mobileAuthenticationToken.getPrincipal();
        String code = (String) mobileAuthenticationToken.getCredentials();
        //todo 验证操作
        UserDetails userDetails = userService.loadUserByMobile(mobile);
        //todo 结果判断
        if (StringUtils.isEmpty(userDetails.getUsername())) {
            throw new ServiceException("用户信息为空");
        }
        EmailAuthenticationToken authenticationResult = new EmailAuthenticationToken(userDetails, code, userDetails.getAuthorities());
        authenticationResult.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationResult;
    }

    /**
     * spring security 验证provider是否支持处理
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return EmailAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
