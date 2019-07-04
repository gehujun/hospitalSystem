package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.employeeBiz;
import com.web.dao.employeeDao;
import com.web.entity.Employee;
import com.web.util.MyBatisUtil;

public class employeeBizImpl implements employeeBiz {
	SqlSession sqlsession = MyBatisUtil.getSqlSession();
	
	employeeDao ed = sqlsession.getMapper(employeeDao.class);
	@Override
	public List<Employee> empNameSelect() {
		// TODO Auto-generated method stub
		return ed.empNameSelect();
	}

}
