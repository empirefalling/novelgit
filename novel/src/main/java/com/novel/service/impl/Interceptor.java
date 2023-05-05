package com.novel.service.impl;


import com.novel.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        String url = request.getRequestURI();
        if(url.indexOf("/login")>=0 || url.indexOf("/register")>=0 || url.lastIndexOf("novel/")==url.length()-6 || url.indexOf("/captcha")>=0){
            return true;
        }
//        if(url.indexOf("/")>=0){
//            return true;
//        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        User user1 = (User) session.getAttribute("ROOT_SESSION");
        if (user != null || user1 != null) {
            return true;
        }

        request.setAttribute("msg","您还没有登录，请先登录！");
        request.getRequestDispatcher("/login").forward(request,response);
//        request.getRequestDispatcher("/captcha");
        return false;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler ,Exception ex) throws Exception{

    }

}
