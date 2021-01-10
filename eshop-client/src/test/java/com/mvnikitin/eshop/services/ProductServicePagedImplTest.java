package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.CategoryDTO;
import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.mappers.ProductMapper;
import com.mvnikitin.eshop.model.Category;
import com.mvnikitin.eshop.model.Product;
import com.mvnikitin.eshop.repositories.ProductRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
public class ProductServicePagedImplTest {

    @Autowired
    private ProductServicePaged productServicePaged;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductMapper productMapper;

    private Pageable pageable = PageRequest.of(1, 1, Sort
            .sort(Product.class)
            .by("name")
            .ascending());

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ProductServicePaged employeeService() throws NoSuchFieldException, IllegalAccessException {
            return new ProductServicePagedImpl();
        }
    }

    @Test
    public void whenGetItemsByPageWithCategoryIsNull_thenReturnExpectedItemsByPage() {

        Product product = new Product();
        product.setName("prod 4");
        product.setIsActive(true);
        product.setPrice(BigDecimal.valueOf(3L));

        List<Product> list = new ArrayList<>();
        list.add(product);
        Page<Product> page = new PageImpl<>(list);

        ProductDTO expected = new ProductDTO();
        expected.setName(product.getName());
        expected.setIsActive(product.getIsActive());
        expected.setPrice(product.getPrice());

        Mockito.doReturn(page).when(productRepository)
                .findByIsActiveTrueAndPriceBetween(
                        BigDecimal.valueOf(2L),
                        BigDecimal.valueOf(5L),
                        pageable);

        Mockito.doReturn(expected)
                .when(productMapper).productToProductDTO(product);

        Page<ProductDTO> actual = productServicePaged.getItemsByPage(BigDecimal.valueOf(2), BigDecimal.valueOf(5), 2, 1, "name", null);

        assertThat(actual.get().collect(Collectors.toList()).size()).isEqualTo(1);
        assertThat(actual.get().collect(Collectors.toList()).get(0)).isEqualTo(expected);
    }

    @Test
    public void whenGetItemsByPageWithCategory_thenReturnExpectedItemsByPage() {

        Category category = new Category();
        category.setName("cat");
        category.setId(1);

        Product product = new Product();
        product.setName("prod 5");
        product.setIsActive(true);
        product.setCategory(category);
        product.setPrice(BigDecimal.valueOf(4L));

        List<Product> list = new ArrayList<>();
        list.add(product);
        Page<Product> page = new PageImpl<>(list);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setId(category.getId());

        ProductDTO expected = new ProductDTO();
        expected.setName(product.getName());
        expected.setIsActive(product.getIsActive());
        expected.setPrice(product.getPrice());
        expected.setCategory(categoryDTO);

        Mockito.doReturn(page).when(productRepository)
                .findByIsActiveTrueAndCategoryIdAndPriceBetween(
                        1,
                        BigDecimal.valueOf(2L),
                        BigDecimal.valueOf(5L),
                        pageable);

        Mockito.doReturn(expected)
                .when(productMapper).productToProductDTO(product);

        Page<ProductDTO> actual = productServicePaged.getItemsByPage(BigDecimal.valueOf(2), BigDecimal.valueOf(5), 2, 1, "name", 1);

        assertThat(actual.get().collect(Collectors.toList()).size()).isEqualTo(1);
        assertThat(actual.get().collect(Collectors.toList()).get(0)).isEqualTo(expected);
    }
}
