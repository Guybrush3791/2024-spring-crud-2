package org.java.spring_crud2;

import java.util.List;
import java.util.Optional;

import org.java.spring_crud2.db.pojo.Product;
import org.java.spring_crud2.db.pojo.Review;
import org.java.spring_crud2.db.serv.ProductService;
import org.java.spring_crud2.db.serv.ReviewServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrud2Application
		implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	@Autowired
	private ReviewServ reviewServ;

	public static void main(String[] args) {
		SpringApplication.run(SpringCrud2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Hello, World!");

		// testProduct();
		// testProductDb();
		// testVincoliIntegrita();
		// testMinMaxAvg();
		testRelations();

		System.out.println("The end");
	}

	public void testProduct() {

		try {
			Product p1 = new Product(
					"Product 1", "Description 1", 100, 10,
					100,
					"New");

			System.out.println(p1);
		} catch (Exception e) {

			System.out.println("Error in testProduct: " + e.getMessage());
		}
	}

	public void testProductDb() {

		try {
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
		} catch (Exception e) {

			System.out.println("Error in testProductDb: " + e.getMessage());
		}
	}

	public void testVincoliIntegrita() {

		try {
			Product p1 = new Product(
					"Product 1", "Description 1", 100, 10,
					-100,
					"New");
		} catch (Exception e) {

			System.out.println("Error in testVincoliIntegrita: " + e.getMessage());
		}
	}

	public void testMinMaxAvg() {

		// Creare almeno 5 entita' da salvare in DB.
		try {

			Product p1 = new Product(
					"Product 1", "Description 1", 324, 10,
					100,
					"New");
			Product p2 = new Product(
					"Product 2", "Description 2", 3, 15,
					200,
					"Barely Used");
			Product p3 = new Product(
					"Product 3", "Description 3", 22, 5,
					300,
					"Good contion");
			Product p4 = new Product(
					"Product 4", "Description 4", 4030, 40,
					400,
					"New");
			Product p5 = new Product(
					"Product 5", "Description 5", 5000, 0,
					500,
					"New");

			productService.save(p1);
			productService.save(p2);
			productService.save(p3);
			productService.save(p4);
			productService.save(p5);

			List<Product> products = productService.getAllProducts();
			System.out.println(products);
			System.out.println("------------------------------------------");

			Product minPriceProduct = getMinPriceProduct(products);
			System.out.println("Product with min price:\n" + minPriceProduct);

			Product maxPriceProduct = getMaxPriceProduct(products);
			System.out.println("Product with max price:\n" + maxPriceProduct);

			int avgPrice = getAvgPrice(products);
			System.out.println("Average price: " + avgPrice);

		} catch (Exception e) {

			System.out.println("Error in testMinMaxAvg: " + e.getMessage());
		}
	}

	// public Product getMinPriceProduct(List<Product> products) {

	// int minPrice = Integer.MAX_VALUE;
	// Product minPriceProduct = null;
	// for (Product p : products) {

	// int productPrice = p.getPrice();
	// if (productPrice < minPrice) {

	// minPrice = productPrice;
	// minPriceProduct = p;
	// }
	// }

	// return minPriceProduct;
	// }

	// public Product getMaxPriceProduct(List<Product> products) {

	// int maxPrice = Integer.MIN_VALUE;
	// Product maxPriceProduct = null;
	// for (Product p : products) {

	// int productPrice = p.getPrice();
	// if (productPrice > maxPrice) {

	// maxPrice = productPrice;
	// maxPriceProduct = p;
	// }
	// }

	// return maxPriceProduct;
	// }

	public Product getMinPriceProduct(List<Product> products) {

		return getMinMaxPriceProduct(products, false);
	}

	public Product getMaxPriceProduct(List<Product> products) {

		return getMinMaxPriceProduct(products, true);
	}

	private Product getMinMaxPriceProduct(List<Product> products, boolean max) {

		int tmpPrice = max ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		Product tmpProduct = null;
		for (Product p : products) {

			int productPrice = p.getPrice();

			if (max) {
				if (productPrice > tmpPrice) {

					tmpPrice = productPrice;
					tmpProduct = p;
				}
			} else {
				if (productPrice < tmpPrice) {

					tmpPrice = productPrice;
					tmpProduct = p;
				}
			}
		}

		return tmpProduct;
	}

	public int getAvgPrice(List<Product> products) {

		int sum = 0;
		for (Product p : products)
			sum += p.getPrice();

		return sum / products.size();
	}

	public void testRelations() {

		try {
			Review r1 = new Review(5, "Excellent product");
			Review r2 = new Review(4, "Good product");
			Review r3 = new Review(3, "Average product");

			System.out.println(r1);
			System.out.println(r2);
			System.out.println(r3);

			reviewServ.save(r1);
			reviewServ.save(r2);
			reviewServ.save(r3);

			List<Review> reviews = reviewServ.getAllReviews();

			System.out.println(reviews);
		} catch (Exception e) {

			System.out.println("Error in testRelations: " + e.getMessage());
		}
	}
}
