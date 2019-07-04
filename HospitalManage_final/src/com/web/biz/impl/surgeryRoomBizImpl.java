package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.surgeryRoomBiz;

import com.web.dao.surgeryRoomDao;
import com.web.entity.Medicaladvice;
import com.web.entity.Surgerroom;
import com.web.util.MyBatisUtil;

public class surgeryRoomBizImpl implements surgeryRoomBiz {
	SqlSession sqlSession = MyBatisUtil.openSession();
	surgeryRoomDao srd = sqlSession.getMapper(surgeryRoomDao.class);
	@Override
	public List<Surgerroom> query(Surgerroom surgerroom) {
		return srd.query(surgerroom);
	}

	@Override
	public boolean add(Surgerroom surgerroom) {
		try {
			srd.add(surgerroom);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Surgerroom findById(String surRoomId) {
		return srd.findById(surRoomId);
	}

	@Override
	public boolean update(Surgerroom surgerroom) {
		try {
			srd.update(surgerroom);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String surRoomId) {
		try {
			srd.delete(surRoomId);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	

}
