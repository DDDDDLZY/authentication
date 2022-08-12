package com.lzy.security.auth.client;

import com.utils.entity.dto.UserDTO;

/**
 * @Author: liuzhanyang
 * @Date: 2022/1/12 11:55
 */
//@FeignClient("center-service")
public interface CenterClient {

    /**
     * 根据用户名加载用户信息
     *
     * @param username
     * @return
     */
//    @GetMapping("/loadByUsername")
    UserDTO loadUserByUsername(String username);
    //UserDTO loadUserByUsername(@RequestParam String username);
}
