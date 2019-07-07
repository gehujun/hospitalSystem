package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.SupplierBiz;
import com.web.dao.SupplierDao;
import com.web.entity.Supplier;
import com.web.util.MyBatisUtil;



public class SupplierBizImpl implements SupplierBiz{

	
	SqlSession sqlSession = MyBatisUtil.openSession();
	SupplierDao dd = sqlSession.getMapper(SupplierDao.class);

	/**
	 * 根据条件查询
	 */
	@Override
	public List<Supplier> queryByCondition(Supplier supplier) {
		
		List<Supplier> list = dd.queryByCondition(supplier);
		
		return list;
	}
	
	public Supplier findById(Integer supid) {
		return dd.findById(supid);
	}
	
	@Override
	public boolean update(Supplier supplier) {
		
		try {
			dd.update(supplier);
			
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Integer supid) {
		try {
			dd.delete(supid);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Override
	public boolean add(Supplier supplier) {
	// TODO Auto-generated method stub
		try {
			dd.add(supplier);
			
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

}
