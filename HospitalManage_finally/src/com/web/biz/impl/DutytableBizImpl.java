package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.DutytableBiz;
import com.web.dao.DutytableDao;
import com.web.entity.Dutytable;
import com.web.entity.Employee;
import com.web.entity.Inprescription;
import com.web.entity.Ward;
import com.web.util.MyBatisUtil;

public class DutytableBizImpl implements DutytableBiz {

	SqlSession sqlSession = MyBatisUtil.openSession();
	
	DutytableDao md = sqlSession.getMapper(DutytableDao.class);
	
	@Override
	public List<Dutytable> queryByCondition(Map<String, Object> map) {
		
			
			List<Dutytable> list = md.queryByCondition(map);
			
			return list;
	}

	@Override
	public List<Employee> empSelect() {
		return md.empSelect();
	}

	@Override
	public List<Ward> wardnameSelect() {
		return md.wardnameSelect();
	}

	@Override
	public Dutytable findById(Integer dutyid) {
		
		return md.findById(dutyid);
	}

	@Override
	public boolean update(Dutytable dutytable) {
		
		try {
			
			md.update(dutytable);
			
			sqlSession.commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(Dutytable dutytable) {
		
		try {
			md.add(dutytable);
			
			sqlSession.commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer dutyid) {
		
		try {
			md.delete(dutyid);
			
			sqlSession.commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	}
	