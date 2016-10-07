package com.niit.thatguybackend.model;







import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.Table;


import org.springframework.stereotype.Component;

@Entity
@Table(name="Category")
@Component
public class Category {
	@Id
	@Column(name="ID")
	private String id;
	private String name;
	private String description;
	
	

	
	@OneToMany(mappedBy="category",fetch=FetchType.EAGER)
	
	//ondelete cascade
	private Set<Product> product;
	public Set<Product> getProduct() {
	return product;
}
	public void setProduct(Set<Product> product){
	this.product=product;
	}
	
	public String getId() {
		return id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}