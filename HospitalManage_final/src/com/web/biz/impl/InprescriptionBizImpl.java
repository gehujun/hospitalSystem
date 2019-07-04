package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

import com.web.biz.InprescriptionBiz;
import com.web.dao.BedDao;
import com.web.dao.InprescriptionDao;
import com.web.entity.Bed;
import com.web.entity.Inprescription;
import com.web.entity.Medicaladvice;
import com.web.entity.Medicine;
import com.web.util.MyBatisUtil;

public class InprescriptionBizImpl implements InprescriptionBiz {


	SqlSession sqlSession = MyBatisUtil.openSession();
	
	InprescriptionDao id = sqlSession.getMapper(InprescriptionDao.class);
	

	@Override
	public List<Medicine> mediidSelect() {
		return id.mediidSelect();
	}
	
	@Override
	public List<Medicaladvice> maidSelect() {
		return id.maidSelect();
	}

	
	@Override
	public List<Inprescription> queryByCondition(Map<String, Object> map) {
		
		List<Inprescription> list = id.queryByCondition(map);
		
		return list;
	}

	public Inprescription findById(Integer mediid,Integer maid){
		
		return id.findById(mediid,maid);
	}
	
	@Override
	public boolean update(Inprescription inprescription) {
		
		try {			
			id.update(inprescription);		
			sqlSession.commit();			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(Inprescription inprescription) {
		try {			
			id.add(inprescription);			
			sqlSession.commit();			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer mediid,Integer maid) {
		try {
			id.delete(mediid,maid);
			
			sqlSession.commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



}
