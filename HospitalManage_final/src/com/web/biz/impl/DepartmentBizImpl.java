package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.DepartmentBiz;
import com.web.dao.DepartmentDao;
import com.web.entity.TDepartment;
import com.web.util.MyBatisUtil;

/**
 * 科室的业务逻辑层实现类
 * @author ASUS
 *
 */
public class DepartmentBizImpl implements DepartmentBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	DepartmentDao dd = sqlSession.getMapper(DepartmentDao.class);

	/**
	 * 根据条件查询
	 */
	@Override
	public List<TDepartment> queryByCondition(TDepartment department) {
		
		List<TDepartment> list = dd.queryByCondition(department);
		
		return list;
	}

	public TDepartment findById(Integer deptId) {
		return dd.findById(deptId);
	}

	@Override
	public boolean update(TDepartment department) {
		
		try {
			dd.update(department);
			
			sqlSession.commit();//提交
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
	
}
