package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Patient;

public interface PatientDao {
	public List<Patient> queryByCondition(Patient patient);
	
	public void deleteById(Patient patient);
	
	public Patient findById(Integer id);
	
	public void updatePatient(Patient patient);
	
	public void addPatient(Patient patient);
}
