package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.SurgeryCommissionBiz;
import com.web.dao.SurgeryCommissionDao;
import com.web.entity.Employee;
import com.web.entity.Surgerycommission;
import com.web.util.MyBatisUtil;

public class SurgeryCommissionBizImpl implements SurgeryCommissionBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	SurgeryCommissionDao scDao=sqlSession.getMapper(SurgeryCommissionDao.class);
	@Override
	public List<Surgerycommission> queryByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scDao.queryByCondition(map);
	}

	@Override
	public List<Surgerycommission> query() {
		// TODO Auto-generated method stub
		return scDao.query();
	}

	@Override
	public List<Employee> queryByJoin() {
		// TODO Auto-generated method stub
		return scDao.queryByJoin();
	}

	@Override
	public boolean deleteById(Surgerycommission surgerycommission) {
		// TODO Auto-generated method stub
		try {
			scDao.deleteById(surgerycommission);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addSurgerycommission(Surgerycommission surgerycommission) {
		// TODO Auto-generated method stub
		try {
			scDao.addSurgerycommission(surgerycommission);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateSurgerycommission(Surgerycommission surgerycommission) {
		// TODO Auto-generated method stub
		try {
			scDao.updateSurgerycommission(surgerycommission);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Employee> querySCEmpId() {
		// TODO Auto-generated method stub
		return scDao.querySCEmpId();
	}

	@Override
	public Surgerycommission findById(Integer id) {
		// TODO Auto-generated method stub
		return scDao.findById(id);
	}

	

}
