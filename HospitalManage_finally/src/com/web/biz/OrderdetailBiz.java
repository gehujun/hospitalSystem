package com.web.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Orderdetail;



public interface OrderdetailBiz {
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
	public boolean update(Orderdetail orderdetail);
	
	public boolean delete(@Param("detid") Integer detid);
	public boolean add(Orderdetail orderdetail);
}
