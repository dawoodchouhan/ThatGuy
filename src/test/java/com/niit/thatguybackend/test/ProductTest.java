package com.niit.thatguybackend.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.ProductDAO;
import com.niit.thatguybackend.model.Product;

public class ProductTest {

	public static void main(String[] args){
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		//we have to specify in which package the classes are there
		context.scan("com.niit.thatguybackend");
		context.refresh();
		ProductDAO productDAO=(ProductDAO) context.getBean("productDAO");
		Product product=(Product) context.getBean("product");
		
		product.setId("123A");
		product.setDescription("this is shoes");
		product.setName("Gucci");
		product.setPrice(100);
		product.setCategory_id("CBCap");
		product.setSupplier_id("Ac123");
		productDAO.saveOrUpdate(product);
		System.out.println("objects are created successfully");
	}

}
