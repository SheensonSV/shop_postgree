package main;

import main.api.entitys.List;
import main.api.entitys.Product;
import main.api.repo.ListRepo;
import main.api.repo.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ListRepo listRepo, ProductRepo productRepo) {
		return args -> {
			Product newProduct1 = new Product(
					"Potato",
					"White Krasnodar Potato",
					100);
			Product newProduct2 = new Product(
					"Tomato",
					"Red chili Tomato",
					20);
			Product newProduct3 = new Product(
					"WasserMelone",
					"Astrakhan WasserMelone",
					250);
			java.util.List<Product> productList = new ArrayList<>() {
				{
					add(newProduct1);
					add(newProduct2);
					add(newProduct3);
				}
			};

			List newList = new List(
					"firstList",
					productList);

			productRepo.save(newProduct1);
			productRepo.save(newProduct2);
			productRepo.save(newProduct3);
			listRepo.save(newList);
		};

	}
}
