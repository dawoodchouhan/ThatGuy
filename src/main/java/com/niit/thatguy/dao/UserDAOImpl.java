package com.niit.thatguy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.thatguybackend.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO{
	Logger log=LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	public UserDAOImpl(SessionFactory sessionFactory){
	try{
		this.sessionFactory=sessionFactory;
		log.info("The connection is established properly..");
	}catch(Exception e){
		log.error("Not able to prepare connection,Please check the application" + "context java file");
		e.printStackTrace();
	}
	}
	@Transactional
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
	@Transactional
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
	@Transactional
	public void delete(String id){
		User user=new User();
		user.setId(id);
		sessionFactory.getCurrentSession().delete(user);
	
	}

	@Transactional
	public boolean saveOrUpdate(User user) {
		log.debug("Starting of method saveOrUpdate");
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return true;
	}
	
	@Transactional	
	public User get(String id){
		//select * from User where id='id'
		log.debug("Starting of get method");
		String hql="from User where id=" + "'"+ id +"'";
		return getUser(hql);
	}
	@Transactional
	public List<User>list(){
		log.debug("starting of the method :List");
		@SuppressWarnings("unchecked")
		List<User> list=(List<User>) sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		log.debug("Ending of method :list");
	return list;
	}
	@Transactional	
	public User isValidUser(String id,String password){
		log.debug("Starting of method");
		//select * from User where id='id' and password='password';
		String hql="from User where id="+"'"+id+"'"+"and"+"password="+"'"+password+"'";
		return getUser(hql);
	}
  private User getUser(String hql)
  {
	  Query query=sessionFactory.getCurrentSession().createQuery(hql);
	  @SuppressWarnings("unchecked")
	  List<User> list=(List<User>)query.list();
	  if(list!=null&&!list.isEmpty()){
		  return list.get(0);//returns the domain object but not true and false
	  }
	  return null;
	  }
  }

