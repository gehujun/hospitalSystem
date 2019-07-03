package com.web.biz;

import java.util.List;

import com.web.entity.Patient;

public interface PatientBiz {
	public List<Patient> queryByCondition(Patient patient);
	
	public boolean deleteById(Patient patient);
	
	public Patient findById(Integer id);
	
	public boolean updatePatient(Patient patient);
	
	public boolean addPatient(Patient patient);
}
