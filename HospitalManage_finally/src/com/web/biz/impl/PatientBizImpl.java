package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.PatientBiz;
import com.web.dao.PatientDao;
import com.web.entity.Patient;
import com.web.util.MyBatisUtil;

public class PatientBizImpl implements PatientBiz {

	SqlSession sqlsession = MyBatisUtil.openSession();
	PatientDao patDao = sqlsession.getMapper(PatientDao.class);
	@Override
	public List<Patient> queryByCondition(Patient patient) {
		// TODO Auto-generated method stub
		return patDao.queryByCondition(patient);
	}
	
	@Override
	public boolean deleteById(Patient patient) {
		// TODO Auto-generated method stub
		try {
			patDao.deleteById(patient);
			sqlsession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Patient findById(Integer id) {
		// TODO Auto-generated method stub
		return patDao.findById(id);
	}

	@Override
	public boolean updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		try {
			patDao.updatePatient(patient);
			sqlsession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean addPatient(Patient patient) {
		// TODO Auto-generated method stub
		try {
			patDao.addPatient(patient);
			sqlsession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	
}
