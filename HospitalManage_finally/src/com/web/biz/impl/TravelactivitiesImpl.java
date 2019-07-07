package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.TravelactivitiesBiz;
import com.web.dao.TravelactivitiesDao;
import com.web.entity.Travelactivities;
import com.web.util.MyBatisUtil;

public class TravelactivitiesImpl implements TravelactivitiesBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	
	TravelactivitiesDao dd = sqlSession.getMapper( TravelactivitiesDao.class);
	
	/**
	 * 根据条件查询
	 */
	@Override
	public List<Travelactivities> queryByCondition(Travelactivities travelactivities) {
		
		List<Travelactivities> list = dd.queryByCondition(travelactivities);
		
		return list;
	}
	
	public Travelactivities findById(Integer traid) {
		return dd.findById(traid);
	}
	
	@Override
	public boolean update(Travelactivities travelactivities) {
		
		try {
			dd.update(travelactivities);
			
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Integer traid) {
		try {
			dd.delete(traid);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean add(Travelactivities travelactivities) {
		
		try {
			dd.add(travelactivities);
			
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
