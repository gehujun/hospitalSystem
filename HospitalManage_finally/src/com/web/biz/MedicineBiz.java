package com.web.biz;

import java.util.List;
import java.util.Map;

import com.web.entity.Medicine;
import com.web.entity.Medicinelibrary;

public interface MedicineBiz {
	
	public List<Medicine> queryByCondition(Map<String, Object> map);
	
	public List<Medicine> queryAll();
	
	public List<Medicinelibrary> queryByJoin();
	
	public Medicine findByID(int mediid);
	
	public boolean deleteById(int mediid);
	
	public boolean addMedicine(Medicine medicine);
	
	public boolean updateMedicine(Medicine medicine);
	
	
}
