package com.niit.thatguy.dao;

import java.util.List;

import com.niit.thatguybackend.model.Product;

public interface ProductDAO {
	public boolean save(Product product);
	public boolean update(Product product);
	public boolean delete(String id);
	public boolean saveOrUpdate(Product product);
	
	//based on the id, it will return category domain
	public Product get(String id);
	
	//To get all the categories
	public List<Product>list();
	
	
}

