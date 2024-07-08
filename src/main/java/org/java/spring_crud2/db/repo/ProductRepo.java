package org.java.spring_crud2.db.repo;

import org.java.spring_crud2.db.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo
        extends JpaRepository<Product, Integer> {

}
