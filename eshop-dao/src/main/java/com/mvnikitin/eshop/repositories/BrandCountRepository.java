package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.model.Brand;
import com.mvnikitin.eshop.model.IBrandCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandCountRepository extends JpaRepository<Brand, Integer> {

    @Query(value = "SELECT b.id AS Id, b.name AS Name, COUNT(p.id) AS Total " +
            "FROM brands AS b LEFT JOIN products AS p ON p.brand_id =" +
            " b.id GROUP BY b.id ORDER BY Total DESC", nativeQuery = true)
    List<IBrandCount> countTotalProductsByBrandNative();
}
