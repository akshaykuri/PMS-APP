package com.nipppon.p2p.web.app.config;

import com.nipppon.p2p.web.app.config.JwtAuthenticationFilter;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig{
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(request ->
				request.requestMatchers("/api/auth/login",
						"/api/auth/validate").permitAll()
				.anyRequest().authenticated())
			.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // No password encoding, plain text password handling
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // CORS configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
    	configuration.setAllowedOrigins(Arrays.asList(
    			//for local server
    			"http://localhost:3000",
    			"http://localhost:3000/NEIN-PMS",
    			//for local host
    			"http://10.206.73.59:3000",
    			"http://10.206.73.59:3000/NEIN-PMS",
    			//for local tomcat server
    			"http://localhost:8090",
    			"http://localhost:8090/NEIN-PMS",
    			//for local test server
    			"https://localhost:8180",
    			"https://localhost:8180/NEIN-PMS",
    			"http://localhost:8180",
    			"http://localhost:8180/NEIN-PMS",
    			//for hosted test server
    			"https://neinsoft-test.nittsu.co.in:8180/NEIN-PMS",
    			"https://neinsoft-test.nittsu.co.in:8180",
    			"http://neinsoft-test.nittsu.co.in:8180/NEIN-PMS",
    			"http://neinsoft-test.nittsu.co.in:8180",
    			//for hosted test server
    			"http://10.206.50.58:8180/NEIN-PMS",
    			"http://10.206.50.58:8180"
			)
		);
    	configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    	configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "*"));
    	configuration.setAllowCredentials(true); //if need of allowing credentials
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	return source;
    }
}