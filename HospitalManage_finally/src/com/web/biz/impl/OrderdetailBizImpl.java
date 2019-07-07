package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.OrderdetailBiz;
import com.web.dao.OrderdetailDao;
import com.web.entity.Orderdetail;
import com.web.util.MyBatisUtil;

public class OrderdetailBizImpl implements OrderdetailBiz {

	SqlSession sqlSession = MyBatisUtil.openSession();
	OrderdetailDao dd=sqlSession.getMapper(OrderdetailDao.class);

	/**
	 * 根据条件查询
	 */
	@Override
	public List<Orderdetail> queryByCondition(Orderdetail orderdetail) {
		
		List<Orderdetail> list = dd.queryByCondition(orderdetail);
		
		return list;
	}
	
	public Orderdetail findById(Integer detid) {
		return dd.findById(detid);
	}
	
	@Override
	public boolean update(Orderdetail orderdetail) {
		
		try {
			dd.update(orderdetail);
			
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Override
	public boolean add(Orderdetail orderdetail) {
		
		try {
			dd.add(orderdetail);
			
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Integer detid) {
		try {
			dd.delete(detid);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	

	
	
	
	
}
