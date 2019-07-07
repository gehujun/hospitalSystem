package com.web.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PositionMenuBiz {

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
   public boolean saveAuthority(Integer poId,int[] menuIds);
}
