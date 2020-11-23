package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.ProductDTO;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface ProductServicePaged {
    Page<ProductDTO> getItemsByPage(BigDecimal priceMin,
                                    BigDecimal priceMax,
                                    Integer pageNumber,
                                    Integer rowsPerPage,
                                    String sortBy,
                                    Integer categoryId);
}
