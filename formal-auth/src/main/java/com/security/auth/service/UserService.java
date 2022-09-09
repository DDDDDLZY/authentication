package com.security.auth.service;

import com.utils.entity.dto.UserDTO;

/**
 * @Author: zhanyang
 * @Date: 2022/8/15 15:52
 */
public interface UserService {

    UserDTO loadUserByOpenId(String openId);
}
