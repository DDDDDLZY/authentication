package com.security.support.cas;

import org.springframework.security.authentication.*;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

public class CasTicketTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "cas_ticket";

    private final AuthenticationManager authenticationManager;

    public CasTicketTokenGranter(AuthenticationManager authenticationManager,
                                 AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    protected CasTicketTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                                    ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    @Override
    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        OAuth2AccessToken token = super.grant(grantType, tokenRequest);
        if (token != null) {
            DefaultOAuth2AccessToken norefresh = new DefaultOAuth2AccessToken(token);
            // The spec says that cas ticket should not be allowed to get a refresh token
            norefresh.setRefreshToken(null);
            token = norefresh;
        }
        return token;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        String username = CasAuthenticationFilter.CAS_STATEFUL_IDENTIFIER;
        String password = parameters.get("ticket");

        if (password == null) {
            throw new InvalidRequestException("A cas ticket must be supplied.");
        }

        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        } catch (BadCredentialsException e) {
            // If the ticket is wrong the spec says we should send 400/invalid grant
            throw new InvalidGrantException(e.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate ticket: " + password);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }

//    private List<TokenGranter> getDefaultTokenGranters() {
//        AuthorizationServerTokenServices tokenServices = tokenServices();
//        AuthorizationCodeServices authorizationCodeServices = authorizationCodeServices();
//        OAuth2RequestFactory requestFactory = requestFactory();
//
//        List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();
//        // 添加授权码模式
//        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService, requestFactory));
//        // 添加刷新令牌的模式
//        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetailsService, requestFactory));
//        // 添加隐士授权模式
//        tokenGranters.add(new ImplicitTokenGranter(tokenServices, clientDetailsService, requestFactory));
//        // 添加客户端模式
//        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetailsService, requestFactory));
//        if (authenticationManager != null) {
//            // 添加密码模式
//            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory));
//            // 添加自定义授权模式（手机号码）
//            tokenGranters.add(new MobileCodeTokenGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory));
//            // 添加自定义授权模式（cas)
//            tokenGranters.add(new CasTicketTokenGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory));
//        }
//        return tokenGranters;
//    }

}
