package com.lzy.security.auth.service;


import com.lzy.security.auth.client.CenterClient;
import com.utils.entity.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lzy
 * @Date: 2022/1/12 14:29
 */
@Service
public class ClientServiceImpl {
    @Autowired
    private CenterClient client;

    public UserDTO registClient(String username) {
        return client.loadUserByUsername(username);
    }
}
