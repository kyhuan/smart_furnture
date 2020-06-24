package com.xupt.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


import com.xupt.common.ReturnMsg;
import com.xupt.mapper.UserMapper;
import com.xupt.pojo.HttpResult;
import com.xupt.pojo.User;
import com.xupt.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	 private UserMapper userMapper;
	/**
	 * 登录
	 */
	@Override
	public HttpResult login(String username, String password) {
		User user =userMapper.queryByName(username);
		HttpResult result=new HttpResult();
		if(user == null) {
			result.setCode("201");
			result.setMsg(ReturnMsg.Status_201);
		}else if(user.getPassword().equals(password)){
			Gson gson = new GsonBuilder().create();
			result.setCode("200");
			result.setMsg(ReturnMsg.Status_200);
			JsonObject jsonObject=new JsonObject();
			jsonObject.addProperty("token",user.getToken());
			jsonObject.addProperty("userId",user.getId());
			result.setData(jsonObject);
		}else {
			result.setCode("202");
			result.setMsg(ReturnMsg.Status_202);
		}
		return result;
	}
	/***
	 * 注册
	 */
	@Override
	public HttpResult register(String username, String password) {
		HttpResult result = new HttpResult();
		User user = userMapper.queryByName(username);
		if(user!=null) {
			result.setCode("205");
			result.setMsg(ReturnMsg.Status_205);
		}else {
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			String token = MD5Util.md5(username+System.currentTimeMillis()+"");
			user.setToken(token);
			int a = userMapper.user_add(user);
			if(a != 1){
				result.setCode("206");
				result.setMsg(ReturnMsg.Status_206);
			}else{
				result.setCode("200");
				result.setMsg(ReturnMsg.Status_200);
				JsonObject obj =new JsonObject();
		//		User queryUser = userMapper.queryByName(name);
				obj.addProperty("username",username);
				obj.addProperty("password",password);
				obj.addProperty("token", token);
//				obj.addProperty("id", queryUser.getId());
				result.setData(obj);
			}
		}
		return result;
	}
	/**
	 * token检查
	 */
	@Override
	public HttpResult checkToken(int id,String token) {
		HttpResult result = new HttpResult();
		User user = userMapper.queryById(id);
		if(user == null) {
			result.setCode("201");
			result.setMsg(ReturnMsg.Status_201);
		}else if(!user.getToken().equals(token)) {
			result.setCode("204");
			result.setMsg(ReturnMsg.Status_204);
		}else {
			result.setCode("200");
			result.setMsg(ReturnMsg.Status_200);
		}
		return result;
	}
	/**
	 * 忘记密码
	 */
	@Override
	public HttpResult forgetPassword(String username) {
		HttpResult result = new HttpResult();
		User user = userMapper.queryByName(username);
		if(user == null) {
			result.setCode("201");
			result.setMsg(ReturnMsg.Status_201);
		}else {
			result.setCode("200");
			result.setMsg(ReturnMsg.Status_200);
			JsonObject obj =new JsonObject();
			obj.addProperty("token", user.getToken());
			obj.addProperty("userId", user.getId());
			result.setData(obj);
		}
		return result;
	}
	/***
	 * 修改密码
	 */
	@Override
	public HttpResult changePassword(String username, String password) {
		HttpResult result = new HttpResult();
		User user =new User();
		user.setUsername(username);
		user.setPassword(password);
		int a = userMapper.updatePassword(user);
		if(a != 1) {
			result.setCode("201");
			result.setMsg(ReturnMsg.Status_201);
		}else {
			result.setCode("200");
			result.setMsg(ReturnMsg.Status_200);
		}
		return result;
	}
	
}
