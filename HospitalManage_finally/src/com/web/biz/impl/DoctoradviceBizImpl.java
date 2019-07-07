package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.DoctoradviceBiz;
import com.web.dao.doctoradviceDao;
import com.web.entity.Doctoradvice;
import com.web.util.MyBatisUtil;

public class DoctoradviceBizImpl implements DoctoradviceBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	doctoradviceDao da = sqlSession.getMapper(doctoradviceDao.class);

	@Override
	public List<Doctoradvice> query() {
		List<Doctoradvice> list=da.query();
		return list;
	}

	@Override
	public List<Doctoradvice> queryByCondition(Map<String, Object> map) {
		List<Doctoradvice> list=da.queryByCondition(map);
		return list;
	}

	@Override
	public Doctoradvice findByDocaId(Integer docaid) {
		return da.findByDocaId(docaid);
	}

	@Override
	public boolean update(Doctoradvice doctoradvice) {
		try {
			da.update(doctoradvice);

			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(Doctoradvice doctoradvice) {
		try {
			da.add(doctoradvice);
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer docaid) {
		try {
			da.delete(docaid);
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
