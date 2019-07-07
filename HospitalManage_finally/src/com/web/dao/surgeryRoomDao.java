package com.web.dao;

import java.util.List;

import com.web.entity.Surgerroom;

public interface surgeryRoomDao {
	public List<Surgerroom> query(Surgerroom surgerroom);
	public boolean add(Surgerroom surgerroom);
	public Surgerroom findById(String surRoomId);
	
	
	public boolean update(Surgerroom surgerroom);
	
	public boolean delete(String surRoomId);
}
