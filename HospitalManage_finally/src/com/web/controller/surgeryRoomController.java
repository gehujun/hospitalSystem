package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import com.web.biz.PatientBiz;
import com.web.biz.medicalAdviceBiz;
import com.web.biz.surgeryRoomBiz;
import com.web.biz.impl.PatientBizImpl;
import com.web.biz.impl.medicalAdviceBizImpl;
import com.web.biz.impl.surgeryRoomBizImpl;
import com.web.entity.Medicaladvice;
import com.web.entity.Patient;
import com.web.entity.Surgerroom;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@WebServlet(urlPatterns = "/surgeryroom")
public class surgeryRoomController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getParameter("method");
		surgeryRoomBiz srb = new surgeryRoomBizImpl();
		
		if ("query".equals(method)) {
			String surRoomId = req.getParameter("surRoomId");
			String surRoomAddr = req.getParameter("surRoomAddr");
			String empId = req.getParameter("empId");
			
			Surgerroom surgerroom = new Surgerroom();
			if (empId != null && empId != "") {
				surgerroom.setEmpid(Integer.parseInt(empId));
			}
			surgerroom.setSurroomaddr(surRoomAddr);
			surgerroom.setSurroomid(surRoomId);
			
			List<Surgerroom> list = srb.query(surgerroom);
			
			String json = JsonUtil.getJson(list);
			System.out.println(json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("add".equals(method)) {
			String surRoomId = req.getParameter("surRoomId");
			String surRoomAddr = req.getParameter("surRoomAddr");
			String empId = req.getParameter("empId");
			String empName = req.getParameter("empName");
			
			String surRoomPre = req.getParameter("surRoomPre");
			
			Surgerroom surgerroom = new Surgerroom();
			surgerroom.setIsdelete(1);
			surgerroom.setSurroomaddr(surRoomAddr);
			surgerroom.setSurroompre(surRoomPre);
			surgerroom.setSurroomid(surRoomId);
			
			if (empId != null && empId != "") {
				surgerroom.setEmpid(Integer.parseInt(empId));
			}
			boolean flag = srb.add(surgerroom);
			String json = JsonUtil.getJson(flag);
			System.out.println(json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("sendUpdate".equals(method)) {
			String surRoomId = req.getParameter("surRoomId");
			
			Surgerroom surgerroom = srb.findById(surRoomId);
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(surgerroom); 
			
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
			System.out.println("nnnn");
		} else if ("update".equals(method)) {
			String surRoomId = req.getParameter("surRoomId");
			String surRoomAddr = req.getParameter("surRoomAddr");
			String empId = req.getParameter("empId");
			String surRoomPre = req.getParameter("surRoomPre");
			
			Surgerroom surgerroom = new Surgerroom();
			if (empId != null && empId != "") {
				surgerroom.setEmpid(Integer.parseInt(empId));
			}
			surgerroom.setSurroomaddr(surRoomAddr);
			surgerroom.setSurroomid(surRoomId);
			surgerroom.setSurroompre(surRoomPre);
			
			boolean flag = srb.update(surgerroom);
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(flag); 
			
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("delete".equals(method)) {
			System.out.println("bbbbb");
			String surRoomId = req.getParameter("surRoomId");
			System.out.println(surRoomId);
			boolean flag = srb.delete(surRoomId);
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
