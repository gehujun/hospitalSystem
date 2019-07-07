package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.DoctoradviceBiz;
import com.web.biz.impl.DoctoradviceBizImpl;
import com.web.dao.doctoradviceDao;
import com.web.entity.Doctoradvice;
import com.web.entity.Employee;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/doctoradvice")
public class DoctoradviceController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		
		//实例化业务逻辑层
		DoctoradviceBiz da=new DoctoradviceBizImpl();
		
		if("query".equals(method)){
			
			//接收前台传过来的参数
			String docaid=req.getParameter("docaid");
			String patid=req.getParameter("patid");
			String patname=req.getParameter("patname");

			String docaempdocid=req.getParameter("docaempdocid");
			String empname=req.getParameter("empname");
			String docadate=req.getParameter("docadate");
			
			Map<String, Object> map = new HashMap<>();
			if(StringUtil.isStringEmpty(docaid)){
				map.put("docaid", docaid);
			}
			if(StringUtil.isStringEmpty(patid)){
				map.put("patid", patid);
			}
			if(StringUtil.isStringEmpty(patname)){
				map.put("patname", patname);
			}
			if(StringUtil.isStringEmpty(docaempdocid)){
				map.put("docaempdocid", docaempdocid);
			}
			if(StringUtil.isStringEmpty(empname)){
				map.put("empname", empname);
			}
			if(StringUtil.isStringEmpty(docadate)){
				map.put("docadate", docadate);
			}
			
			Doctoradvice doctoradvice=new Doctoradvice();
			
			List<Doctoradvice> list=da.queryByCondition(map);
			
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
			  String docaid = req.getParameter("docaid");
			  System.out.println("从网页得到的主键值是: "+docaid);
			  //调用业务逻辑层的查询方法
			  if(StringUtil.isStringEmpty(docaid)){
				  Doctoradvice doctoradvice=da.findByDocaId(Integer.parseInt(docaid));
				  //把数据转换为json字符串
				  String json = JsonUtil.getJson(doctoradvice);
				  
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
			    String docaid = req.getParameter("docaid");
				String patid=req.getParameter("patid");
				String docaillnessexplain=req.getParameter("docaillnessexplain");
				String docatreatmentscheme=req.getParameter("docatreatmentscheme");
				String docaempdocid=req.getParameter("docaempdocid");
				String docadate=req.getParameter("docadate");
			  
			  
			    Doctoradvice doctoradvice = new Doctoradvice(); 
			    if(StringUtil.isStringEmpty(docaid)){
					doctoradvice.setDocaid(Integer.parseInt(docaid));
			    }
			    if(StringUtil.isStringEmpty(patid)){
			    	doctoradvice.setPatid(Integer.parseInt(patid));
			    }
			    
			    if(StringUtil.isStringEmpty(docaillnessexplain)){
			    	doctoradvice.setDocaillnessexplain(docaillnessexplain);
			    }
			    
			    if(StringUtil.isStringEmpty(docatreatmentscheme)){
			    	doctoradvice.setDocatreatmentscheme(docatreatmentscheme);
			    }
			    
			    if(StringUtil.isStringEmpty(docaempdocid)){
			    	doctoradvice.setDocaempdocid(Integer.parseInt(docaempdocid));
			    }
			  
			    if(StringUtil.isStringEmpty(docadate)){
				  
				  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

					try {
						doctoradvice.setDocadate(sdf.parse(docadate));
					} catch (Exception e) {
						e.printStackTrace();
					}
			  }
			  
			  
			  //调用业务逻辑层的修改方法
			 boolean flag =  da.update(doctoradvice);
			 
			 System.out.println("更新状态："+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if ("delete".equals(method)) {
			//删除
				String docaid = req.getParameter("docaid");
				
				if(StringUtil.isStringEmpty(docaid)){
					//调用业务逻辑层的查询方法
					boolean flag =  da.delete(Integer.parseInt(docaid));
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
			
			    String patid=req.getParameter("patid");
				String docaillnessexplain=req.getParameter("docaillnessexplain");
				String docatreatmentscheme=req.getParameter("docatreatmentscheme");
				String docaempdocid=req.getParameter("docaempdocid");
				String docadate=req.getParameter("docadate");
			  
			  
			    Doctoradvice doctoradvice = new Doctoradvice(); 
			  
			    if(StringUtil.isStringEmpty(patid)){
			    	doctoradvice.setPatid(Integer.parseInt(patid));
			    }
			    
			    if(StringUtil.isStringEmpty(docaillnessexplain)){
			    	doctoradvice.setDocaillnessexplain(docaillnessexplain);
			    }
			    
			    if(StringUtil.isStringEmpty(docatreatmentscheme)){
			    	doctoradvice.setDocatreatmentscheme(docatreatmentscheme);
			    }
			    
			    if(StringUtil.isStringEmpty(docaempdocid)){
			    	doctoradvice.setDocaempdocid(Integer.parseInt(docaempdocid));
			    }
			  
			    if(StringUtil.isStringEmpty(docadate)){
				  
				  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

					try {
						doctoradvice.setDocadate(sdf.parse(docadate));
					} catch (Exception e) {
						e.printStackTrace();
					}
			  }
			boolean flag =  da.add(doctoradvice);
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
