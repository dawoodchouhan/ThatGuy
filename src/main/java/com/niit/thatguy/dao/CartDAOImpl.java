package com.niit.thatguy.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.thatguybackend.model.Cart;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {
	@Autowired
	private Cart cart;
	
	@Autowired
	private SessionFactory sessionFactory;
	public CartDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public boolean save(Cart cart){
		try
		{
			sessionFactory.getCurrentSession().save(cart);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Cart cart){
		try
		{
			sessionFactory.getCurrentSession().update(cart);
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
	public boolean saveOrUpdate(Cart cart) {
		sessionFactory.getCurrentSession().saveOrUpdate(cart);
		return true;
	}
	
		
	public Cart get(String id){
		//select * from Cart where id='id'
		String hql="from Cart where id='"+id+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Cart> list=query.list();
		if(list==null||list.isEmpty())
			{
			return null;
			}
		return list.get(0);
	}
	
	public List<Cart>list(){
		return null;
	}

	

}
