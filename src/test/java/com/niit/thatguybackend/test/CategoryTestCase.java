package com.niit.thatguybackend.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.CategoryDAO;
import com.niit.thatguybackend.model.Category;

import junit.framework.Assert;

public class CategoryTestCase {

	
	@Autowired
	private static CategoryDAO categoryDAO;
	
	@Autowired
	private static Category category;
	
	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.thatguybackend");
		context.refresh();
		
		categoryDAO=(CategoryDAO) context.getBean("categoryDAO");
		
		category=(Category) context.getBean("category");
	}
	@Test
	public void createCategory()
	{
		category.setId("cap2");
		category.setName("Accessories2");
		category.setDescription("This is cap");
		
		
		Assert.assertEquals("category create test case", true,categoryDAO.saveOrUpdate(category));
	}
}
