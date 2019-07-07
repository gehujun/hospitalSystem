package com.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Department;
import com.web.entity.Facilities;
import com.web.entity.Medicinelibrary;

public interface FacilitiesDao {
	
	public List<Facilities>  query();
	
	//public List<Facilities>  findDepList(Facilities facilities);

	
	public List<Department>  queryByJoin();
	
	public List<Facilities> queryByCondition(Map<String, Object> map);
	
	public Facilities findById(@Param("faciid")Integer faciid);
	
	public void updateFacilities(Facilities facilities);
	
	public void deleteById(Facilities facilities);
	
	public void addFacilities(Facilities facilities);

}
