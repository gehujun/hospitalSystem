package com.web.dao;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Employee;
import com.web.entity.TUser;

public interface UserDao {

	/**
	 * 根据用户名和密码登录查询
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public Employee login(@Param("userName") String userName,@Param("userPwd") String userPwd);
}
