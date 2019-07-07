package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import java.util.Map;
import com.web.biz.PositionBiz;
import com.web.dao.PositionDao;
import com.web.entity.Professionaltitle;
import com.web.entity.TPosition;
import com.web.util.MyBatisUtil;

public class PositionBizImpl implements PositionBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	PositionDao pd = sqlSession.getMapper(PositionDao.class);

	@Override
	public List<Professionaltitle> queryByCondition(Map<String, Object> map) {
		
		return pd.queryByCondition(map);
	}
	
	@Override
	public List<Professionaltitle> query() {
		// TODO Auto-generated method stub
		return pd.query();
	}
	
	@Override
	public Professionaltitle findById(Integer profid) {
		
		return pd.findByID(profid);
	}
	
	@Override
	public boolean update(Professionaltitle professionaltitle) {
		try {
			pd.update(professionaltitle);
			
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@Override
	public boolean add(Professionaltitle professionaltitle) {
		try {
			pd.add(professionaltitle);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@Override
	public boolean delete(Integer profid) {
		try {
			pd.delete(profid);
			
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
