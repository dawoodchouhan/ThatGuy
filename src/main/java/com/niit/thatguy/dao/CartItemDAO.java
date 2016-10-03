package com.niit.thatguy.dao;

import java.util.List;

import com.niit.thatguybackend.model.CartItem;



public interface CartItemDAO {
	public boolean save(CartItem cartitem);
	public boolean update(CartItem cartitem);
	public boolean delete(String id);
	public boolean saveOrUpdate(CartItem cartitem);
	
	//based on the id, it will return category domain
	public CartItem get(String id);
	
	//To get all the categories
	public List<CartItem>list();
	

}
