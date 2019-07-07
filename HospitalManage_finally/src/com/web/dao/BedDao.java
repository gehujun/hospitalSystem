package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Bed;
import com.web.entity.Employee;
import com.web.entity.Ward;



public interface BedDao {
	
	/**
	 * ȫ��ѯ
	 * @return
	 * ȫ��ѯ��
	 * չ�ֳ���λ��bedid,
	 * ������wardname��
	 * ����ҽ��empiddoc
	 * ��λ״̬isemptybed
	 * ���bedrent
	 */
	public List<Bed> query();
	
	/**
	 * ����������ѯ
	 * @return
	 */
	public List<Bed> queryByCondition(Bed bed);
	
	
	/**
	 * in�Ӿ�Ĳ�ѯ
	 * @param ids
	 * @return
	 */
	
	public Bed findById(@Param("bedid")Integer bedid);//ͨ������ID��ѯ��λ��Ϣ
	
	
	public void update (Bed bed);
	
	public void add (Bed bed);
	
	
	public void delete(@Param("bedid")Integer bedid);
	
	public List<Bed> selectEmpById(@Param("bedid")Integer bedid);

	public List<Employee> empidSelect();

	public List<Ward> wardnameSelect();


	
	

}
