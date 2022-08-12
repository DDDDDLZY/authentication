package com.lzy.security.basic.client.controller;
//
///**
// * @Author: lzy
// * @Date: 2022/2/28 9:50
// */
//
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 获取RSA公钥接口
 * Created by macro on 2020/6/19.
 */
@RestController
public class KeyPairController {
//
//    @Autowired
//    private KeyPair keyPair;
//
//    /**
//     * 获取公钥
//     *
//     * @return
//     */
    @GetMapping("/rsa/keys")
    public String getKey() {
        return "测试keys";
    }
//
//    @GetMapping("/rsa/publicKey")
//    public static RSAPublicKeySpec getPublicKey(String modulu, String pe) {
//        BigInteger publicExponent = new BigInteger(1, Base64.getDecoder().decode(pe));
//        //Base64.getDecoder().decode(modulu)
//        BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(modulu));
//        RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(modulus, publicExponent);
//        return publicSpec;
//    }
}
