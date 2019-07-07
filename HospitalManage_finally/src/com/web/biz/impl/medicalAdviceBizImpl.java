package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.medicalAdviceBiz;
import com.web.dao.medicalAdviceDao;
import com.web.entity.Medicaladvice;
import com.web.util.MyBatisUtil;

public class medicalAdviceBizImpl implements medicalAdviceBiz {
	SqlSession sqlSession = MyBatisUtil.openSession();
	medicalAdviceDao md = sqlSession.getMapper(medicalAdviceDao.class);
	@Override
	public List<Medicaladvice> query(Medicaladvice medicaladvice) {
		// TODO Auto-generated method stub
		System.out.println(medicaladvice.getEmpid());
		return md.query(medicaladvice);
		
	}
	public boolean add(Medicaladvice medicaladvice) {
//		return md.add(medicaladvice);
		try {
			md.add(medicaladvice);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public Medicaladvice findById(Integer maId) {
		return md.findById(maId);
	}
	
	public Integer findinpatId(Integer maId) {
		return md.findinpatId(maId);
	}
	
	public boolean update(Medicaladvice medicaladvice) {
		try {
			md.update(medicaladvice);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Integer maId) {
		try {
			md.delete(maId);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public List<Medicaladvice> maIdSelect() {
		return md.maIdSelect();
	}
}

