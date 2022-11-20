package com.jeeyulee.mongddang.common.interceptor;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.member.service.JwtService;
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
public class AdminInterceptor implements HandlerInterceptor {
    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        log.info("AdminInterceptor preHandle token ===> {}", token);
        log.info("URL ===> {}", request.getRequestURL().toString());

        if (isOptions(request)) {
            return true;
        }

        if (!jwtService.isAdmin(token)) {
            throw new ResultException("불가능한 접근입니다.");
        }

        return true;
    }

    private boolean isOptions(HttpServletRequest request) {
        return "OPTIONS".equals(request.getMethod());
    }
}
