package com.security.basic.vo;

import com.utils.entity.dto.PageBaseDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 集成基类
 * @Author: zhanyang
 * @Date: 2021/12/30 17:37
 */
@Data
@Accessors(chain = true)
public class OauthClientVO extends PageBaseDTO {

    /**
     * client表主键
     */
    private Integer id;
    /**
     * 服务名
     */
    private String clientName;
    /**
     * 服务地址
     */
    private String address;
    /**
     * 状态 异常：-1 正常：0
     */
    private String state;
    /**
     * 客户端Id
     */
    private String clientId;
    /**
     * 资源Id
     */
    private String resourceIds;
    /**
     * 客户端密钥
     */
    private String clientSecret;
    /**
     * 操作范围
     */
    private String scope;
    /**
     * 授权类型
     */
    private String authorizedGrantTypes;
    /**
     * 重定向地址
     */
    private String webServerRedirectUri;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 搜索开始时间
     */
    private String startTime;
    /**
     * 搜索结束时间
     */
    private String endTime;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * 1oauth 2jwt 3cas
     * oauth2\openId\CAS\微信扫码\移动端token验证\手机号+密码\短信+验证码
     */
    private String singleType;
    /**
     * 备注
     */
    private String remark;

}
