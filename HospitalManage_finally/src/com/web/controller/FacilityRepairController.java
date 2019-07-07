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

import org.apache.logging.log4j.core.net.Facility;

import com.web.biz.impl.FacilityRepairBizImpl;
import com.web.biz.FacilityRepairBiz;
import com.web.entity.Department;
import com.web.entity.Facilities;
import com.web.entity.Facilityrepair;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/facilityrepair")
public class FacilityRepairController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		FacilityRepairBiz facilityRepairBiz = new FacilityRepairBizImpl();
		
		if(method.equals("query")){

			List<Facilityrepair> facilityRepairList = facilityRepairBiz.queryAll();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(facilityRepairList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("queryFaciPartly")){
			
			List<Facilities> fcList = facilityRepairBiz.queryFc();
			
			List<Facilityrepair> frList = facilityRepairBiz.queryFr();
			
//			System.out.println("fcList: " + fcList.size());
//			System.out.println("frList: " + frList.size());
			
			for(int i = 0; i < frList.size(); ++i)
			{
				for(int j = 0; j < fcList.size(); ++j)
				{
					if(frList.get(i).getFaciid() == fcList.get(j).getFaciid())
					{
						fcList.remove(j);
						if(j != 0)
							j--;
					}
				}
			}
			
			String json = new String();
			
			if(fcList.isEmpty())
			{
				json = JsonUtil.getJson(null);
			}
			else
			{
				json = JsonUtil.getJson(fcList);
			}
			
//			System.out.println("json: " + fcList.get(0).getFaciid());
			
			//把数据转换为json字符串
			
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
//			System.out.println(json);
		}
		else if(method.equals("queryDeptPartly")){
			
			List<Department> deptList = facilityRepairBiz.queryDept();
			
			List<Facilityrepair> fcDeptList = facilityRepairBiz.queryFcDept();
			
			
			for(int i = 0; i < fcDeptList.size(); ++i)
			{
				for(int j = 0; j < deptList.size(); ++j)
				{
					if(fcDeptList.get(i).getFaciredeptid() == deptList.get(j).getDeptid())
					{
						deptList.remove(j);
						if(j != 0)
							j--;
					}
				}
			}
		
			String json = JsonUtil.getJson(deptList);
			//把数据转换为json字符串
			
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("queryByJoin")){
			
			List<Facilityrepair> frList = facilityRepairBiz.queryByJoin();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(frList);
			
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println(json);
			
		}
		else if(method.equals("queryByCondition")){
			
			String faciname = req.getParameter("faciname");
			String facirefirm = req.getParameter("facirefirm");
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("faciname", faciname);
			map.put("facirefirm", facirefirm);
			
			
			List<Facilityrepair> frList = facilityRepairBiz.queryByCondition(map);
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(frList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("sendUpdate")){
			
			String faciid = req.getParameter("faciid");
			
			
			Facilityrepair facilityrepair = facilityRepairBiz.findByID(Integer.parseInt(faciid));
			
			String json = JsonUtil.getJson(facilityrepair);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		else if(method.equals("delete")){
			String faciid = req.getParameter("faciid");
			
			boolean flag = facilityRepairBiz.deleteById(Integer.parseInt(faciid));
			
			
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
			String faciid = req.getParameter("faciid");

			String faciredeptid = req.getParameter("deptid");
			String facirebudget = req.getParameter("facirebudget");
			String faciredate = req.getParameter("faciredate");
			String facirefirm = req.getParameter("facirefirm");
			String facirefee = req.getParameter("facirefee");
			
			Facilityrepair facilityrepair = new Facilityrepair();
			
			if(StringUtil.isStringEmpty(faciid))
				facilityrepair.setFaciid(Integer.parseInt(faciid));
			if(StringUtil.isStringEmpty(faciredeptid))
				facilityrepair.setFaciredeptid(Integer.parseInt(faciredeptid));
			if(StringUtil.isStringEmpty(facirebudget))
				facilityrepair.setFacirebudget(Float.parseFloat(facirebudget));
			if(StringUtil.isStringEmpty(faciredate))
				facilityrepair.setFaciredate(StringUtil.stringToDate(faciredate));
			if(StringUtil.isStringEmpty(facirefirm))
				facilityrepair.setFacirefirm(facirefirm);
			if(StringUtil.isStringEmpty(facirefee))
				facilityrepair.setFacirefee(Float.parseFloat(facirefee));
			


			facilityrepair.setIsdelete(1);
			
			
			boolean flag = facilityRepairBiz.updateFacilityRepair(facilityrepair);
			
			System.out.println("update result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭

			}
			else if(method.equals("add")){
			
			String faciid = req.getParameter("faciid");
			
//			System.out.println("faciid: "+faciid);

			String faciredeptid = req.getParameter("deptid");
			String facirebudget = req.getParameter("facirebudget");
			String faciredate = req.getParameter("faciredate");
			String facirefirm = req.getParameter("facirefirm");
			String facirefee = req.getParameter("facirefee");
			
			Facilityrepair facilityrepair = new Facilityrepair();
			
			if(StringUtil.isStringEmpty(faciid))
				facilityrepair.setFaciid(Integer.parseInt(faciid));
			if(StringUtil.isStringEmpty(faciredeptid))
				facilityrepair.setFaciredeptid(Integer.parseInt(faciredeptid));
			if(StringUtil.isStringEmpty(facirebudget))
				facilityrepair.setFacirebudget(Float.parseFloat(facirebudget));
//			if(StringUtil.isStringEmpty(faciredate))
//				facilityrepair.setFaciredate(StringUtil.stringToDate(faciredate));
			if(StringUtil.isStringEmpty(facirefirm))
				facilityrepair.setFacirefirm(facirefirm);
			if(StringUtil.isStringEmpty(facirefee))
				facilityrepair.setFacirefee(Float.parseFloat(facirefee));
			

			facilityrepair.setFaciredate(StringUtil.stringToDate("2019-01-02"));
			
			
			boolean flag = facilityRepairBiz.addFacilityRepair(facilityrepair);
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
