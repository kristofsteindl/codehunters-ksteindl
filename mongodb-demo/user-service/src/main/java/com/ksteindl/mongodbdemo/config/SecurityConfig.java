package com.ksteindl.mongodbdemo.config;

import com.ksteindl.mongodbdemo.service.JwtHttpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, JwtHttpFilter jwtHttpFilter) throws Exception {
        httpSecurity
                .cors().configurationSource(createCorsConfigSource())
                .and()
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/v1/login/**").permitAll()
                        .requestMatchers("/v1/dummy/**").permitAll()
                        .requestMatchers("/v1/registration/**").permitAll()
                        .requestMatchers("/v1/eureka/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtHttpFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }

    private CorsConfigurationSource createCorsConfigSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
