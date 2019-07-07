package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.medicinepayreceiptBiz;
import com.web.dao.medicalAdviceDao;
import com.web.dao.medicinepayreceiptDao;
import com.web.entity.Medicinepayreceipt;
import com.web.util.MyBatisUtil;

public class medicinepayreceiptBizImpl implements medicinepayreceiptBiz {
	SqlSession sqlSession = MyBatisUtil.openSession();
	medicinepayreceiptDao md = sqlSession.getMapper(medicinepayreceiptDao.class);
	
	@Override
	public List<Medicinepayreceipt> query(Medicinepayreceipt medicinepayreceipt) {
		// TODO Auto-generated method stub
		return md.query(medicinepayreceipt);
	}

	@Override
	public boolean add(Medicinepayreceipt medicinepayreceipt) {
		// TODO Auto-generated method stub
		try {
			md.add(medicinepayreceipt);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Medicinepayreceipt findById(Integer mprId) {
		// TODO Auto-generated method stub
		return md.findById(mprId);
	}

	@Override
	public boolean update(Medicinepayreceipt medicinepayreceipt) {
		// TODO Auto-generated method stub
		try {
			md.update(medicinepayreceipt);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer mprId) {
		// TODO Auto-generated method stub
		try {
			md.delete(mprId);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
