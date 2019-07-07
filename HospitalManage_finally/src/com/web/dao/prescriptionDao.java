package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.entity.Prescription;

public interface prescriptionDao {

	/**
	 * 全查询
	 * @return
	 */
	public List<Prescription> query();
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Prescription> queryByCondition(Map<String, Object> map);
	
	
	/**
	 * 根据id查询对象
	 * @param deptId
	 * @return
	 */
	//public Prescription findByPresId(@Param("presid") Integer presid);
	public Prescription findByPresId(Integer presid);
	
	/**
	 * 修改
	 * @param department
	 */
	public void update(Prescription prescription);
	
	/*
	 * 增加
	 */
	public void add(Prescription prescription);
	
	/*
	 * 删除
	 */
	public void delete(Integer presid);
}
