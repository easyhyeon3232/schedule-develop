package com.example.scheduledevelop.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//@Component
//public class LoginCheckInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        HttpSession session = request.getSession();
//        if (session.getAttribute("sessionUser") == null) {
//            throw new IllegalArgumentException("로그인이 필요합니다.");
//        }
//        return true; // 로그인 되어 있으면 통과
//    }
//}
