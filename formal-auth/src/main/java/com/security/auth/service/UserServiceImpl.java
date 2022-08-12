package com.security.auth.service;

import cn.hutool.core.util.ArrayUtil;
import com.security.auth.client.CenterClient;
import com.security.auth.entity.SecurityUser;
import com.utils.entity.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理业务类
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    /**
     * 用户客户端对象
     */
    @Autowired
    private CenterClient client;

    /**
     * 根据用户名加载用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = client.loadUserByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("用户名或密码错误!");
        }
        //todo
        String[] strings = ArrayUtil.toArray(userDTO.getRoles(),String.class);
        SecurityUser securityUser = SecurityUser.builder().userName(userDTO.getUserName()).password(userDTO.getPassword())
                .authorities(AuthorityUtils.createAuthorityList(strings)).build();
//        SecurityUser securityUser = new SecurityUser(userDto);
        if (!securityUser.isEnabled()) {
            throw new DisabledException("该账户已被禁用，请联系管理员");
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException("该账户xxx，请联系管理员");
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException("该账户xxx，请联系管理员");
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("该账户xxx，请联系管理员");
        }
        new org.springframework.security.core.userdetails
                .User(username, new BCryptPasswordEncoder().encode(securityUser.getPassword()), securityUser.getAuthorities());
        return securityUser;
    }

    public UserDetails loadUserByMobile(String mobile) {
        UserDTO userDTO = new UserDTO() {{
            setUserName("admin");
            setPassword("$2a$10$vWyL7fMGQRvVNn.i2bK40e3z30Nem4k.ElwuxdLBNzKFxRCcXCoqm");
            List<String> roles = new ArrayList<>();
            roles.add("102");
            roles.add("103");
            setRoles(roles);
        }};
        String[] strings = ArrayUtil.toArray(userDTO.getRoles(),String.class);
        return SecurityUser.builder().userName(userDTO.getUserName()).password(userDTO.getPassword())
                .authorities(AuthorityUtils.createAuthorityList(strings)).build();
    }

}
