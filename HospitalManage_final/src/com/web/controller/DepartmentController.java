package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.el.lang.ELSupport;

import com.web.biz.DepartmentBiz;
import com.web.biz.impl.DepartmentBizImpl;
import com.web.entity.Department;
import com.web.entity.TDepartment;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

/**
 * 科室的控制层
 * @author ASUS
 *
 */
@WebServlet(urlPatterns="/department")
public class DepartmentController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		  String method = req.getParameter("method");
		  
		  //实例化业务逻辑层
		  DepartmentBiz db = new DepartmentBizImpl();
		  
		  //url:http://localhost:8080/HospitalManager/department?method=query
		  if("query".equals(method)){
			  //查询
			  
			  //接收前台传过来的参数
			  String deptName = req.getParameter("deptName");
			  String deptId = req.getParameter("deptId");
			  String deptPhone = req.getParameter("deptPhone");
			  
			  Department department = new Department();
			  department.setDeptname(deptName);
			  if(StringUtil.isStringEmpty(deptId))
				  department.setDeptid(Integer.parseInt(deptId));
			  if(StringUtil.isStringEmpty(deptPhone))
				  department.setDepttel(Integer.parseInt(deptPhone));
			  
			  //调用业务逻辑层中的查询方法
			  List<Department> list = db.queryByCondition(department);
			  
//			  //把数据传到页面上
//			  req.setAttribute("list", list);
//			  
//			  //转发 跳转页面
//			  req.getRequestDispatcher("listDepartment.jsp").forward(req, resp);
			  
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(list);
			  System.out.println(json);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("sendUpdate".equals(method)){
			  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
			  String deptId = req.getParameter("deptId");
			  
			  //调用业务逻辑层的查询方法
			  Department department =  db.findById(Integer.parseInt(deptId));
			  
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(department);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("update".equals(method)){
			  //修改
			  String deptId = req.getParameter("deptId");
			  String deptName = req.getParameter("deptName");
			  String deptAddress = req.getParameter("deptAddress");
			  String deptPhone = req.getParameter("deptPhone");
			  String userId = req.getParameter("userId");
			  
			  Department department = new Department();
			  
			  department.setDeptid(Integer.parseInt(deptId));
			  department.setDeptname(deptName);
			  department.setDeptadr(deptAddress);
			  department.setDepttel(Integer.parseInt(deptPhone));
			  
			  
			  
			  //调用业务逻辑层的修改方法
			 boolean flag =  db.updateDepartment(department);
			 
			 System.out.println(flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if(method.equals("delete")){
			  String deptId = req.getParameter("deptId");
				
			  Department department = new Department();
			  department.setDeptid(Integer.parseInt(deptId));
			  
			  boolean flag = db.deleteById(department);
			
			  System.out.println("delete result: "+flag);
				 
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if(method.equals("add")){
				
				String deptName = req.getParameter("deptName");
				String deptAddress = req.getParameter("deptAddress");
				String deptPhone = req.getParameter("deptPhone");
				
				Department department = new Department();
				
				department.setDeptname(deptName);
				department.setDeptadr(deptAddress);
				if(StringUtil.isStringEmpty(deptPhone));
					department.setDepttel(Integer.parseInt(deptPhone));
				
				
				boolean flag = db.addDepartment(department);
				
				
				System.out.println("add result: "+flag);
				 
				 //获取输出对象
				  PrintWriter out = resp.getWriter();
				  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
				  out.write(flag ? "1" : "0");//输出
				  out.flush();//刷新
				  out.close();//关闭
			} else if ("deptIdSelect".equals(method)) {
				String deptId = req.getParameter("deptId");
				String deptName = req.getParameter("deptName");
				
				Department department = new Department();
				if (deptId != null && deptId != "") {
					department.setDeptid(Integer.parseInt(deptId));
				}
//				department.setDeptId(Integer.parseInt(deptId));
				department.setDeptname(deptName);
				
				List<Department> list = db.deptIdSelect();
				String json = JsonUtil.getJson(list);
				System.out.println(json);
				PrintWriter out = resp.getWriter();
				resp.setContentType("UTF-8");
				out.write(json);
				out.flush();
				out.close();
			}
		  
		  
	}
	
}
