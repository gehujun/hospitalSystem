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

import com.web.biz.MedicineLibraryBiz;
import com.web.biz.impl.MedicineLibraryBizImpl;
import com.web.entity.Employee;
import com.web.entity.Medicinelibrary;
import com.web.entity.Registrationform;
import com.web.entity.TDepartment;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/medicineLibrary")
public class MedicineLibraryController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(req, resp);
		String method=req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MedicineLibraryBiz mdclBiz=new MedicineLibraryBizImpl();
		
		if(method.equals("sendUpdate")){
			String meliid = req.getParameter("meliid");
			
			Medicinelibrary medicinelibrary =mdclBiz.findById(Integer.parseInt(meliid));

			String json = JsonUtil.getJson(medicinelibrary);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}else if(method.equals("update")){
			String meliid = req.getParameter("meliid");
			
			String empid = req.getParameter("empid");
			String meliaddr = req.getParameter("meliaddr");
			String meliarea =req.getParameter("meliarea");
			String meliunit =req.getParameter("meliunit");
			
			Medicinelibrary medicinelibrary=new Medicinelibrary();
			//System.out.println(empId);
			if(StringUtil.isStringEmpty(meliid))
				medicinelibrary.setMeliid(Integer.parseInt(meliid));
			 
			if(StringUtil.isStringEmpty(empid))
				medicinelibrary.setMeliman(Integer.parseInt(empid));
			if(StringUtil.isStringEmpty(meliaddr))
				medicinelibrary.setMeliaddr(meliaddr);
			if(StringUtil.isStringEmpty(meliarea))
				medicinelibrary.setMeliarea(Float.parseFloat(meliarea));
			if(StringUtil.isStringEmpty(meliunit))
				medicinelibrary.setMeliunit(meliunit);
			 medicinelibrary.setIsdelete(1);
			boolean flag = mdclBiz.updateMedicineLib(medicinelibrary);
			
			
			System.out.println("update result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
			  
			
		}else if(method.equals("delete")){
			String meliid = req.getParameter("meliid");
			Medicinelibrary medicinelibrary=new Medicinelibrary();
			//medicinelibrary.setMeliid(Integer.parseInt(meLiId));
			if(meliid != null && meliid != "") {
				medicinelibrary.setMeliid(Integer.parseInt(meliid));
			}
			boolean flag=mdclBiz.deleteById(medicinelibrary); 
			System.out.println("delete result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  
		}else if(method.equals("add")){
			//String meliid = req.getParameter("meliid");
			String empid = req.getParameter("meliman");
			String meliaddr = req.getParameter("meliaddr");
			String meliarea =req.getParameter("meliarea");
			String meliunit =req.getParameter("meliunit");
			Medicinelibrary medicinelibrary=new Medicinelibrary();
			//medicinelibrary.setMeliid(Integer.parseInt(meliid));
			medicinelibrary.setIsdelete(1);
			medicinelibrary.setMeliman(Integer.parseInt(empid));
			medicinelibrary.setMeliaddr(meliaddr);
			medicinelibrary.setMeliarea(Float.parseFloat(meliarea));
			medicinelibrary.setMeliunit(meliunit);
			boolean flag=mdclBiz.addMedicineLib(medicinelibrary);
			
			
			System.out.println("add result: "+flag);
			 
		 //获取输出对象
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
		  out.write(flag ? "1" : "0");//输出
		  out.flush();//刷新
		  out.close();//关闭
		}else if(method.equals("query")){
			List<Medicinelibrary> mList = mdclBiz.query();

			String json = JsonUtil.getJson(mList);
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
			
		}else if(method.equals("queryByCondition")){
			
			String meliid = req.getParameter("meliid");
			String meliaddr = req.getParameter("meliaddr");
			
			
		/*	Medicinelibrary medicinelibrary = new Medicinelibrary();
			if(meliid!=""&&meliid!=null)
				medicinelibrary.setMeliid(Integer.parseInt(meliid));
			if(meliaddr!=""&&meliaddr!=null)
				medicinelibrary.setMeliaddr(meliaddr);*/
			Map<String, Object> map = new HashMap<>();
			
			map.put("meliid", meliid);
			map.put("meliaddr", meliaddr);
			
		     List<Medicinelibrary> ml=mdclBiz.queryByCondition(map);
		     
			//把数据转换为json字符串
			String json = JsonUtil.getJson(ml);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();	
			System.out.println("query result: "+"true");
		}else if(method.equals("queryByJoin")){
			
			List<Employee> MedicinelibraryList = mdclBiz.queryByJoin();
			
//			System.out.println("name: "+MedicinelibraryList.get(0).getMeliid());
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(MedicinelibraryList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
//			System.out.println("a"+MedicinelibraryList);
			
			out.flush();
			out.close();
			System.out.println(json);
			
		}
	}

}
