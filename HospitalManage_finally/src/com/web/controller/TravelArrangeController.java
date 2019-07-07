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

import com.web.biz.impl.TravelArrangeBizImpl;
import com.web.biz.TravelArrangeBiz;
import com.web.entity.Employee;
import com.web.entity.Travelactivities;
import com.web.entity.Travelarrange;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/travelarrange")
public class TravelArrangeController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		TravelArrangeBiz travelArrangeBiz = new TravelArrangeBizImpl();
		
		if(method.equals("query")){

			List<Travelarrange> travelArrangeList = travelArrangeBiz.queryAll();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(travelArrangeList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("queryTravelId")){
			
			List<Travelactivities> tacList = travelArrangeBiz.queryTravelId();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(tacList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
//			System.out.println(json);
		}
		else if(method.equals("queryTravelEmpId")){
			
			List<Employee> EmployeeList = travelArrangeBiz.queryTravelEmpId();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(EmployeeList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("queryByJoin")){
			
			List<Travelarrange> taList = travelArrangeBiz.queryByJoin();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(taList);
			
//			System.out.println("getin"+taList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println(json);
			
		}
		else if(method.equals("queryByCondition")){
			
			String traname = req.getParameter("traname");
			String empname = req.getParameter("empname");
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("traname", traname);
			map.put("empname", empname);
			
			
			List<Travelarrange> taList = travelArrangeBiz.queryByCondition(map);
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(taList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("sendUpdate")){
			
			String traid = req.getParameter("traid");
			String empid = req.getParameter("empid");
			
//			System.out.println("traid: "+traid);
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("traid", Integer.parseInt(traid));
			map.put("empid", Integer.parseInt(empid));
			
			Travelarrange travelarrange = travelArrangeBiz.findByID(map);
			
//			System.out.println("tra: "+travelarrange.getTravellicence());
	
			String json = JsonUtil.getJson(travelarrange);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("delete")){
			String traid = req.getParameter("traid");
			String empid = req.getParameter("empid");
			
			System.out.println("traid: "+traid);
			System.out.println("empid: "+empid);

			Map<String, Object> map = new HashMap<>();
			
			if(StringUtil.isStringEmpty(traid))
				map.put("traid", Integer.parseInt(traid));
			if(StringUtil.isStringEmpty(empid))
				map.put("empid", Integer.parseInt(empid));
			
			
			boolean flag = travelArrangeBiz.deleteById(map);
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
			String traid = req.getParameter("traid");
			String empid = req.getParameter("empid");
			String travellicence = req.getParameter("travellicence");
			
			Travelarrange travelarrange = new Travelarrange();
			
			System.out.println("traid: "+traid);
			
			//更新数据库的数据
			if(StringUtil.isStringEmpty(traid))
				travelarrange.setTravelid(Integer.parseInt(traid));
			if(StringUtil.isStringEmpty(empid))
				travelarrange.setTravelempid(Integer.parseInt(empid));
			if(StringUtil.isStringEmpty(travellicence))
				travelarrange.setTravellicence(travellicence);
			
//			order.setOrddeldate(StringUtil.stringToDate(orddeldate));
//			order.setOrdorddate(StringUtil.stringToDate(ordorddate));
			
		

			travelarrange.setIsdelete(1);
			
//			System.out.println("ordnum: "+order.getOrdnum());
			
			
			boolean flag = travelArrangeBiz.updateTravelArrange(travelarrange);
			
			System.out.println("update result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭

			}
			else if(method.equals("add")){
			
			String traid = req.getParameter("traid");
			String empid = req.getParameter("empid");
			String travellicence = req.getParameter("travellicence");

			
			Travelarrange travelarrange = new Travelarrange();
			
			
			travelarrange.setTravelid(Integer.parseInt(traid));
			travelarrange.setTravelempid(Integer.parseInt(empid));
			travelarrange.setTravellicence(travellicence);
			
			boolean flag = travelArrangeBiz.addTravelArrange(travelarrange);
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
