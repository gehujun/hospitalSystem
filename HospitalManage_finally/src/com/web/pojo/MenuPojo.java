package com.web.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限对象
 * @author ASUS
 *
 */
public class MenuPojo {

	 /**
     * 菜单编号
     */
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单访问页面地址
     */
    private String menuUrl;

    /**
     * 菜单图标
     */
    private String menuImage;

    /**
     * 父级编号
     */
    private Integer parentId;

    /**
     * 菜单层级
     */
    private Integer menuLevel;

    /**
     * 排序
     */
    private Integer menuSort;

    /**
     * 是否被删除1:未删0:已删
     */
    private Integer isDelete;
    
    private boolean hasAuthority;//判断是否有权限   true:有权限    false:没有权限   
    
    //子菜单
    private List<MenuPojo> childMenu = new ArrayList<>();

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isHasAuthority() {
		return hasAuthority;
	}

	public void setHasAuthority(boolean hasAuthority) {
		this.hasAuthority = hasAuthority;
	}

	public List<MenuPojo> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<MenuPojo> childMenu) {
		this.childMenu = childMenu;
	}
    
    

}
