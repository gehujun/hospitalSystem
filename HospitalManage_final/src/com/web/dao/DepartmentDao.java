package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Department;
import com.web.entity.TDepartment;

/**
 * 科室的数据访问层
 * @author ASUS
 *
 */
public interface DepartmentDao {

	/**
	 * 全查询
	 * @return
	 */
	public List<TDepartment> query();
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Department> queryByCondition(Department department);
	
	
	/**
	 * in子句的查询
	 * @param ids
	 * @return
	 */
	public List<TDepartment> queryByIds(@Param("ids") List<Integer> ids);
	
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
	public void update(TDepartment department);
	
	public void deleteById(Department department);
	
	public void addDepartment(Department department);
	
	public void updateDepartment(Department department);
	
	public List<Department> deptIdSelect();
	
}
