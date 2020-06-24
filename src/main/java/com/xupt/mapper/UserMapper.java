package com.xupt.mapper;

import com.xupt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	/**增加用户*/
	 int user_add(User user);
	/**通过用户名查找用户*/
	 User queryByName(String username);
	/**通过userid查找用户*/
	 User queryById(int id);
	/**修改密码*/
	 int updatePassword(User user);

}
