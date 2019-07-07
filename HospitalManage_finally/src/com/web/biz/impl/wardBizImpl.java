package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.wardBiz;
import com.web.dao.wardDao;
import com.web.entity.Ward;
import com.web.util.MyBatisUtil;

public class wardBizImpl implements wardBiz {
	SqlSession sqlSession = MyBatisUtil.openSession();
	wardDao wd = sqlSession.getMapper(wardDao.class);
	@Override
	public List<Ward> query(Ward ward) {
		// TODO Auto-generated method stub
		return wd.query(ward);
	}

	@Override
	public boolean add(Ward ward) {
		// TODO Auto-generated method stub
		try {
			wd.add(ward);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Ward findById(String wardName) {
		// TODO Auto-generated method stub
		return wd.findById(wardName);
	}

	@Override
	public boolean update(Ward ward) {
		// TODO Auto-generated method stub
		try {
			wd.update(ward);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String wardName) {
		// TODO Auto-generated method stub
		try {
			wd.delete(wardName);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
