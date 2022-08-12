package com.lzy.security.auth.demoV1;

import com.lzy.security.auth.service.ClientServiceImpl;
import com.utils.entity.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuzhanyang
 * @Date: 2022/1/12 14:27
 */
@RestController
public class ClientController {
    @Autowired
    private ClientServiceImpl clientService;

//    @Autowired
//    private CenterClient client;

    @RequestMapping("/enCoded")
    public UserDTO registClient(String username) {
        return clientService.registClient(username);
    }
}
