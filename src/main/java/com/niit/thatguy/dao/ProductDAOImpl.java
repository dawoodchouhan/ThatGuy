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

import com.niit.thatguybackend.model.Product;




@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
Logger log=LoggerFactory.getLogger(ProductDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	public ProductDAOImpl(SessionFactory sessionFactory){
	try{
		this.sessionFactory=sessionFactory;
		log.info("The connection is established properly..");
	}catch(Exception e){
		log.error("Not able to prepare connection,Please check the application" + "context java file");
		e.printStackTrace();
	}
	}
	@Transactional
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
	@Transactional
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
	@Transactional
	public void delete(String id){
		Product product=new Product();
		product.setId(id);
		sessionFactory.getCurrentSession().delete(product);
	
	}

	@Transactional
	public boolean saveOrUpdate(Product product) {
		log.debug("Starting of method saveOrUpdate");
		sessionFactory.getCurrentSession().saveOrUpdate(product);
		return true;
		
	}
	
	@Transactional	
	public Product get(String id){
		//select * from Product where id='id'
		log.debug("Starting of get method");
		String hql="from Product where id=" + "'"+ id +"'";
		return getProduct(hql);
	}
	@Transactional
	public List<Product>list(){
		log.debug("starting of the method :List");
		@SuppressWarnings("unchecked")
		List<Product> list=(List<Product>) sessionFactory.getCurrentSession()
				.createCriteria(Product.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		log.debug("Ending of method :list");
	return list;
	}
	@Transactional	
	public Product isValidProduct(String id,String password){
		log.debug("Starting of method");
		//select * from Product where id='id' and password='password';
		String hql="from Product where id="+"'"+id+"'"+"and"+"password="+"'"+password+"'";
		return getProduct(hql);
	}
  private Product getProduct(String hql)
  {
	  Query query=sessionFactory.getCurrentSession().createQuery(hql);
	  @SuppressWarnings("unchecked")
	  List<Product> list=(List<Product>)query.list();
	  if(list!=null&&!list.isEmpty()){
		  return list.get(0);//returns the domain object but not true and false
	  }
	  return null;
	  }
  }

