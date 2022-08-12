package com.lzy.security.basic.client.controller;

import com.utils.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 单点登录跳转Api
 * 方案一：自己内部根据参数调用认证方法，封装异常信息。√
 * 方案二：第三方系统直接调用认证接口
 * @Author: lzy
 * @Date: 2022/1/14 15:59
 */
@RestController
public class SingleController {

    @PostMapping("/authorize")
    public Result authorize() {
        try {
            //1.访问第三方系统预留接口；无返回值
            return Result.success();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * code回调接口
     *
     * @return
     */
    @RequestMapping("/oauthCode")
    public Result oauthCode(/*封装一个DTO对象*/) {
        try {
            //根据参数请求 http://localhost:8081/oauth/authorize?response_type=code&client_id=zyyy
            //数据库配置一个回调地址给第三方系统接收code
            return Result.success();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 第三系统根据获取的code调用本方法
     *
     * @return
     */
    @RequestMapping("/accessToken")
    public Result accessToken(/*封装DTO*/) {
        try {
            //拼接url发送http请求
            //返回Json token对象
            return Result.success();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 查询用户信息
     * @return
     */
    @GetMapping("/fetchInfo")
    public Result fetchInfo() {
        //返回系统当前登录用户信息
        return Result.success();
    }
}
