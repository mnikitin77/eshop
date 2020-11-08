package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.ProductDTO;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductDTO findById(Integer id);
    List<ProductDTO> findAll();
    List<ProductDTO> findAllActive();
    ProductDTO save(ProductDTO productDTO) throws IOException;
    void deleteById(Integer id) throws IOException;
}
