package com.niit.thatguybackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="Cart")
@Component
public class Cart {
	@OneToMany(mappedBy="cart:,fetch=FetchType.EAGER")
	@Id
	@Column(name="ID")
	private String id;
	private String mailId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getCartItem() {
		return cartItem;
	}
	public void setCart_item(String cartItem) {
		this.cartItem = cartItem;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalprice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	private String cartItem;
	private String totalPrice;

}
