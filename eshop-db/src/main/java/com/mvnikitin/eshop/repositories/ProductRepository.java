package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {

    List<Product> findAllByIsActive(Boolean isActive);
    List<Product> findAllByCategoryId(Integer id, Sort sort);
}
