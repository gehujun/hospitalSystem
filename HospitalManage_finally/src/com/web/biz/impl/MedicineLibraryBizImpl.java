package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.MedicineLibraryBiz;
import com.web.dao.MedicineLibraryDao;
import com.web.entity.Employee;
import com.web.entity.Medicinelibrary;
import com.web.util.MyBatisUtil;

public class MedicineLibraryBizImpl implements MedicineLibraryBiz {
	SqlSession sqlSession=MyBatisUtil.openSession();
	MedicineLibraryDao MedicineLibDao=sqlSession.getMapper(MedicineLibraryDao.class);
	@Override
	public List<Medicinelibrary> query() {
		// TODO Auto-generated method stub
		return MedicineLibDao.query();
	}

/*	@Override
	public List<Medicinelibrary> queryByIds(List<Integer> meLiId) {
		// TODO Auto-generated method stub
		return MedicineLibDao.queryByIds(meLiId);
	}*/

	@Override
	public List<Medicinelibrary> queryByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return MedicineLibDao.queryByCondition(map);
	}
	
	@Override
	public List<Employee> queryByJoin() {
		return MedicineLibDao.queryByJoin();
	}
	
	@Override
	public Medicinelibrary findById(Integer id) {
		// TODO Auto-generated method stub
		return MedicineLibDao.findById(id);
	}

	@Override
	public boolean updateMedicineLib(Medicinelibrary medicinelibrary) {
		// TODO Auto-generated method stub
		try {
			MedicineLibDao.updateMedicineLib(medicinelibrary);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteById(Medicinelibrary medicinelibrary) {
		// TODO Auto-generated method stub
		try {
			MedicineLibDao.deleteById(medicinelibrary);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addMedicineLib(Medicinelibrary medicinelibrary) {
		// TODO Auto-generated method stub
		try {
			MedicineLibDao.addMedicineLib(medicinelibrary);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
