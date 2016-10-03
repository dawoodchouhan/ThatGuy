package com.niit.thatguy.dao;

import java.util.List;


import com.niit.thatguybackend.model.User;

public interface UserDAO {
	public boolean save(User user);
	public boolean update(User user);
	public boolean delete(String id);
	public boolean saveOrUpdate(User user);
	
	//based on the id, it will return category domain
	public User get(String id);
	
	//To get all the categories
	public List<User>list();

}
