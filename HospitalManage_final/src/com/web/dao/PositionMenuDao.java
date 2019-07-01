package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.TPosition;
import com.web.entity.TPositionMenu;

public interface PositionMenuDao {

	/**
	 * 根据职位id得到菜单id集合
	 * @param poId
	 * @return
	 */
	public List<Integer> findMenuByPoId(@Param("poId") Integer poId);
	
	/**
     * 保存
     * @param record
     * @return
     */
   public int saveAuthority(Integer poId,Integer menuId);
   
   /**
    * 根据岗位id删除菜单信息
    * @param poId
    */
   public void deleteByPoId(Integer poId);
   
   /**
    * 添加
    * @param positionMenu
    */
   public void addMenuPosition(TPositionMenu positionMenu);
}
