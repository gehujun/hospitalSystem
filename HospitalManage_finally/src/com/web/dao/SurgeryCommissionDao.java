package com.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Employee;
import com.web.entity.Surgerycommission;

public interface SurgeryCommissionDao {
	public List<Surgerycommission> queryByCondition(Map<String, Object> map);
	
	public List<Surgerycommission> query();
	
	public List<Employee> queryByJoin();
	
	public List<Employee> querySCEmpId();

	public Surgerycommission findById(@Param("empid") Integer empid);
	
	public void deleteById(Surgerycommission surgerycommission);
	
	public void addSurgerycommission(Surgerycommission surgerycommission);
	
	public void updateSurgerycommission(Surgerycommission surgerycommission);

}
