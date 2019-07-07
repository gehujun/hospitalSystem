package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.util.HandlerAnnotationInfo;
import com.web.biz.SurgeryprojectBiz;
import com.web.biz.impl.SurgeryprojectBizImpl;
import com.web.entity.Medicaladvice;
import com.web.entity.Professionaltitle;
import com.web.entity.Surgerroom;
import com.web.entity.Surgeryproject;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

import sun.tools.tree.VarDeclarationStatement;

@WebServlet(urlPatterns="/surgeryproject")
public class surgeryprojectController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String method = req.getParameter("method");
		
		SurgeryprojectBiz surProBiz = new SurgeryprojectBizImpl();
		
		if(method.equals("query")){
			
			Map<String, Object> map = new HashMap<>();
			
			String surId = req.getParameter("surId");
			if(StringUtil.isStringEmpty(surId)){
				map.put("surId", Integer.parseInt(surId));
			}
			System.out.println("surId"+surId);
			
			String surRoom = req.getParameter("surRoom");
			if(StringUtil.isStringEmpty(surRoom)){
				if(!surRoom.equals("0"))
					map.put("surRoom", surRoom);
			}
			
			String surDoc = req.getParameter("surDoc");
			if(StringUtil.isStringEmpty(surDoc)){
				Integer surDocI = Integer.parseInt(surDoc);
				if(surDocI  != 0){
					map.put("surDoc", surDocI);
				}
			}
			List<Surgeryproject> surgeryprojects  = surProBiz.queryByCondition(map);
			
			//把数据转换为json字符串
			  String json = JsonUtil.getJson(surgeryprojects);
			  System.out.println(json);
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
					
			
		}else if(method.equals("querySurRoom")){
			
			Map<String, Object> map = new HashMap<>();
			
			List<Surgerroom> SurgerroomList  = surProBiz.querySurRoom();
			
			//把数据转换为json字符串
			  String json = JsonUtil.getJson(SurgerroomList);
			  System.out.println(json);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		}else if ("delete".equals(method)) {
			//删除
			String surid = req.getParameter("surid");
			System.out.println(surid);
			
			//调用业务逻辑层的查询方法
			boolean flag =  surProBiz.delete(Integer.parseInt(surid));
			System.out.println(flag);
			
			String json = JsonUtil.getJson(flag);
			
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		}else if ("add".equals(method)) {
			String maId = req.getParameter("maId");
			String name = req.getParameter("name");
			String surRoomId = req.getParameter("surRoomId");
			String chiefSurgeon = req.getParameter("chiefSurgeon");
			String surDate = req.getParameter("surDate");
			String surPat = req.getParameter("surPat");
			Surgeryproject surgeryproject = new Surgeryproject();
			
			if(StringUtil.isStringEmpty(maId)){
				surgeryproject.setMaid(Integer.parseInt(maId));
			}
			if(StringUtil.isStringEmpty(name)){
				surgeryproject.setName(name);
			}
			if(StringUtil.isStringEmpty(surRoomId)){
				surgeryproject.setSurroomid(surRoomId);
			}
			if(StringUtil.isStringEmpty(chiefSurgeon)){
				surgeryproject.setChiefsurgeon(Integer.parseInt(chiefSurgeon));
			}
			
			if(StringUtil.isStringEmpty(surPat)){
				surgeryproject.setInpatid(Integer.parseInt(surPat));
			}
			
			boolean flag =  surProBiz.add(surgeryproject);
			System.out.println(flag);
			
			String json = JsonUtil.getJson(flag); 
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
			
			
		}else if ("maidSelect".equals(method)) {
			
			List<Medicaladvice> medicaladvices  = surProBiz.queryMA();
			
			//把数据转换为json字符串
			  String json = JsonUtil.getJson(medicaladvices);
			  System.out.println(json);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		}else if("sendUpdate".equals(method)){
			
			String surid = req.getParameter("surid");
			
			Surgeryproject surgeryproject = surProBiz.findByID(Integer.parseInt(surid));
			
			 //把数据转换为json字符串
			  String json = JsonUtil.getJson(surgeryproject);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		}else if("update".equals(method)){
			
			String surid = req.getParameter("surid");
			String maId = req.getParameter("maId");
			String name = req.getParameter("name");
			String surRoomId = req.getParameter("surRoomId");
			String chiefSurgeon = req.getParameter("chiefSurgeon");
			String surDate = req.getParameter("surDate");
			String surPat = req.getParameter("surPat");
			Surgeryproject surgeryproject = new Surgeryproject();
			
			if(StringUtil.isStringEmpty(surid)){
				surgeryproject.setSurid(Integer.parseInt(surid));
			}
			if(StringUtil.isStringEmpty(maId)){
				surgeryproject.setMaid(Integer.parseInt(maId));
			}
			if(StringUtil.isStringEmpty(name)){
				surgeryproject.setName(name);
			}
			if(StringUtil.isStringEmpty(surRoomId)){
				surgeryproject.setSurroomid(surRoomId);
			}
			if(StringUtil.isStringEmpty(chiefSurgeon)){
				surgeryproject.setChiefsurgeon(Integer.parseInt(chiefSurgeon));
			}
			
			if(StringUtil.isStringEmpty(surPat)){
				surgeryproject.setInpatid(Integer.parseInt(surPat));
			}
			
			boolean flag =  surProBiz.update(surgeryproject);
			 
			 System.out.println(flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		}
	}
}
