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

import com.niit.thatguybackend.model.Cart;


@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {

Logger log=LoggerFactory.getLogger(CartDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	public CartDAOImpl(SessionFactory sessionFactory){
	try{
		this.sessionFactory=sessionFactory;
		log.info("The connection is established properly..");
	}catch(Exception e){
		log.error("Not able to prepare connection,Please check the application" + "context java file");
		e.printStackTrace();
	}
	}
	@Transactional
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
	@Transactional
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
	@Transactional
	public void delete(String id){
		Cart cart=new Cart();
		cart.setId(id);
		sessionFactory.getCurrentSession().delete(cart);
	
	}

	@Transactional
	public boolean saveOrUpdate(Cart cart) {
		log.debug("Starting of method saveOrUpdate");
		sessionFactory.getCurrentSession().saveOrUpdate(cart);
		return true;
		
	}
	
	@Transactional	
	public Cart get(String id){
		//select * from Cart where id='id'
		log.debug("Starting of get method");
		String hql="from Cart where id=" + "'"+ id +"'";
		return getCart(hql);
	}
	@Transactional
	public List<Cart>list(){
		log.debug("starting of the method :List");
		@SuppressWarnings("unchecked")
		List<Cart> list=(List<Cart>) sessionFactory.getCurrentSession()
				.createCriteria(Cart.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		log.debug("Ending of method :list");
	return list;
	}
	@Transactional	
	public Cart isValidCart(String id,String password){
		log.debug("Starting of method");
		//select * from Cart where id='id' and password='password';
		String hql="from Cart where id="+"'"+id+"'"+"and"+"password="+"'"+password+"'";
		return getCart(hql);
	}
  private Cart getCart(String hql)
  {
	  Query query=sessionFactory.getCurrentSession().createQuery(hql);
	  @SuppressWarnings("unchecked")
	  List<Cart> list=(List<Cart>)query.list();
	  if(list!=null&&!list.isEmpty()){
		  return list.get(0);//returns the domain object but not true and false
	  }
	  return null;
	  }
  }

