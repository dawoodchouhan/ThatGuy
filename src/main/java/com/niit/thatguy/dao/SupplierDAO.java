package com.niit.thatguy.dao;

import java.util.List;

import com.niit.thatguybackend.model.Supplier;

public interface SupplierDAO {
	public boolean save(Supplier supplier);
	public boolean update(Supplier supplier);
	public void delete(String id);
	public boolean saveOrUpdate(Supplier supplier);
	
	//based on the id, it will return category domain
	public Supplier get(String id);
	
	//To get all the categories
	public List<Supplier>list();
	public Supplier getByName(String name);
	

}
