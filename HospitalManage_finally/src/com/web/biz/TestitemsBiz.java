package com.web.biz;

import java.util.List;
import java.util.Map;

import com.web.entity.Testitems;


public interface TestitemsBiz {

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
	 * 根据处方id查询对象
	 * @param workempid
	 * @return
	 */
	public Testitems findByTestId(Integer testid);
	
	
	/**
	 * 修改
	 * @param department
	 */
	public boolean update(Testitems testitems);
	
	/*
	 * 增加
	 */
	public boolean add(Testitems testitems);
	
	/*
	 * 删除
	 */
	public boolean delete(Integer testid);
}
