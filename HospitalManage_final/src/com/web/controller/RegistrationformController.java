package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.RegistrationformBiz;
import com.web.biz.impl.RegistrationformImpl;
import com.web.entity.Registrationform;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/Registrationform")
public class RegistrationformController extends HttpServlet {
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
		
		RegistrationformBiz regBiz = new RegistrationformImpl();
		
		if(method.equals("query")){
			
			String regid = req.getParameter("regId");
			String regdate = req.getParameter("regDate");
			String regPatName = req.getParameter("regPatName");
			String deptId = req.getParameter("deptId");
			
			
			
			Registrationform registrationform = new Registrationform();
			
			if(StringUtil.isStringEmpty(regid))
				registrationform.setRegid(Integer.parseInt(regid));
			
			if(StringUtil.isStringEmpty(regdate)){
				registrationform.setRegdate(StringUtil.stringToDate(regdate));
			}
			
			if(StringUtil.isStringEmpty(regPatName)){
				registrationform.setRegpatid(Integer.parseInt(regPatName));
			}
			if(StringUtil.isStringEmpty(deptId)){
				registrationform.setRegdeptid(Integer.parseInt(deptId));
			}
			List<Registrationform> registrationformsList = regBiz.queryByCondition(registrationform);
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(registrationformsList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println("query result: "+"true");
			
		}else if(method.equals("sendUpdate")){
			
			String regId = req.getParameter("regid");
			Registrationform reg = regBiz.findByID(Integer.parseInt(regId));
	
			String json = JsonUtil.getJson(reg);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			
			
		}else if(method.equals("delete")){
			String regid = req.getParameter("regid");
			Registrationform registrationform = new Registrationform();
			registrationform.setRegid(Integer.parseInt(regid));
			
			boolean flag = regBiz.deleteById(registrationform);
			System.out.println("delete result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
			
		}else if(method.equals("update")){
			String regid = req.getParameter("regId");
			String regPatient = req.getParameter("regPatient");
			String regDeptId = req.getParameter("regDeptId");
			String regDoc = req.getParameter("regDoc");
			Registrationform registrationform = new Registrationform();
			System.out.println(regDeptId);
			if(StringUtil.isStringEmpty(regid))
				registrationform.setRegid(Integer.parseInt(regid));
			//if(StringUtil.isStringEmpty(regPatient))
			//	registrationform.setRegpatid(1);
			if(StringUtil.isStringEmpty(regDeptId))
				registrationform.setRegdeptid(Integer.parseInt(regDeptId));
			//if(StringUtil.isStringEmpty(regDoc))
			//	registrationform.setRegid(1);
			
			boolean flag = regBiz.updateReg(registrationform);
			
			System.out.println("update result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
			
			 
			
			
		}else if(method.equals("add")){
			
			String regDeptId = req.getParameter("regDeptId");
			
			Registrationform registrationform = new Registrationform();
			
			registrationform.setRegdeptid(Integer.parseInt(regDeptId));
			//其他参数也应该一样，但是没有controller，直接在后台写死的
			registrationform.setEmpdocid(1);
			registrationform.setEmpid(1);
			registrationform.setRegpatid(1);
			registrationform.setIsdelete(1);
			
			boolean flag = regBiz.addReg(registrationform);
			System.out.println("add result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭

		}
		
		
	}
}
