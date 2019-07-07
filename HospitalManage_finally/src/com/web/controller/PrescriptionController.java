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

import com.web.biz.PrescriptionBiz;
import com.web.biz.impl.PrescriptionBizImpl;
import com.web.entity.Prescription;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/prescription")
public class PrescriptionController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		//实例化业务逻辑层
		PrescriptionBiz pt=new PrescriptionBizImpl();
		if("query".equals(method)){
			
			//接收前台传过来的参数
			String presid=req.getParameter("presid");
			String mediid=req.getParameter("mediid");
			String mediname=req.getParameter("mediname");
			//String medinum=req.getParameter("medinum");
			String presdocaid=req.getParameter("presdocaid");
			
			Map<String, Object> map = new HashMap<>();
			if(StringUtil.isStringEmpty(presid)){
				map.put("presid", presid);
			}
			if(StringUtil.isStringEmpty(mediid)){
				map.put("mediid", mediid);
			}
			if(StringUtil.isStringEmpty(mediname)){
				map.put("mediname", mediname);
			}
			if(StringUtil.isStringEmpty(presdocaid)){
				map.put("presdocaid", presdocaid);
			}
			
			Prescription prescription=new Prescription();
			
			List<Prescription> list=pt.queryByCondition(map);
			
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
			  String presid = req.getParameter("presid");
			  System.out.println("从网页得到的主键值是: "+presid);
			  //调用业务逻辑层的查询方法
			  if(StringUtil.isStringEmpty(presid)){
				  Prescription prescription=pt.findByPresId(Integer.parseInt(presid));
				  //把数据转换为json字符串
				  String json = JsonUtil.getJson(prescription);
				  
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
			    String presid = req.getParameter("presid");
			    System.out.println("更新语句得到的主键值是："+presid);
			    String mediid = req.getParameter("mediid");
			    System.out.println("更新语句得到的药品编号值是："+mediid);
				String medinum=req.getParameter("medinum");
				String presdocaid=req.getParameter("presdocaid");
			  
			    Prescription prescription = new Prescription(); 
			    
			    if(StringUtil.isStringEmpty(presid)){
			    	prescription.setPresid(Integer.parseInt(presid));
			    }
			    
			    if(StringUtil.isStringEmpty(mediid)){
			    	prescription.setMediid(Integer.parseInt(mediid));
			    }
			    if(StringUtil.isStringEmpty(medinum)){
			    	prescription.setMedinum(Integer.parseInt(medinum));
			    }
			    if(StringUtil.isStringEmpty(presdocaid)){
			    	prescription.setPresdocaid(Integer.parseInt(presdocaid));
			    }
			  
			  //调用业务逻辑层的修改方法
			 boolean flag =  pt.update(prescription);
			 
			 System.out.println("更新状态："+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if ("delete".equals(method)) {
			//删除
				String presid = req.getParameter("presid");
				
				if(StringUtil.isStringEmpty(presid)){
					//调用业务逻辑层的查询方法
					boolean flag =  pt.delete(Integer.parseInt(presid));
					System.out.println("删除状态:"+flag);
					
					String json = JsonUtil.getJson(flag); 
					//获取输出对象
					PrintWriter out = resp.getWriter();
					resp.setContentType("UTF-8");
					out.write(json);//输出
					out.flush();//刷新
					out.close();//关闭
				}
		  }else if ("add".equals(method)) {
			
			    String mediid=req.getParameter("mediid");
				String medinum=req.getParameter("medinum");
				String presdocaid=req.getParameter("presdocaid");
			  
			  
			    Prescription prescription = new Prescription(); 
			  
			    if(StringUtil.isStringEmpty(mediid)){
			    	prescription.setMediid(Integer.parseInt(mediid));
			    }
			    
			    if(StringUtil.isStringEmpty(medinum)){
			    	prescription.setMedinum(Integer.parseInt(medinum));
			    }
			    
			    if(StringUtil.isStringEmpty(presdocaid)){
			    	prescription.setPresdocaid(Integer.parseInt(presdocaid));
			    }
			    
				boolean flag =  pt.add(prescription);
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
