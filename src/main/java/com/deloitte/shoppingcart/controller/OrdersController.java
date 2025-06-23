package com.deloitte.shoppingcart.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.shoppingcart.exception.ResourceNotFoundException;
import com.deloitte.shoppingcart.model.Order;
import com.deloitte.shoppingcart.repository.OrderRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shoppingcart")
public class OrdersController {

	private final OrderRepository orderRepository;
	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	OrdersController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	/**
	 * To get Orders using emailId
	 * @param emailId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/myorders/{emailId}")
	public ResponseEntity<List<Order>> getOrderByEmailId(@PathVariable String emailId)
			throws ResourceNotFoundException {
		logger.debug(" --- Getting all orders for given user with details---");
		List<Order> orders = this.orderRepository.findByEmailId(emailId);
		if (null != orders && !orders.isEmpty()) {
			return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException(" Orders not found for EmailId :: " + emailId);
		}
	}


	/**
	 * To get the available orders using id.
	 * @param orderId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId)
			throws ResourceNotFoundException {
		logger.debug(" --- Getting Order details based on given OrderId --- ");
		Order order = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException(" Order not found for :: " + orderId));
		return new ResponseEntity<Order>(order, HttpStatus.OK);

	}

	/**
	 * To Save orders data
	 * @param order
	 * @return
	 */
	@PostMapping("/placeorder")
	public ResponseEntity<Order> saveOrderDetails(@Valid @RequestBody Order order) {
		logger.debug(" --- Saving Order Details for given EmailId --- ");
		orderRepository.save(order);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	/**
	 * To cancel INPROGRESS orders
	 * @param orderId
	 * @param order
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/orders/updatestatus/{id}")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable(value = "id") Long orderId,
			@Valid @RequestBody Order order) throws ResourceNotFoundException {
		logger.debug(" --- Updating Order Status based on OrderId --- ");
		Order dbOrder = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException(" Order not found for OrderId :: " + orderId));
		dbOrder.setStatus(order.getStatus());
		dbOrder.setDeliveredDate(order.getDeliveredDate());
		final Order updatedOrder = orderRepository.save(dbOrder);
		return new ResponseEntity<Order>(updatedOrder, HttpStatus.OK);

	}

	/**
	 * To update delivery status if the item is delivered.
	 * @param orderId
	 * @param order
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/orders/updatedeliverrystatus/{id}")
	public ResponseEntity<Order> updateDeliveryStatus(@PathVariable(value = "id") Long orderId,
			@Valid @RequestBody Order order) throws ResourceNotFoundException {
		logger.debug(" --- Updating Delivery Status based on given OrderId --- ");
		Order dbOrder = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException(" Order not found for OrderId :: " + orderId));

		dbOrder.setDeliveredDate(order.getDeliveredDate());
		dbOrder.setStatus("DELIVERED");
		final Order updatedOrder = orderRepository.save(dbOrder);
		return new ResponseEntity<Order>(updatedOrder, HttpStatus.OK);

	}

}
