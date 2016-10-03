package com.niit.thatguy.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.thatguybackend.model.CartItem;




@Repository("cartItemDAO")
public class CartItemDAOImpl implements CartItemDAO{
	@Autowired
	private CartItem cartItem;
	
	@Autowired
	private SessionFactory sessionFactory;
	public CartItemDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public boolean save(CartItem cartItem){
		try
		{
			sessionFactory.getCurrentSession().save(cartItem);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(CartItem cartItem){
		try
		{
			sessionFactory.getCurrentSession().update(cartItem);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean delete(String id){
		try
		{
			sessionFactory.getCurrentSession().delete(get(id));
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean saveOrUpdate(CartItem cartItem) {
		sessionFactory.getCurrentSession().saveOrUpdate(cartItem);
		return true;
	}
	
		
	public CartItem get(String id){
		//select * from CartItem where id='id'
		String hql="from CartItem where id='"+id+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<CartItem> list=query.list();
		if(list==null||list.isEmpty())
			{
			return null;
			}
		return list.get(0);
	}
	
	public List<CartItem>list(){
		return null;
	}

	
}
