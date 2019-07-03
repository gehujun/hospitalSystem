package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.MedicalhistoryBiz;
import com.web.biz.impl.MedicalhistoryBizImpl;
import com.web.entity.Medicalhistory;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/Medicalhistory")
public class MedicalhistoryController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MedicalhistoryBiz medhBiz = new MedicalhistoryBizImpl();
		
		if(method.equals("query")){
			
			String medhid = req.getParameter("medhId");
			String patname = req.getParameter("medhPatId");
			
			Map<String, Object> map = new HashMap<>();
			
			Medicalhistory medicalhistory = new Medicalhistory();
			
			if(StringUtil.isStringEmpty(medhid))
				map.put("medhid", medhid);
			if(StringUtil.isStringEmpty(patname))
				map.put("patname", patname);
				
			List<Medicalhistory> list = medhBiz.queryByCondition(map);
			
			//把数据转换为json字符串
			  String json = JsonUtil.getJson(list);
			  System.out.println(json);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
			
		}else if(method.equals("delete")){
			String medhid = req.getParameter("medhid");
			
			Medicalhistory medicalhistory = new Medicalhistory();
			if(StringUtil.isStringEmpty(medhid))
				medicalhistory.setMedhid(Integer.parseInt(medhid));
			
			boolean flag = medhBiz.deleteById(medicalhistory);
			System.out.println("delete result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		}else if(method.equals("add")){
			String medhid = req.getParameter("medhid");
			String patid = req.getParameter("patName");
			String patType =req.getParameter("patType");
			String medhhis = req.getParameter("medhhis");
			
			Medicalhistory medh = new Medicalhistory();
			
			if(StringUtil.isStringEmpty(patid))
				medh.setMedhpatid(Integer.parseInt(patid));
			medh.setMedhtype(patType);
			medh.setMedhhistory(medhhis);
			medh.setIsdelete(1);
			
			boolean flag = medhBiz.addMedh(medh);
			System.out.println("add result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		}else if(method.equals("sendUpdate")){
			String medhid = req.getParameter("medhid");
			
			Medicalhistory medicalhistory = medhBiz.findByID(Integer.parseInt(medhid));
			String json = JsonUtil.getJson(medicalhistory);
			System.out.println(medicalhistory.getMedhpatid());
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println("send update: "+json);
		}else if(method.equals("update")){
			String medhid = req.getParameter("medhId");
			String patid = req.getParameter("patName");
			String patType =req.getParameter("patType");
			String medhhis = req.getParameter("medhhis");
			
			Medicalhistory medicalhistory =new Medicalhistory();
			if(StringUtil.isStringEmpty(medhid))
				medicalhistory.setMedhid(Integer.parseInt(medhid));
			if(StringUtil.isStringEmpty(patid))
				medicalhistory.setMedhpatid(Integer.parseInt(patid));
			System.out.println("medhid： "+medhid+" patid："+patid);
			medicalhistory.setMedhtype(patType);
			medicalhistory.setMedhhistory(medhhis);
			
			boolean flag = medhBiz.updateMedh(medicalhistory);
			
			System.out.println("update result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		}
		
		
		
		
		
	}
}
