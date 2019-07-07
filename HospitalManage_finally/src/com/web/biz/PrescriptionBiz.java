package com.web.biz;

import java.util.List;
import java.util.Map;

import com.web.entity.Prescription;

public interface PrescriptionBiz {

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
	 * 根据处方id查询对象
	 * @param workempid
	 * @return
	 */
	public Prescription findByPresId(Integer presid);
	
	
	/**
	 * 修改
	 * @param department
	 */
	public boolean update(Prescription prescription);
	
	/*
	 * 增加
	 */
	public boolean add(Prescription prescription);
	
	/*
	 * 删除
	 */
	public boolean delete(Integer presid);
}
