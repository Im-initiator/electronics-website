package com.electronics_store.configuration;

import static org.springframework.http.HttpMethod.GET;

import java.util.*;

import jakarta.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.electronics_store.auth.filter.JWTAuthenticationFilter;
import com.electronics_store.auth.handler.CustomLogoutHandler;
import com.electronics_store.auth.userDetails.CustomUserDetailsService;
import com.electronics_store.exception.CustomAccessDeniedHandler;
import com.electronics_store.exception.CustomAuthenticationEntryPoint;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WebSecurityConfig {

    JWTAuthenticationFilter jwtAuthenticationFilter;
    CustomLogoutHandler customLogoutHandler;

    // spotless:off
    private static final String[] PERMITALL = {
            "/login",
            "/register",
            "/account/token/refresh-token",
            "/shop",
            "/home",
            "/home/product",
            "/shop",
            "/swagger-ui/**",
            "/v3/**",
    };
    private static final String[]  ADMIN = {
            "/admin/**"
    };

    private static final String[]  MANAGER = {
            "/manager/**",
    };

    private static final String[]  EMPLOYEE = {
            "/employee/**"
    };

    private static final String[]  USER = {
            "/user/**"
    };

    // spotless:on
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.setAllowCredentials(true); // cho phép chia sẻ thông tin xác thực
            // thiết lập danh sách các nguồn không bị  chặn cors
            corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:8081"));
            corsConfig.setAllowedMethods(Collections.singletonList("*")); // các method HTTP cho phép
            corsConfig.setAllowedHeaders(Collections.singletonList("*")); // các header cho phép
            corsConfig.setMaxAge(3600L); // thời gian cho phép trình duyệt không cần phải gửi yêu cầu duyệt cors
            return corsConfig;
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                //                .cors( c -> c.configurationSource(corsConfigurationSource()))//config cors
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth.requestMatchers(PERMITALL)
                        .permitAll()
                        .requestMatchers(USER)
                        .hasAnyAuthority("USER")
                        .requestMatchers(EMPLOYEE)
                        .hasAnyAuthority("EMPLOYEE")
                        .requestMatchers(MANAGER)
                        .hasAnyAuthority("MANAGER","ADMIN")
                        .requestMatchers(ADMIN)
                        .hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/shop/**")
                        .permitAll()
                        .requestMatchers(GET, "/home", "/home/product")
                        .permitAll()
                        .requestMatchers("/admin/post")
                        .hasAnyAuthority("ADMIN", "MANAGER")
                        .dispatcherTypeMatchers(DispatcherType.ERROR) // allow /error default is permitAll
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(authenticationEntryPoint());
                    ex.accessDeniedHandler(accessDeniedHandler());
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .logout((logout) -> logout.logoutUrl("account/logout")
                        .logoutSuccessUrl("/account/login")
                        .addLogoutHandler(customLogoutHandler)
                        .logoutSuccessHandler(
                                ((request, response, authentication) -> SecurityContextHolder.clearContext())))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
