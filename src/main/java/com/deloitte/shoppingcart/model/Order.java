package com.deloitte.shoppingcart.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Orders")
public class Order {

	@Transient
	public static final String SEQUENCE_NAME = "database_sequences";

	@Id
	private long id;

	private String emailId;
	private String orderedDate;
	private String deliveredDate;
	private String status;

	private List<CartItem> cartItems;
	private CartPricing pricing;

	public Order() {
	}

	public Order(String emailId, String orderedDate, String deliveredDate, String status, List<CartItem> cartItems,
			CartPricing pricing) {
		this.status = status;
		this.emailId = emailId;
		this.orderedDate = orderedDate;
		this.deliveredDate = deliveredDate;
		this.cartItems = cartItems;
		this.pricing = pricing;

	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the cartItems
	 */
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	/**
	 * @param cartItems the cartItems to set
	 */
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	/**
	 * @return the pricing
	 */
	public CartPricing getPricing() {
		return pricing;
	}

	/**
	 * @param pricing the pricing to set
	 */
	public void setPricing(CartPricing pricing) {
		this.pricing = pricing;
	}

	/**
	 * @return the orderedDate
	 */
	public String getOrderedDate() {
		return orderedDate;
	}

	/**
	 * @param orderedDate the orderedDate to set
	 */
	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	/**
	 * @return the deliveredDate
	 */
	public String getDeliveredDate() {
		return deliveredDate;
	}

	/**
	 * @param deliveredDate the deliveredDate to set
	 */
	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

}
