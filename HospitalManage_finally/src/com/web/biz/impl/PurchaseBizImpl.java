package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.PurchaseBiz;
import com.web.dao.PurchaseDao;
import com.web.entity.Employee;
import com.web.entity.Facilities;
import com.web.entity.Purchase;
import com.web.entity.Supplier;
import com.web.util.MyBatisUtil;

public class PurchaseBizImpl implements PurchaseBiz {
	SqlSession sqlSession=MyBatisUtil.openSession();
	PurchaseDao purchaseDao=sqlSession.getMapper(PurchaseDao.class);
	@Override
	public List<Purchase> queryByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return purchaseDao.queryByCondition(map);
	}

	@Override
	public List<Purchase> query() {
		// TODO Auto-generated method stub
		return purchaseDao.query();
	}

	@Override
	public List<Purchase> queryByJoin() {
		// TODO Auto-generated method stub
		return purchaseDao.queryByJoin();
	}

	@Override
	public List<Supplier> querySupId() {
		// TODO Auto-generated method stub
		return purchaseDao.querySupId();
	}

	@Override
	public List<Employee> queryEmpId() {
		// TODO Auto-generated method stub
		return purchaseDao.queryEmpId();
	}
	
	@Override
	public Purchase findById(Integer purid) {
		// TODO Auto-generated method stub
		return purchaseDao.findById(purid);
	}

	@Override
	public boolean deleteById(Purchase purchase) {
		// TODO Auto-generated method stub
		try {
			purchaseDao.deleteById(purchase);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addPurchase(Purchase purchase) {
		// TODO Auto-generated method stub
		try {
			purchaseDao.addPurchase(purchase);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePurchase(Purchase purchase) {
		// TODO Auto-generated method stub
		try {
			purchaseDao.updatePurchase(purchase);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
