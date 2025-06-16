package com.deloitte.shoppingcart.test;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
public class UserControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;
	private HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@SuppressWarnings("unchecked")
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = (HttpMessageConverter<Object>) Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

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
	public void userNotFound() throws Exception {
		logger.debug("---Testing User not found response---");
		mockMvc.perform(get("/shoppingcart/login/C16").content(this.json(new User())).contentType(contentType))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void userFound() throws Exception {
		logger.debug("---Testing User not found response---");
		mockMvc.perform(get("/shoppingcart/login/Gop2@123").content(this.json(new User())).contentType(contentType))
				.andExpect(status().isOk());
	}

	
	@Test
	public void userSignUpSuccess() throws Exception {
		logger.debug("---Testing Order creation---");
		String couponJson = this.json(new User("GOPI", "Gopiii@123", 9876543210L, "Deloitte Default T1", "Deloitte Shipping T1"));
		mockMvc.perform(post("/shoppingcart/signup").contentType(contentType).content(couponJson))
				.andExpect(status().isOk());
	}
	
	@Test
	public void userSignUpFailure() throws Exception {
		logger.debug("---Testing Order creation---");
		String couponJson = this.json(new User("GOPI", "Gopi@123", 9876543210L, "Deloitte Default T1", "Deloitte Shipping T1"));
		mockMvc.perform(post("/shoppingcart/signup").contentType(contentType).content(couponJson))
				.andExpect(status().is(404));
	}
	
	
	@Test
	public void userUpdateSuccess() throws Exception {
		logger.debug("---Testing Order creation---");
		String couponJson = this.json(new User("GOPI", "Gopi@123", 9876543210L, "Deloitte Default in Test", "Deloitte Shipping in Testing"));
		mockMvc.perform(put("/shoppingcart/updateUserDetails").contentType(contentType).content(couponJson))
				.andExpect(status().isOk());
	}
	
	@Test
	public void userUpdateFailure() throws Exception {
		logger.debug("---Testing Order creation---");
		String couponJson = this.json(new User("GOPI", "Gopui@123", 9876543210L, "Deloitte Default T1", "Deloitte Shipping T1"));
		mockMvc.perform(put("/shoppingcart/updateUserDetails").contentType(contentType).content(couponJson))
				.andExpect(status().is(404));
	}
	
	
	
	@Test
	public void OrderNotFound() throws Exception {
		logger.debug("---Testing Order not found response---");
		mockMvc.perform(get("/shoppingcart/myorders/C16").content(this.json(new Order())).contentType(contentType))
				.andExpect(status().is(404));
	}

	@Test
	public void OrderFound() throws Exception {
		logger.debug("---Testing Order not found response---");
		mockMvc.perform(get("/shoppingcart/myorders/Gop2@123").content(this.json(new Order())).contentType(contentType))
				.andExpect(status().isOk());
	}
	
	
	@Test
	public void OrderNFound() throws Exception {
				
		logger.debug("---Testing User found response---");
		
		String couponJson = this.json(orderRepository.save(new Order("Gop2@123", "2/21/2021", "", "DD", loadCartItems(), loadCartPricing())));
		MvcResult result = mockMvc.perform(post("/shoppingcart/placeorder").contentType(contentType).content(couponJson))
				.andReturn();
		String[] splitJson = result.getResponse().getContentAsString().split(":", 3);
		String[] readIdentifier = splitJson[1].split(",", 2);
		mockMvc.perform(get("/shoppingcart/orders/" + readIdentifier[0])
				.content(this.json(new Order())).contentType(contentType)).andExpect(status().isOk());
		
	}
	
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
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
