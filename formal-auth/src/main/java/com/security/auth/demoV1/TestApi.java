package com.security.auth.demoV1;

import cn.hutool.crypto.digest.BCrypt;
import com.security.utils.MD5dUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhanyang
 * @Date: 2022/1/12 9:22
 */
@RestController
public class TestApi {


    public boolean enCoded(String code){
        String ss =  BCrypt.hashpw(code);
        System.out.println("测试输出byte[]+++++++++++++++"+ss);
        System.out.println("测试输出MD5编码"+MD5dUtils.encode(code));
        return BCrypt.checkpw(code,ss);
    }
}
