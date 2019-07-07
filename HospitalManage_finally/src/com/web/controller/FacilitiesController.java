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

import com.web.biz.DepartmentBiz;
import com.web.biz.FacilitiesBiz;
import com.web.biz.impl.DepartmentBizImpl;
import com.web.biz.impl.FacilitiesBizImpl;
import com.web.entity.Department;
import com.web.entity.Facilities;
import com.web.entity.TDepartment;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/facilities")
public class FacilitiesController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String method = req.getParameter("method");
		
		FacilitiesBiz facilitiesBiz=new FacilitiesBizImpl();
		if(method.equals("query")){
			List<Facilities> fList=facilitiesBiz.query();
			System.out.println(fList);
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(fList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();	
			System.out.println("query result: "+"true");
		}else if(method.equals("queryByCondition")){
			String facifirm = req.getParameter("facifirm");
			String faciname = req.getParameter("faciname");
			Map<String, Object> map = new HashMap<>();
			
			map.put("facifirm", facifirm);
			map.put("faciname", faciname);
			List<Facilities> fList=facilitiesBiz.queryByCondition(map);
			String json = JsonUtil.getJson(fList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}else if(method.equals("queryByJoin")){
			List<Department> fList=facilitiesBiz.queryByJoin();
			String json = JsonUtil.getJson(fList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			
		}else if(method.equals("sendUpdate")){
			String faciId = req.getParameter("faciid");
			Facilities facilities=facilitiesBiz.findById(Integer.parseInt(faciId)); 
			
			String json = JsonUtil.getJson(facilities);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			
		}else if(method.equals("update")){
			String faciId = req.getParameter("faciid");
			String faciName = req.getParameter("faciname");
			String faciPrice = req.getParameter("faciprice");
			String faciNum = req.getParameter("facinum");
			String faciFirm = req.getParameter("facifirm");
			String faciDeptId = req.getParameter("deptid");
			
			Facilities facilities=new Facilities();
			if(StringUtil.isStringEmpty(faciId))
				facilities.setFaciid(Integer.parseInt(faciId));
			if(StringUtil.isStringEmpty(faciName))
				facilities.setFaciname(faciName);
			if(StringUtil.isStringEmpty(faciPrice))
				facilities.setFaciprice(Float.parseFloat(faciPrice));
			if(StringUtil.isStringEmpty(faciNum))
				facilities.setFacinum(Integer.parseInt(faciNum));
			if(StringUtil.isStringEmpty(faciFirm))
				facilities.setFacifirm(faciFirm);
			if(StringUtil.isStringEmpty(faciDeptId))
				facilities.setFacideptid(Integer.parseInt(faciDeptId));
			facilities.setIsdelete(1);
			
		//	System.out.println("facideptid: "+faciDeptId);
			
			boolean flag=facilitiesBiz.updateFacilities(facilities);
			 
			System.out.println("update result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
			
		}else if(method.equals("delete")){
			
			String faciId = req.getParameter("faciid");
			
			Facilities facilities=new Facilities();
			facilities.setFaciid(Integer.parseInt(faciId));

			System.out.println(facilities.getFaciid());
			
			boolean flag=facilitiesBiz.deleteById(facilities);

			
			System.out.println("delete result: "+flag);
			 
		 //获取输出对象
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
		  out.write(flag ? "1" : "0");//输出
		  out.flush();//刷新
		  out.close();//关闭
		}else if(method.equals("add")){
			
			//String faciId = req.getParameter("faciid");
			String faciName = req.getParameter("faciname");
			String faciPrice = req.getParameter("faciprice");
			String faciNum = req.getParameter("facinum");
			String faciFirm = req.getParameter("facifirm");
			String faciDeptId = req.getParameter("deptname");
			
			Facilities facilities=new Facilities();
			
			System.out.println("deptid:"+faciDeptId);
		//	facilities.setFaciid(Integer.parseInt(faciId));
			facilities.setFaciname(faciName);
			facilities.setFaciprice(Float.parseFloat(faciPrice));
			facilities.setFacinum(Integer.parseInt(faciNum));
			facilities.setFacifirm(faciFirm);
			facilities.setFacideptid(Integer.parseInt(faciDeptId));
			facilities.setIsdelete(1);
			
			
			boolean flag=facilitiesBiz.addFacilities(facilities);

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
