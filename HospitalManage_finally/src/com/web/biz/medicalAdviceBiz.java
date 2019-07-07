package com.web.biz;

import java.util.List;

import com.web.entity.Medicaladvice;

public interface medicalAdviceBiz {
	public List<Medicaladvice> query(Medicaladvice medicaladvice);
	public boolean add(Medicaladvice medicaladvice);
	public Medicaladvice findById(Integer maId);
	
	public Integer findinpatId(Integer maId);
	
	
	public boolean update(Medicaladvice medicaladvice);
	
	public boolean delete(Integer maId);
	public List<Medicaladvice> maIdSelect();
}	
