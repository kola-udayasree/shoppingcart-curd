package com.deloitte.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.deloitte.shoppingcart.model.Product;
import com.deloitte.shoppingcart.model.User;
import com.deloitte.shoppingcart.repository.OrderRepository;
import com.deloitte.shoppingcart.repository.ProductRepository;
import com.deloitte.shoppingcart.repository.UserRepository;

@SpringBootApplication
public class ShoppingCartApplication {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	public static final String PRODUCT_SIZE = "S,M,L,XL";

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
            // To delete the existing users, products and orders.
			orderRepository.deleteAll();
			userRepository.deleteAll();
			productRepository.deleteAll();
			addProductsAndUsers();
		};

	}
	
	
	/**
	 * To Add Products Information to DB on load.
	 */
	public void addProductsAndUsers() {
		// Save Default User Details.
		userRepository.save(new	User("Gopi Eluri","geluri@deloitte.com",8805140054L,"Deloitte Default T1","Deloitte Shipping T1"));
		
		// Save Available Products Information.
		productRepository.save(new Product("GDN-0011", "T Shirt Men", "WROGN", PRODUCT_SIZE, "Green", "M",
				"https://image.shutterstock.com/z/stock-photo-clothing-design-concept-man-in-blank-green-t-shirt-front-and-back-view-292337915.jpg", 1200, 5));

		productRepository.save(new Product("GDN-0012", "Shirt Men", "ARROW", PRODUCT_SIZE, "Blue", "M",
				"https://image.shutterstock.com/z/stock-photo-confidence-and-business-concept-portrait-of-charming-successful-young-entrepreneur-in-blue-collar-1043571091.jpg", 1540, 5));

		productRepository.save(new Product("GDN-0013", "Trouser Men", "LEE", PRODUCT_SIZE, "Grey", "M",
				"https://image.shutterstock.com/z/stock-photo-young-fashion-businessman-s-legs-in-classic-suit-and-shoes-on-wooden-floor-281854640.jpg", 1400, 5));

		productRepository.save(new Product("GDN-0014", "Women Top", "BIBA", PRODUCT_SIZE, "White", "F",
				"https://image.shutterstock.com/z/stock-photo-smiling-young-business-woman-portrait-on-blue-wall-background-white-shirt-435785464.jpg", 1200, 5));

		productRepository.save(new Product("GDN-0015", "Women Saree", "SOCH", PRODUCT_SIZE, "Blue", "F",
				"https://image.shutterstock.com/z/stock-photo-indian-traditional-kancheepuram-silk-saree-1545931655.jpg", 140, 5));
				
		productRepository.save(new Product("GDN-0016", "Women Trouser", "MUFTI", PRODUCT_SIZE, "Yellow", "F",
				"https://image.shutterstock.com/image-photo/girls-beautiful-yellow-skinny-trousers-600w-712737529.jpg", 140, 5));
				
	    productRepository.save(new Product("GDN-0017", "Suite Boy", "TRENDS", PRODUCT_SIZE, "White", "M",
				"https://image.shutterstock.com/z/stock-vector-set-of-baby-clothes-design-template-collection-of-party-suit-set-suit-white-shirt-and-waistcoat-1025969764.jpg", 250, 5));
		productRepository.save(new Product("GDN-0018", "Dress Girl", "MENMOM", PRODUCT_SIZE, "Pink", "F",
				"https://image.shutterstock.com/image-illustration/baby-watercolor-dress-small-girl-600w-1524175229.jpg", 200, 5));
		productRepository.save(new Product("GDN-0019", "Shirt Boy", "GAP", PRODUCT_SIZE, "Red", "M",
				"https://image.shutterstock.com/image-vector/checkered-shortsleeved-casual-shirt-front-600w-1164082099.jpg", 100, 5));
	}
	
	

}
