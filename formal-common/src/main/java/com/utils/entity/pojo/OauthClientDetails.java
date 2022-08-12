package com.utils.entity.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "oauth_client_details")
public class OauthClientDetails {
    //客户端Id
    @TableId(value = "client_id")
    private String clientId;
    //资源Id
    private String resourceIds;
    //客户端密钥
    private String clientSecret;
    //操作范围
    private String scope;
    //授权类型
    private String authorizedGrantTypes;
    //重定向地址
    private String webServerRedirectUri;
    //权限
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    //自动批准
    private String autoapprove;

}
