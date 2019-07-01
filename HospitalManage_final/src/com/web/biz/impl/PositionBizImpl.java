package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import java.util.Map;
import com.web.biz.PositionBiz;
import com.web.dao.PositionDao;
import com.web.entity.TPosition;
import com.web.util.MyBatisUtil;

public class PositionBizImpl implements PositionBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	PositionDao pd = sqlSession.getMapper(PositionDao.class);

	@Override
	public List<TPosition> queryByCondition(Map<String, Object> map) {
		
		return pd.queryByCondition(map);
	}

}
