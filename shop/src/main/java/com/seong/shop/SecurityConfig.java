package com.seong.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 어떤 페이지를 로그인 검사할지 여부
    // FilterChain : 모든 유저의 요청과 서버의 응답 사이에 자동으로 실행해주고 싶은 코드 담는 곳
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)->csrf.disable());
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/my-page").hasAnyRole("Admin")
        /* requestMatchers("/**") : 모든 페이지, requestMatchers("/url") : 해당 url 로그인 검사 permitAll() : 아무나 접속허용*/
        );
        http.formLogin(login->login
                .defaultSuccessUrl("/list")
                .loginProcessingUrl("/loginReg"));
        http.logout(logout-> logout.logoutUrl("/logout"));


        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
