package com.web.biz;

import java.util.List;

import com.web.entity.Registrationform;

public interface RegistrationformBiz {
	
	public List<Registrationform> queryByCondition(Registrationform registrationform);
	
	public Registrationform findByID(int regid);
	
	public boolean deleteById(Registrationform registrationform);
	
	public boolean addReg(Registrationform registrationform);
	
	public boolean updateReg(Registrationform registrationform);
	
}
