package com.web.biz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Attendance;
import com.web.entity.Employee;
import com.web.entity.Workinghours;

public interface AttendanceBiz {
	
	public List<Attendance> queryByCondition(@Param("empname") String empname);
	
	public List<Attendance> queryAll();
	
	public List<Attendance> queryByJoin();

	public Attendance findByID(Attendance attendance);
	
	public List<Employee> queryEmp();
	
	public List<Attendance> queryAt();	
	
	public List<Workinghours> queryWt();
	
	public boolean deleteById(Attendance attendance);
	
	public boolean addAttendance(Attendance attendance);
	
	public boolean updateAttendance(Attendance attendance);
}
