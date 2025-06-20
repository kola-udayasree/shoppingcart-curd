package com.deloitte.shoppingcart.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CartPricing")
public class CartPricing {


	private int bagTotal;
	private int bagDiscount;
	private int tax;
	private int couponDiscount;
	private int discountedTotal;
	private int deliveryCharges;
	private int totalCharges;

	public CartPricing() {
	}

	public CartPricing(int bagTotal, int bagDiscount, int tax, int couponDiscount, int discountedTotal,
			int deliveryCharges, int totalCharges) {
		this.bagTotal = bagTotal;
		this.bagDiscount = bagDiscount;
		this.tax = tax;
		this.couponDiscount = couponDiscount;
		this.discountedTotal = discountedTotal;
		this.deliveryCharges = deliveryCharges;
		this.totalCharges = totalCharges;

	}

	
	public int getBagTotal() {
		return bagTotal;
	}

	public void setBagTotal(int bagTotal) {
		this.bagTotal = bagTotal;
	}

	public int getBagDiscount() {
		return bagDiscount;
	}

	public void setBagDiscount(int bagDiscount) {
		this.bagDiscount = bagDiscount;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public int getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(int couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public int getDiscountedTotal() {
		return discountedTotal;
	}

	public void setDiscountedTotal(int discountedTotal) {
		this.discountedTotal = discountedTotal;
	}

	public int getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(int deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public int getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(int totalCharges) {
		this.totalCharges = totalCharges;
	}

	@Override
	public String toString() {
		return String.format("Pricing[bagTotal=%s, pricingTotal='%s']", bagTotal, totalCharges);
	}

}
