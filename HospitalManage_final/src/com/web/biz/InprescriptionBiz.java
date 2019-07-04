package com.web.biz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.entity.Bed;
import com.web.entity.Inprescription;
import com.web.entity.Medicaladvice;
import com.web.entity.Medicine;

public interface InprescriptionBiz {

	public List<Inprescription> queryByCondition(Map<String, Object> map);
	
	
	public Inprescription findById(@Param("mediid") Integer mediid,@Param("maid") Integer maid);
	
	public boolean update(Inprescription inprescription);
	
	public boolean add(Inprescription inprescription);

	public boolean delete(@Param("mediid") Integer mediid,@Param("maid") Integer maid);
	
	public List<Medicaladvice> maidSelect();

	public List<Medicine> mediidSelect();
}
