package com.web.biz;

import java.util.List;
import java.util.Map;

import com.web.entity.Employee;
import com.web.entity.Order;
import com.web.entity.Supplier;

public interface OrderBiz {
	
	public List<Order> queryByCondition(Map<String, Object> map);
	
	public List<Order> queryAll();
	
	public List<Order> queryByJoin();
	
	public Order findByID(int ordid);
	
	public List<Supplier> querySupId();
	
	public List<Employee> queryEmpId();
	
	public boolean deleteById(int ordid);
	
	public boolean addOrder(Order order);
	
	public boolean updateOrder(Order order);
	
}
