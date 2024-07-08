package org.java.spring_crud2;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.openssl.pem_password_cb;
import org.java.spring_crud2.db.pojo.Product;
import org.java.spring_crud2.db.serv.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrud2Application
		implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(SpringCrud2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Hello, World!");

		// testProduct();
		testProductDb();

		System.out.println("The end");
	}

	public void testProduct() {

		Product p1 = new Product(
				"Product 1", "Description 1", 100, 10,
				100,
				"New");

		System.out.println(p1);
	}

	public void testProductDb() {

		Product p1 = new Product(
				"Product 1", "Description 1", 100, 10,
				100,
				"New");
		Product p2 = new Product(
				"Product 2", "Description 2", 200, 20,
				200,
				"Barely Used");
		Product p3 = new Product(
				"Product 3", "Description 3", 300, 30,
				300,
				"Good contion");

		productService.save(p1);
		productService.save(p2);
		productService.save(p3);

		List<Product> products = productService.getAllProducts();

		System.out.println(products);

		Optional<Product> pId1 = productService.getProductById(1);
		Optional<Product> pId5 = productService.getProductById(5);
		System.out.println(pId1.isPresent() ? pId1 : "Product with id 1 not found");
		System.out.println(pId5.isPresent() ? pId5 : "Product with id 5 not found");

		productService.delete(p1);
	}
}
