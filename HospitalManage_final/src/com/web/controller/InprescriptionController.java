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

import com.web.biz.BedBiz;
import com.web.biz.InprescriptionBiz;
import com.web.biz.impl.BedBizImpl;
import com.web.biz.impl.InprescriptionBizImpl;
import com.web.entity.Bed;
import com.web.entity.Inprescription;
import com.web.entity.Medicaladvice;
import com.web.entity.Medicine;
import com.web.util.JsonUtil;
import com.web.util.MyBatisUtil;
import com.web.util.StringUtil;
@WebServlet(urlPatterns="/inprescription")
public class InprescriptionController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 String method = req.getParameter("method");		  
		  //实例化业务逻辑层
		  InprescriptionBiz ib = new InprescriptionBizImpl();
		  
		  if("query".equals(method)){
			  
			  String mediid = req.getParameter("mediid");
			  String maid = req.getParameter("maid");
			  
			  Map<String, Object> map = new HashMap<>();
			  
			  if(StringUtil.isStringEmpty(maid))
				  map.put("maid",maid);
			  if(StringUtil.isStringEmpty(mediid))
				  map.put("mediid",mediid);
			  
			  Inprescription inprescription=new Inprescription();
			  
			  
  
			//调用业务逻辑层中的查询方法
			  List<Inprescription> list = ib.queryByCondition(map);
	
			  String json = JsonUtil.getJson(list);	  
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
	
		  } else if("sendUpdate".equals(method)){

			  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
			  
			  String mediid = req.getParameter("mediid");
			  String maid = req.getParameter("maid");
			  
			
			  Inprescription inprescription =  ib.findById(Integer.parseInt(mediid),Integer.parseInt(maid));
		
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(inprescription);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
	}else if("delete".equals(method)){
		
		
//		  System.out.println("我是删除呀呀呀呀呀呀晕晕11111");
		  String mediid = req.getParameter("mediid");
		  String maid = req.getParameter("maid");
		 
		  boolean flag =  ib.delete(Integer.parseInt(mediid),Integer.parseInt(maid));		
		  
		  System.out.println(flag);
		  
		  String json=JsonUtil.getJson(flag);
		  
		  System.out.println("我是删除呀呀呀呀呀呀晕晕");
		 //获取输出对象
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
		  out.write(flag ? "1" : "0");//输出
		  out.flush();//刷新
		  out.close();//关闭
	  }else if("update".equals(method)){
		  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
		  System.out.println("你好");

		  String mediid = req.getParameter("medi");
		  
		  System.out.println("mediid: "+mediid);
		 

		  String maid = req.getParameter("maid");
		  System.out.println("maid: "+maid);
		  String medicinenum = req.getParameter("medicinenum");
		 
		  
		  Inprescription inprescription=new Inprescription();
		  
		  inprescription.setIsdelete(1);
		  
		  
		  if(StringUtil.isStringEmpty(mediid))
		  {
			  inprescription.setmediid(Integer.parseInt(mediid));
		  }
		  
		  if(StringUtil.isStringEmpty(maid))
		  {
			  inprescription.setMaid(Integer.parseInt(maid));
		  }
		  if(StringUtil.isStringEmpty(medicinenum))
		  {
			  inprescription.setMedicinenum(Integer.parseInt(medicinenum));
		  }
		  
		  
		  //调用业务逻辑层的修改方法
		 boolean flag =  ib.update(inprescription);
		 
		 System.out.println(flag);
		 
		 //获取输出对象
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
		  out.write(flag ? "1" : "0");//输出
		  out.flush();//刷新
		  out.close();//关闭
	  	}else if("add".equals(method)){
	  		
	  		 String mediid = req.getParameter("mediid");
	  		 System.out.println("mediid:"+mediid);
			  String maid = req.getParameter("maid");
			  System.out.println("maid:"+maid);
			  String medicinenum = req.getParameter("medicinenum");
			 
			  
			  Inprescription inprescription=new Inprescription();
			  inprescription.setIsdelete(1);
			  
			  
			  if(StringUtil.isStringEmpty(mediid))
			  {
				  inprescription.setmediid(Integer.parseInt(mediid));
			  }
			  
			  if(StringUtil.isStringEmpty(maid))
			  {
				  inprescription.setMaid(Integer.parseInt(maid));
			  }
			  if(StringUtil.isStringEmpty(medicinenum))
			  {
				  inprescription.setMedicinenum(Integer.parseInt(medicinenum));
			  }
			  boolean flag =  ib.add(inprescription);
			  
			 
			  System.out.println(flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
	  	}if ("maidSelect".equals(method)) {
			
			String maid = req.getParameter("maid");
			
			Medicaladvice medicaladvice= new Medicaladvice();

			if(StringUtil.isStringEmpty(maid))
			{
				medicaladvice.setMaid(Integer.parseInt(maid));
			}
			
			
			List<Medicaladvice> list = ib.maidSelect();
			System.out.println(list);
			String json = JsonUtil.getJson(list);
			System.out.println(json);
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}if ("mediidSelect".equals(method)) {
			
			
			String mediid = req.getParameter("mediid");
			System.out.println("我是药品ID筛选呀"+mediid);
			
			Medicine medicine= new Medicine();

			if(StringUtil.isStringEmpty(mediid))
			{
				medicine.setMediid(Integer.parseInt(mediid));
			}
			
			
			List<Medicine> list = ib.mediidSelect();
			
			String json = JsonUtil.getJson(list);
			System.out.println(json);
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}
		  
		
	}
}
