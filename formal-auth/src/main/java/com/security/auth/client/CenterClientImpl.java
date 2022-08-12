package com.security.auth.client;

import com.utils.entity.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanyang
 * @Date: 2022/8/11 13:54
 */
@Service
public class CenterClientImpl implements CenterClient {

    @Override
    public UserDTO loadUserByUsername(String username) {
        UserDTO userDTO = new UserDTO(){{
            setUserName("admin");
            setPassword("$2a$10$vWyL7fMGQRvVNn.i2bK40e3z30Nem4k.ElwuxdLBNzKFxRCcXCoqm");
            List<String> roles = new ArrayList<>();
            roles.add("102");roles.add("103");
            setRoles(roles);
        }};
        return userDTO;
    }
}
