package com.utils.entity.dto;

import lombok.Data;

/**
 * @Author: zhanyang
 * @Date: 2022/2/18 14:26
 */
@Data
public class UserTokenDTO {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户账号
     */
    private String userName;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * token
     */
    private String token;
}
