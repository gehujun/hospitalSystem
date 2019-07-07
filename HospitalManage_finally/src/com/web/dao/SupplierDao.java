package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Supplier;



public interface SupplierDao {

	/**
	 * 全查询
	 * @return
	 */
	public List<Supplier> query();
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Supplier> queryByCondition(Supplier supplier);
	
	
	/**
	 * in子句的查询
	 * @param ids
	 * @return
	 */
	//public List<Supplier> queryByIds(@Param("ids") List<Integer> ids);
	
	/**
	 * 根据id查询对象
	 * @param supId
	 * @return
	 */
	public Supplier findById(@Param("supid") Integer supid);
	
	
	/**
	 * 修改
	 * @param department
	 */
	public void update(Supplier supplier);
	
	public void  delete(Integer supid);
	
	public void add(Supplier supplier);
	
	
}
