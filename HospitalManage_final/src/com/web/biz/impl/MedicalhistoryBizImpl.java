package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.web.biz.MedicalhistoryBiz;
import com.web.dao.MedicalhistoryDao;
import com.web.entity.Medicalhistory;
import com.web.util.MyBatisUtil;

public class MedicalhistoryBizImpl implements MedicalhistoryBiz {

	SqlSession sqlSession = MyBatisUtil.openSession();
	MedicalhistoryDao MedhDao =  sqlSession.getMapper(MedicalhistoryDao.class);

	
	@Override
	public List<Medicalhistory> queryByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return MedhDao.queryByCondition(map);
	}
	
	@Override
	public boolean deleteById(Medicalhistory medicalhistory) {
		// TODO Auto-generated method stub
		try {
			MedhDao.deleteById(medicalhistory);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean addMedh(Medicalhistory medicalhistory) {
		// TODO Auto-generated method stub
		try {
			MedhDao.addMedh(medicalhistory);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Medicalhistory findByID(int medhid) {
		// TODO Auto-generated method stub
		return MedhDao.findByID(medhid);
	}
	
	@Override
	public boolean updateMedh(Medicalhistory medicalhistory) {
		// TODO Auto-generated method stub
		try {
			MedhDao.updateMedh(medicalhistory);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
