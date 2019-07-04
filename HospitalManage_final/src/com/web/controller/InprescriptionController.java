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
		  //ʵ����ҵ���߼���
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
			  
			  
  
			//����ҵ���߼����еĲ�ѯ����
			  List<Inprescription> list = ib.queryByCondition(map);
	
			  String json = JsonUtil.getJson(list);	  
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//��json���ݵı����ʽ����ΪUTF-8
			  out.write(json);//���
			  out.flush();//ˢ��
			  out.close();//�ر�
	
		  } else if("sendUpdate".equals(method)){

			  //��ת���޸�ҳ��  ����Ҫ�������������ݿ��в�ѯԭ����������Ϣ)
			  
			  String mediid = req.getParameter("mediid");
			  String maid = req.getParameter("maid");
			  
			
			  Inprescription inprescription =  ib.findById(Integer.parseInt(mediid),Integer.parseInt(maid));
		
			  //������ת��Ϊjson�ַ���
			  String json = JsonUtil.getJson(inprescription);
			  
			  //��ȡ�������
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//��json���ݵı����ʽ����ΪUTF-8
			  out.write(json);//���
			  out.flush();//ˢ��
			  out.close();//�ر�
	}else if("delete".equals(method)){
		
		
//		  System.out.println("����ɾ��ѽѽѽѽѽѽ����11111");
		  String mediid = req.getParameter("mediid");
		  String maid = req.getParameter("maid");
		 
		  boolean flag =  ib.delete(Integer.parseInt(mediid),Integer.parseInt(maid));		
		  
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
		  System.out.println("���");

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
		  
		  
		  //����ҵ���߼�����޸ķ���
		 boolean flag =  ib.update(inprescription);
		 
		 System.out.println(flag);
		 
		 //��ȡ�������
		  PrintWriter out = resp.getWriter();
		  resp.setContentType("UTF-8");//��json���ݵı����ʽ����ΪUTF-8
		  out.write(flag ? "1" : "0");//���
		  out.flush();//ˢ��
		  out.close();//�ر�
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
			 
			 //��ȡ�������
			  PrintWriter out = resp.getWriter();
			  
			  resp.setContentType("UTF-8");//��json���ݵı����ʽ����ΪUTF-8
			  out.write(flag ? "1" : "0");//���
			  out.flush();//ˢ��
			  out.close();//�ر�
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
			System.out.println("����ҩƷIDɸѡѽ"+mediid);
			
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
