package com.web.dao;

import java.util.List;

import com.web.entity.Registrationform;

public interface RegistrationformDao {

	public List<Registrationform> queryByCondition(Registrationform registrationform);
	
	public Registrationform findByID(int regid);
	
	public void deleteById(Registrationform registrationform);
	
	public void addReg(Registrationform registrationform);
	
	public void updateReg(Registrationform registrationform);
}
