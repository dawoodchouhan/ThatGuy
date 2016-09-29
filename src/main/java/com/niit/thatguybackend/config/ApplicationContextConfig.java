package com.niit.thatguybackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.thatguy.dao.CategoryDAO;
import com.niit.thatguy.dao.CategoryDAOImpl;

import com.niit.thatguybackend.model.Category;

@Configuration
@ComponentScan("com.niit.thatguy")
@EnableTransactionManagement
public class ApplicationContextConfig {
	@Bean(name="dataSource")
			public DataSource getH2DataSource(){
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		/*String url="jdbc:h2:~/test;"+
		"INIT=CREATE SCHEMA IIF NOT EXISTS TEST";*/
	
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
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
		//sessionBuilder.addAnnotatedClasses(Supplier.class);
		//sessionBuilder.addAnnotatedClasses(User.class);
		//sessionBuilder.addAnnotatedClasses(Product.class);
		//sessionBuilder.addAnnotatedClasses(Cart.class);
		//sessionBuilder.addAnnotatedClasses(CartItem.class);
		//sessionBuilder.addAnnotatedClasses(Account.class);
		return sessionBuilder.buildSessionFactory();
		}
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		 HibernateTransactionManager transactionManager=new  HibernateTransactionManager(sessionFactory);
		 return transactionManager;
	}
	@Autowired
	@Bean(name="categoryDao")
	public CategoryDAO getCategoryDao(SessionFactory sessionFactory){
		return new CategoryDAOImpl(sessionFactory);
	}
	//@Autowired
	//@Bean(name="cartDao")
	//public CartDAO getCartDao(SessionFactory sessionFactory){
		//return new CartDAOImpl(sessionFactory);
	//}
}
