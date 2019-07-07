package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.OrderdetailBiz;
import com.web.biz.impl.OrderdetailBizImpl;
import com.web.entity.Orderdetail;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/orderdetail")
public class OrderdetailController extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		  String method = req.getParameter("method");
		  req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
		  
		  //实例化业务逻辑层
		  OrderdetailBiz db=new OrderdetailBizImpl();
		  
		  //url:http://localhost:8080/HospitalManager/orderdetail?method=query
		  if("query".equals(method)){
			  	//接收传过来的参数
			  
			  String ordid=req.getParameter("ordid");
			  String mediid=req.getParameter("mediid");
			  String detnum=req.getParameter("detnum");
			  String dettotalfee=req.getParameter("dettotalfee");
			  String detbatchnum=req.getParameter("detbatchnum");
			  
			  Orderdetail orderdetail=new Orderdetail();

			  
			 /* orderdetail.setDetid(detid);*/
			  if(StringUtil.isStringEmpty(ordid)){
				  orderdetail.setOrdid(Integer.parseInt(ordid));
			  }
			  
			  if(StringUtil.isStringEmpty(mediid)){
				  orderdetail.setMediid(Integer.parseInt(mediid));
			  }
			  
			  if(StringUtil.isStringEmpty(detnum)){
				  orderdetail.setDetnum(Integer.parseInt(detnum));
			  }
			  
			  if(StringUtil.isStringEmpty(dettotalfee)){
				  orderdetail.setDettotalfee(Float.parseFloat(dettotalfee));
			  }
			  
			  orderdetail.setDetbatchnum(detbatchnum);
			  
			  //调用业务逻辑层中的查询方法
			  List<Orderdetail> list=db.queryByCondition(orderdetail);
			  
//			  //把数据传到页面上
//			  req.setAttribute("list", list);
//			  
//			  //转发 跳转页面
//			  req.getRequestDispatcher("lisOrderdetail.jsp").forward(req, resp);
			  
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(list);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		  } else if("sendUpdate".equals(method)){
			  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
			  String detid = req.getParameter("detid");
			  
			  //调用业务逻辑层的查询方法
			  Orderdetail orderdetail =  db.findById(Integer.parseInt(detid));
			  
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(orderdetail);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("update".equals(method)){
			  //修改			  
			  String detid=req.getParameter("detid");
			  String ordid=req.getParameter("ordid");
			  String mediid=req.getParameter("mediid");
			  String detnum=req.getParameter("detnum");
			  String dettotalfee=req.getParameter("dettotalfee");
			  String detbatchnum=req.getParameter("detbatchnum");
			  
			  Orderdetail orderdetail = new Orderdetail();

			  if(StringUtil.isStringEmpty(detid)){
				  orderdetail.setDetid(Integer.parseInt(detid));
			  }
			  if(StringUtil.isStringEmpty(ordid)){
				  orderdetail.setOrdid(Integer.parseInt(ordid));
			  }
			  
			  if(StringUtil.isStringEmpty(mediid)){
				  orderdetail.setMediid(Integer.parseInt(mediid));
			  }
			  
			  if(StringUtil.isStringEmpty(detnum)){
				  orderdetail.setDetnum(Integer.parseInt(detnum));
			  }
			  
			  if(StringUtil.isStringEmpty(dettotalfee)){
				  orderdetail.setDettotalfee(Float.parseFloat(dettotalfee));
			  }
			  
			  orderdetail.setDetbatchnum(detbatchnum);
			  
			  //调用业务逻辑层的修改方法
			 boolean flag =  db.update(orderdetail);
			 
			 System.out.println(flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("delete".equals(method)) {
				//删除
				String detid = req.getParameter("detid");
				System.out.println(detid);
				//调用业务逻辑层的查询方法
				boolean flag =  db.delete(Integer.parseInt(detid));
				System.out.println(flag);
				
				String json = JsonUtil.getJson(flag); 
				//获取输出对象
				PrintWriter out = resp.getWriter();
				resp.setContentType("UTF-8");
				out.write(json);//输出
				out.flush();//刷新
				out.close();//关闭
			}else if("add".equals(method)){
				  //修改			  
				  String detid=req.getParameter("detid");
				  String ordid=req.getParameter("ordid");
				  String mediid=req.getParameter("mediid");
				  String detnum=req.getParameter("detnum");
				  String dettotalfee=req.getParameter("dettotalfee");
				  String detbatchnum=req.getParameter("detbatchnum");
				  
				  
				  System.out.println(ordid);
				  System.out.println(mediid);
				  Orderdetail orderdetail = new Orderdetail();

				  if(StringUtil.isStringEmpty(detid)){
					  orderdetail.setDetid(Integer.parseInt(detid));
				  }
				  if(StringUtil.isStringEmpty(ordid)){
					  orderdetail.setOrdid(Integer.parseInt(ordid));
				  }
				  
				  if(StringUtil.isStringEmpty(mediid)){
					  orderdetail.setMediid(Integer.parseInt(mediid));
				  }
				  
				  if(StringUtil.isStringEmpty(detnum)){
					  orderdetail.setDetnum(Integer.parseInt(detnum));
				  }
				  
				  if(StringUtil.isStringEmpty(dettotalfee)){
					  orderdetail.setDettotalfee(Float.parseFloat(dettotalfee));
				  }
				  
				  orderdetail.setDetbatchnum(detbatchnum);
				  System.out.println("tianjiahou  "+orderdetail.getDetbatchnum());
				  //调用业务逻辑层的修改方法
				 boolean flag =  db.add(orderdetail);
				 
				 System.out.println("添加状态："+flag);
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
