//package com.security.support.cas;
//
//import org.jasig.cas.client.proxy.ProxyGrantingTicketStorageImpl;
//import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.cas.ServiceProperties;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//
///**
// * @Author: zhanyang
// * @Date: 2022/8/16 15:07
// */
//public class CasAuthenticationProvider implements AuthenticationProvider {
//
//    public static String casServerUrlPrefix="http://127.0.0.1:8081/cas";
//
//    @Bean("pgtStorage")
//    public ProxyGrantingTicketStorageImpl proxyGrantingTicketStorageImpl(){
//        return new ProxyGrantingTicketStorageImpl();
//    }
//
//    @Bean("casAuthenticationProvider")
//    public CasAuthenticationProvider casAuthenticationProvider() {
//        CasAuthenticationProvider authenticationProvider=new CasAuthenticationProvider();
//        authenticationProvider.setKey("casProvider");
//        authenticationProvider.setAuthenticationUserDetailsService(OauthCasService);
//        Cas20ProxyTicketValidator ticketValidator=new Cas20ProxyTicketValidator(casServerUrlPrefix);
//        ticketValidator.setAcceptAnyProxy(true);//允许所有代理回调链接
//        ticketValidator.setProxyGrantingTicketStorage(proxyGrantingTicketStorageImpl());
//        authenticationProvider.setTicketValidator(ticketValidator);
//        authenticationProvider.setServiceProperties(serviceProperties());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public ServiceProperties serviceProperties(){
//        ServiceProperties serviceProperties=new ServiceProperties();
//        serviceProperties.setService("http://127.0.0.1:8881/base/oauth/toke");
//        serviceProperties.setAuthenticateAllArtifacts(true);
//        return serviceProperties;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//}
