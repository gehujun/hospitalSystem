package com.web.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.web.dao.DepartmentDao;
import com.web.entity.TDepartment;
import com.web.entity.TPosition;
import com.web.util.MyBatisUtil;

public class TestDeparment {

	
	@Test
	public void testQuery() throws Exception {
		
		SqlSession sqlSession = MyBatisUtil.openSession();
		
		DepartmentDao dd = sqlSession.getMapper(DepartmentDao.class);
		
		List<TDepartment> list = dd.query();
		
		for (TDepartment department : list) {
			System.out.println("科室名称:"+department.getDeptName());
			
			List<TPosition> positionList = department.getPositionList();
			
			for (TPosition tPosition : positionList) {
				System.out.println("职位名称:"+tPosition.getPoName());
			}
			
			System.out.println("---------------------------");
		}
	}
	
	@Test
	public void testQueryByCondition() throws Exception {
        SqlSession sqlSession = MyBatisUtil.openSession();
		
		DepartmentDao dd = sqlSession.getMapper(DepartmentDao.class);
		
		TDepartment department = new TDepartment();
		department.setDeptName("科");
		department.setDeptPhone("187");
		
		List<TDepartment> list = dd.queryByCondition(department);
		
		for (TDepartment tDepartment : list) {
			System.out.println("科室名称:"+tDepartment.getDeptName()+",科室电话:"+tDepartment.getDeptPhone());
		}
	}
	
	
	@Test
	public void testQueryForeach() throws Exception {
		   SqlSession sqlSession = MyBatisUtil.openSession();
		 	
			DepartmentDao dd = sqlSession.getMapper(DepartmentDao.class);
			
			List<Integer> ids = new ArrayList<>();
			ids.add(1);
			ids.add(2);
			ids.add(3);
			
			List<TDepartment> deptlist = dd.queryByIds(ids);
			for (TDepartment tDepartment : deptlist) {
				System.out.println("科室名称:"+tDepartment.getDeptName()+",科室电话:"+tDepartment.getDeptPhone());
			}
			
	}
	
	
	
	
	
	
}
