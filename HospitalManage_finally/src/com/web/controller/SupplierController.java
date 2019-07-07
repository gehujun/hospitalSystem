package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.web.biz.SupplierBiz;
import com.web.biz.impl.SupplierBizImpl;
import com.web.entity.Supplier;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;


@WebServlet(urlPatterns="/supplier")
public class SupplierController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		  String method = req.getParameter("method");
		  
		  //实例化业务逻辑层
		  SupplierBiz db = new SupplierBizImpl();
		  
		  //url:http://localhost:8080/HospitalManager/supplier?method=query
		  if("query".equals(method)){
			  	//接收传过来的参数
			  String supname = req.getParameter("supname");
			  String supaddr = req.getParameter("supaddr");
			  String suptel = req.getParameter("suptel");
			  String supcreditconditions = req.getParameter("supcreditconditions");
			  
			  Supplier supplier = new Supplier();
			  
			
			  supplier.setSupname(supname);
			  supplier.setSupaddr(supaddr);
			  supplier.setSuptel(suptel);
			  supplier.setSupcreditconditions(supcreditconditions);
			  
			  //调用业务逻辑层中的查询方法
			  List<Supplier> list = db.queryByCondition(supplier);
			  
//			  //把数据传到页面上
//			  req.setAttribute("list", list);
//			  
//			  //转发 跳转页面
//			  req.getRequestDispatcher("listDepartment.jsp").forward(req, resp);
			  
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(list);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  
			  System.out.println("emmmmmmmm");
			  System.out.println(json);
			  out.flush();//刷新
			  out.close();//关闭
			 
		  } else if("sendUpdate".equals(method)){
			  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
			  String supId = req.getParameter("supid");
			  System.out.println(supId);
			  
			  //调用业务逻辑层的查询方法
			  Supplier supplier =  db.findById(Integer.parseInt(supId));
			  
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(supplier);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  System.out.println("jhhhhhhhhm");
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("update".equals(method)){
			  //修改
			 
			  String supid = req.getParameter("supid");
			  
			  System.out.println("supid: "+supid);
			  String supname = req.getParameter("supname");
			  String supaddr = req.getParameter("supaddr");
			  String suptel = req.getParameter("suptel");
			  String supcreditconditions= req.getParameter("supcreditconditions");
			  
			  
			  Supplier supplier=new Supplier();
			  if (StringUtil.isStringEmpty(supid)) {
				  supplier.setSupid(Integer.parseInt(supid));
			  }
			  
			  supplier.setSupaddr(supaddr);			  	  
			  supplier.setSupname(supname);
			  supplier.setSuptel(suptel);
			  supplier.setIsdelete(0);
			  supplier.setSupcreditconditions(supcreditconditions);
			  
			  //调用业务逻辑层的修改方法
			 boolean flag =  db.update(supplier);
			 
			 System.out.println(flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("delete".equals(method)) {
				//删除
				String supId = req.getParameter("supid");
				System.out.println(supId);
				//调用业务逻辑层的查询方法
				boolean flag =  db.delete(Integer.parseInt(supId));
				System.out.println(flag);
				
				String json = JsonUtil.getJson(flag); 
				//获取输出对象
				PrintWriter out = resp.getWriter();
				resp.setContentType("UTF-8");
				out.write(json);//输出
				out.flush();//刷新
				out.close();//关闭
			}else if(method.equals("add")){
				
			    String supid = req.getParameter("supid");  
				
			    String supname = req.getParameter("supname");
			    String supaddr = req.getParameter("supaddr");
			    String suptel = req.getParameter("suptel");
			    String supcreditconditions= req.getParameter("supcreditconditions");
				Supplier supplier=new Supplier();
				
				System.out.println("wwwwwwssssshm");
				
				if (StringUtil.isStringEmpty(supid)) {
					  supplier.setSupid(Integer.parseInt(supid));
				  }
				
				supplier.setSupaddr(supaddr);			  	  
				supplier.setSupname(supname);
				supplier.setSuptel(suptel);
				//supplier.setIsdelete(0);
				supplier.setSupcreditconditions(supcreditconditions);
				
				boolean flag =  db.add(supplier);
				
				System.out.println("add result: "+flag);
				
				String json = JsonUtil.getJson(flag);
				 //获取输出对象
				  PrintWriter out = resp.getWriter();
				  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
				  out.write(json);//输出
				  out.flush();//刷新
				  out.close();//关闭
			}
		  	  		  
	}
	
	
}
