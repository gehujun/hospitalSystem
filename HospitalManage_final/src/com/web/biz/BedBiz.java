package com.web.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Bed;
import com.web.entity.Employee;
import com.web.entity.Ward;



public interface BedBiz {

	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Bed> queryByCondition(Bed bed);

	public Bed findById(@Param("bedid") Integer bedid);
	
	public boolean update(Bed bed);
	
	public boolean add(Bed bed);

	public boolean delete( @Param("bedid") Integer bedid);
	
	public List<Employee> empidSelect();

	public List<Ward> wardnameSelect();
}
