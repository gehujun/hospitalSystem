package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.RegistrationformBiz;
import com.web.dao.RegistrationformDao;
import com.web.entity.Registrationform;
import com.web.util.MyBatisUtil;

public class RegistrationformImpl implements RegistrationformBiz {

	SqlSession sqlSession = MyBatisUtil.openSession();
	RegistrationformDao regDao = sqlSession.getMapper(RegistrationformDao.class);
	
	
	@Override
	public List<Registrationform> queryByCondition(Registrationform registrationform) {
		// TODO Auto-generated method stub
		return regDao.queryByCondition(registrationform);
	}
	
	@Override
	public Registrationform findByID(int regid) {
		// TODO Auto-generated method stub
		return regDao.findByID(regid);
	}
	
	@Override
	public boolean deleteById(Registrationform registrationform) {
		// TODO Auto-generated method stub
		try {
			regDao.deleteById(registrationform);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean addReg(Registrationform registrationform) {
		// TODO Auto-generated method stub
		try {
			regDao.addReg(registrationform);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateReg(Registrationform registrationform) {
		// TODO Auto-generated method stub
		try {
			regDao.updateReg(registrationform);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
