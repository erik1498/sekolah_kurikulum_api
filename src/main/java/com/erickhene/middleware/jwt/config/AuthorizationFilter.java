package com.erickhene.middleware.jwt.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.erickhene.middleware.jwt.dto.response.GlobalResponse;
import com.erickhene.middleware.jwt.service.JwtUserDetailsService;
import com.erickhene.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthorizationFilter extends BasicAuthenticationFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private final JwtUserDetailsService jwtUserDetailsService;
    private final String secret;
    public AuthorizationFilter(AuthenticationManager authenticationManager, JwtUserDetailsService jwtUserDetailsService, @Value("${jwt.secret}") String secret) {
        super(authenticationManager);
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
            if (authenticationToken == null){
                filterChain.doFilter(request, response);
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            GlobalResponse errorResponse = new GlobalResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(ObjectMapperUtil.generateObjectMapper().writeValueAsString(errorResponse));
            filterChain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.startsWith(TOKEN_PREFIX)){
            return null;
        }
        String email = JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        if (email == null) return null;
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.isCredentialsNonExpired(),
                userDetails.getAuthorities());
    }
}
