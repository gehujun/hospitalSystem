package com.web.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.TDepartment;

/**
 * 科室的业务逻辑层
 */
public interface DepartmentBiz {

	/**
	 * 根据条件查询
	 * @return
	 */
	public List<TDepartment> queryByCondition(TDepartment department);
	
	/**
	 * 根据id查询对象
	 * @param deptId
	 * @return
	 */
	public TDepartment findById(@Param("deptId") Integer deptId);
	
	/**
	 * 修改
	 * @param department
	 */
	public boolean update(TDepartment department);

	
}
