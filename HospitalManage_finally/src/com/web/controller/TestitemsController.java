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

import com.web.biz.TestitemsBiz;
import com.web.biz.impl.TestitemsBizImpl;
import com.web.entity.Testitems;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/testitems")
public class TestitemsController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		//实例化业务逻辑层
		TestitemsBiz ti=new TestitemsBizImpl();
		if("query".equals(method)){
			
			//接收前台传过来的参数
			String testid=req.getParameter("testid");
			String testname=req.getParameter("testname");
			String testtype=req.getParameter("testtype");
			String testfee=req.getParameter("testfee");
			
			Map<String, Object> map = new HashMap<>();
			if(StringUtil.isStringEmpty(testid)){
				map.put("testid", testid);
			}
			if(StringUtil.isStringEmpty(testname)){
				map.put("testname", testname);
			}
			if(StringUtil.isStringEmpty(testtype)){
				map.put("mediname", testtype);
			}
			if(StringUtil.isStringEmpty(testfee)){
				map.put("testfee", testfee);
			}
			
			Testitems testitems=new Testitems();
			
			List<Testitems> list=ti.queryByCondition(map);
			
			String json = JsonUtil.getJson(list);
			System.out.println(json);
			  
		    //获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		}else if("sendUpdate".equals(method)){
			  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
			  String testid = req.getParameter("testid");
			  System.out.println("从网页得到的主键值是: "+testid);
			  //调用业务逻辑层的查询方法
			  if(StringUtil.isStringEmpty(testid)){
				  Testitems testitems=ti.findByTestId(Integer.parseInt(testid));
				  //把数据转换为json字符串
				  String json = JsonUtil.getJson(testitems);
				  
				  System.out.println("从网页得daoshidiyi: "+testitems.getTesttaboo());
				  System.out.println("从网页得daoshidier: "+testitems.getTestexplain());
				  
				//获取输出对象
				  PrintWriter out = resp.getWriter();
				  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
				  out.write(json);//输出
				  out.flush();//刷新
				  out.close();//关闭
			  }
			  
			  
		  }else if("update".equals(method)){
			  //修改
			  //接收前台传过来的参数
			    String testid = req.getParameter("testid");
			    String testname = req.getParameter("testname");
			    String testtype = req.getParameter("testtype");
				String testfee=req.getParameter("testfee");
				String testexplain=req.getParameter("testexplain");
				String testtaboo=req.getParameter("testtaboo");
			  
			    Testitems testitems = new Testitems(); 
			    
			    if(StringUtil.isStringEmpty(testid)){
			    	testitems.setTestid(Integer.parseInt(testid));
			    }
			    
			    if(StringUtil.isStringEmpty(testname)){
			    	testitems.setTestname(testname);
			    }
			    
			    if(StringUtil.isStringEmpty(testtype)){
			    	testitems.setTesttype(testtype);
			    }
			    if(StringUtil.isStringEmpty(testfee)){
			    	testitems.setTestfee(Float.parseFloat(testfee));
			    }
			    if(StringUtil.isStringEmpty(testexplain)){
			    	testitems.setTestexplain(testexplain);
			    }
			    if(StringUtil.isStringEmpty(testtaboo)){
			    	testitems.setTesttaboo(testtaboo);
			    }
			  
			  //调用业务逻辑层的修改方法
			 boolean flag =  ti.update(testitems);
			 
			 System.out.println("更新状态："+flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if ("delete".equals(method)) {
			//删除
				String testid = req.getParameter("testid");
				
				if(StringUtil.isStringEmpty(testid)){
					//调用业务逻辑层的查询方法
					boolean flag =  ti.delete(Integer.parseInt(testid));
					System.out.println("删除状态:"+flag);
					
					String json = JsonUtil.getJson(flag); 
					//获取输出对象
					PrintWriter out = resp.getWriter();
					resp.setContentType("UTF-8");
					out.write(json);//输出
					out.flush();//刷新
					out.close();//关闭
				}
		  }else if ("add".equals(method)) {
			
			    String testname=req.getParameter("testname");
				String testtype=req.getParameter("testtype");
				String testfee=req.getParameter("testfee");
				String testexplain=req.getParameter("testexplain");
				String testtaboo=req.getParameter("testtaboo");
			  
			  
			    Testitems testitems = new Testitems(); 
			  
			    if(StringUtil.isStringEmpty(testname)){
			    	testitems.setTestname(testname);
			    }
			    
			    if(StringUtil.isStringEmpty(testtype)){
			    	testitems.setTesttype(testtype);
			    }
			    
			    if(StringUtil.isStringEmpty(testfee)){
			    	testitems.setTestfee(Float.parseFloat(testfee));
			    }
			    
			    if(StringUtil.isStringEmpty(testexplain)){
			    	testitems.setTestexplain(testexplain);
			    }
			    
			    if(StringUtil.isStringEmpty(testtaboo)){
			    	testitems.setTesttaboo(testtaboo);
			    }
			    
				boolean flag =  ti.add(testitems);
				System.out.println(flag);
	
				String json = JsonUtil.getJson(flag);
				
				//获取输出对象
				PrintWriter out = resp.getWriter();
				resp.setContentType("UTF-8");
				out.write(json);//输出
				out.flush();//刷新
				out.close();//关闭
			
		}
	}
}
