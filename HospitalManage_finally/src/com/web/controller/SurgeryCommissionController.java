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

import com.web.biz.SurgeryCommissionBiz;
import com.web.biz.impl.SurgeryCommissionBizImpl;
import com.web.entity.Employee;
import com.web.entity.Medicinelibrary;
import com.web.entity.Surgerycommission;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@WebServlet(urlPatterns="/surgeryCommission")
public class SurgeryCommissionController extends HttpServlet {
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
		SurgeryCommissionBiz scBiz=new SurgeryCommissionBizImpl();

		if(method.equals("query")){

			List<Surgerycommission> scList = scBiz.query();
			
			System.out.println(scList);
		
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(scList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}else if(method.equals("queryByJoin")){
			
			List<Employee> eList = scBiz.queryByJoin();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(eList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println(json);
			
		}else if(method.equals("querySCEmpId")){
			
			List<Employee> EmployeeList = scBiz.querySCEmpId();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(EmployeeList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}else if(method.equals("queryByCondition")){
			
			String surctype = req.getParameter("surctype");
			String empname = req.getParameter("empname");
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("surctype", surctype);
			map.put("empname", empname);
			
			List<Surgerycommission> scList = scBiz.queryByCondition(map);
			
			System.out.println("List:  "+scList);
			//把数据转换为json字符串
			String json = JsonUtil.getJson(scList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}else if(method.equals("sendUpdate")){
			
			String empid = req.getParameter("empid");
			System.out.println("empid: "+empid);
			
			Surgerycommission sc = scBiz.findById(Integer.parseInt(empid));
	
			String json = JsonUtil.getJson(sc);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			
			
		}else if(method.equals("delete")){
			String empid = req.getParameter("empid");
//			System.out.println(empid);
			Surgerycommission sc=new Surgerycommission();
			if(StringUtil.isStringEmpty(empid)){
				sc.setSurcid(Integer.parseInt(empid));
			}
			System.out.println("epmid: " + sc.getSurcid());
			boolean flag = scBiz.deleteById(sc);
			
			System.out.println("delete result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
			
		}else if(method.equals("update")){
			//获取用户输入的数据
			String surcid=req.getParameter("empid");
			String surctype = req.getParameter("surctype");
			String surcpushfee = req.getParameter("surcpushfee");
			Surgerycommission suc=new Surgerycommission();
			if(StringUtil.isStringEmpty(surcid))
				suc.setSurcid(Integer.parseInt(surcid));
			
			if(StringUtil.isStringEmpty(surctype))
				suc.setSurctype(surctype);
			if(StringUtil.isStringEmpty(surcpushfee))
				suc.setSurcpushfee(Float.parseFloat(surcpushfee));
			
			suc.setIsdelete(1);
			
			
			boolean flag = scBiz.updateSurgerycommission(suc);
			
			System.out.println("update result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
			
		}else if(method.equals("add")){
			String surcid = req.getParameter("empid");
			String surctype = req.getParameter("surctype");
			String surcpushfee = req.getParameter("surcpushfee");
			
			Surgerycommission sc=new Surgerycommission();
			
			sc.setSurcid(Integer.parseInt(surcid));
			sc.setSurctype(surctype);
			sc.setSurcpushfee(Float.parseFloat(surcpushfee));
			sc.setIsdelete(1);
			boolean flag=scBiz.addSurgerycommission(sc);
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
