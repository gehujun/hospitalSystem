package com.web.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.TMenu;

public interface MenuBiz {

	/**
	 * 根据父级id查询菜单的信息
	 * @param parentId
	 * @return
	 */
	public List<TMenu> findByParentId(@Param("parentId") Integer parentId);
}
