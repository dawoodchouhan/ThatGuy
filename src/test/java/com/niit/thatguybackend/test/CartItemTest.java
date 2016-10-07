package com.niit.thatguybackend.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.CartItemDAO;
import com.niit.thatguybackend.model.CartItem;

public class CartItemTest {
	public static void main(String[] args){
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		//we have to specify in which package the classes are there
		context.scan("com.niit.thatguybackend");
		context.refresh();
		CartItemDAO cartItemDAO=(CartItemDAO) context.getBean("cartItemDAO");
		CartItem cartItem=(CartItem) context.getBean("cartItem");
		
		cartItem.setId("123A");
		cartItem.setBrand("fastrack");
		cartItem.setCategory("Watch");
		cartItem.setPrice("4800");
		cartItem.setQuantity("30");
	
		cartItemDAO.saveOrUpdate(cartItem);
		System.out.println("objects are created successfully");
	}

}
