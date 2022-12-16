package com.example.fileupload.config;

import com.example.fileupload.auth.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않음
                .and()
                .formLogin().disable() // jwt 인증방식을 위해 id pw를 폼로그인으로 처리 안함
                .httpBasic().disable() // http 기본 인증방식을 사용 안함
                .apply(new CustomDsl())
                .and()
//                .authorizeHttpRequests(authorize -> authorize
//
//                                .requestMatchers("/**")
//                                .permitAll()
//                        // Todo: 권한 설정
//                )
                .build();
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            System.out.println("authenticationManager: " + authenticationManager);
            http
                    .addFilter(new JwtAuthenticationFilter(authenticationManager));
        }
    }
}
