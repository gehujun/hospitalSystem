package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.UserBiz;
import com.web.dao.UserDao;
import com.web.entity.Employee;
import com.web.entity.TUser;
import com.web.util.MyBatisUtil;

public class UserBizImpl implements UserBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	UserDao ud = sqlSession.getMapper(UserDao.class);

	@Override
	public Employee login(String userName, String userPwd) {
		return ud.login(userName, userPwd);
	}
	
	@Override
	public List<Employee> queryByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ud.queryByCondition(map);
	}

	
	@Override
	public boolean delete(Integer profid) {
		// TODO Auto-generated method stub
		try {
			ud.delete(profid);
			
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}
	}
	
	@Override
	public boolean add(Employee employee) {
		// TODO Auto-generated method stub
		try {
			ud.add(employee);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}
	}
}
