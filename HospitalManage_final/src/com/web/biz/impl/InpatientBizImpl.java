package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.InpatientBiz;
import com.web.dao.InpatientDao;
import com.web.entity.Inpatient;
import com.web.util.MyBatisUtil;

public class InpatientBizImpl implements InpatientBiz {
	SqlSession sqlSession = MyBatisUtil.openSession();
	InpatientDao id = sqlSession.getMapper(InpatientDao.class);
	@Override
	public List<Inpatient> query(Inpatient inpatient) {
		// TODO Auto-generated method stub
		System.out.println("Yes!");
		return id.query(inpatient);
	}

	@Override
	public List<Inpatient> queryAll() {
		// TODO Auto-generated method stub
		return id.queryAll();
	}
	public Inpatient findById(Integer inpatId) {
		return id.findById(inpatId);
	}
	public boolean updateInpatient(Inpatient inpatient) {
		
		System.out.println("bed ID："+inpatient.getBedid());
		
		try {
			id.updateInpatient(inpatient);
			sqlSession.commit();
			return true;
		} catch ( Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	public boolean updateIsDelete(Integer inpatId) {
		try {
			id.updateIsDelete(inpatId);
			sqlSession.commit();
			return true;
		} catch ( Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
//		System.out.println(inpatId);
//		return id.updateIsDelete(inpatId);
	}
	
	public List<Inpatient> inpatIdSelect() {
		return id.inpatIdSelect();
	}
	
	
	public Integer findPatId(Integer inpatId) {
		
		return id.findPatId(inpatId);
	}
}
