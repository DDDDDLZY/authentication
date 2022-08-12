package com.lzy.security.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录用户信息
 * Created by macro on 2020/6/19.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {

    /**
     * ID
     */
    private String id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Boolean enabled;

    /**
     * 权限数据
     */
    private Collection<GrantedAuthority> authorities;


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

//    public SecurityUser(UserDTO userDTO) {
////        this.setId(userDto.getUserId());
////        this.setUserName(userDto.getUserName());
////        this.setPassword(userDto.getPassword());
////        this.setEnabled(true);
////        if (userDto.getRoles() != null) {
////            authorities = new ArrayList<>();
////            userDto.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
////        }
//        String[] strings = ArrayUtil.toArray(userDTO.getRoles(), String.class);
//        SecurityUser.builder().userName(userDTO.getUserName()).password(userDTO.getPassword())
//                .authorities(AuthorityUtils.createAuthorityList(strings)).build();
//    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
