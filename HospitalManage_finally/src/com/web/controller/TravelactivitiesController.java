package com.web.controller;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.TravelactivitiesBiz;
import com.web.biz.impl.TravelactivitiesImpl;
import com.web.entity.Travelactivities;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/travelactivities")
public class TravelactivitiesController extends HttpServlet {

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
		  TravelactivitiesBiz db = new TravelactivitiesImpl();
		  
		  //url:http://localhost:8080/HospitalManager/travelactivities?method=query
		  if("query".equals(method)){
			  	//接收传过来的参数
			  String traname = req.getParameter("traname");
			  String tradest = req.getParameter("tradest");
			  String trastart = req.getParameter("trastart");
			  String tracontent = req.getParameter("tracontent");
			  String trafee = req.getParameter("trafee");
			  String tradate= req.getParameter("tradate");
			  
			  			
			  Travelactivities travelactivities=new Travelactivities();
			  travelactivities.setTraname(traname);
			  travelactivities.setTradest(tradest);
			  travelactivities.setTrastart(trastart);
			  travelactivities.setTracontent(tracontent);
			  if(StringUtil.isStringEmpty(trafee)) {
				  travelactivities.setTrafee(Float.parseFloat(trafee));
			  }
			  
			  
			  if(StringUtil.isStringEmpty(tradate)){

					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

					try {
						//doctoradvice.setDocadate(sdf.parse(docadate));
						travelactivities.setTradate(sdf.parse(tradate));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			  
			  
			  //调用业务逻辑层中的查询方法
			  List<Travelactivities> list = db.queryByCondition(travelactivities);
			  
//			  //把数据传到页面上
//			  req.setAttribute("list", list);
//			  
//			  //转发 跳转页面
//			  req.getRequestDispatcher("listtravelactivities.jsp").forward(req, resp);
			  
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
			  String traid = req.getParameter("traid");
			  
			  //调用业务逻辑层的查询方法
			  Travelactivities travelactivities = new Travelactivities();
			  if (traid != null && traid != "") {
				  travelactivities =  db.findById(Integer.parseInt(traid));
			  }
			  
			  
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(travelactivities);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("update".equals(method)){
			  //修改
			  String traid = req.getParameter("traid");
			  String traname = req.getParameter("traname");
			  String tradest = req.getParameter("tradest");
			  String trastart = req.getParameter("trastart");
			  String tracontent = req.getParameter("tracontent");
			  String trafee= req.getParameter("trafee");
			  String tradate= req.getParameter("tradate");
			  
			  Travelactivities travelactivities = new Travelactivities();
			  travelactivities.setTraname(traname);
			  travelactivities.setTrastart(trastart);
			  if (traid != null && traid != "") {
				  travelactivities.setTraid(Integer.parseInt(traid));
			  }
		
			  
			  travelactivities.setTradest(tradest);
			  travelactivities.setTracontent(tracontent);
			  //travelactivities.setIsdelete(0);
			  if(StringUtil.isStringEmpty(trafee)) {
				  travelactivities.setTrafee(Float.parseFloat(trafee));
			  }
			  
			  if(StringUtil.isStringEmpty(tradate)){

					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

					try {
						//doctoradvice.setDocadate(sdf.parse(docadate));
						travelactivities.setTradate(sdf.parse(tradate));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			  
			  
			  
			  //调用业务逻辑层的修改方法
			 boolean flag =  db.update(travelactivities);
			 
			 System.out.println(flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("delete".equals(method)) {
				//删除
				String traid = req.getParameter("traid");
				System.out.println(traid);
				//调用业务逻辑层的查询方法
				boolean flag = false;
				if (traid != null && traid != "") {
					flag =  db.delete(Integer.parseInt(traid));
				}
				
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
				   
				  String traid = req.getParameter("traid");
				  String traname = req.getParameter("traname");
				  String tradest = req.getParameter("tradest");
				  String trastart = req.getParameter("trastart");
				  String tracontent = req.getParameter("tracontent");
				  String trafee= req.getParameter("trafee");
				  String tradate= req.getParameter("tradate");
				  
				  Travelactivities travelactivities = new Travelactivities();
				  
				 
				  travelactivities.setTrastart(trastart);
				  if (traid != null && traid != "") {
					  travelactivities.setTraid(Integer.parseInt(traid));
				  }
				  travelactivities.setTraname(traname);
				  
				  travelactivities.setTradest(tradest);
				  travelactivities.setTracontent(tracontent);
				  //travelactivities.setIsdelete(0);
				  if(StringUtil.isStringEmpty(trafee)) {
					  travelactivities.setTrafee(Float.parseFloat(trafee));
				  }
				  
				  if(StringUtil.isStringEmpty(tradate)){

						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

						try {
							//doctoradvice.setDocadate(sdf.parse(docadate));
							travelactivities.setTradate(sdf.parse(tradate));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				  
				  //调用业务逻辑层的修改方法
				 boolean flag =  db.add(travelactivities);
				 System.out.println("tradate:"+tradate);
				 
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
