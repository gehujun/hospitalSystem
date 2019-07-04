package com.web.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.BedBiz;
import com.web.biz.impl.BedBizImpl;
import com.web.entity.Bed;
import com.web.entity.Employee;
import com.web.entity.Ward;
import com.web.util.JsonUtil;
import com.web.util.MyBatisUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/bed")
public class BedController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		  String method = req.getParameter("method");
		  
		  //实例化业务逻辑层
		  BedBiz bb = new BedBizImpl();
		  
		  
		  if("query".equals(method)){
			
			  
			  String wardname=req.getParameter("wardname");
			   
			  String bedrent =req.getParameter("bedrent");	  
			 
			  Bed bed=new Bed();
			  bed.setWardname(wardname);			  		  
			  if(bedrent!=null && bedrent.length()>0)
			  {
				  bed.setBedrent(Float.parseFloat(bedrent));
				  System.out.println("转换成功");
			  }
			  			  
			//调用业务逻辑层中的查询方法
			  List<Bed> list = bb.queryByCondition(bed);
			  
			  System.out.println(list.get(0).getEmpdoc().getEmpname());
			  System.out.println(list.get(0).getEmpnur().getEmpname());
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(list);
			  
			  System.out.println(json);
			  //
			  System.out.println("---------nihahhahhahhhhahh------------------");
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
	
		  } else if("sendUpdate".equals(method)){
			  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
			  String bedid = req.getParameter("bedid");
			
			 Bed bed =  bb.findById(Integer.parseInt(bedid));
			 
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(bed);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
	}else if("delete".equals(method)){
		  System.out.println("我是删除呀呀呀呀呀呀晕晕11111");
		  //删除
		  String bedid = req.getParameter("bedid");
		  System.out.println(bedid);
		  //调用业务逻辑层的修改方法
		  boolean flag =  bb.delete(Integer.parseInt(bedid));		
		  
		  System.out.println(flag);
		  
		  String json=JsonUtil.getJson(flag);
		  
		  System.out.println("我是删除呀呀呀呀呀呀晕晕");
		 //获取输出对象
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
		  out.write(flag ? "1" : "0");//输出
		  out.flush();//刷新
		  out.close();//关闭
	  }else if("update".equals(method)){
		  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
		  String bedid = req.getParameter("bedid");
		  String wardname = req.getParameter("wardname");
		  String bedrent = req.getParameter("bedrent");
		  String isemptybed = req.getParameter("isbedempty");
		  System.out.println("isemptybed--------------------------"+isemptybed);
		  String empiddoc = req.getParameter("empiddoc");
		  String empidnur = req.getParameter("empidnur");
		  
		  
		  Bed bed = new Bed();
		  
		  bed.setWardname(wardname);
		  bed.setIsemptybed(isemptybed);
		
		  bed.setBedrent(Float.parseFloat(bedrent));
		  bed.setIsdelete(1);
		  
		  if(StringUtil.isStringEmpty(bedid))
		  {
		  		bed.setBedid(Integer.parseInt(bedid));
		  }
		  
		  if(StringUtil.isStringEmpty(empiddoc))
		  {
		 		bed.setEmpiddoc(Integer.parseInt(empiddoc));
		  }
		  
		  if(StringUtil.isStringEmpty(empidnur))
		  {
		 		bed.setEmpidnur(Integer.parseInt(empidnur));
		  }
		  
		  //调用业务逻辑层的修改方法
		 boolean flag =  bb.update(bed);
		 
		 System.out.println(flag);
		 
		 //获取输出对象
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
		  out.write(flag ? "1" : "0");//输出
		  out.flush();//刷新
		  out.close();//关闭
	  	}else if("add".equals(method)){
			  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
	  		  
			  
			  String wardname = req.getParameter("wardname");
			  String bedrent = req.getParameter("bedrent");
			  String isemptybed = req.getParameter("isbedempty");
			  System.out.println("isemptybed--------------------------"+isemptybed);
			  String empiddoc = req.getParameter("empiddoc");
			  String empidnur = req.getParameter("empidnur");
			  
			  
			  Bed bed = new Bed();
			  
			  bed.setWardname(wardname);
			  bed.setIsemptybed(isemptybed);
			  if(StringUtil.isStringEmpty(bedrent))
				  bed.setBedrent(Float.parseFloat(bedrent));
			  bed.setIsdelete(1);
			  
		
			  
			  if(StringUtil.isStringEmpty(empiddoc))
			  {
			 		bed.setEmpiddoc(Integer.parseInt(empiddoc));
			  }
			  
			  if(StringUtil.isStringEmpty(empidnur))
			  {
			 		bed.setEmpidnur(Integer.parseInt(empidnur));
			  }
			  System.out.println("bbbbbbbbbbbbbbbbb");
			  
			  System.out.println(bed);
			  //调用业务逻辑层的修改方法
			  boolean flag =  bb.add(bed);
			 
			  System.out.println(flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
	}if ("empidSelect".equals(method)) {
		
		String empid = req.getParameter("empid");
		String empname = req.getParameter("empname");
		
		Employee employee = new Employee();
		
		if (empid != null && empid != "") {
			employee.setEmpid(Integer.parseInt(empid));
		}
		System.out.println("hello1111");
		
		employee.setEmpname(empname);
		
		
		List<Employee> list = bb.empidSelect();
		
		System.out.println("hello222222");
		
		String json = JsonUtil.getJson(list);
		System.out.println(json);
		PrintWriter out = resp.getWriter();
		resp.setContentType("UTF-8");
		out.write(json);
		out.flush();
		out.close();
	}if ("wardnameSelect".equals(method)) {
		
		String wardname = req.getParameter("wardname");
		
		Ward ward = new Ward();
		
		ward.setWardname(wardname);
		
		List<Ward> list = bb.wardnameSelect();
		
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
