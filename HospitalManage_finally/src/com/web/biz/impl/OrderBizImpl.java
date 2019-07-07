package com.web.biz.impl;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.OrderBiz;
import com.web.dao.OrderDao;
import com.web.entity.Employee;
import com.web.entity.Order;
import com.web.entity.Supplier;
import com.web.util.MyBatisUtil;

public class OrderBizImpl implements OrderBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
	
	@Override
	public List<Order> queryAll() {
		// TODO Auto-generated method stub
		return orderDao.queryAll();
	}

	@Override
	public Order findByID(int ordid) {
		// TODO Auto-generated method stub
		return orderDao.findByID(ordid);
	}

	@Override
	public boolean deleteById(int ordid) {
		// TODO Auto-generated method stub
		try {
			orderDao.deleteById(ordid);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addOrder(Order order) {
		try {
			orderDao.addOrder(order);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateOrder(Order order) {
		try {
			orderDao.updateOrder(order);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Supplier> querySupId() {
		return orderDao.querySupId();
	}
	
	@Override
	public List<Employee> queryEmpId() {
		return orderDao.queryEmpId();
	}

	@Override
	public List<Order> queryByJoin() {
		return orderDao.queryByJoin();
	}

	@Override
	public List<Order> queryByCondition(Map<String, Object> map) {
		return orderDao.queryByCondition(map);
	}

}

