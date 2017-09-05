package pl.zaprogramuj.webapplication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Users in memory.
		auth.inMemoryAuthentication().withUser("user").password("12345").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("12345").roles("USER, ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 .antMatchers("/*").permitAll()
		 .antMatchers("/profile/**").authenticated()
		 .antMatchers("/admin/**").hasRole("ADMIN")
	        .and().formLogin().loginPage("/login").defaultSuccessUrl("/").usernameParameter("userLogin").passwordParameter("password")
				.and().csrf().disable().exceptionHandling().accessDeniedPage("/accessDenied");
	}
}
