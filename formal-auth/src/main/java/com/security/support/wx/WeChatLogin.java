//package com.security.support.wx;
//
//import cn.hutool.http.HttpRequest;
//import cn.hutool.http.HttpResponse;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import com.security.auth.service.UserService;
//import com.utils.common.exception.ServiceException;
//import com.utils.entity.Result;
//import com.utils.entity.dto.UserDTO;
//import com.utils.redis.service.RedisService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Author: lzy
// * @Date: 2022/2/9 14:57
// */
//@RestController
//@Slf4j
//public class WeChatLogin {
//
//    @Autowired(required = false)
//    private RedisService redisService;
//
//    @Autowired
//    private UserService userService;
//    /**
//     * 操作步骤
//     * 1.https://open.work.weixin.qq.com/wwopen/sso/qrConnect?appid=ww6b80474a9340cf15&agentid=1000002&redirect_uri=http%3A%2F%2Fzypaas.top
//     * redirect_uri需要encode
//     * 2.redirect_uri访问登录方法(@param：code)
//     * 3.https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww6b80474a9340cf15&corpsecret=K5uByQRsHggkxHaYX8i6eXNjZBQcsbzN6mFitFrWsvw
//     * 获取token
//     * 4.https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=yoVBygD-0OuE24xrqcvnuX1kGW-xbIm0n-r8Qj-pEJhjQwQmv8GIrNUR4Bn2BgQDwP-RGPSCRFcissYYj_T8bC4Co0kNbe75FCFgo24uLqXRkqIj7K0BwylCZ60HMgZu6Cc6G9sQJTg8KC-Z8PPNnmNFA-NHmkqthlX13yL6AJxLTIyPqeqgaMczvH2X7bpNaipPuCLHCiB-Hf4zFe0v3w&code=4MQkPev_OjmxeaiLmELyrZ1LBAJHsRQxl-Yia3cztp0&appid=ww6b80474a9340cf15
//     * 根据token和code获取userId
//     * 5.再根据userId和token获取其他信息
//     **/
//
//    /**
//     * 微信扫码重定向方法
//     * @param code
//     * @return
//     */
//    @RequestMapping(value = "wechatLogin", method = RequestMethod.GET)
//    public Result wechatLogin(@RequestParam("code") String code) {
//        //获取得到Access_token
//        String accessToken = getAccessToken();
//        String userId = getWechatUserId(code, accessToken);
//        if (userId != null && !"".equals(userId)) {
//            /**
//             * TODO login action
//             * 1.查询用户信息
//             * 2.返回加密后的密码到前端 or 拿到密码直接走密码登录不返回给前端->拿到token或者请求头√
//             * 3.走单独接口登录
//             */
//            UserDTO userDTO = userService.loadUserByOpenId(userId);
//            return Result.success(loginByPassWord(userDTO));
//        } else {
//            throw new ServiceException("企业维信授权失败");
//        }
//    }
//
//    /**
//     * 获取AccessToken
//     *
//     * @return
//     */
//    public String getAccessToken() {
//        String accessToken = redisService.get("WECHAT_LOGIN_ACCESS_TOKEN").toString();
//        if (accessToken == null || "".equals(accessToken)) {
//            HttpResponse res = HttpRequest.get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww6b80474a9340cf15&corpsecret=K5uByQRsHggkxHaYX8i6eXNjZBQcsbzN6mFitFrWsvw").execute();
//            JSONObject jsonObject = JSON.parseObject(res.body());
//            if ("ok".equals(jsonObject.get("errmsg"))) {
//                accessToken = (String) jsonObject.get("access_token");
//                redisService.set("WECHAT_LOGIN_ACCESS_TOKEN", accessToken, 3600L);
//            } else {
//                log.info("请求accessToken失败，失败详细信息为:" + jsonObject.get("errmsg"));
//            }
//
//        }
//        return accessToken;
//    }
//
//    public String getWechatUserId(String code, String accessToken) {
//        //https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=yoVBygD-0OuE24xrqcvnuX1kGW-xbIm0n-r8Qj-pEJhjQwQmv8GIrNUR4Bn2BgQDwP-RGPSCRFcissYYj_T8bC4Co0kNbe75FCFgo24uLqXRkqIj7K0BwylCZ60HMgZu6Cc6G9sQJTg8KC-Z8PPNnmNFA-NHmkqthlX13yL6AJxLTIyPqeqgaMczvH2X7bpNaipPuCLHCiB-Hf4zFe0v3w&code=4MQkPev_OjmxeaiLmELyrZ1LBAJHsRQxl-Yia3cztp0&appid=ww6b80474a9340cf15
//        HttpResponse res = HttpRequest.get("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=" + accessToken + "&code=" + code).execute();
//        String userid = "";
//        JSONObject jsonObject = JSON.parseObject(res.body());
//        if ("ok".equals(jsonObject.get("errmsg"))) {
//            userid = (String) jsonObject.get("UserId");
//        } else {
//            log.info("请求UserId失败，失败详细信息为:" + jsonObject.get("errmsg"));
//        }
//        log.info("获取到的userid为:" + userid);
//        return userid;
//    }
//
//    public String getWXUserInfo(String token,String userId){
//        HttpResponse res = HttpRequest.get("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID").execute();
//        return null;
//    }
//
//    /**
//     * 密码方式登录
//     * @param userDTO 用户信息
//     * @Return token||请求头+UserDTO
//     */
//    private JSONObject loginByPassWord(UserDTO userDTO) {
//        String url ="/auth-service/oauth/token?grant_type=password&password="+userDTO.getPassword()+"&username="+userDTO.getUserId()+"&client_id=zyyy&client_secret=123";
//        HttpResponse response = HttpRequest.get(url).execute();
//        JSONObject jsonObject = JSON.parseObject(response.body());
//        return jsonObject;
//    }
//}
