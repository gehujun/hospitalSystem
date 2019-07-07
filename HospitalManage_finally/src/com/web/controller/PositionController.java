package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.MenuBiz;
import com.web.biz.PositionBiz;
import com.web.biz.PositionMenuBiz;
import com.web.biz.impl.MenuBizImpl;
import com.web.biz.impl.PositionBizImpl;
import com.web.biz.impl.PositionMenuBizImpl;
import com.web.entity.Professionaltitle;
import com.web.entity.TMenu;
import com.web.entity.TPosition;
import com.web.pojo.MenuPojo;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
import com.web.util.TreePojo;

import sun.swing.StringUIClientPropertyKey;

@WebServlet(urlPatterns="/position")
public class PositionController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getParameter("method");
		PositionBiz pb = new PositionBizImpl();
		
		PositionMenuBiz pmb = new PositionMenuBizImpl();
		MenuBiz mb = new MenuBizImpl();
		
		if("query".equals(method)){
			
			Map<String, Object> map = new HashMap<>();
			String poName = req.getParameter("poName");
			if(StringUtil.isStringEmpty(poName)){
				map.put("poName", poName);
			}
			
			String poSalary1 = req.getParameter("poSalary1");
			if(StringUtil.isStringEmpty(poSalary1)){
				map.put("poSalary1", Double.parseDouble(poSalary1));
			}
			
			String poSalary2 = req.getParameter("poSalary2");
			if(StringUtil.isStringEmpty(poSalary2)){
				map.put("poSalary2", Double.parseDouble(poSalary2));
			}
			
			String deptIds = req.getParameter("deptId");
			if(StringUtil.isStringEmpty(deptIds)){
				Integer deptId = Integer.parseInt(deptIds);
				if(deptId  != 0){
					map.put("deptId", deptId);
				}
			}
			
			List<Professionaltitle> list = pb.queryByCondition(map);
			
			 //把数据转换为json字符串
			  String json = JsonUtil.getJson(list);
			  System.out.println(json);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
			  
		}else if ("sendAuthority".equals(method)){
			String poId = req.getParameter("poId");
			
			//根据职位id查询菜单id集合  （权限)
			List<Integer> menuList = pmb.findMenuByPoId(Integer.parseInt(poId));
			
			//查询所有的菜单
			List<TMenu> allMenu = mb.findByParentId(null);
			
			//匹配操作
			List<TreePojo> myList = merge(menuList,allMenu);
			
			//把数据转换为json字符串
			  String json = JsonUtil.getJson(myList);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		}else if("saveAuthority".equals(method)){
			String idss = req.getParameter("menuIds");//"1,2,3,4,5,6"
			String[] ids = idss.split(",");//【1,2,3,4,5】
			
			Integer poId = Integer.parseInt(req.getParameter("poId").toString());
			
			int[] idsInt = new int[ids.length];
			
			for(int i=0;i<ids.length;i++){
				idsInt[i]=Integer.parseInt(ids[i]);
			}
			
			boolean flag = pmb.saveAuthority(poId, idsInt);
			
			 //获取输出对象
			 PrintWriter out = resp.getWriter();
			 resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			 out.write(flag ? "1" : "0");//输出
			 out.flush();//刷新
			 out.close();//关闭
		}else if ("delete".equals(method)) {
			//删除
			String profid = req.getParameter("profid");
			System.out.println(profid);
			
			//调用业务逻辑层的查询方法
			boolean flag =  pb.delete(Integer.parseInt(profid));
			System.out.println(flag);
			
			String json = JsonUtil.getJson(flag);
			
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		}else if ("add".equals(method)) {
			String profid = req.getParameter("profid");
			String profname = req.getParameter("profname");
			String deptid = req.getParameter("deptid");
		    String s_profbasesalary = req.getParameter("profbasesalary");
			  
			Professionaltitle professionaltitle=new Professionaltitle();
			if(StringUtil.isStringEmpty(profid)){
				professionaltitle.setProfid(Integer.parseInt(profid));
			}
			professionaltitle.setProfname(profname);
			if(StringUtil.isStringEmpty(deptid)){
				professionaltitle.setDeptid(Integer.parseInt(deptid));
			}
			BigDecimal profbasesalary =new BigDecimal("0.0");
			if(StringUtil.isStringEmpty(s_profbasesalary)){
				profbasesalary = BigDecimal.valueOf(Double.parseDouble(s_profbasesalary));
			}
				 
			profbasesalary.setScale(0, BigDecimal.ROUND_HALF_UP);
			  
			if(profbasesalary!=null){
				
				professionaltitle.setProfbasesalary(Long.parseLong(s_profbasesalary));
				
			}
			
			professionaltitle.setIsdelete(1);
			
			//调用业务逻辑层的查询方法
			boolean flag =  pb.add(professionaltitle);
			System.out.println(flag);
			
			String json = JsonUtil.getJson(flag); 
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
			
		}else if("sendUpdate".equals(method)){
			  //跳转到修改页面  （需要根据主键从数据库中查询原来的数据信息)
			  String profid = req.getParameter("profid");
			  
			  //调用业务逻辑层的查询方法
			  Professionaltitle professionaltitle=pb.findById(Integer.parseInt(profid));
			  
			  //把数据转换为json字符串
			  String json = JsonUtil.getJson(professionaltitle);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }else if("update".equals(method)){
			  //修改
			  String profid = req.getParameter("profid");
			  String profname = req.getParameter("profname");
			  String deptid = req.getParameter("deptname");
			  String s_profbasesalary = req.getParameter("profbasesalary");
			  
			  Professionaltitle professionaltitle = new Professionaltitle();
			  if (profid!=null && profid!="") {
				  professionaltitle.setProfid(Integer.parseInt(profid));
			  }
			  professionaltitle.setProfname(profname);
			  if (deptid!=null && deptid!="") {
				  professionaltitle.setDeptid(Integer.parseInt(deptid));
			  }
			  
			  BigDecimal profbasesalary =new BigDecimal("0.0");
			  if(StringUtil.isStringEmpty(s_profbasesalary)){
					 profbasesalary = BigDecimal.valueOf(Double.parseDouble(s_profbasesalary));
				  }
				 
			  profbasesalary.setScale(0, BigDecimal.ROUND_HALF_UP);
			  
			  if(profbasesalary!=null){
				  professionaltitle.setProfbasesalary(Long.parseLong(s_profbasesalary));
			  }
			  
			  //调用业务逻辑层的修改方法
			 boolean flag =  pb.update(professionaltitle);
			 
			 System.out.println(flag);
			 
			 //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(flag ? "1" : "0");//输出
			  out.flush();//刷新
			  out.close();//关闭
		  }
	}
	
	/**
	 * 匹配权限
	 * @param menuList
	 * @param myList
	 * @return
	 */
	public List<TreePojo> merge(List<Integer> menuList,List<TMenu> allMenu ){
		
		List<TreePojo> list = new ArrayList<>();
		
		for (TMenu tMenu : allMenu) {
			TreePojo pj = new TreePojo();
			
			//判断是否有权限 来设置是否选中状态
			pj.setChecked(hasAuthority(tMenu.getMenuId(), menuList));
			
			//递归
			pj.setChildren(merge(menuList,tMenu.getChildMenu()));
			
			pj.setIcon(tMenu.getMenuImage());
			pj.setId(tMenu.getMenuId());
			pj.setUrl(tMenu.getMenuUrl());
			pj.setPid(tMenu.getParentId());
			pj.setName(tMenu.getMenuName());
			
			list.add(pj);
			
		}
		return list;
	}
	
	/**
	 * 判断是否有权限
	 * @param menuId
	 * @param menuList
	 * @return
	 */
	public boolean hasAuthority(Integer menuId,List<Integer> menuList){
		
		for (Integer b : menuList) {
			
			if(menuId == b){
				return true;//有权限
			}
		}
		return false;//没有权限
	}
	
	
	
	
}
