package com.electronics_store.auth.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.electronics_store.auth.userDetails.CustomUserDetails;
import com.electronics_store.auth.userDetails.CustomUserDetailsService;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.CustomUsernameNotFoundException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.service.TokenService;
import com.electronics_store.service.jwt.JwtService;

import io.jsonwebtoken.security.SignatureException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;

        if (isBypass(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        try {
            userName = jwtService.extractUsername(jwt);
        } catch (SignatureException exception) {
            throw new CustomRuntimeException(ErrorSystem.ACCESS_TOKEN_IS_CORRECT);
        }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CustomUserDetails userDetails = null;
            try {
                userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userName);
            } catch (CustomUsernameNotFoundException e) {
                throw new CustomRuntimeException(ErrorSystem.ACCESS_TOKEN_IS_CORRECT);
            }
            boolean isTokenExist = tokenService.isTokenExist(jwt);
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenExist) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                throw new CustomRuntimeException(ErrorSystem.ACCESS_TOKEN_IS_CORRECT);
            }
        } else {
            throw new CustomRuntimeException(ErrorSystem.ACCESS_TOKEN_IS_CORRECT);
        }
        filterChain.doFilter(request, response);
    }

    private boolean isBypass(@NonNull HttpServletRequest request) {
        Map<String, String> uriPath = new HashMap<>();
        uriPath.put("/home", "GET");
        uriPath.put("/shop", "GET");
        uriPath.put("/product", "GET");
        uriPath.put("/account/login", "POST");
        uriPath.put("/account/register", "POST");
        uriPath.put("/account/token/refresh-token", "POST");
        String uri = request.getRequestURI();
        if (uriPath.containsKey(uri)) {
            String method = request.getMethod();
            return uriPath.get(uri).equals(method);
        }
        return false;
    }
}
