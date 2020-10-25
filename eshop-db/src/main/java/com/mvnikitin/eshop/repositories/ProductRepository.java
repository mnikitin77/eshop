package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
}
