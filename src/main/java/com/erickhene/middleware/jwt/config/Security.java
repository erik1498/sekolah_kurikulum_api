package com.erickhene.middleware.jwt.config;

import com.erickhene.middleware.jwt.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class Security {
    private AuthenticationManager authenticationManager;
    private final AuthSuccessHandler authSuccessHandler;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final String secret;

    public Security(AuthenticationManager authenticationManager, AuthSuccessHandler authSuccessHandler, JwtUserDetailsService jwtUserDetailsService, @Value("${jwt.secret}") String secret) {
        this.authenticationManager = authenticationManager;
        this.authSuccessHandler = authSuccessHandler;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.secret = secret;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests((auth) -> {
                    try{
                        auth
                                .antMatchers("/api/**").hasRole("ADMIN")
                                .anyRequest().permitAll()
                                .and()
                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .addFilter(new AuthorizationFilter(authenticationManager, jwtUserDetailsService, secret))
                                .exceptionHandling()
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public ObjectAuthenticationFilter authenticationFilter() throws Exception {
        ObjectAuthenticationFilter filter = new ObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
}
