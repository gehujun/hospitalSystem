package com.web.biz;

import java.util.List;

import com.web.entity.Medicinepayreceipt;

public interface medicinepayreceiptBiz {
	public List<Medicinepayreceipt> query(Medicinepayreceipt medicinepayreceipt);
	public boolean add(Medicinepayreceipt medicinepayreceipt);
	public Medicinepayreceipt findById(Integer mprId);
	
	
	public boolean update(Medicinepayreceipt medicinepayreceipt);
	
	public boolean delete(Integer mprId);
}
