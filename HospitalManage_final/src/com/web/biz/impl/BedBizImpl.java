package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.BedBiz;
import com.web.dao.BedDao;
import com.web.entity.Bed;
import com.web.entity.Employee;
import com.web.entity.Ward;
import com.web.util.MyBatisUtil;

public class BedBizImpl implements BedBiz {


	SqlSession sqlSession = MyBatisUtil.openSession();
	
	BedDao dd = sqlSession.getMapper(BedDao.class);

	@Override
	public List<Employee> empidSelect() {
		return dd.empidSelect();
	}

	
	@Override
	public boolean add(Bed bed) {
		try {
			
			dd.add(bed);
			
			sqlSession.commit();			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Bed findById(Integer bedid) {
		return dd.findById(bedid);
	}

	@Override
	public boolean update(Bed bed) {
		try {
			
			dd.update(bed);
			
			sqlSession.commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer bedid) {
		try {
			dd.delete(bedid);
			
			sqlSession.commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 根据条件查询
	 */
	@Override
	public List<Bed> queryByCondition(Bed bed) {
		
		List<Bed> list = dd.queryByCondition(bed);
		
		return list;
	}


	@Override
	public List<Ward> wardnameSelect() {
		return dd.wardnameSelect();
	}

}
