package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.BrandDTO;
import com.mvnikitin.eshop.dto.CategoryDTO;
import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.model.Brand;
import com.mvnikitin.eshop.model.Category;
import com.mvnikitin.eshop.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-01T18:59:22+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setCode( product.getCode() );
        productDTO.setName( product.getName() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setOldPrice( product.getOldPrice() );
        productDTO.setIsActive( product.getIsActive() );
        productDTO.setCategory( categoryToCategoryDTO( product.getCategory() ) );
        productDTO.setBrand( brandToBrandDTO( product.getBrand() ) );
        productDTO.setImages( imageMapper.imagesToImageDTOs( product.getImages() ) );

        return productDTO;
    }

    @Override
    public Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDTO.getId() );
        product.setCode( productDTO.getCode() );
        product.setName( productDTO.getName() );
        product.setDescription( productDTO.getDescription() );
        product.setPrice( productDTO.getPrice() );
        product.setOldPrice( productDTO.getOldPrice() );
        product.setIsActive( productDTO.getIsActive() );
        product.setCategory( categoryDTOToCategory( productDTO.getCategory() ) );
        product.setBrand( brandDTOToBrand( productDTO.getBrand() ) );
        product.setImages( imageMapper.imageDTOsToImages( productDTO.getImages() ) );

        return product;
    }

    @Override
    public List<ProductDTO> productsToProductDTOs(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( products.size() );
        for ( Product product : products ) {
            list.add( productToProductDTO( product ) );
        }

        return list;
    }

    @Override
    public List<Product> productDTOsToProducts(List<ProductDTO> productDTOs) {
        if ( productDTOs == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productDTOs.size() );
        for ( ProductDTO productDTO : productDTOs ) {
            list.add( productDTOToProduct( productDTO ) );
        }

        return list;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setParent( categoryToCategoryDTO( category.getParent() ) );

        return categoryDTO;
    }

    protected BrandDTO brandToBrandDTO(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDTO brandDTO = new BrandDTO();

        brandDTO.setId( brand.getId() );
        brandDTO.setName( brand.getName() );

        return brandDTO;
    }

    protected Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        category.setParent( categoryDTOToCategory( categoryDTO.getParent() ) );

        return category;
    }

    protected Brand brandDTOToBrand(BrandDTO brandDTO) {
        if ( brandDTO == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( brandDTO.getId() );
        brand.setName( brandDTO.getName() );

        return brand;
    }
}
