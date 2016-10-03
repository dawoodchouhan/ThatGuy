package com.niit.thatguy.dao;

import java.util.List;

import com.niit.thatguybackend.model.Cart;

public interface CartDAO {
	public boolean save(Cart cart);
	public boolean update(Cart cart);
	public boolean delete(String id);
	public boolean saveOrUpdate(Cart cart);
	
	//based on the id, it will return category domain
	public Cart get(String id);
	
	//To get all the categories
	public List<Cart>list();
	

}
