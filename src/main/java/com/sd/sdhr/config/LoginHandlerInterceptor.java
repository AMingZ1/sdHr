package com.sd.sdhr.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * by:GQ 2022-08-17
 * 登陆拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登陆成功后应该有用户session
        Object loginUser=request.getSession().getAttribute("userId");
        if (loginUser==null){//未登陆
            request.setAttribute("msg","请先登陆！");
            request.getRequestDispatcher("index.html").forward(request,response);
            return false;
        }
        return true;
       // return HandlerInterceptor.super.preHandle(request, response, handler);
    }

  /*  @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }*/
}
