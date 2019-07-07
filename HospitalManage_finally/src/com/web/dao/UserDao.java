package com.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Employee;
import com.web.entity.Professionaltitle;
import com.web.entity.TUser;

public interface UserDao {

	/**
	 * 根据用户名和密码登录查询
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public Employee login(@Param("userName") String userName,@Param("userPwd") String userPwd);

	public List<Employee> queryByCondition(Map<String, Object> map);
	
	public void delete(Integer profid);
	
	public void add(Employee employee);
	
	public Employee findByID(Integer empid);
	
	public void update(Employee employee);
	
}
