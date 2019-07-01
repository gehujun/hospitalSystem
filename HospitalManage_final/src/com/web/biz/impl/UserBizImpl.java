package com.web.biz.impl;

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

}
