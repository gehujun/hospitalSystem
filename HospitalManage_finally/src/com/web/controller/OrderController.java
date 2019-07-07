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

import com.web.biz.impl.OrderBizImpl;
import com.web.biz.OrderBiz;
import com.web.entity.Employee;

import com.web.entity.Order;
import com.web.entity.Supplier;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/order")
public class OrderController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String method = req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		OrderBiz orderBiz = new OrderBizImpl();
		
		if(method.equals("query")){

//			System.out.println("lalala");
			List<Order> orderList = orderBiz.queryAll();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(orderList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("querySupId")){
			
			List<Supplier> SupplierList = orderBiz.querySupId();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(SupplierList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
//			System.out.println(json);
		}
		else if(method.equals("queryEmpId")){
			
			List<Employee> EmployeeList = orderBiz.queryEmpId();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(EmployeeList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("queryByJoin")){
			
			List<Order> orderList = orderBiz.queryByJoin();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(orderList);
			
//			System.out.println("order: " + orderList.get(1).getSupid());
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println(json);
			
		}
		else if(method.equals("queryByCondition")){
			
			String supname = req.getParameter("supname");
			String empname = req.getParameter("empname");
			
			System.out.println("supname: "+supname);
			
//			System.out.println("meliaddr: "+meliaddr);
			
			Map<String, Object> map = new HashMap<>();
			
//			if(StringUtil.isStringEmpty(supname))
//				map.put("supname",Integer.parseInt(supname) );
//			if(StringUtil.isStringEmpty(empname))
//				map.put("empname",Integer.parseInt(empname) );
			
			map.put("supname",supname);
			map.put("empname",empname);
			
//			System.out.println("medifirm: "+medifirm);
//			System.out.println("meliaddr: "+meliaddr);
			
			List<Order> orderList = orderBiz.queryByCondition(map);
			
//			System.out.println("List: "+medicineList);
			//把数据转换为json字符串
			String json = JsonUtil.getJson(orderList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("sendUpdate")){
			
			String ordid = req.getParameter("ordid");
			Order order = orderBiz.findByID(Integer.parseInt(ordid));
			
//			System.out.println("order: "+order.getOrdnum());
	
			String json = JsonUtil.getJson(order);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("delete")){
			String ordid = req.getParameter("ordid");
//			Order order = new Order();
//			order.setOrdid(Integer.parseInt(ordid));
			
			boolean flag = orderBiz.deleteById(Integer.parseInt(ordid));
			System.out.println("delete result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
			
		}
		else if(method.equals("update")){
			//获取用户输入的数据
			String ordid = req.getParameter("ordid");
			String ordnum = req.getParameter("ordnum");
			String ordorddate = req.getParameter("ordorddate");
			String orddeldate = req.getParameter("orddeldate");
			String supid = req.getParameter("supid");
			String empid = req.getParameter("empid");
			
			Order order = new Order();
			
//			System.out.println("ordid: "+ordid);
			
			//更新数据库的数据
			if(StringUtil.isStringEmpty(ordnum))
				order.setOrdnum(Integer.parseInt(ordnum));
			if(StringUtil.isStringEmpty(ordid))
				order.setOrdid(Integer.parseInt(ordid));
			if(StringUtil.isStringEmpty(supid))
				order.setSupid(Integer.parseInt(supid));
			if(StringUtil.isStringEmpty(empid))
				order.setEmpid(Integer.parseInt(empid));
			
//			order.setOrddeldate(StringUtil.stringToDate(orddeldate));
//			order.setOrdorddate(StringUtil.stringToDate(ordorddate));
			
			
			order.setOrddeldate(StringUtil.stringToDate("2019-01-02"));
			order.setOrdorddate(StringUtil.stringToDate("2019-01-02"));

			order.setIsdelete(1);
			
//			System.out.println("ordnum: "+order.getOrdnum());
			
			
			boolean flag = orderBiz.updateOrder(order);
			
			System.out.println("update result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭

			}
			else if(method.equals("add")){
			
//			String ordid = req.getParameter("ordid");
			String supid = req.getParameter("supid");
			String ordnum = req.getParameter("ordnum");
			String ordorddate = req.getParameter("ordorddate");
			String orddeldate = req.getParameter("orddeldate");
			String empid = req.getParameter("empid");
			
			Order order = new Order();
			
			
//			order.setOrdid(Integer.parseInt(ordid));
			order.setSupid(Integer.parseInt(supid));
			order.setOrdnum(Integer.parseInt(ordnum));
			order.setOrdorddate(StringUtil.stringToDate(ordorddate));
			order.setOrddeldate(StringUtil.stringToDate(orddeldate));
			order.setEmpid(Integer.parseInt(empid));
			
			boolean flag = orderBiz.addOrder(order);
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
