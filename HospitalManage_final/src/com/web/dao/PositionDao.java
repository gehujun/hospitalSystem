package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

import com.web.entity.Professionaltitle;
import com.web.entity.TPosition;

public interface PositionDao {

	/**
	 * 添加
	 * @param tposition
	 */
	public void add(Professionaltitle professionaltitle);
	
	/**
	 * 修改
	 * @param tpostion
	 */
	public void update(Professionaltitle professionaltitle);
	
	/**
	 * 根据id查询职位信息
	 * @param poId
	 * @return
	 */
	public Professionaltitle findByID(Integer poId);
	
	/**
	 * Map<String,Object> map
	 * map.put("poName","医生");
	 * @return
	 */
	public List<Professionaltitle>  query();
	
	
	/**
	 * 根据名字模糊查询
	 * @Param：参数注解  (如果有一个参数)
	 * map.put("poName","医生");
	 * @return
	 */
	public List<Professionaltitle>  queryByName(@Param("names") String poName);
	
	/**
	 * Map<String,Object> map
	 * map.put("poName","医生");
	 * @return
	 */
	public List<Professionaltitle>  queryByCondition(Map<String, Object> map);
	
	
	public void delete(Integer profid);
	
	
}
