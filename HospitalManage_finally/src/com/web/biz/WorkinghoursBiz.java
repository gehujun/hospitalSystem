package com.web.biz;

import java.util.List;
import java.util.Map;

import com.web.entity.Employee;
import com.web.entity.Workinghours;

public interface WorkinghoursBiz {

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
	 * 根据员工id查询对象
	 * @param workempid
	 * @return
	 */
	public Workinghours findByWorkId(Integer workid);
	
	
	/**
	 * 修改
	 * @param department
	 */
	public boolean update(Workinghours workinghours);
	
	/*
	 * 增加
	 */
	public boolean add(Workinghours workinghours);
	
	/*
	 * 删除
	 */
	public boolean delete(Integer workempid);
	
	
}
