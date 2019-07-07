package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Results;

import com.web.biz.WorkinghoursBiz;
import com.web.biz.impl.WorkinghoursBizImpl;
import com.web.entity.Employee;
import com.web.entity.Workinghours;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@WebServlet(urlPatterns="/workinghours")
public class WorkinghoursController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getParameter("method");
		
		//实例化业务逻辑层
		WorkinghoursBiz wh=new WorkinghoursBizImpl();
		
		//http://localhost:8080/HospitalManager/workinghours?method=query
		if("query".equals(method)){
			
			//接收前台传过来的参数
			String workid=req.getParameter("workid");
			String workempid=req.getParameter("workempid");
			String empname=req.getParameter("empname");
			String worktime=req.getParameter("worktime");
			
			Map<String, Object> map = new HashMap<>();
			Workinghours workinghours=new Workinghours();
			
			if(StringUtil.isStringEmpty(workid)){
				map.put("workid", workid);
			}
			if(StringUtil.isStringEmpty(workempid)){
				map.put("workempid", workempid);
			}
			if(StringUtil.isStringEmpty(empname)){
				map.put("empname", empname);
			}
			
			if(StringUtil.isStringEmpty(worktime)){
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

				try {
					map.put("worktime", worktime);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			List<Workinghours> list=wh.queryByCondition(map);
			
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
			  String workid = req.getParameter("workid");
			  //调用业务逻辑层的查询方法
			  if(StringUtil.isStringEmpty(workid)){
				  Workinghours workinghours=wh.findByWorkId(Integer.parseInt(workid));
				  //把数据转换为json字符串
				  String json = JsonUtil.getJson(workinghours);
				  
				//获取输出对象
				  PrintWriter out = resp.getWriter();
				  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
				  out.write(json);//输出
				  out.flush();//刷新
				  out.close();//关闭
			  }
			  
			  
		  }else if("update".equals(method)){
			  //修改
			  //接收前台传过来的参数
			  String workid = req.getParameter("workid");
			  String workempid=req.getParameter("workempid");
			  String worktime=req.getParameter("worktime");
			  
			  
			  Workinghours workinghours = new Workinghours();
			  
			  if(StringUtil.isStringEmpty(workid)){
					workinghours.setWorkid(Integer.parseInt(workid));
			  }
			  
			  if(StringUtil.isStringEmpty(workempid)){
					workinghours.setWorkempid(Integer.parseInt(workempid));
			  }
			  
			  if(StringUtil.isStringEmpty(worktime)){
				  
				  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

					try {
						workinghours.setWorktime(sdf.parse(worktime));
					} catch (Exception e) {
						e.printStackTrace();
					}
			  }
			  
			  
			  //调用业务逻辑层的修改方法
			 boolean flag =  wh.update(workinghours);
			 
			 System.out.println(flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if ("delete".equals(method)) {
			//删除
				String workid = req.getParameter("workid");
				System.out.println(workid);
				
				if(StringUtil.isStringEmpty(workid)){
					//调用业务逻辑层的查询方法
					boolean flag =  wh.delete(Integer.parseInt(workid));
					System.out.println(flag);
					
					String json = JsonUtil.getJson(flag); 
					//获取输出对象
					PrintWriter out = resp.getWriter();
					resp.setContentType("UTF-8");
					out.write(json);//输出
					out.flush();//刷新
					out.close();//关闭
				}
		}else if ("add".equals(method)) {
			
			String workempid = req.getParameter("workempid");
			String worktime = req.getParameter("worktime"); 

			Workinghours workinghours=new Workinghours();
			
			if(StringUtil.isStringEmpty(workempid)){
				workinghours.setWorkempid(Integer.parseInt(workempid));
		    }
			
			if(StringUtil.isStringEmpty(worktime)){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

				try {
					workinghours.setWorktime(sdf.parse(worktime));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//调用业务逻辑层的查询方法
			boolean flag =  wh.add(workinghours);
			System.out.println(flag);

			String json = JsonUtil.getJson(flag);
			
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
			
		}
	}
}
