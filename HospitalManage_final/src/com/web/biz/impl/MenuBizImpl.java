package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sun.glass.ui.Menu;
import com.web.biz.MenuBiz;
import com.web.dao.MenuDao;
import com.web.entity.TMenu;
import com.web.util.MyBatisUtil;

public class MenuBizImpl implements MenuBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	MenuDao md = sqlSession.getMapper(MenuDao.class);

	@Override
	public List<TMenu> findByParentId(Integer parentId) {
		
		//查询到第一层的菜单信息
		List<TMenu> firstList = md.findByParentId(parentId);
		
		//递归调用算法
		execursion(firstList);
		
		return firstList;
	}

	/**
	 * 递归
	 */
	public void  execursion(List<TMenu> firstList){
		if(firstList != null && firstList.size()>0){
			for (TMenu tMenu : firstList) {
				//获取第二层菜单的父级id
				Integer twoparentId = tMenu.getMenuId();
				
				//得到第二层的菜单信息
				List<TMenu> secondList = md.findByParentId(twoparentId);
				
				//递归调用
				execursion(secondList);
				
				tMenu.setChildMenu(secondList);//设置第一层菜单的子菜单
			}
		}
	}
	
	
}
