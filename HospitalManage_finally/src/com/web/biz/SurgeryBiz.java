package com.web.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Surgery;


public interface SurgeryBiz {
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Surgery> queryByCondition(Surgery surgery);
	
	public Surgery findById(@Param("surgid") Integer surgid);
	
	/**
	 * 修改
	 * @param department
	 */
	public boolean update(Surgery surgery);
	
	public boolean delete(@Param("surgid") Integer surgid);
	public boolean add(Surgery surgery);
	
	
	
}
