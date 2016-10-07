package com.niit.thatguybackend.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.ProductDAO;
import com.niit.thatguybackend.model.Product;

import junit.framework.Assert;

public class ProductTestCase {
	@Autowired
	private static ProductDAO productDAO;
	
	@Autowired
	private static Product product;
	
	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.thatguybackend");
		context.refresh();
		
		productDAO=(ProductDAO) context.getBean("productDAO");
		
		product=(Product) context.getBean("product");
	}
	@Test
	public void createProduct()
	{

		product.setId("A01");
		product.setDescription("this is shoes");
		product.setName("Gucci");
		product.setPrice(100);
		product.setCategory_id("CAP01");
		product.setSupplier_id("AAA01");
		
		Assert.assertEquals("product create test case", true,productDAO.saveOrUpdate(product));
	}
}



