package com.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Employee;
import com.web.entity.Medicinelibrary;

public interface MedicineLibraryDao {
	
	public List<Medicinelibrary>  query();
	
	//public List<Medicinelibrary>  findDepList(Medicinelibrary Medicinelibrary);
	
	public List<Medicinelibrary>  queryByIds(@Param("meliid") List<Integer> meliid);
	
	public List<Employee> queryByJoin();
	
	public List<Medicinelibrary> queryByCondition(Map<String, Object> map);
	
	public Medicinelibrary findById(@Param("meliid") Integer meliid);
	
	public void updateMedicineLib(Medicinelibrary medicineLibrary);
	
	public void deleteById(Medicinelibrary medicineLibrary);
	
	public void addMedicineLib(Medicinelibrary Medicinelibrary);

}
