package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.core.net.Facility;

import com.web.biz.FacilityRepairBiz;
import com.web.dao.FacilityRepairDao;
import com.web.entity.Department;
import com.web.entity.Facilities;
import com.web.entity.Facilityrepair;
import com.web.util.MyBatisUtil;

public class FacilityRepairBizImpl implements FacilityRepairBiz {
	SqlSession sqlSession = MyBatisUtil.openSession();
	FacilityRepairDao facilityRepairDao = sqlSession.getMapper(FacilityRepairDao.class);

	@Override
	public List<Facilityrepair> queryByCondition(Map<String, Object> map) {
		return facilityRepairDao.queryByCondition(map);
	}

	@Override
	public List<Facilityrepair> queryAll() {
		return facilityRepairDao.queryAll();
	}

	@Override
	public List<Facilityrepair> queryByJoin() {
		return facilityRepairDao.queryByJoin();
	}

	@Override
	public Facilityrepair findByID(int faciid) {
		return facilityRepairDao.findByID(faciid);
	}

	@Override
	public List<Facilityrepair> queryFr() {
		return facilityRepairDao.queryFr();
	}
	
	@Override
	public List<Facilities> queryFc() {
		return facilityRepairDao.queryFc();
	}

	@Override
	public List<Facilityrepair> queryFcDept() {
		return facilityRepairDao.queryFcDept();
	}
	
	
	@Override
	public List<Department> queryDept() {
		return facilityRepairDao.queryDept();
	}

	@Override
	public boolean deleteById(int faciid) {
		try {
			facilityRepairDao.deleteById(faciid);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addFacilityRepair(Facilityrepair facilityrepair) {
		try {
			facilityRepairDao.addFacilityRepair(facilityrepair);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateFacilityRepair(Facilityrepair facilityrepair) {
		try {
			facilityRepairDao.updateFacilityRepair(facilityrepair);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
