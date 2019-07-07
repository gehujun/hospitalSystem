package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Orderdetail;



public interface OrderdetailDao {

	/**
	 * 全查询
	 * @return
	 */
	public List<Orderdetail> query();
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Orderdetail> queryByCondition(Orderdetail orderdetail);
	


	public Orderdetail findById(@Param("detid") Integer detid);
	
	
	/**
	 * 修改
	 * @param orderdetail
	 */
	public void update(Orderdetail orderdetail);
	
	public void  delete(Integer detid);
	public void add(Orderdetail orderdetail);
	
}
