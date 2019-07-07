package com.web.biz;

import java.util.List;
import java.util.Map;

import com.web.dao.MedicalhistoryDao;
import com.web.entity.Medicalhistory;

public interface MedicalhistoryBiz {

	public List<Medicalhistory> queryByCondition(Map<String, Object> map);
	
	public boolean deleteById(Medicalhistory medicalhistory); 
	
	public boolean addMedh(Medicalhistory medicalhistory);
	
	public Medicalhistory findByID(int medhid);
	
	public boolean updateMedh(Medicalhistory medicalhistory);
}
