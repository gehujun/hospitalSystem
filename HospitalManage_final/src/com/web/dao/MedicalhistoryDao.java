package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.entity.Medicalhistory;

public interface MedicalhistoryDao {
	public List<Medicalhistory> queryByCondition(Map<String, Object> map);
	
	public void deleteById(Medicalhistory medicalhistory); 
	
	public void addMedh(Medicalhistory medicalhistory);
	
	public Medicalhistory findByID(int medhid);
	
	public void updateMedh(Medicalhistory medicalhistory);
 }
