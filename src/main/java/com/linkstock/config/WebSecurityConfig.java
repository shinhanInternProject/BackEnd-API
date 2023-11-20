package com.linkstock.config;

import com.linkstock.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // http 시큐리티 빌더
        httpSecurity.cors() // WebMvcConfig에서 이미 설정했으므로 기본 cors 설정
            .and()
            .csrf() // csrf는 현재 사용하지 않으므로 disable
                .disable()
            .httpBasic() // token을 사용하므로 basic 인증 disable
                .disable()
            .sessionManagement() // session 기반이 아님을 선언
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests() // /와 /auth/** 경로는 인증 안 해도 된다.
                .antMatchers("/", "/auth/**").permitAll()
            .anyRequest() // /와 /auth/** 이외의 모든 경로는 인증해야 된다.
                .authenticated()
            .and() // filter 등록
                .addFilterAfter(jwtAuthenticationFilter, CorsFilter.class); // 매 요청마다 CorsFilter 실행한 후에 jwtAuthenticationFilter 실행한다.

        return httpSecurity.build();
    }
}
