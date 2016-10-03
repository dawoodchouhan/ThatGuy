package com.niit.thatguy.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.thatguybackend.model.Product;



@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private Product product;
	
	@Autowired
	private SessionFactory sessionFactory;
	public ProductDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public boolean save(Product product){
		try
		{
			sessionFactory.getCurrentSession().save(product);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Product product){
		try
		{
			sessionFactory.getCurrentSession().update(product);
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
	public boolean saveOrUpdate(Product product) {
		sessionFactory.getCurrentSession().saveOrUpdate(product);
		return true;
	}
	
		
	public Product get(String id){
		//select * from Product where id='id'
		String hql="from Product where id='"+id+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Product> list=query.list();
		if(list==null||list.isEmpty())
			{
			return null;
			}
		return list.get(0);
	}
	
	public List<Product>list(){
		return null;
	}

	
}



