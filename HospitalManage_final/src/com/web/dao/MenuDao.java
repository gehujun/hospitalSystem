package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.TMenu;

public interface MenuDao {

	/**
	 * 根据父级id查询菜单的信息
	 * @param parentId
	 * @return
	 */
	public List<TMenu> findByParentId(Integer parentId);
}
