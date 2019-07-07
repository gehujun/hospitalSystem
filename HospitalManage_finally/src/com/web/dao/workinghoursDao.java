package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.entity.Employee;
import com.web.entity.Workinghours;

public interface workinghoursDao {

	/**
	 * 全查询
	 * @return
	 */
	public List<Workinghours> query();
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Workinghours> queryByCondition(Map<String, Object> map);
	
	
	/**
	 * 根据id查询对象
	 * @param workEmpId
	 * @return
	 */
	public Workinghours findByWorkId(Integer workid);
	
	
	/**
	 * 修改
	 * @param department
	 */
	public void update(Workinghours workinghours);
	
	/*
	 * 增加
	 */
	public void add(Workinghours workinghours);
	
	/*
	 * 删除
	 */
	public void delete(Integer workempid);
	
}
