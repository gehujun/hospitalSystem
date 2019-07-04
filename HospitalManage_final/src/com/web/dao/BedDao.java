package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Bed;
import com.web.entity.Employee;
import com.web.entity.Ward;



public interface BedDao {
	
	/**
	 * 全查询
	 * @return
	 * 全查询，
	 * 展现出床位的bedid,
	 * 病区名wardname，
	 * 护理医生empiddoc
	 * 床位状态isemptybed
	 * 租金bedrent
	 */
	public List<Bed> query();
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<Bed> queryByCondition(Bed bed);
	
	
	/**
	 * in子句的查询
	 * @param ids
	 * @return
	 */
	
	public Bed findById(@Param("bedid")Integer bedid);//通过病床ID查询床位信息
	
	
	public void update (Bed bed);
	
	public void add (Bed bed);
	
	
	public void delete(@Param("bedid")Integer bedid);
	
	public List<Bed> selectEmpById(@Param("bedid")Integer bedid);

	public List<Employee> empidSelect();

	public List<Ward> wardnameSelect();


	
	

}
