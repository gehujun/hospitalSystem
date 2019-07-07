package com.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Employee;
import com.web.entity.Facilities;
import com.web.entity.Purchase;
import com.web.entity.Supplier;

public interface PurchaseDao {
	public List<Purchase> queryByCondition(Map<String, Object> map);
	
	public List<Purchase> query();
	
	public List<Purchase> queryByJoin();
	
	public List<Supplier> querySupId(); 
	
	public List<Employee> queryEmpId();

	public Purchase findById(@Param("purid") Integer purid);
	
	public void deleteById(Purchase purchase);
	
	public void addPurchase(Purchase purchase);
	
	public void updatePurchase(Purchase purchase);

}
