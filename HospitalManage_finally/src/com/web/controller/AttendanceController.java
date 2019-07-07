package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.impl.AttendanceBizImpl;
import com.web.biz.AttendanceBiz;
import com.web.entity.Attendance;
import com.web.entity.Department;
import com.web.entity.Employee;
import com.web.entity.Facilities;
import com.web.entity.Facilityrepair;
import com.web.entity.Workinghours;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/attendance")
public class AttendanceController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		String method = req.getParameter("method");
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		AttendanceBiz aBiz=new AttendanceBizImpl();
		
		
		if(method.equals("query")){
			List<Attendance> attendancesList = aBiz.queryAll();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(attendancesList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}else if(method.equals("queryEmpPartly")){
		
		List<Employee> empList = aBiz.queryEmp();
		
		List<Attendance> aList = aBiz.queryAt();
		
//		System.out.println("fcList: " + fcList.size());
//		System.out.println("frList: " + frList.size());
		
		for(int i = 0; i < aList.size(); ++i)
		{
			for(int j = 0; j < empList.size(); ++j)
			{
				if(aList.get(i).getAttenid() == empList.get(j).getEmpid())
				{
					empList.remove(j);
					if(j != 0)
						j--;
				}
			}
		}
		
		String json = new String();
		
		if(empList.isEmpty())
		{
			json = JsonUtil.getJson(null);
		}
		else
		{
			json = JsonUtil.getJson(empList);
		}
		
//		System.out.println("json: " + fcList.get(0).getFaciid());
		
		//把数据转换为json字符串
		
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("UTF-8");
		out.write(json);
		out.flush();
		out.close();
//		System.out.println(json);
	}
	else if(method.equals("queryWTPartly")){
		
		List<Workinghours> wtList = aBiz.queryWt();
		
		List<Attendance> aList = aBiz.queryAt();
		
		
		for(int i = 0; i < aList.size(); ++i)
		{
			for(int j = 0; j < wtList.size(); ++j)
			{
				if(aList.get(i).getWorktime().compareTo(wtList.get(j).getWorktime()) == 0)
				{
//					System.out.println("same");
					wtList.remove(j);
					if(j != 0)
						j--;
				}
			}
		}
		
//		if(wtList.isEmpty())
//		{
//			System.out.println("empty");
//		}
	
		String json = JsonUtil.getJson(wtList);
		//把数据转换为json字符串

		
		PrintWriter out = resp.getWriter();
		resp.setContentType("UTF-8");
		out.write(json);
		out.flush();
		out.close();
	}
	else if(method.equals("queryByJoin")){
		
		List<Attendance> aList = aBiz.queryByJoin();
		
		//把数据转换为json字符串
		String json = JsonUtil.getJson(aList);
		
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("UTF-8");
		out.write(json);
		out.flush();
		out.close();
		System.out.println(json);
		
	}
	else if(method.equals("queryByCondition")){
		
		String empname = req.getParameter("empname");
		
//		System.out.println("empname: "+empname);
		
		List<Attendance> aList = aBiz.queryByCondition(empname);
		
//		System.out.println("abiz: " + aList.get(0).getEmp());
		
		//把数据转换为json字符串
		String json = JsonUtil.getJson(aList);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("UTF-8");
		out.write(json);
		out.flush();
		out.close();
	}
	else if(method.equals("sendUpdate")){
		
		String attenid = req.getParameter("attenid");
		String worktime = req.getParameter("worktime");
		
		Attendance attendance = new Attendance();
		
		attendance.setAttenid(Integer.parseInt(attenid));
		attendance.setWorktime(StringUtil.stringToDate(worktime));
		
		
		Attendance attendanceBack = aBiz.findByID(attendance);
		
		String json = JsonUtil.getJson(attendanceBack);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("UTF-8");
		out.write(json);
		out.flush();
		out.close();
	}
	else if(method.equals("delete")){
		String attenid = req.getParameter("attenid");
		String worktime = req.getParameter("worktime");
		
		Attendance attendance = new Attendance();
		
		attendance.setAttenid(Integer.parseInt(attenid));
		attendance.setWorktime(StringUtil.stringToDate(worktime));
		
		boolean flag = aBiz.deleteById(attendance);
		
		
		System.out.println("delete result: "+flag);
		 
		 //获取输出对象
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
		  out.write(flag ? "1" : "0");//输出
		  out.flush();//刷新
		  out.close();//关闭
		
	}
	else if(method.equals("update")){
		//获取用户输入的数据
		String attenid = req.getParameter("attenid");
		
		String worktime = req.getParameter("worktime");
		String isattendant = req.getParameter("isattendant");
		
		Attendance attendance = new Attendance();
		
		if(StringUtil.isStringEmpty(attenid))
			attendance.setAttenid(Integer.parseInt(attenid));
		if(StringUtil.isStringEmpty(isattendant))
			attendance.setIsattendant(Integer.parseInt(isattendant));
		
		attendance.setWorktime(StringUtil.stringToDate("2019-01-02"));

		attendance.setIsdelete(1);
		
		
		boolean flag = aBiz.updateAttendance(attendance);
		
		System.out.println("update result: "+flag);
		 
		 //获取输出对象
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
		  out.write(flag ? "1" : "0");//输出
		  out.flush();//刷新
		  out.close();//关闭

		}
		else if(method.equals("add")){
		
		String attenid = req.getParameter("empname");
		
//		System.out.println("faciid: "+faciid);

		String worktime = req.getParameter("worktime");
		String isattendant = req.getParameter("isattendant");
		
		Attendance attendance = new Attendance();
		
		if(StringUtil.isStringEmpty(attenid))
			attendance.setAttenid(Integer.parseInt(attenid));
		if(StringUtil.isStringEmpty(worktime))
			attendance.setWorktime(StringUtil.stringToDate(worktime));
		if(StringUtil.isStringEmpty(isattendant))
			attendance.setIsattendant(Integer.parseInt(isattendant));
		

//		attendance.setWorktime(StringUtil.stringToDate("2019-01-02"));
		
		
		boolean flag = aBiz.addAttendance(attendance);
		System.out.println("add result: "+flag);
		 
		 //获取输出对象
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
		  out.write(flag ? "1" : "0");//输出
		  out.flush();//刷新
		  out.close();//关闭

	}
}

}
