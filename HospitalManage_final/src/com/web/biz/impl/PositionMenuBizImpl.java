package com.web.biz.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.biz.PositionMenuBiz;
import com.web.dao.PositionMenuDao;
import com.web.entity.TPositionMenu;
import com.web.util.MyBatisUtil;

public class PositionMenuBizImpl implements PositionMenuBiz {
	
	SqlSession sqlSession = MyBatisUtil.openSession();
	PositionMenuDao pmd = sqlSession.getMapper(PositionMenuDao.class);

	@Override
	public List<Integer> findMenuByPoId(Integer poId) {
		return pmd.findMenuByPoId(poId);
	}

	@Override
	public boolean saveAuthority(Integer poId, int[] menuIds) {
		
		//清空数据
		pmd.deleteByPoId(poId);
		
		//影响的行数
		int counts = 0;
		
		try {
			for (Integer menuId : menuIds) {
				TPositionMenu positionMenu = new TPositionMenu();
				positionMenu.setMenuId(menuId);
				positionMenu.setPoId(poId);
				pmd.addMenuPosition(positionMenu);
				
				counts++;
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return counts>0 ? true : false;
	}

}
