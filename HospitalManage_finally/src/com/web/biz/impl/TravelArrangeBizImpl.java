package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.TravelArrangeBiz;
import com.web.dao.MedicineDao;
import com.web.dao.TravelArrangeDao;
import com.web.entity.Employee;
import com.web.entity.Travelactivities;
import com.web.entity.Travelarrange;
import com.web.util.MyBatisUtil;

public class TravelArrangeBizImpl implements TravelArrangeBiz{
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	TravelArrangeDao travelArrangeDao = sqlSession.getMapper(TravelArrangeDao.class);

	@Override
	public List<Travelarrange> queryByCondition(Map<String, Object> map) {
		return travelArrangeDao.queryByCondition(map);
	}

	@Override
	public List<Travelarrange> queryAll() {
		return travelArrangeDao.queryAll();
	}

	@Override
	public List<Travelarrange> queryByJoin() {
		return travelArrangeDao.queryByJoin();
	}

	@Override
	public Travelarrange findByID(Map<String, Object> map) {
		return travelArrangeDao.findByID(map);
	}

	@Override
	public List<Travelactivities> queryTravelId() {
		return travelArrangeDao.queryTravelId();
	}

	@Override
	public List<Employee> queryTravelEmpId() {
		return travelArrangeDao.queryTravelEmpId();
	}

	@Override
	public boolean deleteById(Map<String, Object> map) {
		try {
			travelArrangeDao.deleteById(map);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addTravelArrange(Travelarrange travelarrange) {
		try {
			travelArrangeDao.addTravelArrange(travelarrange);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateTravelArrange(Travelarrange travelarrange) {
		try {
			travelArrangeDao.updateTravelArrange(travelarrange);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
