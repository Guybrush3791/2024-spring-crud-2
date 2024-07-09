package org.java.spring_crud2.db.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_crud2.db.pojo.Product;
import org.java.spring_crud2.db.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {

        return productRepo.findAll();
    }

    @Transactional
    public List<Product> getAllProductsWReviews() {

        List<Product> products = productRepo.findAll();

        for (Product product : products) {

            Hibernate.initialize(product.getReviews());
        }

        return products;
    }

    public Optional<Product> getProductById(int id) {

        return productRepo.findById(id);
    }

    @Transactional
    public Optional<Product> getProductWReviews(int id) {

        Optional<Product> optProduct = getProductById(id);

        if (optProduct.isEmpty())
            return Optional.empty();

        Hibernate.initialize(optProduct.get().getReviews());

        return optProduct;
    }

    public void save(Product product) {

        productRepo.save(product);
    }

    public void delete(Product product) {

        productRepo.delete(product);
    }
}
