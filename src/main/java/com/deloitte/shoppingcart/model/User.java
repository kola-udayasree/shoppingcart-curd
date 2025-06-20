package com.deloitte.shoppingcart.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {

	@Id
	private String id;

	private String userName;

	@Indexed(unique = true)
	private String emailId;
	private Long phoneNumber;
	private String defaultShippingAddress;
	private String defaultBillingAddress;

	public User() {

	}

	public User(String userName, String emailId, Long phoneNumber, String defaultBillingAddress,
			String defaultShippingAddress) {

		this.userName = userName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.defaultBillingAddress = defaultBillingAddress;
		this.defaultShippingAddress = defaultShippingAddress;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the phoneNumber
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the defaultShippingAddress
	 */
	public String getDefaultShippingAddress() {
		return defaultShippingAddress;
	}

	/**
	 * @param defaultShippingAddress the defaultShippingAddress to set
	 */
	public void setDefaultShippingAddress(String defaultShippingAddress) {
		this.defaultShippingAddress = defaultShippingAddress;
	}

	/**
	 * @return the defaultBillingAddress
	 */
	public String getDefaultBillingAddress() {
		return defaultBillingAddress;
	}

	/**
	 * @param defaultBillingAddress the defaultBillingAddress to set
	 */
	public void setDefaultBillingAddress(String defaultBillingAddress) {
		this.defaultBillingAddress = defaultBillingAddress;
	}

	@Override
	public String toString() {
		return "User [Name=" + userName + ", EmailId=" + emailId + ", phoneNumber=" + phoneNumber + ",defaultShippingAddress ="
				+ defaultShippingAddress + ", defaultBillingAddress= " + defaultBillingAddress + "]";
	}

}
