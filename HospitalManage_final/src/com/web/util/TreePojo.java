package com.web.util;

import java.util.List;

/**
 * ztree的json数据
 * @author xxq
 *
 */
public class TreePojo {
	
	private Integer id;//编号
	private Integer pid;//父级id
	private String name;//节点的名称
	private String url;//地址
	private String icon;//图标
	private List<TreePojo> children;//子节点 
	private boolean checked;//是否选中
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<TreePojo> getChildren() {
		return children;
	}
	public void setChildren(List<TreePojo> children) {
		this.children = children;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	

}
