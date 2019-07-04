package com.web.biz;

import java.util.List;
import java.util.Map;

import com.web.entity.Medicaladvice;
import com.web.entity.Surgerroom;
import com.web.entity.Surgeryproject;

public interface SurgeryprojectBiz {

	public List<Surgeryproject> queryByCondition(Map<String, Object> map);
	
	public List<Surgerroom> querySurRoom();
	
	public boolean delete(Integer surid);
	
	public List<Medicaladvice> queryMA();
	
	public boolean add (Surgeryproject surgeryproject);
	
	public Surgeryproject findByID(Integer surid);
	
	public boolean update(Surgeryproject surgeryproject);
}
