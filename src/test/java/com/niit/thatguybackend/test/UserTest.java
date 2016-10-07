package com.niit.thatguybackend.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.UserDAO;
import com.niit.thatguybackend.model.User;

public class UserTest {
	public static void main(String[] args){
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		//we have to specify in which package the classes are there
		context.scan("com.niit.thatguybackend");
		context.refresh();
		UserDAO userDAO=(UserDAO) context.getBean("userDAO");
		User user=(User) context.getBean("user");
		
		user.setId("AB123");
		user.setName("Dawood");
		user.setPassword("dawo123");
		user.setMobile("1234567889");
		user.setMail_id("d@123");
		user.setRole("user");
		userDAO.saveOrUpdate(user);
		System.out.println("objects are created successfully");
	}
	}


