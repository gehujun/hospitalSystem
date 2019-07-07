package com.web.biz;

import java.util.List;
import java.util.Map;

import com.web.entity.Doctoradvice;


public interface DoctoradviceBiz {

	/**
	 * 全查询
	 * @return
	 */
	public List<Doctoradvice> query();
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Doctoradvice> queryByCondition(Map<String, Object> map);
	
	/**
	 * 根据医嘱id查询对象
	 * @param workempid
	 * @return
	 */
	public Doctoradvice findByDocaId(Integer docaid);
	
	
	/**
	 * 修改
	 * @param department
	 */
	public boolean update(Doctoradvice doctoradvice);
	
	/*
	 * 增加
	 */
	public boolean add(Doctoradvice doctoradvice);
	
	/*
	 * 删除
	 */
	public boolean delete(Integer docaid);
	
	/*
	 * 外键：根据员工表查询
	 */
}
