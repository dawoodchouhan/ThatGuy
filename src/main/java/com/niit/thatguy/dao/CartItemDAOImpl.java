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

import com.niit.thatguybackend.model.CartItem;





@Repository("cartItemDAO")
public class CartItemDAOImpl implements CartItemDAO{
Logger log=LoggerFactory.getLogger(CartItemDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	public CartItemDAOImpl(SessionFactory sessionFactory){
	try{
		this.sessionFactory=sessionFactory;
		log.info("The connection is established properly..");
	}catch(Exception e){
		log.error("Not able to prepare connection,Please check the application" + "context java file");
		e.printStackTrace();
	}
	}
	@Transactional
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
	@Transactional
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
	@Transactional
	public void delete(String id){
		CartItem cartItem=new CartItem();
		cartItem.setId(id);
		sessionFactory.getCurrentSession().delete(cartItem);
	
	}

	@Transactional
	public boolean saveOrUpdate(CartItem cartItem) {
		log.debug("Starting of method saveOrUpdate");
		sessionFactory.getCurrentSession().saveOrUpdate(cartItem);
		return true;
		
	}
	
	@Transactional	
	public CartItem get(String id){
		//select * from CartItem where id='id'
		log.debug("Starting of get method");
		String hql="from CartItem where id=" + "'"+ id +"'";
		return getCartItem(hql);
	}
	@Transactional
	public List<CartItem>list(){
		log.debug("starting of the method :List");
		@SuppressWarnings("unchecked")
		List<CartItem> list=(List<CartItem>) sessionFactory.getCurrentSession()
				.createCriteria(CartItem.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		log.debug("Ending of method :list");
	return list;
	}
	@Transactional	
	public CartItem isValidCartItem(String id,String password){
		log.debug("Starting of method");
		//select * from CartItem where id='id' and password='password';
		String hql="from CartItem where id="+"'"+id+"'"+"and"+"password="+"'"+password+"'";
		return getCartItem(hql);
	}
  private CartItem getCartItem(String hql)
  {
	  Query query=sessionFactory.getCurrentSession().createQuery(hql);
	  @SuppressWarnings("unchecked")
	  List<CartItem> list=(List<CartItem>)query.list();
	  if(list!=null&&!list.isEmpty()){
		  return list.get(0);//returns the domain object but not true and false
	  }
	  return null;
	  }
  }

