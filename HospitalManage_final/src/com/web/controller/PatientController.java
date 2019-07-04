package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.PatientBiz;
import com.web.biz.impl.PatientBizImpl;
import com.web.entity.Patient;
import com.web.entity.TDepartment;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/patient")
public class PatientController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String method = req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		PatientBiz patientBiz = new PatientBizImpl();
		
		if(method.equals("query")){
			
			String patId = req.getParameter("patId");
			String patName = req.getParameter("patName");
			
			Patient patient = new Patient();
			if(StringUtil.isStringEmpty(patId))
				patient.setPatid(Integer.parseInt(patId));
			if(StringUtil.isStringEmpty(patName))
				patient.setPatname(patName);
			
			
			List<Patient> pList = patientBiz.queryByCondition(patient);
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(pList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println("query result: "+"true"+json);
			
		}else if(method.equals("delete")){
			
			String patId = req.getParameter("patid");
			Patient patient = new Patient();
			patient.setPatid(Integer.parseInt(patId));

			boolean flag = patientBiz.deleteById(patient);
			
			System.out.println("delete result: "+flag);
			 
		 //获取输出对象
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
		  out.write(flag ? "1" : "0");//输出
		  out.flush();//刷新
		  out.close();//关闭
		}else if(method.equals("sendUpdate")){
			String patId = req.getParameter("patid");
			Patient patient = patientBiz.findById(Integer.parseInt(patId));
			String json = JsonUtil.getJson(patient);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			
		}else if(method.equals("update")){
			String patId = req.getParameter("patid");
			String patName = req.getParameter("patName");
			String patSex = req.getParameter("patSex");
			String patAge = req.getParameter("patAge");
			
			Patient patient = new Patient();
			
			patient.setPatid(Integer.parseInt(patId));
			patient.setPatname(patName);
			patient.setPatsex(patSex);
			patient.setPatage(Integer.parseInt(patAge));
			
			boolean flag = patientBiz.updatePatient(patient);
			System.out.println("update result: "+flag);
			 
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		}else if(method.equals("add")){
			
			String patName = req.getParameter("patName");
			String patSex = req.getParameter("patSex");
			String patAge = req.getParameter("patAge");
			
			Patient patient = new Patient();
			
			patient.setPatname(patName);
			patient.setPatsex(patSex);
			patient.setPatage(Integer.parseInt(patAge));
			
			boolean flag = patientBiz.addPatient(patient);
			
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
