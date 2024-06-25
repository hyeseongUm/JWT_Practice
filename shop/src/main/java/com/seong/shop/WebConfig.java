package com.seong.shop;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**")  // 경로에 대한 CORS 허용
               .allowedOrigins("https://s3.ap-northeast-2.amazonaws.com/hyeseong_prac") // 허용할 경로
               .allowedMethods("GET","POST","PUT","OPTIONS") // OPTIONS : Preflight 요청 위해
               .allowedHeaders("*")
               .exposedHeaders("ETag")
               .allowCredentials(true)
               .maxAge(3000);
    }
}
