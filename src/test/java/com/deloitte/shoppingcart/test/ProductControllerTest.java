package com.deloitte.shoppingcart.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.deloitte.shoppingcart.ShoppingCartApplication;
import com.deloitte.shoppingcart.model.CartItem;
import com.deloitte.shoppingcart.model.CartPricing;
import com.deloitte.shoppingcart.model.Order;
import com.deloitte.shoppingcart.model.User;
import com.deloitte.shoppingcart.repository.OrderRepository;
import com.deloitte.shoppingcart.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShoppingCartApplication.class)
@WebAppConfiguration
public class ProductControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(ProductControllerTest.class);

	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		userRepository.deleteAll();
		//orderRepository.deleteAll();

		// Load Order Details into Database
		userRepository.save(new User("GOPI", "Gopi@123", 9876543210L, "Deloitte Default T1", "Deloitte Shipping T1"));
		userRepository.save(new User("G2", "Gop2@123", 9576543210L, "Deloitte Default T2", "Deloitte Shipping T2"));


		orderRepository.save(new Order("Gop2@123", "2/21/2020", "", "Inprogress", loadCartItems(), loadCartPricing()));
		orderRepository.save(new Order("Gop2@123", "2/21/2020", "", "Inprogress", loadCartItems(), loadCartPricing()));

	
	}

	@Test
	public void getOrdersData() throws Exception {
		logger.debug("---Testing orders response---");
		mockMvc.perform(get("/shoppingcart/products")).andExpect(status().isOk());
	}
	
	
	private List<CartItem> loadCartItems() {

		List<CartItem> cartItems = new ArrayList<CartItem>();
		CartItem cartItem1 = new CartItem();
		CartItem cartItem2 = new CartItem();

		cartItem1.setPrice(100);
		cartItem1.setProductId("ABZB");
		cartItem1.setSize("M");
		cartItem1.setUnits(5);

		cartItem2.setPrice(10);
		cartItem2.setProductId("ANCB");
		cartItem2.setSize("L");
		cartItem2.setUnits(1);

		cartItems.add(cartItem1);
		cartItems.add(cartItem2);

		return cartItems;
	}

	private CartPricing loadCartPricing() {

		CartPricing cartPricing = new CartPricing();

		cartPricing.setBagDiscount(10);
		cartPricing.setBagTotal(20);
		cartPricing.setCouponDiscount(10);
		cartPricing.setDeliveryCharges(0);
		cartPricing.setDiscountedTotal(5);
		cartPricing.setTax(12);
		cartPricing.setTotalCharges(210);

		return cartPricing;
	}

}
