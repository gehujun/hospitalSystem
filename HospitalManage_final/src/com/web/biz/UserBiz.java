package com.web.biz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Employee;
import com.web.entity.TUser;

public interface UserBiz {

	/**
	 * 根据用户名和密码登录查询
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public Employee login(@Param("userName") String userName,@Param("userPwd") String userPwd);

	public List<Employee> queryByCondition(Map<String, Object> map);
	
	public boolean delete(Integer profid);
	
	public boolean add(Employee employee);
	
}
