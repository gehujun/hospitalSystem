package com.web.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Department;
import com.web.entity.TDepartment;

/**
 * 科室的业务逻辑层
 */
public interface DepartmentBiz {

	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Department> queryByCondition(Department department);
	
	/**
	 * 根据id查询对象
	 * @param deptId
	 * @return
	 */
	public Department findById(@Param("deptId") Integer deptId);
	
	/**
	 * 修改
	 * @param department
	 */
	
	public boolean deleteById(Department department);
	
	public boolean addDepartment(Department department);
	
	public boolean updateDepartment(Department department);

	public List<Department> deptIdSelect();
}
