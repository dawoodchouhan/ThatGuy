package com.niit.thatguy.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.thatguybackend.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO{
	@Autowired
	private User user;
	
	@Autowired
	private SessionFactory sessionFactory;
	public UserDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public boolean save(User user){
		try
		{
			sessionFactory.getCurrentSession().save(user);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(User user){
		try
		{
			sessionFactory.getCurrentSession().update(user);
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
	public boolean saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return true;
	}
	
		
	public User get(String id){
		//select * from User where id='id'
		String hql="from User where id='"+id+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list=query.list();
		if(list==null||list.isEmpty())
			{
			return null;
			}
		return list.get(0);
	}
	
	public List<User>list(){
		return null;
	}


}
