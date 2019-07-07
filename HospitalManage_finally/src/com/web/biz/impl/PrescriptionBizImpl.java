package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.PrescriptionBiz;
import com.web.dao.prescriptionDao;
import com.web.entity.Prescription;
import com.web.util.MyBatisUtil;

public class PrescriptionBizImpl implements PrescriptionBiz {

	SqlSession sqlSession = MyBatisUtil.openSession();
	prescriptionDao pt = sqlSession.getMapper(prescriptionDao.class);
	
	@Override
	public List<Prescription> query() {
		List<Prescription> list=pt.query();
		return list;
	}

	@Override
	public List<Prescription> queryByCondition(Map<String, Object> map) {
		List<Prescription> list=pt.queryByCondition(map);
		return list;
	}

	@Override
	public Prescription findByPresId(Integer presid) {
		return pt.findByPresId(presid);
	}

	@Override
	public boolean update(Prescription prescription) {
		try {
			pt.update(prescription);

			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(Prescription prescription) {
		try {
			pt.add(prescription);

			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer presid) {
		try {
			pt.delete(presid);

			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
