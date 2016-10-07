package com.niit.thatguybackend.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.CartDAO;
import com.niit.thatguybackend.model.Cart;

import junit.framework.Assert;

public class CartTestCase {

	
	@Autowired
	private static CartDAO cartDAO;
	
	@Autowired
	private static Cart cart;
	
	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.thatguybackend");
		context.refresh();
		
		cartDAO=(CartDAO) context.getBean("cartDAO");
		
		cart=(Cart) context.getBean("cart");
	}
	@Test
	public void createCart()
	{
		cart.setTotal_price("1500");
		cart.setId("B12");
		cart.setMail_id("daw@123");
		cart.setCart_item("item 001");
		
		
		Assert.assertEquals("cart create test case", true,cartDAO.saveOrUpdate(cart));
	}
}
