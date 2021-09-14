package com.lw.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器作用：
 *      日志记录，权限检查，性能监控，通用行为
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 判断session里面是否有用户，没有的话重定向到登录页面，给拦截掉
        if(request.getSession().getAttribute("user") == null){
            request.setAttribute("message", "没有权限, 请登录。");
            request.getRequestDispatcher("/frontend/login").forward(request, response);
            return false;
        }

        return true;  // 放行
    }
}