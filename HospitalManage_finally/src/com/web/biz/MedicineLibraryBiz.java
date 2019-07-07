package com.web.biz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Employee;
import com.web.entity.Medicinelibrary;

public interface MedicineLibraryBiz {
	
	public List<Medicinelibrary>  query();
	public List<Employee>  queryByJoin();
	
//	public List<Medicinelibrary>  findDepList(Medicinelibrary medicinelibrary);
	
	//public List<Medicinelibrary>  queryByIds(@Param("meliid") List<Integer> meliid);
	
	public List<Medicinelibrary> queryByCondition(Map<String, Object> map);
	
	public Medicinelibrary findById(Integer meliid);
	
	public boolean updateMedicineLib(Medicinelibrary medicinelibrary);
	
	public boolean deleteById(Medicinelibrary medicinelibrary);
	
	public boolean addMedicineLib(Medicinelibrary medicinelibrary);

}
