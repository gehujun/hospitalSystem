package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import com.web.biz.InpatientBiz;
import com.web.biz.PatientBiz;
import com.web.biz.medicalAdviceBiz;
import com.web.biz.impl.InpatientBizImpl;
import com.web.biz.impl.PatientBizImpl;
import com.web.biz.impl.medicalAdviceBizImpl;
import com.web.entity.Medicaladvice;
import com.web.entity.Patient;

import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@WebServlet(urlPatterns = "/medicaladvice")
public class medicalAdviceController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getParameter("method");
		medicalAdviceBiz mab = new medicalAdviceBizImpl();
		InpatientBiz ib = new InpatientBizImpl();
		PatientBiz pb = new PatientBizImpl();
		if ("query".equals(method)) {
			
			String diseaseName = req.getParameter("diseaseName");
			String empId = req.getParameter("empId");
			String maId = req.getParameter("maId");
			
			Medicaladvice medicaladvice = new Medicaladvice();
			if (empId != null && empId != "") {
				medicaladvice.setEmpid(Integer.parseInt(empId));
			}
			if (maId != null && maId != "") {
				medicaladvice.setMaid(Integer.parseInt(maId));
			}
			
			medicaladvice.setDiseasename(diseaseName);
			System.out.println("diseaseName:"+medicaladvice.getDiseasename());
			List<Medicaladvice> list = mab.query(medicaladvice);
			
			String json = JsonUtil.getJson(list);
			System.out.println("medicalAdvice查询结果：" + json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("add".equals(method)) {
			System.out.println("add:");
			String diseaseName = req.getParameter("diseaseName");
			String madate = req.getParameter("madate");
			String maContent = req.getParameter("maContent");
			String empId = req.getParameter("empId");
			String inpatId = req.getParameter("inpatId");
			String isOut = req.getParameter("isOut");
			System.out.println(diseaseName);
			Medicaladvice medicaladvice = new Medicaladvice();
			
			medicaladvice.setDiseasename(diseaseName);
			if (empId != null && empId != "") {
				medicaladvice.setEmpid(Integer.parseInt(empId));
			}
			if (inpatId != null && inpatId != "") {
				medicaladvice.setInpatid(Integer.parseInt(inpatId));
			}
			
			medicaladvice.setIsout(isOut);
			medicaladvice.setMacontent(maContent);
			medicaladvice.setMadate(StringUtil.stringToDate(madate));
			medicaladvice.setIsdelete(1);
			boolean flag = mab.add(medicaladvice);
			String json = JsonUtil.getJson(flag);
			System.out.println(json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("UTF-8"); //把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("sendUpdate".equals(method)) {
			String maId = req.getParameter("maId");
			
			Medicaladvice medicaladvice = new Medicaladvice();
			if (maId != null && maId != "") {
				medicaladvice = mab.findById(Integer.parseInt(maId));
			}
			
			//把数据转换为json字符串
			String json = JsonUtil.getJson(medicaladvice); 
//			System.out.println("maId: " + maId);
			System.out.println("??????????????????????????????medicaladvice: " + json);
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("update".equals(method)) {
			String maId = req.getParameter("maId");
			
			String diseaseName = req.getParameter("diseaseName");
			String maContent = req.getParameter("maContent");
			String empId = req.getParameter("empId");
			
			String isOut = req.getParameter("isOut");
			Medicaladvice medicaladvice = new Medicaladvice();
			if (maId != null && maId != "") {
				medicaladvice.setMaid(Integer.parseInt(maId));
			}
			
			medicaladvice.setDiseasename(diseaseName);
			
			medicaladvice.setIsout(isOut);
			medicaladvice.setMacontent(maContent);
			boolean flag = mab.update(medicaladvice);
			
			Patient patient = new Patient();
			Integer inpatId = -1;
			
			if (maId != null && maId != "") {
				inpatId = mab.findinpatId(Integer.parseInt(maId));
			}
			
			
			Integer patId = ib.findPatId(inpatId);
			
			System.out.println("patId: " + patId);
			String patName = req.getParameter("patName");
			String patSex = req.getParameter("patSex");
			String patAge = req.getParameter("patAge");
			
			System.out.println(patAge);
			if (patAge != null && patAge != "") {
				patient.setPatage(Integer.parseInt(patAge));
			}
			patient.setPatid(patId);
			patient.setPatname(patName);
			patient.setPatsex(patSex);
			boolean mark = pb.updatePatient(patient);
			//把数据转换为json字符串
			String json = JsonUtil.getJson(mark); 
			
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("delete".equals(method)) {
			String maId = req.getParameter("maId");
			System.out.println(maId);
			//调用业务逻辑层的查询方法
			
			boolean flag = false;
			if (maId != null && maId != "") {
				flag = mab.delete(Integer.parseInt(maId));
			}
			
			System.out.println(flag);
			
			String json = JsonUtil.getJson(flag); 
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		} else if ("maIdSelect".equals(method)) {
			List<Medicaladvice> list = mab.maIdSelect();
			String json = JsonUtil.getJson(list); 
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		}
	}
}
