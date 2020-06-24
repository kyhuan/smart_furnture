package com.xupt.web.interceptor;

import com.xupt.web.annotation.Permission;
import com.xupt.web.component.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionInterceptor implements HandlerInterceptor {
	@Autowired
	private UserManager userManager;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
//			Object bean = handlerMethod.getBean();
//			Method method = handlerMethod.getMethod();
			Permission permissionAnnotation = handlerMethod.getMethodAnnotation(Permission.class);
			if (permissionAnnotation != null) {
				int[] value = permissionAnnotation.value();
				if (value.length == 0) {
					// 已登录用户权限
					Integer userId = userManager.getUserId();
					if (userId == null) {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						return false;
					}
				} else if (value.length == 1) {
					// TODO 单角色用户权限
				} else {
					// TODO 多角色用户权限
				}
			}
		}
		return true;
	}
}
