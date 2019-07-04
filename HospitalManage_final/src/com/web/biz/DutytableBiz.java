package com.web.biz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Bed;
import com.web.entity.Dutytable;
import com.web.entity.Employee;
import com.web.entity.Inprescription;
import com.web.entity.Medicaladvice;
import com.web.entity.Ward;

public interface DutytableBiz {

	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Dutytable> queryByCondition(Map<String, Object> map);
	

	public Dutytable findById(@Param("dutyid") Integer dutyid);
	
	public boolean update(Dutytable dutytable);
	
	public boolean add(Dutytable dutytable);

	public boolean delete( @Param("dutyid") Integer dutyid);
	
	public List<Employee> empSelect();
	
	public List<Ward> wardnameSelect();
}
