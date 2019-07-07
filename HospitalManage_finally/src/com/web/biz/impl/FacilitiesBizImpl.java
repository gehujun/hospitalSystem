package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.FacilitiesBiz;
import com.web.dao.DepartmentDao;
import com.web.dao.FacilitiesDao;
import com.web.entity.Department;
import com.web.entity.Facilities;
import com.web.util.MyBatisUtil;

public class FacilitiesBizImpl implements FacilitiesBiz {

	SqlSession sqlSession = MyBatisUtil.openSession();
	FacilitiesDao facilitiesDao=sqlSession.getMapper(FacilitiesDao.class);

	
	@Override
	public List<Facilities> query() {
		// TODO Auto-generated method stub
		return facilitiesDao.query();
	}

/*	@Override
	public List<Facilities> findDepList(Facilities facilities) {
		// TODO Auto-generated method stub
		return facilitiesDao.findDepList(facilities);
	}*/

	@Override
	public List<Department> queryByJoin( ) {
		// TODO Auto-generated method stub
		return facilitiesDao.queryByJoin();
	}

	@Override
	public List<Facilities> queryByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return facilitiesDao.queryByCondition(map);
	}

	@Override
	public Facilities findById(Integer id) {
		// TODO Auto-generated method stub
		return facilitiesDao.findById(id);
	}

	@Override
	public boolean updateFacilities(Facilities facilities) {
		// TODO Auto-generated method stub
		try {
			facilitiesDao.updateFacilities(facilities);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteById(Facilities facilities) {
		// TODO Auto-generated method stub
		try {
			facilitiesDao.deleteById(facilities);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addFacilities(Facilities facilities) {
		// TODO Auto-generated method stub
		try {
			facilitiesDao.addFacilities(facilities);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
