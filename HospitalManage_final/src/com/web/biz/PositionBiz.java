package com.web.biz;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Professionaltitle;
import com.web.entity.TPosition;

public interface PositionBiz {

	/**
	 * Map<String,Object> map
	 * map.put("poName","医生");
	 * @return
	 */
	public List<Professionaltitle>  queryByCondition(Map<String, Object> map);
	
	/**
	 * 全查询
	 * @return
	 */
	public List<Professionaltitle> query();
	/**
	 * 根据id查询对象
	 * @param deptId
	 * @return
	 */
	public Professionaltitle findById(@Param("profid") Integer profid);
	
	
	/**
	 * 修改
	 * @param department
	 */
	public boolean update(Professionaltitle professionaltitle);
	
	/**
	 * 增加
	 */
	public boolean add(Professionaltitle professionaltitle);
	
	/**
	 * 删除
	 */
	public boolean delete(Integer profid);
	
	
}
