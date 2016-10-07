package com.niit.thatguybackend.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.thatguy.dao.SupplierDAO;
import com.niit.thatguybackend.model.Supplier;

public class SupplierTest {
	public static void main(String[] args){
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		//we have to specify in which package the classes are there
		context.scan("com.niit.thatguybackend");
		context.refresh();
		SupplierDAO supplierDAO=(SupplierDAO) context.getBean("supplierDAO");
		Supplier supplier=(Supplier) context.getBean("supplier");
		
		supplier.setId("Ac123");
		supplier.setName("Akshay");
		supplier.setAddress("hyderabad");
		supplierDAO.saveOrUpdate(supplier);
		System.out.println("objects are created successfully");
	}
	}


