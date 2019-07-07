package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.MedicineBiz;
import com.web.dao.MedicineDao;
import com.web.entity.Medicine;
import com.web.entity.Medicinelibrary;
import com.web.util.MyBatisUtil;

public class MedicineBizImpl implements MedicineBiz {

	SqlSession sqlSession = MyBatisUtil.openSession();
	MedicineDao medicineDao = sqlSession.getMapper(MedicineDao.class);
	
	@Override
	public List<Medicine> queryByCondition(Map<String, Object> map) {
		return medicineDao.queryByCondition(map);
	}
	
	@Override
	public List<Medicine> queryAll() {
		return medicineDao.queryAll();
	}
	
	@Override
	public List<Medicinelibrary> queryByJoin() {
		return medicineDao.queryByJoin();
	}

	@Override
	public Medicine findByID(int mediid) {
		return medicineDao.findByID(mediid);
	}

	@Override
	public boolean deleteById(int mediid) {
		try {
			medicineDao.deleteById(mediid);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addMedicine(Medicine medicine) {
		try {
			medicineDao.addMedicine(medicine);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateMedicine(Medicine medicine) {
		
		try {
			medicineDao.updateMedicine(medicine);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
