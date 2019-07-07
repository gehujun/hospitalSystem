package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Surgery;


public interface SurgeryDao {
	/**
	 * 全查询
	 * @return
	 */
	public List<Surgery> query();
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Surgery> queryByCondition(Surgery surgery);
	
	
	/**
	 * in子句的查询
	 * @param ids
	 * @return
	 */
	public List<Surgery> queryByIds(@Param("ids") List<Integer> ids);
	
	/**
	 * 根据id查询对象
	 * @param surgid
	 * @return
	 */
	public Surgery findById(@Param("surgid") Integer surgid);
		
	/**
	 * 修改
	 * @param surgery
	 */
	public void update(Surgery surgery);
	
	public void  delete(Integer surgid);
	
	public void add(Surgery surgery);

}
