package es.codeurjc.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.codeurjc.web.security.jwt.JwtRequestFilter;
import es.codeurjc.web.security.jwt.UnauthorizedHandlerJwt;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public RepositoryUserDetailsService userDetailService;

    @Autowired
    private UnauthorizedHandlerJwt unauthorizedHandlerJwt;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
	@Order(1)
	public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		
		http
			.securityMatcher("/api/**")
			.exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandlerJwt));
		
		http
			.authorizeHttpRequests(authorize -> authorize
                    // PRIVATE ENDPOINTS
                    //Only ADMIN
                    .requestMatchers(HttpMethod.POST,"/api/cast/").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/api/cast/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/api/cast/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/api/movies/").hasAnyRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/api/movies/**").hasAnyRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/api/movies/**").hasAnyRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/api/users/").hasAnyRole("ADMIN")
                    //ADMIN and USER
                    .requestMatchers(HttpMethod.PUT,"/api/users/me").hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.DELETE,"/api/users/me").hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.GET,"/api/users/me").hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.POST,"/api/myReviews/").hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.DELETE,"/api/myReviews/**").hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.GET,"/api/myReviews/").hasAnyRole("ADMIN","USER")
					// PUBLIC ENDPOINTS
					.anyRequest().permitAll()
			);
		
        // Disable Form login Authentication
        http.formLogin(formLogin -> formLogin.disable());

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf(csrf -> csrf.disable());

        // Disable Basic Authentication
        http.httpBasic(httpBasic -> httpBasic.disable());

        // Stateless session
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// Add JWT Token filter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

    @Bean
    @Order(2)
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Private pages
                        // Only admin
                        .requestMatchers("/cast/new").hasAnyRole("ADMIN")
                        .requestMatchers("/cast/{id}/delete").hasAnyRole("ADMIN")
                        .requestMatchers("/cast/{id}/modify").hasAnyRole("ADMIN")
                        .requestMatchers("/movies/new").hasAnyRole("ADMIN")
                        .requestMatchers("/movies/{id}/delete").hasAnyRole("ADMIN")
                        .requestMatchers("/movies/{id}/modify").hasAnyRole("ADMIN")
                        .requestMatchers("/user/new").hasAnyRole("ADMIN")
                        // Admin and user (registered)
                        .requestMatchers("/myReviews").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/movies/{id}/review/new").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/movies/{id}/review/{idReview}/delete").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/myProfile").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/user/delete").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/user/modify").hasAnyRole("ADMIN","USER")
                        // Public pages (for all users: unregistered, registered and admin)
                        .anyRequest().permitAll())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login") // URL to process the login
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll())
                .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/error?status=403"));

        return http.build();
    }

}
