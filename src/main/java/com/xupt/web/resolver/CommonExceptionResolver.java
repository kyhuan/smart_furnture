package com.xupt.web.resolver;

import com.google.gson.Gson;
import com.xupt.web.exception.WebException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CommonExceptionResolver extends SimpleMappingExceptionResolver {
	private static final String ERROR = "error";

	private static final Gson gson = new Gson();

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (ex instanceof WebException) {

			jsonMap.put(ERROR, ex.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			jsonMap.put(ERROR, "服务器错误！");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(jsonMap));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
