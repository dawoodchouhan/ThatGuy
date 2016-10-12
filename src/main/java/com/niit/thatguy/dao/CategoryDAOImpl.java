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

import com.niit.thatguybackend.model.Category;
import com.niit.thatguybackend.model.User;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO{
	
	  private static final Logger log=LoggerFactory.getLogger(CategoryDAOImpl.class);
	
	
	@Autowired
	private SessionFactory sessionFactory;
	public CategoryDAOImpl(SessionFactory sessionFactory){
		try{
			this.sessionFactory=sessionFactory;
			log.info("The connection is established properly..");
		}catch(Exception e){
			log.error("Not able to prepare connection,Please check the application" + "context java file");
			e.printStackTrace();
		}
		}
	
	@Transactional
	public boolean save(Category category){
		try
		{
			sessionFactory.getCurrentSession().save(category);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Transactional
	public boolean update(Category category){
	try
		{
			sessionFactory.getCurrentSession().update(category);
		}catch(Exception e)
	{
		e.printStackTrace();
			return false;
		}
		return true;
	}
	@Transactional
	public void delete(String id){
		Category category=new Category();
		category.setId(id);
		sessionFactory.getCurrentSession().delete(category);
	
	}
	@Transactional
	public boolean saveOrUpdate(Category category) {
		log.debug("Starting of the method saveOrUpdate");
	try{
		sessionFactory.getCurrentSession().saveOrUpdate(category);
		return true;
	}catch (Exception e){
		e.printStackTrace();
		log.error("Exception occured while saving category");
		log.error(e.getMessage());
		return false;
		
	}
	}
	@Transactional	
	public Category get(String id){
		//select * from Category where id='id'
		log.debug("calling get");
		String hql="from Category where id=" + "'" + id + "'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("hql:"+hql);
		@SuppressWarnings("unchecked")
		List<Category> listCategory=(List<Category>)query.list();
		if(listCategory!=null&&!listCategory.isEmpty()){
			return listCategory.get(0);
		}
		log.debug("End get");
		
			return null;
			}
		
	@Transactional
	public Category getByName(String Name){
	 String hql="from Category where name="+ "'"+ Name +"'";
	 Query query=sessionFactory.getCurrentSession().createQuery(hql);
	 @SuppressWarnings("unchecked")
		
	 List<Category> listCategory=(List<Category>)query.list();
		if(listCategory!=null&&!listCategory.isEmpty()){
			return listCategory.get(0);
		}
		log.debug("End get");
		
			return null;
			}
		
	@Transactional
	public List<Category>list(){
		log.debug("starting of the method :List");
		@SuppressWarnings("unchecked")
		List<Category> list=(List<Category>) sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		log.debug("Ending of method :list");
	return list;
	}
	
}
