//package com.xupt.config;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 登陆拦截
// * @author 寇祎欢
// * date 2020.3.30
// */
//public class LoginHandlerInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Object loginUser = request.getSession().getAttribute("loginUser");
//        if (loginUser == null) {
//            request.setAttribute("msg", "没有登录权限");
//            request.getRequestDispatcher("/static/index.html").forward(request, response);
//
//            return false;
//        }else {
//            return true;
//        }
//    }
//}
