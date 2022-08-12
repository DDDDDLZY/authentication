package com.security.basic.vo;

import com.utils.entity.dto.PageBaseDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: zhanyang
 * @Date: 2021/12/30 17:37
 */
@Data
@Accessors(chain = true)
public class OauthClientDetailsVO extends PageBaseDTO {
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
     * 权限
     */
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    /**
     * 自动批准
     */
    private String autoapprove;
    /**
     * 密钥
     */
    private String secretKey;

    /**
     * 认证类型
     */
    private String singleType;
    /**
     * 备注
     */
    private String remark;
}
