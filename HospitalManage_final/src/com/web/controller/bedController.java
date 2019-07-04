package com.web.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.bedBiz;
import com.web.biz.impl.bedBizImpl;
import com.web.entity.Bed;
import com.web.util.JsonUtil;
@WebServlet(urlPatterns="/bed")
public class bedController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getParameter("method");
		
		bedBiz bb = new bedBizImpl();
		
		if ("bedIdSelect".equals(method)) {
//			String bedId = req.getParameter("bedId");
//			String wardName = req.getParameter("wardName");
//			
//			Bed bed = new Bed();
//			bed.setBedId(bedId);
//			bed.setWardName(wardName);
			
			List<String> list = bb.bedIdSelect();
			String json = JsonUtil.getJson(list);
			System.out.println("Bed hello");
			System.out.println(json);
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);
			out.flush();out.close();
		}
		
	}
}
