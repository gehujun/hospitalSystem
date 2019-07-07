package com.web.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Travelactivities;


public interface TravelactivitiesBiz {

	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Travelactivities> queryByCondition(Travelactivities travelactivities);
	
	public Travelactivities findById(@Param("traid") Integer traid);
	
	/**
	 * 修改
	 * @param department
	 */
	public boolean update(Travelactivities travelactivities);
	
	public boolean delete(@Param("traid") Integer traid);
	
	public boolean add(Travelactivities travelactivities);
	
}
