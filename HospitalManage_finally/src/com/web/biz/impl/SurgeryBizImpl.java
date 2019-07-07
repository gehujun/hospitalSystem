package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.SurgeryBiz;
import com.web.dao.SurgeryDao;

import com.web.entity.Surgery;

import com.web.util.MyBatisUtil;

public class SurgeryBizImpl implements SurgeryBiz {
	SqlSession sqlSession = MyBatisUtil.openSession();
	SurgeryDao dd=sqlSession.getMapper(SurgeryDao.class);

	/**
	 * 根据条件查询
	 */
	@Override
	public List<Surgery> queryByCondition(Surgery surgery) {
		
		List<Surgery> list = dd.queryByCondition(surgery);
		
		return list;
	}
	
	public Surgery findById(Integer surgid) {
		return dd.findById(surgid);
	}
	

	
	@Override
	public boolean update(Surgery surgery) {
		
		try {
			dd.update(surgery);
			
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Integer surgid) {
		try {
			dd.delete(surgid);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean add(Surgery surgery) {
		
		try {
			dd.add(surgery);
			
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	


}
