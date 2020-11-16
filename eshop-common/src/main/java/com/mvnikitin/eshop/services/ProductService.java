package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.BrandDTO;
import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.model.Category;
import com.mvnikitin.eshop.model.Product;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductDTO findById(Integer id);
    List<ProductDTO> findAll();
    List<ProductDTO> findAllActive();
    List<ProductDTO> findAllByCategoryIdActive(Integer id);
    ProductDTO save(ProductDTO productDTO) throws IOException;
    void deleteById(Integer id) throws IOException;

    Page<ProductDTO> getItemsByPage(BigDecimal priceMin,
                                 BigDecimal priceMax,
                                 Integer pageNumber,
                                 Integer rowsPerPage,
                                 String sortBy,
                                 Integer categoryId);
}
