package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.WorkinghoursBiz;
import com.web.dao.workinghoursDao;
import com.web.entity.Employee;
import com.web.entity.Workinghours;
import com.web.util.MyBatisUtil;

public class WorkinghoursBizImpl implements WorkinghoursBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	workinghoursDao wh = sqlSession.getMapper(workinghoursDao.class);

	@Override
	public List<Workinghours> query() {
		List<Workinghours> list=wh.query();
		return list;
	}

	@Override
	public List<Workinghours> queryByCondition(Map<String, Object> map) {
		List<Workinghours> list=wh.queryByCondition(map);
		return list;
	}

	@Override
	public Workinghours findByWorkId(Integer workid) {
		
		return wh.findByWorkId(workid);
	}

	@Override
	public boolean update(Workinghours workinghours) {
		try {
			wh.update(workinghours);

			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(Workinghours workinghours) {
		try {
			wh.add(workinghours);
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer workempid) {
		try {
			wh.delete(workempid);
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
