package com.jeeyulee.mongddang.common.interceptor;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.member.service.JwtService;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        log.info("JwtInterceptor preHandle token ===> {}", token);
        log.info("URL ===> {}", request.getRequestURL().toString());

        if (isOptions(request) || (StringUtils.hasText(token) && jwtService.validate(token))) {
            return true;
        }

        throw new ResultException("[WARNING] TOKEN IS NOT VALIDATE");
    }

    private boolean isOptions(HttpServletRequest request) {
        return "OPTIONS".equals(request.getMethod());
    }
}
