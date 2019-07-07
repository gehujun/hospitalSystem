package com.web.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Supplier;

public interface SupplierBiz {

	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Supplier> queryByCondition(Supplier supplier);
	
	/**
	 * 根据id查询对象
	 * @param deptId
	 * @return
	 */
	public Supplier findById(@Param("supid") Integer supid);
	
	/**
	 * 修改
	 * @param department
	 */
	public boolean update(Supplier supplier);
	public boolean delete(@Param("supid") Integer supid);
	public boolean add(Supplier supplier);
	
	
}
