package com.web.biz;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.net.Facility;

import com.web.entity.Department;
import com.web.entity.Facilities;
import com.web.entity.Facilityrepair;



public interface FacilityRepairBiz {
	
	public List<Facilityrepair> queryByCondition(Map<String, Object> map);
	
	public List<Facilityrepair> queryAll();
	
	public List<Facilityrepair> queryByJoin();
	
	public Facilityrepair findByID(int faciid);
	
	public List<Facilityrepair> queryFr();
	
	public List<Facilities> queryFc();
	
	public List<Facilityrepair> queryFcDept();
	
	public List<Department> queryDept();
	
	public boolean deleteById(int faciid);
	
	public boolean addFacilityRepair(Facilityrepair facilityrepair);
	
	public boolean updateFacilityRepair(Facilityrepair facilityrepair);
}
