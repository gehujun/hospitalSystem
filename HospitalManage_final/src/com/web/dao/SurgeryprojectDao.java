package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.entity.Medicaladvice;
import com.web.entity.Surgerroom;
import com.web.entity.Surgeryproject;

public interface SurgeryprojectDao {

	public List<Surgeryproject> queryByCondition(Map<String, Object> map);
	
	public List<Surgerroom> querySurRoom();
	
	public List<Medicaladvice> queryMA();
	
	public void delete(Integer surid);
	
	public void add (Surgeryproject surgeryproject);
	
	public Surgeryproject findByID(Integer surid);
	
	public void update(Surgeryproject surgeryproject);
}
