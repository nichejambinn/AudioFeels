package ca.moodyjay.audio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private LoginAccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// define our url constraints for specific roles
				// we constrain urls not htmls
				.antMatchers("/tracks").hasRole("ADMIN") // allows all HttpMethods
				.antMatchers("/model").hasRole("ADMIN").antMatchers("/data").hasRole("ADMIN")

				.antMatchers(HttpMethod.POST, "/deleteTrack/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/saveTrackLabel/{id:\\w+}").hasRole("ADMIN")

				// everyone has access without login, constraints take priority
				.antMatchers("/", "/css/**", "/images/**", "/js/**", "/**").permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll().and()
				// mostly default logout behaviour explicated
				.logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // default goes to /login?logout
				.logoutSuccessUrl("/login/?logout").permitAll().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
