package com.jeeyulee.mongddang.common.interceptor;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.member.service.JwtService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        log.info("JwtInterceptor preHandle token ===> {}", token);
        log.info("URL ===> {}", request.getRequestURL().toString());

        if (isOptions(request)) {
            return true;
        }

        if (!StringUtils.hasText(token) || !jwtService.validate(token)) {
            throw new ResultException("세션이 만료되었습니다. 다시 로그인해주세요.");
        }

        return true;
    }

    private boolean isOptions(HttpServletRequest request) {
        return "OPTIONS".equals(request.getMethod());
    }
}
