package com.niit.thatguybackend.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="Supplier")
@Component
public class Supplier {
	private String id;
	private String name;
	private String address;
	
	@Id
	@Column(name="ID")
	
		@OneToMany(mappedBy="supplier", fetch=FetchType.EAGER)
		private Set<Product> products;
		
		public String getId() {
		return id;
	}
		public Set<Product> getProducts()
		{
			return products;
		}
		public void setProducts(Set<Product> products){
			this.products=products;
		}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
