package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.SurgeryBiz;
import com.web.biz.impl.SurgeryBizImpl;
import com.web.entity.Surgery;
import com.web.util.JsonUtil;
import com.web.util.MyBatisUtil;
import com.web.util.StringUtil;


@WebServlet(urlPatterns="/surgery")
public class SurgeryController extends HttpServlet {

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
		  SurgeryBiz db=new SurgeryBizImpl();
		  
		  //url:http://localhost:8080/HospitalManager/surgery?method=query
		  if("query".equals(method)){
			  	//接收传过来的参数
			  String surgname = req.getParameter("surgname");
			  String surgprofid = req.getParameter("surgprofid");
			  String surgpushfee = req.getParameter("surgpushfee");
			  String surgfee = req.getParameter("surgfee");
			  
			  Surgery surgery = new Surgery();
			  
			  surgery.setSurgname(surgname);
			  /*if(StringUtil.isStringEmpty(surgid)){
				  surgery.setSurgid(Integer.parseInt(surgid));
			  }	*/		  
			  if(StringUtil.isStringEmpty(surgprofid)){
				  surgery.setSurgprofid(Integer.parseInt(surgprofid));
			  }
			  if(StringUtil.isStringEmpty(surgpushfee)){
				  surgery.setSurgpushfee(Float.parseFloat(surgpushfee));
			  }
			  if(StringUtil.isStringEmpty(surgfee)){
				  surgery.setSurgfee(Float.parseFloat(surgfee));
			  }
			  			  
			  
			  //调用业务逻辑层中的查询方法
			  List<Surgery> list = db.queryByCondition(surgery);
			  
//			  //把数据传到页面上
//			  req.setAttribute("list", list);
//			  
//			  //转发 跳转页面
//			  req.getRequestDispatcher("lisSurgery.jsp").forward(req, resp);
			  
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(list);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("sendUpdate".equals(method)){
			  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
			  String surgid = req.getParameter("surgid");
			  
			  //调用业务逻辑层的查询方法
			  Surgery surgery =  db.findById(Integer.parseInt(surgid));
			  
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(surgery);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("update".equals(method)){
			  //修改
			  String surgid = req.getParameter("surgid");
			  String surgname = req.getParameter("surgname");
			  String surgprofid = req.getParameter("surgprofid");		 
			  String surgpushfee = req.getParameter("surgpushfee");
			  String surgfee = req.getParameter("surgfee");
			  		  
			  Surgery surgery = new Surgery();
			  
			 
			  		  
			  surgery.setSurgname(surgname);
			  
			  if(StringUtil.isStringEmpty(surgid)){
				  surgery.setSurgid(Integer.parseInt(surgid));
			  }			  
			  if(StringUtil.isStringEmpty(surgprofid)){
				  surgery.setSurgprofid(Integer.parseInt(surgprofid));
			  }
			  if(StringUtil.isStringEmpty(surgpushfee)){
				  surgery.setSurgpushfee(Float.parseFloat(surgpushfee));
			  }
			  if(StringUtil.isStringEmpty(surgfee)){
				  surgery.setSurgfee(Float.parseFloat(surgfee));
			  }
			  surgery.setIsdelete(1);	
			  	
			  
			  //调用业务逻辑层的修改方法
			 boolean flag =  db.update(surgery);
			 
			 System.out.println(flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("delete".equals(method)) {
				//删除
				String surgid = req.getParameter("surgid");
				System.out.println(surgid);
				//调用业务逻辑层的查询方法
				boolean flag =  db.delete(Integer.parseInt(surgid));
				System.out.println(flag);
				
				String json = JsonUtil.getJson(flag); 
				//获取输出对象
				PrintWriter out = resp.getWriter();
				resp.setContentType("UTF-8");
				out.write(json);//输出
				out.flush();//刷新
				out.close();//关闭
			}else if("add".equals(method)){
				  //增加		  
				 				  
				  String surgid = req.getParameter("surgid");
				  String surgname = req.getParameter("surgname");
				  String surgprofid = req.getParameter("surgprofid");		 
				  String surgpushfee = req.getParameter("surgpushfee");
				  String surgfee = req.getParameter("surgfee");
				  				  
				  Surgery surgery = new Surgery();

				  surgery.setSurgname(surgname);
				  
				  if(StringUtil.isStringEmpty(surgid)){
					  surgery.setSurgid(Integer.parseInt(surgid));
				  }			  
				  if(StringUtil.isStringEmpty(surgprofid)){
					  surgery.setSurgprofid(Integer.parseInt(surgprofid));
				  }
				  if(StringUtil.isStringEmpty(surgpushfee)){
					  surgery.setSurgpushfee(Float.parseFloat(surgpushfee));
				  }
				  if(StringUtil.isStringEmpty(surgfee)){
					  surgery.setSurgfee(Float.parseFloat(surgfee));
				  }
				  surgery.setIsdelete(1);

				  //调用业务逻辑层的修改方法
				 boolean flag =  db.add(surgery);
				 
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
