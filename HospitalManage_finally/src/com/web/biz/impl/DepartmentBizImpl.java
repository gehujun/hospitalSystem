package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.DepartmentBiz;
import com.web.dao.DepartmentDao;
import com.web.entity.Department;
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
	public List<Department> queryByCondition(Department department) {
		
		List<Department> list = dd.queryByCondition(department);
		
		return list;
	}

	public Department findById(Integer deptId) {
		return dd.findById(deptId);
	}
	
	@Override
	public boolean deleteById(Department department) {
		// TODO Auto-generated method stub
		try {
			dd.deleteById(department);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean addDepartment(Department department) {
	// TODO Auto-generated method stub
		try {
			dd.addDepartment(department);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateDepartment(Department department) {
	// TODO Auto-generated method stub
		
		try {
			dd.updateDepartment(department);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	
}
