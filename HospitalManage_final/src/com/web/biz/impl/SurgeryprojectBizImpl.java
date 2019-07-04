package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.SurgeryprojectBiz;
import com.web.dao.SurgeryprojectDao;
import com.web.entity.Medicaladvice;
import com.web.entity.Surgerroom;
import com.web.entity.Surgeryproject;
import com.web.util.MyBatisUtil;

public class SurgeryprojectBizImpl implements SurgeryprojectBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	
	SurgeryprojectDao surDao =sqlSession.getMapper(SurgeryprojectDao.class);
	
	@Override
	public List<Surgeryproject> queryByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return surDao.queryByCondition(map);
	}
	
	@Override
	public List<Surgerroom> querySurRoom() {
		// TODO Auto-generated method stub
		return surDao.querySurRoom();
	}
	
	@Override
	public boolean delete(Integer surid) {
		// TODO Auto-generated method stub
		try {
			surDao.delete(surid);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Medicaladvice> queryMA() {
		// TODO Auto-generated method stub
		return surDao.queryMA();
	}
	
	@Override
	public boolean add(Surgeryproject surgeryproject) {
		// TODO Auto-generated method stub
		try {
			surDao.add(surgeryproject);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Surgeryproject findByID(Integer surid) {
		// TODO Auto-generated method stub
		return surDao.findByID(surid);
	}
	
	@Override
	public boolean update(Surgeryproject surgeryproject) {
		// TODO Auto-generated method stub
		try {
			surDao.update(surgeryproject);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
