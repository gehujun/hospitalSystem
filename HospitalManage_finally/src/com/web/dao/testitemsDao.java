package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.entity.Testitems;

public interface testitemsDao {

	/**
	 * 全查询
	 * @return
	 */
	public List<Testitems> query();
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Testitems> queryByCondition(Map<String, Object> map);
	
	
	/**
	 * 根据id查询对象
	 * @param deptId
	 * @return
	 */
	//public Prescription findByPresId(@Param("presid") Integer presid);
	public Testitems findByTestId(Integer testid);
	
	/**
	 * 修改
	 * @param department
	 */
	public void update(Testitems testitems);
	
	/*
	 * 增加
	 */
	public void add(Testitems testitems);
	
	/*
	 * 删除
	 */
	public void delete(Integer testid);
}
