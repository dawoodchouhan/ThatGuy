package com.niit.thatguy.dao;

import java.util.List;

import com.niit.thatguybackend.model.Category;

public interface CategoryDAO {
//what are the operations we are going to do and declare it
	//CRUD operations
	public boolean save(Category category);
	public boolean update(Category category);
	public boolean delete(String id);
	public void saveOrUpdate(Category category);
	
	//based on the id, it will return category domain
	public Category get(String id);
	
	//To get all the categories
	public List<Category>list();
	
	
}
