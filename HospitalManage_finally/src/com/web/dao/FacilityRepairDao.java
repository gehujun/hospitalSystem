package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.entity.Department;
import com.web.entity.Facilities;
import com.web.entity.Facilityrepair;


public interface FacilityRepairDao {
	
	public List<Facilityrepair> queryByCondition(Map<String, Object> map);
	
	public List<Facilityrepair> queryAll();
	
	public List<Facilityrepair> queryByJoin();

	public Facilityrepair findByID(int faciid);
	
	public List<Facilityrepair> queryFr();
	
	public List<Facilities> queryFc();	
	
	public List<Facilityrepair> queryFcDept();
	
	public List<Department> queryDept();
	
	public void deleteById(int faciid);
	
	public void addFacilityRepair(Facilityrepair facilityrepair);
	
	public void updateFacilityRepair(Facilityrepair facilityrepair);
}
