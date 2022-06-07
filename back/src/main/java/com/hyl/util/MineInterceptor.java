package com.hyl.util;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MineInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        //判断用户ID是否存在，不存在就跳转到登录界面
        String token = req.getHeader("Authorization");
        if ("".equals(token) || "null".equals(token)) {
            res.setStatus(403);
            return false;
        }
        return true;
    }
}
