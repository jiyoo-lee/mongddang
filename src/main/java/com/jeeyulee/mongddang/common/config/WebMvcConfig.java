package com.jeeyulee.mongddang.common.config;

import com.jeeyulee.mongddang.common.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                // Swagger 접속 요청
                .excludePathPatterns("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/error","/webjars/**")
                .excludePathPatterns("/api/v1/member/login","/api/v1/member/join");
    }
}
