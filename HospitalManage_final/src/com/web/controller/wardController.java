package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.biz.wardBiz;
import com.web.biz.impl.wardBizImpl;
import com.web.dao.wardDao;
import com.web.entity.Ward;
import com.web.util.JsonUtil;
@WebServlet(urlPatterns = "/ward")
public class wardController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getParameter("method");
		wardBiz wb = new wardBizImpl();
		if ("query".equals(method)) {
			String wardName = req.getParameter("wardName");
			String empId = req.getParameter("empId");
			
			Ward ward = new Ward();
			ward.setWardname(wardName);
			if (empId != null && empId != "") {
				ward.setEmpid(Integer.parseInt(empId));
			}
			List<Ward> list = wb.query(ward);
			String json = JsonUtil.getJson(list);
			System.out.println(json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("add".equals(method)) {
			String wardName = req.getParameter("wardName");
			String bedNum = req.getParameter("bedNum");
			String empId = req.getParameter("empId");
			String emptyBedNum = req.getParameter("emptyBedNum");
			System.out.println("ward add的empId" + empId);
			Ward ward = new Ward();
			ward.setWardname(wardName);
			if (bedNum != null && bedNum != "") {
				System.out.println("输出bedNum：" + Integer.parseInt(bedNum));
				ward.setBednum(Integer.parseInt(bedNum));
			}
			if (empId != null && empId != "") {
				System.out.println("输出empId：" + Integer.parseInt(empId));
				ward.setEmpid(Integer.parseInt(empId));
			}
			if (emptyBedNum != null && emptyBedNum != "") {
				System.out.println("输出emptyBedNum：" + Integer.parseInt(emptyBedNum));
				ward.setEmptybednum(Integer.parseInt(emptyBedNum));
			}
			ward.setIsdelete(1);
			boolean flag = wb.add(ward);
			String json = JsonUtil.getJson(flag);
			System.out.println(json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("sendUpdate".equals(method)) {
			String wardName = req.getParameter("wardName");
			Ward ward = wb.findById(wardName);
			String json = JsonUtil.getJson(ward);
			System.out.println("json的"+json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("update".equals(method)) {
			String wardName = req.getParameter("wardName");
			String empId = req.getParameter("empId");
			String bedNum = req.getParameter("bedNum");
			String emptyBedNum = req.getParameter("emptyBedNum");
			
			Ward ward = new Ward();
			ward.setWardname(wardName);
			if (bedNum != null && bedNum != "") {
				ward.setBednum(Integer.parseInt(bedNum));
			}
			if (empId != null && empId != "") {
				ward.setEmpid(Integer.parseInt(empId));
			}
			if (emptyBedNum != null && emptyBedNum != "") {
				ward.setEmptybednum(Integer.parseInt(emptyBedNum));
			}
			
			boolean flag = wb.update(ward);
			String json = JsonUtil.getJson(flag);
			System.out.println(json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("delete".equals(method)) {
			System.out.println("www");
			String wardName = req.getParameter("wardName");
			boolean flag = wb.delete(wardName);
			
			String json = JsonUtil.getJson(flag);
			System.out.println(json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		}
	}
}
