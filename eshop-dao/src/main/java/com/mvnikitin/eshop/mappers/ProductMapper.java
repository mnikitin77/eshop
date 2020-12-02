package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = ImageMapper.class)
public interface ProductMapper {

    @Mapping(target = "file", ignore = true)
    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);

    List<ProductDTO> productsToProductDTOs(List<Product> products);

    List<Product> productDTOsToProducts(List<ProductDTO> productDTOs);

}
