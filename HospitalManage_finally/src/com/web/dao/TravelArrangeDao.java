package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.entity.Employee;
import com.web.entity.Travelactivities;
import com.web.entity.Travelarrange;



public interface TravelArrangeDao {
	
	public List<Travelarrange> queryByCondition(Map<String, Object> map);
	
	public List<Travelarrange> queryAll();
	
	public List<Travelarrange> queryByJoin();

	public Travelarrange findByID(Map<String, Object> map);
	
	public List<Travelactivities> queryTravelId();
	
	public List<Employee> queryTravelEmpId();
	
	public void deleteById(Map<String, Object> map);
	
	public void addTravelArrange(Travelarrange travelarrange);
	
	public void updateTravelArrange(Travelarrange travelarrange);

}
