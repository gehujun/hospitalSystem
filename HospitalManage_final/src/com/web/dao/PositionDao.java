package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import com.web.entity.TPosition;

public interface PositionDao {

	/**
	 * 添加
	 * @param tposition
	 */
	public void addPosition(TPosition tposition);
	
	/**
	 * 修改
	 * @param tpostion
	 */
	public void updatePostion(TPosition tpostion);
	
	/**
	 * 根据id查询职位信息
	 * @param poId
	 * @return
	 */
	public TPosition findByID(Integer poId);
	
	/**
	 * Map<String,Object> map
	 * map.put("poName","医生");
	 * @return
	 */
	public List<TPosition>  query();
	
	
	/**
	 * 根据名字模糊查询
	 * @Param：参数注解  (如果有一个参数)
	 * map.put("poName","医生");
	 * @return
	 */
	public List<TPosition>  queryByName(@Param("names") String poName);
	
	
	@Delete(value="delete from t_position where poId=#{poId}")
	public void delete(Integer poId);
	
	/**
	 * Map<String,Object> map
	 * map.put("poName","医生");
	 * @return
	 */
	public List<TPosition>  queryByCondition(Map<String, Object> map);
	
	
}
