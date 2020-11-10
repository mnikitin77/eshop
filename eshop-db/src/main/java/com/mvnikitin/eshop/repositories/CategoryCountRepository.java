package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.model.Category;
import com.mvnikitin.eshop.model.ICategoryCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryCountRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT c.id AS Id, c.parent_id AS ParentId, c.name " +
            "AS Name, COUNT(p.id) AS Total FROM categories AS c " +
            "LEFT JOIN products AS p ON p.category_id = c.id GROUP BY c.id " +
            "ORDER BY Total DESC", nativeQuery = true)
    List<ICategoryCount> countTotalProductsByCategoryNative();
}
