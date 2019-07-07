package com.web.biz;

import java.util.List;
import java.util.Map;

import com.web.entity.Employee;
import com.web.entity.Surgerycommission;

public interface SurgeryCommissionBiz {
	public List<Surgerycommission> queryByCondition(Map<String, Object> map);
	
	public List<Surgerycommission> query();
	
	public List<Employee> queryByJoin();
	
	public List<Employee> querySCEmpId();
	
	public Surgerycommission findById(Integer id);
	
	public boolean deleteById(Surgerycommission surgerycommission);
	
	public boolean addSurgerycommission(Surgerycommission surgerycommission);
	
	public boolean updateSurgerycommission(Surgerycommission surgerycommission);
}
