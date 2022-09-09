package com.security.auth.service;

import com.utils.entity.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanyang
 * @Date: 2022/8/15 15:52
 */
public class UserServiceImpl implements UserService {


    /**
     * todo
     * 加载微信用户信息
     * @param openId
     * @return
     */
    @Override
    public UserDTO loadUserByOpenId(String openId) {
        UserDTO userDTO = new UserDTO() {{
            setUserName("admin");
            setPassword("$2a$10$vWyL7fMGQRvVNn.i2bK40e3z30Nem4k.ElwuxdLBNzKFxRCcXCoqm");
            List<String> roles = new ArrayList<>();
            roles.add("102");
            roles.add("103");
            setRoles(roles);
        }};
        return userDTO;
    }

}
