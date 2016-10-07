package com.niit.thatguybackend.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.UserDAO;
import com.niit.thatguybackend.model.User;

import junit.framework.Assert;

public class UserTestCase {
	@Autowired
	private static UserDAO userDAO;
	
	@Autowired
	private static User user;
	
	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.thatguybackend");
		context.refresh();
		
		userDAO=(UserDAO) context.getBean("userDAO");
		
		user=(User) context.getBean("user");
	}
	@Test
	public void createUser()
	{
		user.setId("dawcohhan");
		user.setName("chouhan");
		user.setPassword("dawo123");
		user.setMobile("1234567889");
		user.setMail_id("d@321");
		user.setRole("user");
		
		Assert.assertEquals("user create test case", true,userDAO.saveOrUpdate(user));
	}
	//@Test
	//public void deleteUser(){
	//	Assert.assertEquals("deleteUser",true,userDAO.delete("AB123"));	
	//}
}


