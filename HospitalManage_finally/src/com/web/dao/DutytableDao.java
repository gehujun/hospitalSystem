package com.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


import com.web.entity.Dutytable;
import com.web.entity.Employee;
import com.web.entity.Inprescription;
import com.web.entity.Medicaladvice;
import com.web.entity.Ward;

public interface DutytableDao {

	public List<Dutytable> query();
	
	public List<Dutytable> queryByCondition(Map<String, Object> map);
	
	public Dutytable findById(@Param("dutyid") Integer dutyid);//通过病床ID查询床位信息
	
	public void update (Dutytable dutytable);
	
	public void add (Dutytable dutytable);
	
	public void delete(@Param("dutyid") Integer dutyid);
	
	public List<Employee> empSelect();
	
	public List<Ward> wardnameSelect();
	

}
