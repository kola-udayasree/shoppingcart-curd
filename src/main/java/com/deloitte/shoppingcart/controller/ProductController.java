package com.deloitte.shoppingcart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.shoppingcart.model.Product;
import com.deloitte.shoppingcart.repository.ProductRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shoppingcart")
public class ProductController {

	private final ProductRepository productRepository;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * To get all the available products.
	 * @return
	 */
	@GetMapping("/products")
	public List<Product> getProducts() {

		logger.debug("---Getting all products---");
		List<Product> productList = this.productRepository.findAll();
		logger.debug("Product List: "+productList);
		return productList;
	}
	
	
}
