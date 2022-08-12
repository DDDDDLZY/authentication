package com.lzy.security.auth.demoV1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: liuzhanyang
 * @Date: 2022/1/10 11:55
 */
@RestController
@Slf4j
public class SSOController {
    @RequestMapping("/fallback")
    public String fallback(HttpServletRequest request) {
        String code = request.getParameter("code");
        log.info("================code================" + code);
        return "success";
    }

    @RequestMapping("/testToken")
    public String testToken(HttpServletRequest request) {
        String code = request.getParameter("code");
        log.info("================code================" + code);
        return "success----------------------------------++++++++++++++++++++++++++++++";
    }
}
