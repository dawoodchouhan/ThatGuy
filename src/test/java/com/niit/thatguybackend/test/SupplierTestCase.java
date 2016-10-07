package com.niit.thatguybackend.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.SupplierDAO;
import com.niit.thatguybackend.model.Supplier;

import junit.framework.Assert;

public class SupplierTestCase {
	@Autowired
	private static SupplierDAO supplierDAO;
	
	@Autowired
	private static Supplier supplier;
	
	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.thatguybackend");
		context.refresh();
		
		supplierDAO=(SupplierDAO) context.getBean("supplierDAO");
		
		supplier=(Supplier) context.getBean("supplier");
	}
	@Test
	public void createSupplier()
	{

		supplier.setId("AAA01");
		supplier.setName("Akshay");
		supplier.setAddress("hyderabad");
		
		Assert.assertEquals("supplier create test case", true,supplierDAO.saveOrUpdate(supplier));
	}

}
