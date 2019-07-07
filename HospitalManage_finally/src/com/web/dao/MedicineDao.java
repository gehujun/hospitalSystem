package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.entity.Medicine;
import com.web.entity.Medicinelibrary;

public interface MedicineDao {
	

		public List<Medicine> queryByCondition(Map<String, Object> map);
		
		public List<Medicine> queryAll();
		
		public List<Medicinelibrary> queryByJoin();

		public Medicine findByID(int mediid);
		
		public void deleteById(int mediid);
		
		public void addMedicine(Medicine medicine);
		
		public void updateMedicine(Medicine medicine);


	
}
