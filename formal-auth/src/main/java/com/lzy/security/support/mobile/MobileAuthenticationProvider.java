package com.lzy.security.support.mobile;

import com.lzy.security.auth.service.UserServiceImpl;
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
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    public MobileAuthenticationProvider(UserServiceImpl userService1, PasswordEncoder passwordEncoder) {
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
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;
        String mobile = (String) mobileAuthenticationToken.getPrincipal();
        String code = (String) mobileAuthenticationToken.getCredentials();
        //todo 验证操作
        UserDetails userDetails = userService.loadUserByMobile(mobile);
        //todo 结果判断
        if (StringUtils.isEmpty(userDetails.getUsername())) {
            throw new ServiceException("用户信息为空");
        }
        MobileAuthenticationToken authenticationResult = new MobileAuthenticationToken(userDetails, code, userDetails.getAuthorities());
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
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
