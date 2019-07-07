package com.web.biz;

import java.util.List;

import com.web.entity.Medicaladvice;
import com.web.entity.Surgerroom;

public interface surgeryRoomBiz {
	public List<Surgerroom> query(Surgerroom surgerroom);
	public boolean add(Surgerroom surgerroom);
	public Surgerroom findById(String surRoomId);
	
	
	public boolean update(Surgerroom surgerroom);
	
	public boolean delete(String surRoomId);
}	
