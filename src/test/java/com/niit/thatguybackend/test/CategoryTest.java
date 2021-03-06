package com.niit.thatguybackend.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.CategoryDAO;
import com.niit.thatguybackend.model.Category;

public class CategoryTest {


public static void main(String[] args){
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	//we have to specify in which package the classes are there
	context.scan("com.niit.thatguybackend");
	context.refresh();
	CategoryDAO categoryDAO=(CategoryDAO) context.getBean("categoryDAO");
	Category category=(Category) context.getBean("category");
	
	category.setId("XYZCap123");
	category.setName("shoe123");
	category.setDescription("This is shoe");
	
	categoryDAO.saveOrUpdate(category);
	System.out.println("objects are created successfully");
}
}