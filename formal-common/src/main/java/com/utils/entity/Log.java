package com.utils.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: zhanyang
 * @Date: 2021/11/25 11:09
 */
@Data
public class Log {
    private Integer id;
    private String username;
    private String operation;
    private String method;
    private String params;
    private String returnParams;
    private String error;
    private String ip;
    private Date createTime;
}
