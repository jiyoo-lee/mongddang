package com.jeeyulee.mongddang.common.config;

import com.jeeyulee.mongddang.common.interceptor.AdminInterceptor;
import com.jeeyulee.mongddang.common.interceptor.JwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final JwtInterceptor jwtInterceptor;

    private final AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                // Swagger 접속 요청
                .excludePathPatterns("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/error","/webjars/**")
                .excludePathPatterns("/api/v1/member/login","/api/v1/member/join", "/api/v1/member/overlap",
                                     "/api/v1/member/seeking-id/auth-number", "/api/v1/member/user-id",
                                     "/api/v1/member/update-password/auth-number", "/api/v1/member/password");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/**/admin/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true);
    }
}
