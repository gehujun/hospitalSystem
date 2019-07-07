package com.web.biz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Department;
import com.web.entity.Facilities;

public interface FacilitiesBiz {
	public List<Facilities>  query();
	
	//public List<Facilities>  findDepList(Facilities facilities);
	
	public List<Department>  queryByJoin();
	
	public List<Facilities> queryByCondition(Map<String, Object> map);
	
	public Facilities findById(Integer faciid);
	
	public boolean updateFacilities(Facilities facilities);
	
	public boolean deleteById(Facilities facilities);
	
	public boolean addFacilities(Facilities facilities);

}
