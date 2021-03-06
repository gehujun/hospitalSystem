package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.kaptcha.Constants;
import com.sun.glass.ui.Menu;
import com.web.biz.MenuBiz;
import com.web.biz.PositionMenuBiz;
import com.web.biz.UserBiz;
import com.web.biz.impl.MenuBizImpl;
import com.web.biz.impl.PositionMenuBizImpl;
import com.web.biz.impl.UserBizImpl;
import com.web.entity.Employee;
import com.web.entity.Professionaltitle;
import com.web.entity.TMenu;
import com.web.entity.TUser;
import com.web.pojo.MenuPojo;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;

@WebServlet(urlPatterns="/user")
public class UserController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getParameter("method");
		
		//实例化用户的业务逻辑层
		UserBiz ub = new UserBizImpl();
		
		PositionMenuBiz pmb = new PositionMenuBizImpl();
		
		MenuBiz mb = new MenuBizImpl();
		
		if("login".equals(method)){
			//登录
			
			//判断验证码是否输入正确
			HttpSession session = req.getSession();
			
			//获取随机生成的验证码
			String randomCode = session.getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
			
			//获取页面上输入的验证码
			String code = req.getParameter("code");
			
			if(code.equalsIgnoreCase(randomCode)){
				//判断用户名和密码在数据库中是否存在
				String userName = req.getParameter("userName");
				String userPwd = req.getParameter("userPwd");
				
				//调用用户的业务逻辑层方法
				Employee employee = ub.login(userName, userPwd);
				
				if(employee !=null){
					
					//得到用户的职位id
					Integer poId = employee.getProfid();
					
					//根据职位id查询菜单id集合  （权限)
					List<Integer> menuList = pmb.findMenuByPoId(poId);
					
					//查询所有的菜单
					List<TMenu> allMenu = mb.findByParentId(null);
					
					//匹配操作
					List<MenuPojo> myList = merge(menuList,allMenu);
					
					//把数据传到页面上去
					req.setAttribute("mymenuList", myList);
					
					//转发跳转页面
					req.getRequestDispatcher("gentelella/docs/main.jsp").forward(req, resp);
					
				}else{
					//用户名和密码输入错误
					req.getRequestDispatcher("gentelella/docs/login.jsp").forward(req, resp);
				}
				
			}else{
				//验证码输入错误
				req.getRequestDispatcher("gentelella/docs/login.jsp").forward(req, resp);
			}
		}else if(method.equals("query")){
			
			Map<String, Object> map = new HashMap<>();
			
			String empName = req.getParameter("empName");
			if(StringUtil.isStringEmpty(empName)){
				map.put("empName", empName);
			}
			
			String profName = req.getParameter("profName");
			if(StringUtil.isStringEmpty(profName)){
				if(!profName.equals("0"))
					map.put("profName", profName);
			}
			
			List<Employee> empList = ub.queryByCondition(map);
			
			 //把数据转换为json字符串
			  String json = JsonUtil.getJson(empList);
			  System.out.println(json);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		}else if ("delete".equals(method)) {
			//删除
			String empid = req.getParameter("empid");
			System.out.println(empid);
			
			//调用业务逻辑层的查询方法
			boolean flag =  ub.delete(Integer.parseInt(empid));
			System.out.println(flag);
			
			String json = JsonUtil.getJson(flag);
			
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		}else if(method.equals("add")){
			System.out.println("add");
			String empName = req.getParameter("empName");
			String empSex = req.getParameter("empSex");
			String empProfId = req.getParameter("empProf");
			String empPwd = req.getParameter("empPwd");
			
			Employee employee = new Employee();
			
			if(StringUtil.isStringEmpty(empName)){
				employee.setEmpname(empName);
			}
			if(StringUtil.isStringEmpty(empSex)){
				employee.setEmpsex(empSex);
			}
			if(StringUtil.isStringEmpty(empProfId)){
				employee.setProfid(Integer.parseInt(empProfId));
			}
			
			if(StringUtil.isStringEmpty(empPwd)){
				employee.setPassword(empPwd);
			}
			
			boolean flag =  ub.add(employee);
			System.out.println(flag);
			
			String json = JsonUtil.getJson(flag); 
			//获取输出对象
			PrintWriter out = resp.getWriter();
			resp.setContentType("UTF-8");
			out.write(json);//输出
			out.flush();//刷新
			out.close();//关闭
		}else if(method.equals("sendUpdate")){
			
			String empId = req.getParameter("empid");
			Employee employee = new Employee();
			if(StringUtil.isStringEmpty(empId))
				employee = ub.findByID(Integer.parseInt(empId));
			
			//把数据转换为json字符串
			  String json = JsonUtil.getJson(employee);
			  
			  //获取输出对象
			  PrintWriter out = resp.getWriter();
			  resp.setContentType("UTF-8");//把json数据的编码格式设置为UTF-8
			  out.write(json);//输出
			  out.flush();//刷新
			  out.close();//关闭
		}else if(method.equals("update")){
			System.out.println("update");
			String empId = req.getParameter("empId");
			String empName = req.getParameter("empName");
			String empSex = req.getParameter("empSex");
			String empProfId = req.getParameter("empProf");
			
			Employee employee = new Employee();
			
			if(StringUtil.isStringEmpty(empName)){
				employee.setEmpname(empName);
			}
			if(StringUtil.isStringEmpty(empSex)){
				employee.setEmpsex(empSex);
			}
			if(StringUtil.isStringEmpty(empProfId)){
				employee.setProfid(Integer.parseInt(empProfId));
			}
			if(StringUtil.isStringEmpty(empId)){
				employee.setEmpid(Integer.parseInt(empId));
			}
			boolean flag =  ub.update(employee);
			 
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
	 * [1,2,3,8,9]
	 * 1,2,3,4,5,6,7,8,9,10
	 * @return
	 */
	public List<MenuPojo>  merge(List<Integer> menuList,List<TMenu> allMenu){
		
		List<MenuPojo> list = new ArrayList<>();
		
		if(allMenu != null && allMenu.size() > 0){
			for (TMenu tMenu : allMenu) {
				Integer menuId = tMenu.getMenuId();
				
				if(hasAuthority(menuId, menuList)){//判断是否有权限
					
					MenuPojo mp = new MenuPojo();
					
					mp.setHasAuthority(true);
					mp.setIsDelete(tMenu.getIsDelete());
					mp.setMenuId(tMenu.getMenuId());
					mp.setMenuImage(tMenu.getMenuImage());
					mp.setMenuLevel(tMenu.getMenuLevel());
					mp.setMenuName(tMenu.getMenuName());
					mp.setMenuSort(tMenu.getMenuSort());
					mp.setMenuUrl(tMenu.getMenuUrl());
					mp.setParentId(tMenu.getParentId());
					
					//递归算法
					mp.setChildMenu(merge(menuList,tMenu.getChildMenu()));
					
					list.add(mp);//把对象一个一个的添加进去
				}
			}
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
