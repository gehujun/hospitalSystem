package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.medicinepayreceiptBiz;
import com.web.biz.impl.medicinepayreceiptBizImpl;
import com.web.entity.Medicinepayreceipt;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@WebServlet(urlPatterns="/medicinepayreceipt")
public class medicinepayreceiptController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getParameter("method");
		medicinepayreceiptBiz mb = new medicinepayreceiptBizImpl();
		if ("query".equals(method)) {
			String mprId = req.getParameter("mprId");
			String mprContent = req.getParameter("mprContent");
			String empId = req.getParameter("empId");
			
			Medicinepayreceipt medicinepayreceipt = new Medicinepayreceipt();
			if (mprId != null && mprId != "") {
				medicinepayreceipt.setMprid(Integer.parseInt(mprId));
			}
			medicinepayreceipt.setMprcontent(mprContent);
			if (empId != null && empId != "") {
				medicinepayreceipt.setEmpid(Integer.parseInt(empId));
			}
			List<Medicinepayreceipt> list = mb.query(medicinepayreceipt);
			String json = JsonUtil.getJson(list);
			System.out.println(json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("add".equals(method)) {
//			String mprId = req.getParameter("mprId");
			String maId = req.getParameter("maId");
			String mprContent = req.getParameter("mprContent");
			String inpatId = req.getParameter("inpatId");
			String mprDate = req.getParameter("mprDate");
			String empId = req.getParameter("empId");
			String amount = req.getParameter("amount");
			Medicinepayreceipt medicinepayreceipt = new Medicinepayreceipt();
			if (maId != null && maId != "") {
				medicinepayreceipt.setMaid(Integer.parseInt(maId));
			}
			
			medicinepayreceipt.setMprcontent(mprContent);
			if (inpatId != null && inpatId != "") {
				medicinepayreceipt.setInpatid(Integer.parseInt(inpatId));
			}
			
			if (StringUtil.isStrNotEmpty(mprDate)) {
				//把字符串转换为日期格式
				medicinepayreceipt.setMprdate(StringUtil.stringToDate(mprDate));
			}
			if (empId != null && empId != "") {
				medicinepayreceipt.setEmpid(Integer.parseInt(empId));
			}
			if (amount != null && amount != "") {
				medicinepayreceipt.setAmount(Float.parseFloat(amount));
			}
			medicinepayreceipt.setIsdelete(1);
			
			
			boolean flag = mb.add(medicinepayreceipt);
			String json = JsonUtil.getJson(flag);
			System.out.println(json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("sendUpdate".equals(method)) {
			String mprId = req.getParameter("mprId");
			Medicinepayreceipt medicinepayreceipt = new Medicinepayreceipt();
			if (mprId != null && mprId != "") {
				medicinepayreceipt = mb.findById(Integer.parseInt(mprId));
			}
			String json = JsonUtil.getJson(medicinepayreceipt); 
			
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
			
		} else if ("update".equals(method)) {
			String mprId = req.getParameter("mprId");
			String mprContent = req.getParameter("mprContent");
			String mprDate = req.getParameter("mprDate");
			String amount = req.getParameter("amount");
			Medicinepayreceipt medicinepayreceipt = new Medicinepayreceipt();
			medicinepayreceipt.setMprcontent(mprContent);
			
			if (mprId != null && mprId != "") {
				medicinepayreceipt.setMprid(Integer.parseInt(mprId));
			}
			
			if (StringUtil.isStrNotEmpty(mprDate)) {
				//把字符串转换为日期格式
				medicinepayreceipt.setMprdate(StringUtil.stringToDate(mprDate));
			}
			if (amount != null && amount != "") {
				medicinepayreceipt.setAmount(Float.parseFloat(amount));
			}
			
			boolean flag = mb.update(medicinepayreceipt);
			
//			String json = JsonUtil.getJson(flag); 
			
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write("1");//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("delete".equals(method)) {
			System.out.println("aaaaa");
			String mprId = req.getParameter("mprId");
			boolean flag = false;
			if (mprId != null && mprId != "") {
				flag = mb.delete(Integer.parseInt(mprId));
			}
			
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
