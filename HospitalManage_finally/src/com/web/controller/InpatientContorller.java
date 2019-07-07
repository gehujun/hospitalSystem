package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.InpatientBiz;
import com.web.biz.PatientBiz;
import com.web.biz.impl.InpatientBizImpl;
import com.web.biz.impl.PatientBizImpl;
import com.web.entity.Department;
import com.web.entity.Inpatient;
import com.web.entity.Patient;
import com.web.util.JsonUtil;
@WebServlet(urlPatterns="/inpatient")
public class InpatientContorller extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getParameter("method");
//		System.out.println("hello");
		InpatientBiz ib = new InpatientBizImpl();
		PatientBiz pb = new PatientBizImpl();
		System.out.println("hello");
		//http://localhost:8080/moduledemo/inpatient?method=searchall
		if ("query".equals(method)) {
			
			String inpatId = req.getParameter("inpatId");
			String deptId = req.getParameter("deptId");
			String bedId = req.getParameter("bedId");
			Inpatient inpatient = new Inpatient();
			if (bedId != null && bedId != "") {
				inpatient.setBedid(Integer.parseInt(bedId));
			}
			
			if (deptId != null && deptId != "") {
				inpatient.setDeptid(Integer.parseInt(deptId));
			}
			if (inpatId != null && inpatId != "") {
				inpatient.setInpatid(Integer.parseInt(inpatId));
			}
			
			
			List<Inpatient> list = ib.query(inpatient);
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(list);
			
			System.out.println(json);
//			System.out.println("hello");
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("sendUpdate".equals(method)) {
			
			String inpatId = req.getParameter("inpatId");
//			System.out.println(inpatId);
			Inpatient inpatient = new Inpatient();
			
			if (inpatId != null && inpatId != "") {
				inpatient.setInpatid(Integer.parseInt(inpatId));
			}
			List<Inpatient> list = ib.query(inpatient);
			String json = JsonUtil.getJson(list); 
//			System.out.println("sendUpdate2");
//			System.out.println(json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
//			System.out.println("sendUpdate2");
			out.flush();//刷新
			out.close();//关闭
		} else if ("update".equals(method)) {
			
			String inpatId = req.getParameter("inpatId");
			String patName = req.getParameter("patName");
			
			String patSex = req.getParameter("patSex");
			
			String patAge = req.getParameter("patAge");
			String patTel = req.getParameter("patTel");
			String patLinkmanName = req.getParameter("patLinkmanName");
			String patLinkmanTel = req.getParameter("patLinkmanTel");
			String deptId = req.getParameter("deptId");
			String bedId = req.getParameter("bedId");
			String state = req.getParameter("state");
//			System.out.println(bedId);
//			System.out.println("sendUpdate2");
			Integer patId = -1;
			if (inpatId != null && inpatId != "") {
				patId = ib.findPatId(Integer.parseInt(inpatId));
			}
			
			System.out.println("state: " + state);
//			if (state == "住院") {
//				state = "1";
//			}
//			else if(state == "出院"){
//				state = "0";
//			}
//			System.out.println("state: " + state);
			Inpatient inpatient = new Inpatient();
			if (inpatId != null && inpatId != "") {
				inpatient.setInpatid(Integer.parseInt(inpatId));
			}
			if (deptId != null && deptId != "") {
				inpatient.setDeptid(Integer.parseInt(deptId));
			}
			if (bedId != null && bedId != "") {
				inpatient.setBedid(Integer.parseInt(bedId));
			}
			
			inpatient.setState(state);
			boolean flag = ib.updateInpatient(inpatient);
			
			
			Patient patient = new Patient();
			if (patAge != null && patAge != "") {
				patient.setPatage(Integer.parseInt(patAge));
			}
			
			patient.setPatid(patId);
			patient.setPatname(patName);
			patient.setPattel(patTel);
			patient.setPatsex(patSex);
			patient.setPatlinkmanname(patLinkmanName);
			patient.setPatlinkmantel(patLinkmanTel);
			
			System.out.println("patSex:----------?????????"+patient.getPatsex());
			
			boolean mark = pb.updatePatient(patient);
			String json = JsonUtil.getJson(flag); 
//			System.out.println(mark);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
			
		} else if ("delete".equals(method)) {
			String inpatId = req.getParameter("inpatId");
//			Integer patId = ib.findPatId(inpatId);
			boolean flag = false;
			if (inpatId != null && inpatId != "") {
				flag = ib.updateIsDelete(Integer.parseInt(inpatId));
			}
			
			String json = JsonUtil.getJson(flag); 
			System.out.println(flag);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("inpatIdSelect".equals(method)) {
			List<Inpatient> list = ib.inpatIdSelect();
			String json = JsonUtil.getJson(list);
			System.out.println("inpatId结果:"+json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		}

	}
}
