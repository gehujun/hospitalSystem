package com.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Bed;
import com.web.entity.Inprescription;
import com.web.entity.Medicaladvice;
import com.web.entity.Medicine;

public interface InprescriptionDao {

public List<Bed> query();
	
	
	public List<Inprescription> queryByCondition(Map<String, Object> map);

	
	public Inprescription findById(@Param("mediid") Integer mediid,@Param("maid") Integer maid);//ͨ������ID��ѯ��λ��Ϣ
	
	
	public void update (Inprescription inprescription);
	
	public void add (Inprescription inprescription);
	
	
	public void delete(@Param("mediid") Integer mediid,@Param("maid") Integer maid);
	
	public List<Medicaladvice> maidSelect();

	public List<Medicine> mediidSelect();
	
	
}
