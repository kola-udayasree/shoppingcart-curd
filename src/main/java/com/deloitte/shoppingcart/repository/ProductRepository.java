package com.deloitte.shoppingcart.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deloitte.shoppingcart.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	public List<Product> findAll();
}
