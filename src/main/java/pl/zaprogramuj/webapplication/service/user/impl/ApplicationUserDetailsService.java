package pl.zaprogramuj.webapplication.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.zaprogramuj.webapplication.model.user.UserProfile;
import pl.zaprogramuj.webapplication.model.user.UserRole;
import pl.zaprogramuj.webapplication.service.user.UserService;

@Service("userDetailsService")
public class ApplicationUserDetailsService implements UserDetailsService{
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException 
	{
		UserProfile userProfile = userService.findUserByLogin(userLogin);
		
		if(userProfile == null)
		{
			throw new UsernameNotFoundException("Userlogin not found");
		}
		
		return new User(userProfile.getLogin(), userProfile.getPassword(), true, true, true,true, getGrantedAuthorities(userProfile));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(UserProfile userProfile)
	{
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserRole role : userProfile.getRoles())
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		}		
		return authorities;
	}

}
