package com.xupt.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.xupt.pojo.HttpResult;
import com.xupt.service.UserService;
import com.xupt.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	/***
	 * 用户注册
	 * @param request
	 * @param response
	 */
	@PostMapping("/register")
	public void register(HttpServletRequest request,HttpServletResponse response) {
		Gson gson = new GsonBuilder().create();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpResult result = userService.register(username, password);
		Response.responseStr(response, gson.toJson(result));
	}

	/**
	 * 用户登录
	 * @param request
	 * @param response
	 */
	@GetMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response) {
		Gson gson = new GsonBuilder().create();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpResult result = userService.login(username, password);
		Response.responseStr(response, gson.toJson(result));
	}

	/**
	 * 忘记用户密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/forgetPassword")
	public void forgetPassword(HttpServletRequest request,HttpServletResponse response) {
		Gson gson = new GsonBuilder().create();
		String username = request.getParameter("username");
		HttpResult result = userService.forgetPassword(username);
		Response.responseStr(response, gson.toJson(result));
	}

	/**
	 * 修改用户密码
	 * @param request
	 * @param response
	 */
	@PostMapping("/changePassword")
	public void changePassword(HttpServletRequest request,HttpServletResponse response) {
		Gson gson = new GsonBuilder().create();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpResult result = userService.changePassword(username,password);
		Response.responseStr(response, gson.toJson(result));
	}
	
}
