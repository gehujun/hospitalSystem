package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.TestitemsBiz;
import com.web.dao.testitemsDao;
import com.web.entity.Testitems;
import com.web.util.MyBatisUtil;

public class TestitemsBizImpl implements TestitemsBiz {

	SqlSession sqlSession = MyBatisUtil.openSession();
	testitemsDao ti = sqlSession.getMapper(testitemsDao.class);
	
	@Override
	public List<Testitems> query() {
		List<Testitems> list=ti.query();
		return list;
	}

	@Override
	public List<Testitems> queryByCondition(Map<String, Object> map) {
		List<Testitems> list=ti.queryByCondition(map);
		return list;
	}

	@Override
	public Testitems findByTestId(Integer testid) {
		return ti.findByTestId(testid);
	}

	@Override
	public boolean update(Testitems testitems) {
		try {
			ti.update(testitems);

			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(Testitems testitems) {
		try {
			ti.add(testitems);

			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer testid) {
		try {
			ti.delete(testid);

			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
