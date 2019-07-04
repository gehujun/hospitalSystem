package com.web.dao;

import java.util.List;

import com.web.entity.Ward;



public interface wardDao {
	public List<Ward> query(Ward ward);
	public boolean add(Ward ward);
	public Ward findById(String wardName);
	
	
	public boolean update(Ward ward);
	
	public boolean delete(String wardName);
}
