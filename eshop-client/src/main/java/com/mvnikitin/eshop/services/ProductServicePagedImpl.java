package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.mappers.ProductMapper;
import com.mvnikitin.eshop.model.Product;
import com.mvnikitin.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Qualifier("productServicePaged")
@Primary
@Service
public class ProductServicePagedImpl implements ProductServicePaged {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Value("${eshop.default_items_per_page}")
    private String defaultItemsPerPage;

    @Value("${eshop.default_sort_by}")
    private String defaultSortBy;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Page<ProductDTO> getItemsByPage(BigDecimal priceMin,
                                           BigDecimal priceMax,
                                           Integer pageNumber,
                                           Integer rowsPerPage,
                                           String sortBy,
                                           Integer categoryId) {

        Sort sort = Sort.sort(Product.class)
                .by(sortBy)
                .ascending();

        Pageable pageable = PageRequest.of(
                pageNumber != null ? pageNumber - 1 : 0,
                rowsPerPage,
                sort);

        if (categoryId != null) {
            return productRepository.findByIsActiveTrueAndCategoryIdAndPriceBetween(
                    categoryId,
                    priceMin,
                    priceMax,
                    pageable)
                    .map(prod -> productMapper.productToProductDTO((Product)prod));
        } else {
            return productRepository.findByIsActiveTrueAndPriceBetween(
                    priceMin, priceMax, pageable)
                    .map(prod -> productMapper.productToProductDTO((Product)prod));
        }
    }
}
