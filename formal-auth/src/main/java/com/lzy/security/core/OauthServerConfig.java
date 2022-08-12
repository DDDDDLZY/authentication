package com.lzy.security.core;

import com.lzy.security.support.CustomJwtTokenEnhancer;
import com.lzy.security.support.mobile.MobileCodeTokenGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * '$2a$10$vWyL7fMGQRvVNn.i2bK40e3z30Nem4k.ElwuxdLBNzKFxRCcXCoqm'数据库密码123456
 */
@Order(1)
@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${config.jwt.secret}")
    private String JWT_SINGING_KEY;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //converter.setSigningKey("secret");
        // converter.setKeyPair(keyPair());
        converter.setSigningKey(JWT_SINGING_KEY);
        return converter;
    }

    /**
     * 从classpath下的证书中获取秘钥对
     *
     * @return
     */
//    @Bean
//    public KeyPair keyPair() {
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
//        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
//    }
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
    }

    @Primary
    @Bean
    public DefaultTokenServices tokenService() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        defaultTokenServices.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        defaultTokenServices.setSupportRefreshToken(true);//支持刷新令牌
        defaultTokenServices.setTokenStore(tokenStore());//令牌存储策略

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(customJwtTokenEnhancer());//自定义JWT模板
        enhancers.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(enhancers);

        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        return defaultTokenServices;
    }

    @Bean
    public TokenEnhancer customJwtTokenEnhancer() {
        return new CustomJwtTokenEnhancer();
    }

    /**
     * 创建grant_type列表
     *
     * @param endpoints
     * @return
     */
    public TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> list = new ArrayList<>();
        // 这里配置密码模式
        if (authenticationManager != null) {
            list.add(new ResourceOwnerPasswordTokenGranter(authenticationManager,
                    endpoints.getTokenServices(),
                    endpoints.getClientDetailsService(),
                    endpoints.getOAuth2RequestFactory()));
        }
        //刷新token模式、
        list.add(new RefreshTokenGranter
                (endpoints.getTokenServices(),
                        endpoints.getClientDetailsService(),
                        endpoints.getOAuth2RequestFactory()));
        //自定义手机号验证码模式、
        list.add(new MobileCodeTokenGranter(
                authenticationManager,
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()));
        //授权码模式、
        list.add(new AuthorizationCodeTokenGranter(
                endpoints.getTokenServices(),
                endpoints.getAuthorizationCodeServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()));
        //、简化模式
        list.add(new ImplicitTokenGranter(
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()));

        //客户端模式
        list.add(new ClientCredentialsTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));

        return new CompositeTokenGranter(list);
    }

    //添加到配置中
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 具体可以查询AuthorizationServerEndpointsConfigurer中的getDefaultTokenGranters方法，建议放在最末尾，因为依赖endpoints中的参数
                .tokenGranter(tokenGranter(endpoints))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)//若无，refresh_token会有UserDetailsService is required错误
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenServices(tokenService());
    }


//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {//配置令牌的访问端点和令牌服务
//        endpoints
//                .authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService)//若无，refresh_token会有UserDetailsService is required错误
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenServices(tokenService());
//    }
}
