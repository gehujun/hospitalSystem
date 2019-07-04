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

import com.web.biz.BedBiz;
import com.web.biz.DutytableBiz;
import com.web.biz.impl.BedBizImpl;
import com.web.biz.impl.DutytableBizImpl;
import com.web.entity.Bed;
import com.web.entity.Dutytable;
import com.web.entity.Employee;
import com.web.entity.Inprescription;
import com.web.entity.Medicaladvice;
import com.web.entity.Ward;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@WebServlet(urlPatterns="/dutytable")
public class DutytableController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getParameter("method");
		  
		  //ʵ����ҵ���߼���
		DutytableBiz db = new DutytableBizImpl();
		
		  if("query".equals(method)){
			  String wardname=req.getParameter("wardname");
			  
			  String emp = req.getParameter("emp");
			  System.out.println("wardname:"+wardname);
			  System.out.println("emp:"+emp);
			  Map<String, Object> map = new HashMap<>();
			  
			  if(StringUtil.isStringEmpty(wardname))
				  map.put("wardname",wardname);
			  if(StringUtil.isStringEmpty(emp))
				  map.put("emp",emp);
			  
			  
			  
			  Inprescription inprescription=new Inprescription();
			  
			  List<Dutytable> list = db.queryByCondition(map);
			  System.out.println("qury: "+list.get(0).getDutyid());
			  String json = JsonUtil.getJson(list);	  
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//��json���ݵı����ʽ����ΪUTF-8
			  out.write(json);//���
			  out.flush();//ˢ��
			  out.close();//�ر�
			  
		  }else if("sendUpdate".equals(method)){
			  
			  String dutyid = req.getParameter("dutyid");
			  
			  Dutytable dutytable=db.findById(Integer.parseInt(dutyid));
			  		  
			  String json = JsonUtil.getJson(dutytable);
			  System.out.println("zcxdeshuchu:"+json);
			  //��ȡ�������
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//��json���ݵı����ʽ����ΪUTF-8
			  out.write(json);//���
			  out.flush();//ˢ��
			  out.close();//�ر�
			  
		  }else if("delete".equals(method)){
			
			  String dutyid = req.getParameter("dutyid");
			 
			  boolean flag =  db.delete(Integer.parseInt(dutyid));		
			  
			  System.out.println(flag);
			  
			  String json=JsonUtil.getJson(flag);
			  
			  System.out.println("����ɾ��ѽѽѽѽѽѽ����");
			 //��ȡ�������
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//��json���ݵı����ʽ����ΪUTF-8
			  out.write(flag ? "1" : "0");//���
			  out.flush();//ˢ��
			  out.close();//�ر�
		  }else if("update".equals(method)){
			  //��ת���޸�ҳ��  ����Ҫ�������������ݿ��в�ѯԭ����������Ϣ)
//			  System.out.println("���");
			  
			  String emp = req.getParameter("emp");
			  
			  //System.out.println("mediid: "+mediid);
			 

			  String wardname = req.getParameter("wardname");
			  //System.out.println("maid: "+maid);
			  String dutydate = req.getParameter("dutydate");
			  
			  String dutyid = req.getParameter("dutyid");
			  System.out.println("dutyid: "+dutyid);
			  
			  Dutytable dutytable=new Dutytable();
			  
			  dutytable.setIsdelete(1);
			  if (dutyid != null && dutyid != "") {
				  dutytable.setDutyid(Integer.parseInt(dutyid));
			  }
			  
			  if(StringUtil.isStringEmpty(emp))
			  {
				  dutytable.setEmpid(Integer.parseInt(emp));
			  }
			  
			  if(StringUtil.isStringEmpty(wardname))
			  {
				  dutytable.setWardname(wardname);
			  }
			  
			  if(StringUtil.isStringEmpty(dutydate))
			  {
				  SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
				  try {
					  
					  dutytable.setDutydate(sdf.parse(dutydate));
					
				} catch (Exception e) {
					e.printStackTrace();
			  }
			  }
			  
			  
			  //����ҵ���߼�����޸ķ���
			 boolean flag =  db.update(dutytable);
			 
			 System.out.println(flag);
			 
			 //��ȡ�������
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//��json���ݵı����ʽ����ΪUTF-8
			  out.write(flag ? "1" : "0");//���
			  out.flush();//ˢ��
			  out.close();//�ر�
		  }else if("add".equals(method)){
		  		
			  System.out.println("���");

			  String emp = req.getParameter("emp");
			  
			  //System.out.println("mediid: "+mediid);
			 

			  String wardname = req.getParameter("wardname");
			  //System.out.println("maid: "+maid);
			  String dutydate = req.getParameter("dutydate");
			 
			  
			  Dutytable dutytable=new Dutytable();
			  
			  dutytable.setIsdelete(1);
			  
			  
			  if(StringUtil.isStringEmpty(emp))
			  {
				  dutytable.setEmpid(Integer.parseInt(emp));
			  }
			  
			  if(StringUtil.isStringEmpty(wardname))
			  {
				  dutytable.setWardname(wardname);
			  }
			  
			  if(StringUtil.isStringEmpty(dutydate))
			  {
				  SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
				  try {
					  
					  dutytable.setDutydate(sdf.parse(dutydate));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			  }
			  
			  
			  //����ҵ���߼�����޸ķ���
			 boolean flag =  db.add(dutytable);
			 
			 System.out.println(flag);
			 
			 //��ȡ�������
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//��json���ݵı����ʽ����ΪUTF-8
			  out.write(flag ? "1" : "0");//���
			  out.flush();//ˢ��
			  out.close();//�ر�
		  }if ("empSelect".equals(method)) {
				

				String empid = req.getParameter("empid");
				String empname = req.getParameter("empname");
				
				Employee employee = new Employee();
				
				if (empid != null && empid != "") {
					employee.setEmpid(Integer.parseInt(empid));
				}
				System.out.println("hello1111");
				
				employee.setEmpname(empname);
				
				
				List<Employee> list = db.empSelect();
				
				System.out.println("hello222222");
				
				String json = JsonUtil.getJson(list);
				System.out.println(json);
				PrintWriter out = resp.getWriter();
				resp.setContentType("UTF-8");
				out.write(json);
				out.flush();
				out.close();
			}if ("wardnameSelect".equals(method)) {
				
				String wardname = req.getParameter("wardname");
				
				Ward ward = new Ward();
				
				ward.setWardname(wardname);
				
				List<Ward> list = db.wardnameSelect();
				
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
