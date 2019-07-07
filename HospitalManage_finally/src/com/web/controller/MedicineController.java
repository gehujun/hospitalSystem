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

import com.web.biz.impl.MedicineBizImpl;
import com.web.biz.MedicineBiz;
import com.web.entity.Medicine;
import com.web.entity.Medicinelibrary;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/medicine")
public class MedicineController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MedicineBiz medBiz = new MedicineBizImpl();
		
		if(method.equals("query")){

			List<Medicine> medicineList = medBiz.queryAll();
			
			System.out.println(medicineList);
		
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(medicineList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}else if(method.equals("queryByJoin")){
			
			List<Medicinelibrary> MedicinelibraryList = medBiz.queryByJoin();
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(MedicinelibraryList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println(json);
			
		}else if(method.equals("queryByCondition")){
			
			String medifirm = req.getParameter("medifirm");
			String meliaddr = req.getParameter("meliaddr");
			
//			System.out.println("meliaddr: "+meliaddr);
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("medifirm", medifirm);
			map.put("meliaddr", meliaddr);
			
//			System.out.println("medifirm: "+medifirm);
//			System.out.println("meliaddr: "+meliaddr);
			
			List<Medicine> medicineList = medBiz.queryByCondition(map);
			
			System.out.println("List:  "+medicineList);
			//把数据转换为json字符串
			String json = JsonUtil.getJson(medicineList);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();

			
		}else if(method.equals("sendUpdate")){
			
			String mediid = req.getParameter("mediid");
			Medicine medicine = medBiz.findByID(Integer.parseInt(mediid));
//			System.out.println("mediid:"+medicine.getMediname());
	
			String json = JsonUtil.getJson(medicine);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			
			
		}else if(method.equals("delete")){
			String mediid = req.getParameter("mediid");
			Medicine medicine = new Medicine();
			medicine.setMediid(Integer.parseInt(mediid));
			
			boolean flag = medBiz.deleteById(Integer.parseInt(mediid));
			System.out.println("delete result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
			
		}else if(method.equals("update")){
			//获取用户输入的数据
			String mediid = req.getParameter("mediid");
			String meliid = req.getParameter("meliid");
			String mediname = req.getParameter("mediname");
			String medispecifi = req.getParameter("medispecifi");
			String mediunit = req.getParameter("mediunit");
			String mediprice = req.getParameter("mediprice");
			String mediexpirationdate = req.getParameter("mediexpirationdate");
			String medifirm = req.getParameter("medifirm");
			
			Medicine medicine = new Medicine();
			
			//更新数据库的数据
//			System.out.println(mediid);
			if(StringUtil.isStringEmpty(mediid))
				medicine.setMediid(Integer.parseInt(mediid));
			if(StringUtil.isStringEmpty(meliid))
				medicine.setMeliid(Integer.parseInt(meliid));
			if(StringUtil.isStringEmpty(mediname))
				medicine.setMediname(mediname);
			if(StringUtil.isStringEmpty(medispecifi))
				medicine.setMedispecifi(Float.parseFloat(medispecifi));
			if(StringUtil.isStringEmpty(mediunit))
				medicine.setMediunit(mediunit);
			if(StringUtil.isStringEmpty(mediprice))
				medicine.setMediprice(Float.parseFloat(mediprice));
//			if(StringUtil.isStringEmpty(mediexpirationdate))
//				medicine.setMediexpirationdate(StringUtil.stringToDate(mediexpirationdate));
			if(StringUtil.isStringEmpty(medifirm))
				medicine.setMedifirm(medifirm);
			
			medicine.setIsdelete(1);
			medicine.setMediexpirationdate(StringUtil.stringToDate("2019-01-02"));
			
			
			boolean flag = medBiz.updateMedicine(medicine);
			
//			System.out.println("medicine:"+medicine.getMediprice());
			
			System.out.println("update result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
			
			 
			
			
		}else if(method.equals("add")){
			
//			String mediid = req.getParameter("mediid");
//			String meliid = req.getParameter("meliid");
			
			String mediaddr = req.getParameter("mediaddr");
			String mediname = req.getParameter("mediname");
			String medispecifi = req.getParameter("medispecifi");
			String mediunit = req.getParameter("mediunit");
			String mediprice = req.getParameter("mediprice");
			String mediexpirationdate = req.getParameter("mediexpirationdate");
			String medifirm = req.getParameter("medifirm");
			
			
			Medicine medicine = new Medicine();
			
//			medicine.setMediid(Integer.parseInt(mediid));
			medicine.setMeliid(Integer.parseInt(mediaddr));			//这里的mediaddr就是meliid		
			medicine.setMediname(mediname);
			medicine.setMedispecifi(Float.parseFloat(medispecifi));
			medicine.setMediunit(mediunit);
			medicine.setMediprice(Float.parseFloat(mediprice));
			medicine.setMediexpirationdate(StringUtil.stringToDate(mediexpirationdate));
			medicine.setMedifirm(medifirm);
			medicine.setIsdelete(1);
			
			
			
			boolean flag = medBiz.addMedicine(medicine);
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
