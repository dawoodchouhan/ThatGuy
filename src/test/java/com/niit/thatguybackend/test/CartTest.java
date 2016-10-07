package com.niit.thatguybackend.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.CartDAO;
import com.niit.thatguybackend.model.Cart;

public class CartTest {
	public static void main(String[] args){
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		//we have to specify in which package the classes are there
		context.scan("com.niit.thatguybackend");
		context.refresh();
		CartDAO cartDAO=(CartDAO) context.getBean("cartDAO");
		Cart cart=(Cart) context.getBean("cart");
		
		cart.setTotal_price("1000");
		cart.setId("Aw123");
		cart.setMail_id("d@1232");
		cart.setCart_item("item 123");
		cartDAO.saveOrUpdate(cart);
		System.out.println("objects are created successfully");
	}
	}
