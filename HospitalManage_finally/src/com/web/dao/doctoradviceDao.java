package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.entity.Doctoradvice;

public interface doctoradviceDao {

	/*
	 * 全查询
	 */
	public List<Doctoradvice> query();
	
	/*
	 * 根据条件查询
	 */
	public List<Doctoradvice> queryByCondition(Map<String, Object> map);
	
	/**
	 * 根据医嘱id查询对象
	 * @param workEmpId
	 * @return
	 */
	public Doctoradvice findByDocaId(Integer docaid);
	
	
	/**
	 * 修改
	 * @param department
	 */
	public void update(Doctoradvice doctoradvice);
	
	/*
	 * 增加
	 */
	public void add(Doctoradvice doctoradvice);
	
	/*
	 * 删除
	 */
	public void delete(Integer docaid);
}
