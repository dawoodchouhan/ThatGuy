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

import com.niit.thatguybackend.model.Supplier;
import com.niit.thatguybackend.model.User;

@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO {
	 private static final Logger log=LoggerFactory.getLogger(SupplierDAOImpl.class);
		
		
		@Autowired
		private SessionFactory sessionFactory;
		public SupplierDAOImpl(SessionFactory sessionFactory){
			try{
				this.sessionFactory=sessionFactory;
				log.info("The connection is established properly..");
			}catch(Exception e){
				log.error("Not able to prepare connection,Please check the application" + "context java file");
				e.printStackTrace();
			}
			}
		
		@Transactional
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
		@Transactional
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
		@Transactional
		public void delete(String id){
			Supplier supplier=new Supplier();
			supplier.setId(id);
			sessionFactory.getCurrentSession().delete(supplier);
		
		}
		@Transactional
		public boolean saveOrUpdate(Supplier supplier) {
			log.debug("Starting of the method saveOrUpdate");
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(supplier);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			log.error("Exception occured while saving supplier");
			log.error(e.getMessage());
			return false;
			
		}
		}
		@Transactional	
		public Supplier get(String id){
			//select * from Supplier where id='id'
			log.debug("calling get");
			String hql="from Supplier where id=" + "'" + id + "'";
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("hql:"+hql);
			@SuppressWarnings("unchecked")
			List<Supplier> listSupplier=(List<Supplier>)query.list();
			if(listSupplier!=null&&!listSupplier.isEmpty()){
				return listSupplier.get(0);
			}
			log.debug("End get");
			
				return null;
				}
			
		@Transactional
		public Supplier getByName(String Name){
		 String hql="from Supplier where name="+ "'"+ Name +"'";
		 Query query=sessionFactory.getCurrentSession().createQuery(hql);
		 @SuppressWarnings("unchecked")
			
		 List<Supplier> listSupplier=(List<Supplier>)query.list();
			if(listSupplier!=null&&!listSupplier.isEmpty()){
				return listSupplier.get(0);
			}
			log.debug("End get");
			
				return null;
				}
			
		@Transactional
		public List<Supplier>list(){
			log.debug("starting of the method :List");
			@SuppressWarnings("unchecked")
			List<Supplier> list=(List<Supplier>) sessionFactory.getCurrentSession()
					.createCriteria(User.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			log.debug("Ending of method :list");
		return list;
		}
		
	}
