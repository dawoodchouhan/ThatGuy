package com.niit.thatguybackend.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.CartItemDAO;
import com.niit.thatguybackend.model.CartItem;

import junit.framework.Assert;

public class CartItemTestCase {
	@Autowired
	private static CartItemDAO cartItemDAO;
	
	@Autowired
	private static CartItem cartItem;
	
	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.thatguybackend");
		context.refresh();
		
		cartItemDAO=(CartItemDAO) context.getBean("cartItemDAO");
		
		cartItem=(CartItem) context.getBean("cartItem");
	}
	@Test
	public void createCartItem()
	{
		cartItem.setId("123B");
		cartItem.setBrand("fossil");
		cartItem.setCategory("Watch");
		cartItem.setPrice("5900");
		cartItem.setQuantity("20");
	
		
		Assert.assertEquals("cartItem create test case", true,cartItemDAO.saveOrUpdate(cartItem));
	}
}


