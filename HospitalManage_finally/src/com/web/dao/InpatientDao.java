package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Inpatient;

public interface InpatientDao {
	//根据inpatId查找住院病人的信息 入院科室 入院时间 床号 状态
	//根据病人id 找姓名 性别 年龄 电话 联系人姓名 联系人电话
	public List<Inpatient> query(Inpatient inpatient); //单个查询病人
	
	
	public List<Inpatient> queryAll();
	
	public Inpatient findById(Integer inpatId);
	
	//删除住院病人 根据点击传过来的id删除病人
	//public boolean deleteInpatient(Integer inpatId);
	
	//修改住院病人信息
	public boolean updateInpatient(Inpatient inpatient);
	
	public Integer findPatId(Integer inpatId);
	
	public boolean updateIsDelete(Integer inpatId);
	
	public List<Inpatient> inpatIdSelect();
	
	
}
