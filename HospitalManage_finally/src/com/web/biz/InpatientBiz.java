package com.web.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Inpatient;

public interface InpatientBiz {
	public List<Inpatient> query(Inpatient inpatient); //单个查询病人
	
	public List<Inpatient> queryAll();
	
	public Inpatient findById(Integer inpatId);
	
	public boolean updateInpatient(Inpatient inpatient);
	
	public Integer findPatId(Integer inpatId);
	
	public boolean updateIsDelete(Integer inpatId);
	
	public List<Inpatient> inpatIdSelect();
	
	
}
