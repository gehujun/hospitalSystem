package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Travelactivities;
public interface TravelactivitiesDao  {

	/**
	 * 全查询
	 * @return
	 */
	public List<Travelactivities> query();
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Travelactivities> queryByCondition(Travelactivities travelactivities);
		
	/**
	 * 根据id查询对象
	 * @param traid
	 * @return
	 */
	public Travelactivities findById(@Param("traid") Integer traid);
	
	
	/**
	 * 修改
	 * @param department
	 */
	public void update(Travelactivities travelactivities);
	
	public void  delete(Integer traid);
	
	public void add(Travelactivities travelactivities);
	
	
	
}
