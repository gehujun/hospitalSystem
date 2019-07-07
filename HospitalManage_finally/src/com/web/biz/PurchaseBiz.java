package com.web.biz;

import java.util.List;

import java.util.Map;

import com.web.entity.Employee;
import com.web.entity.Purchase;
import com.web.entity.Supplier;

public interface PurchaseBiz {
	public List<Purchase> queryByCondition(Map<String, Object> map);
	
	public List<Purchase> query();
	
	public List<Purchase> queryByJoin();
	
	public Purchase findById(Integer purid);
	
	public List<Supplier> querySupId(); 
	
	public List<Employee> queryEmpId();
	
	public boolean deleteById(Purchase purchase);
	
	public boolean addPurchase(Purchase purchase);
	
	public boolean updatePurchase(Purchase purchase);
	

}
