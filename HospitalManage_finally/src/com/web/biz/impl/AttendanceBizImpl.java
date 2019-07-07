package com.web.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import com.web.biz.AttendanceBiz;
import com.web.dao.AttendanceDao;
import com.web.entity.Attendance;
import com.web.entity.Employee;
import com.web.entity.Workinghours;
import com.web.util.MyBatisUtil;

public class AttendanceBizImpl implements AttendanceBiz {

	
	SqlSession sqlSession = MyBatisUtil.openSession();
	AttendanceDao attendanceDao = sqlSession.getMapper(AttendanceDao.class);
	
	
	@Override
	public List<Attendance> queryByCondition(@Param("empname") String empname) {
		return attendanceDao.queryByCondition(empname);
	}

	@Override
	public List<Attendance> queryAll() {
		return attendanceDao.queryAll();
	}

	@Override
	public List<Attendance> queryByJoin() {
		return attendanceDao.queryByJoin();
	}

	@Override
	public Attendance findByID(Attendance attendance) {
		// TODO Auto-generated method stub
		return attendanceDao.findByID(attendance);
	}

	@Override
	public boolean deleteById(Attendance attendance) {
		try {
			attendanceDao.deleteById(attendance);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAttendance(Attendance attendance) {
		try {
			attendanceDao.addAttendance(attendance);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateAttendance(Attendance attendance) {
		try {
			attendanceDao.updateAttendance(attendance);
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public List<Employee> queryEmp() {
		return attendanceDao.queryEmp();
	}

	@Override
	public List<Attendance> queryAt() {
		return attendanceDao.queryAt();
	}

	@Override
	public List<Workinghours> queryWt() {
		return attendanceDao.queryWt();
	}

}
