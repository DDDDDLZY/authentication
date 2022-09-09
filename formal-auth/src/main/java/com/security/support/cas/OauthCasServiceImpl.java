//package com.security.support.cas;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service("OauthCasService")
//public class OauthCasServiceImpl implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
//
//	@Autowired
//	private com.datanew.service.UserService UserService;
//
//	@Override
//	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
//		String name = token.getName();
//		System.out.println("获得的用户名："+name);
//		BaseOperator userinfo = UserService.getUserByUsername(name);
//		if (userinfo==null){
//			throw new UsernameNotFoundException(name+"不存在");
//		}
//		User user =  new User(name,userinfo.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
//		return new OauthUser(userinfo,user);
//	}
//}
//
//public interface OauthCasService extends UserDetailsService {
//
//}
