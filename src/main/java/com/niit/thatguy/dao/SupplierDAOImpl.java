package com.niit.thatguy.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.thatguybackend.model.Supplier;

@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO {
	@Autowired
	private Supplier supplier;
	
	@Autowired
	private SessionFactory sessionFactory;
	public SupplierDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public boolean save(Supplier supplier){
		try
		{
			sessionFactory.getCurrentSession().save(supplier);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Supplier supplier){
		try
		{
			sessionFactory.getCurrentSession().update(supplier);
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
	public boolean saveOrUpdate(Supplier supplier) {
		sessionFactory.getCurrentSession().saveOrUpdate(supplier);
		return true;
	}
	
		
	public Supplier get(String id){
		//select * from Supplier where id='id'
		String hql="from Supplier where id='"+id+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Supplier> list=query.list();
		if(list==null||list.isEmpty())
			{
			return null;
			}
		return list.get(0);
	}
	
	public List<Supplier>list(){
		return null;
	}


}
