package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
//		new VirtualUserController().addingProducts();
		SpringApplication.run(ShopApplication.class, args);
	}
}
