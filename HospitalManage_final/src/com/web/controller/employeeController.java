package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.employeeBiz;
import com.web.biz.impl.employeeBizImpl;
import com.web.entity.Employee;
import com.web.util.JsonUtil;
@WebServlet(urlPatterns = "/employee")
public class employeeController extends HttpServlet {
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getParameter("method");
		
		employeeBiz eb = new employeeBizImpl();
		if ("empNameSelect".equals(method)) {
			List<Employee> list = eb.empNameSelect();
			String json = JsonUtil.getJson(list);
//			System.out.println("医生名单:" + json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		}
		
	} 
}
