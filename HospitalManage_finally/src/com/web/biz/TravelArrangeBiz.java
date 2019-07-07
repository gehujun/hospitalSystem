package com.web.biz;

import java.util.List;
import java.util.Map;

import com.web.entity.Employee;
import com.web.entity.Travelactivities;
import com.web.entity.Travelarrange;

public interface TravelArrangeBiz {
	
	public List<Travelarrange> queryByCondition(Map<String, Object> map);
	
	public List<Travelarrange> queryAll();
	
	public List<Travelarrange> queryByJoin();
	
	public Travelarrange findByID(Map<String, Object> map);
	
	public List<Travelactivities> queryTravelId();
	
	public List<Employee> queryTravelEmpId();
	
	public boolean deleteById(Map<String, Object> map);
	
	public boolean addTravelArrange(Travelarrange travelarrange);
	
	public boolean updateTravelArrange(Travelarrange travelarrange);
}
