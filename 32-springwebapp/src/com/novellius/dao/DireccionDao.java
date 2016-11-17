package com.novellius.dao;

import java.util.List;

import com.novellius.pojo.Admin;
import com.novellius.pojo.Direccion;

public interface DireccionDao {

	public void save(Direccion direccion);
	public List<Direccion> findAll(Admin admin);		
	
	
}
