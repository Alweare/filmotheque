package fr.eni.tp.filmotheque.configuration.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@EnableWebSecurity
@Configuration
public class EniSecurityConfig {
	
	@Bean
	UserDetailsManager users(DataSource dataSource) {
		
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.setUsersByUsernameQuery("SELECT email,password,'true' as enabled FROM MEMBRE WHERE email = ?");
		users.setAuthoritiesByUsernameQuery("SELECT email,ROLE FROM MEMBRE INNER JOIN ROLES ON admin = IS_ADMIN WHERE email = ?");
		
		//SELECT ROLE,IS_ADMIN FROM ROLES INNER JOIN MEMBRE ON IS_ADMIN = admin WHERE email = ?
		return users;
	}
	
	@Bean
	public SecurityFilterChain web(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests((authorize) -> authorize
		    .requestMatchers("/").permitAll()
		    .requestMatchers("/films").permitAll()
		    .requestMatchers("/details").permitAll()
		    .requestMatchers("/creation-film").hasAnyRole("ADMIN")
		    .requestMatchers("/css/*").permitAll()
		    .requestMatchers("/images/*").permitAll()
		    .requestMatchers("/login").permitAll()
		    .anyRequest().authenticated()
	        );
	    
	    http.formLogin(form ->{ form.loginPage("/login");
		form.permitAll();
		form.defaultSuccessUrl("/session");});
		
		http.logout(form -> {
			form.invalidateHttpSession(true);
			form.clearAuthentication(true);
			form.deleteCookies("JSESSIONID");
			form.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
			form.logoutSuccessUrl("/");
			
		});
	    
	    
	   
	    
	    return http.build();
	}
	
	
	

}
