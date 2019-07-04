package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.bedBiz;
import com.web.dao.bedDao;
import com.web.dao.departmentDao;
import com.web.entity.Bed;
import com.web.util.MyBatisUtil;

public class bedBizImpl implements bedBiz {
	SqlSession sqlSession = MyBatisUtil.openSession();
	bedDao bd = sqlSession.getMapper(bedDao.class);
	@Override
	public List<String> bedIdSelect() {
		// TODO Auto-generated method stub
		return bd.bedIdSelect();
	}

}
