package com.deloitte.shoppingcart.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deloitte.shoppingcart.model.Order;

public interface OrderRepository extends MongoRepository<Order, Long> {

	public List<Order> findByEmailId(String emailId);

	public List<Order> findAll();

}
