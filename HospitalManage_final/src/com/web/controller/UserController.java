package com.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.web.entity.TMenu;
import com.web.entity.TUser;
import com.web.pojo.MenuPojo;

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
					req.getRequestDispatcher("gentelella/docslogin.jsp").forward(req, resp);
				}
				
			}else{
				//验证码输入错误
				req.getRequestDispatcher("gentelella/docslogin.jsp").forward(req, resp);
			}
			
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
