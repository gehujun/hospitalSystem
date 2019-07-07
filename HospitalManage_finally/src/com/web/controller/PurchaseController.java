package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.PurchaseBiz;
import com.web.biz.impl.PurchaseBizImpl;
import com.web.entity.Employee;
import com.web.entity.Facilities;
import com.web.entity.Purchase;
import com.web.entity.Supplier;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/purchase")
public class PurchaseController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		String method = req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		PurchaseBiz pBiz=new PurchaseBizImpl();
		if(method.equals("query")){
			List<Purchase> purchaseList=pBiz.query();
			System.out.println(purchaseList);
			String json=JsonUtil.getJson(purchaseList);
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			
		}else if(method.equals("querySupId")){
			List<Supplier> sList=pBiz.querySupId();
			String json=JsonUtil.getJson(sList);
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println(json);
		}else if(method.equals("queryEmpId")){
			List<Employee> eList=pBiz.queryEmpId();
			String json=JsonUtil.getJson(eList);
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println(json);
			
		}else if(method.equals("queryByJoin")){
			List<Purchase> purchaseList=pBiz.queryByJoin();
			String json=JsonUtil.getJson(purchaseList);
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
			System.out.println(json);
		}else if(method.equals("queryByCondition")){
			String supname=req.getParameter("supname");
			String empname=req.getParameter("empname");
			String purname=req.getParameter("purname");
			
			Map<String,Object> map=new HashMap<>();
			map.put("supname", supname);
			map.put("empname", empname);
			map.put("purname", purname);
			List<Purchase> pList=pBiz.queryByCondition(map);
			String json=JsonUtil.getJson(pList);
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}else if(method.equals("sendUpdate")){
			String purid=req.getParameter("purid");
			Purchase purchase=pBiz.findById(Integer.parseInt(purid));
			String json=JsonUtil.getJson(purchase);
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		}else if(method.equals("delete")){
			String purid=req.getParameter("purid");
			Purchase purchase=new Purchase();
			if(purid!=null && purid!=""){
				purchase.setPurid(Integer.parseInt(purid));
			}
			boolean flag=pBiz.deleteById(purchase);
			System.out.println("delete result:"+flag);
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			out.write(flag ? "1" : "0");//输出
			out.flush();//刷新
			out.close();//关闭
		}else if(method.equals("update")){
			String purid=req.getParameter("purid");
			String purname=req.getParameter("purname");
			String supid=req.getParameter("supid");
			String empid=req.getParameter("empid");
			String purdate=req.getParameter("purdate");
			String purnum=req.getParameter("purnum");
			String purfee=req.getParameter("purfee");
			
			Purchase purchase=new Purchase();
			if(StringUtil.isStringEmpty(purid))
				purchase.setPurid(Integer.parseInt(purid));
			if(StringUtil.isStringEmpty(purname))
				purchase.setPurname(purname);
			if(StringUtil.isStringEmpty(supid))
				purchase.setSupid(Integer.parseInt(supid));
			if(StringUtil.isStringEmpty(empid))
				purchase.setEmpid(Integer.parseInt(empid));
			if(StringUtil.isStringEmpty(purdate)){

				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

				try {
					//doctoradvice.setDocadate(sdf.parse(docadate));
					purchase.setPurdate(sdf.parse(purdate));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(StringUtil.isStringEmpty(purnum))
				purchase.setPurnum(Integer.parseInt(purnum));
			if(StringUtil.isStringEmpty(purfee))
				purchase.setPurfee(Float.parseFloat(purfee));
			purchase.setIsdelete(1);
			boolean flag=pBiz.updatePurchase(purchase);
			System.out.println("update result: "+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		}else if(method.equals("add")){
			//String purid=req.getParameter("purid");
			String purname=req.getParameter("purname");
			String supid=req.getParameter("supid");
			String empid=req.getParameter("empid");
			String purdate=req.getParameter("purdate");
			String purnum=req.getParameter("purnum");
			String purfee=req.getParameter("purfee");
			
			Purchase purchase=new Purchase();
			purchase.setIsdelete(1);
			
			purchase.setPurname(purname);
			purchase.setSupid(Integer.parseInt(supid));
			purchase.setEmpid(Integer.parseInt(empid));
			if(StringUtil.isStringEmpty(purdate)){

				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

				try {
					//doctoradvice.setDocadate(sdf.parse(docadate));
					purchase.setPurdate(sdf.parse(purdate));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			purchase.setPurnum(Integer.parseInt(purnum));
			purchase.setPurfee(Float.parseFloat(purfee));
			
			boolean flag=pBiz.addPurchase(purchase);
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
