package es.codeurjc.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager usersDetailsService(){
        UserDetails user=User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("pass"))
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usersDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authenticationProvider(authenticationProvider());
        http
			.authorizeHttpRequests(authorize -> authorize
					//Private pages
                    //Only admin
					.requestMatchers("/cast/new").hasAnyRole("ADMIN")
                    .requestMatchers("/cast/{id}/delete").hasAnyRole("ADMIN")
                    .requestMatchers("/cast/{id}/modify").hasAnyRole("ADMIN")
					.requestMatchers("/movie/new").hasAnyRole("ADMIN")
                    .requestMatchers("/movie/{id}/delete").hasAnyRole("ADMIN")
                    .requestMatchers("/movie/{id}/modify").hasAnyRole("ADMIN")
                    //Admin and user (registered)
                    .requestMatchers("/myReviews").hasAnyRole("ADMIN","USER")
                    .requestMatchers("/movie/{id}/review/new").hasAnyRole("ADMIN","USER")
                    .requestMatchers("/movie/{id}/review/{idReview}/delete").hasAnyRole("ADMIN","USER")
                    //Public pages (for all users: unregistered, registered and admin)
                    .anyRequest().permitAll()
			)
			.formLogin(formLogin -> formLogin
					.loginPage("/login")
					.failureUrl("/loginerror")
					.defaultSuccessUrl("/")
					.permitAll()
			)
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.permitAll()
			);

		return http.build();
    }

}
