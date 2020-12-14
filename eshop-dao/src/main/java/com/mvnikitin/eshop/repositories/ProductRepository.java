package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {

    List<Product> findAllByIsActiveTrue();
    List<Product> findAllByCategoryIdAndIsActiveTrue(Integer id, Sort sort);

    Page<Product> findByIsActiveTrueAndPriceBetween(
                                                BigDecimal priceMin,
                                                BigDecimal priceMax,
                                                Pageable pageable);

    Page<Product> findByIsActiveTrueAndCategoryIdAndPriceBetween(Integer id,
                                                             BigDecimal priceMin,
                                                             BigDecimal priceMax,
                                                             Pageable pageable);
}
