package com.niit.thatguybackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
//import org.springframework.orm.hibernate4.annotation.AnnotationSessionFactoryBean;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.thatguy.dao.CartDAO;
import com.niit.thatguy.dao.CartDAOImpl;
import com.niit.thatguy.dao.CartItemDAO;
import com.niit.thatguy.dao.CartItemDAOImpl;
import com.niit.thatguy.dao.CategoryDAO;
import com.niit.thatguy.dao.CategoryDAOImpl;
import com.niit.thatguy.dao.ProductDAO;
import com.niit.thatguy.dao.ProductDAOImpl;
import com.niit.thatguy.dao.SupplierDAO;
import com.niit.thatguy.dao.SupplierDAOImpl;
import com.niit.thatguy.dao.UserDAO;
import com.niit.thatguy.dao.UserDAOImpl;
import com.niit.thatguybackend.model.Cart;
import com.niit.thatguybackend.model.CartItem;
import com.niit.thatguybackend.model.Category;
import com.niit.thatguybackend.model.Product;
import com.niit.thatguybackend.model.Supplier;
import com.niit.thatguybackend.model.User;

@Configuration
@ComponentScan("com.thatguy")
@EnableTransactionManagement
public class ApplicationContextConfig {
	@Bean(name="dataSource")
			public DataSource getH2DataSource(){
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		/*String url="jdbc:h2:~/test;"+
		"INIT=CREATE SCHEMA IIF NOT EXISTS TEST";*/
	
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/thatguy");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}
	private Properties getHibernateProperties(){
		Properties properties=new Properties();
		properties.put("hibernate.show.sql", "true");
		properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		return properties;
	}

	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(Category.class);
		sessionBuilder.addAnnotatedClasses(User.class);
		sessionBuilder.addAnnotatedClasses(Supplier.class);
		sessionBuilder.addAnnotatedClasses(Cart.class);
		sessionBuilder.addAnnotatedClasses(CartItem.class);
		sessionBuilder.addAnnotatedClasses(Product.class);
		return sessionBuilder.buildSessionFactory();
		}
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		 HibernateTransactionManager transactionManager=new  HibernateTransactionManager(sessionFactory);
		 return transactionManager;
	}
	@Autowired
	@Bean(name="categoryDAO")
	public CategoryDAO getCategoryDAO(SessionFactory sessionFactory){
		return new CategoryDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory){
		return new UserDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="supplierDAO")
	public SupplierDAO getSupplierDAO(SessionFactory sessionFactory){
		return new SupplierDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="cartDAO")
	public CartDAO getCartDAO(SessionFactory sessionFactory){
		return new CartDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="cartItemDAO")
	public CartItemDAO getCartItemDAO(SessionFactory sessionFactory){
		return new CartItemDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="productDAO")
	public ProductDAO getProductDAO(SessionFactory sessionFactory){
		return new ProductDAOImpl(sessionFactory);
	}
}
