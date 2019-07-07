package com.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Attendance;
import com.web.entity.Employee;
import com.web.entity.Workinghours;



public interface AttendanceDao {
	
	public List<Attendance> queryByCondition(@Param("empname") String empname);
	
	public List<Attendance> queryAll();
	
	public List<Attendance> queryByJoin();

	public Attendance findByID(Attendance attendance);
	
	public List<Employee> queryEmp();
	
	public List<Attendance> queryAt();	
	
	public List<Workinghours> queryWt();

	public void deleteById(Attendance attendance);
	
	public void addAttendance(Attendance attendance);
	
	public void updateAttendance(Attendance attendance);
}
