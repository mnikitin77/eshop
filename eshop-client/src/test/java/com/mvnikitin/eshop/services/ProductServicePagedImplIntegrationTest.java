package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.EshopClientApplication;
import com.mvnikitin.eshop.dto.CategoryDTO;
import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.mappers.CategoryMapper;
import com.mvnikitin.eshop.mappers.ProductMapper;
import com.mvnikitin.eshop.model.Category;
import com.mvnikitin.eshop.model.Product;
import com.mvnikitin.eshop.repositories.CategoryRepository;
import com.mvnikitin.eshop.repositories.ProductRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopClientApplication.class)
public class ProductServicePagedImplIntegrationTest {

    @Autowired
    private ProductServicePaged productServicePaged;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @MockBean
    private EurekaClient eurekaClient;

    private Pageable pageable = PageRequest.of(1, 1, Sort
            .sort(Product.class)
            .by("name")
            .ascending());

    @Test
    @Transactional
    public void whenGetItemsByPageWithCategoryIsNull_thenReturnExpectedItemsByPage() {

        Product[] products = new Product[6];
        initDB(products);

        ProductDTO expected = productMapper.productToProductDTO(products[3]);

        Page<ProductDTO> actual = productServicePaged.getItemsByPage(
                BigDecimal.valueOf(2), BigDecimal.valueOf(5), 2, 1, "name", null);

        assertThat(actual.get().collect(Collectors.toList()).size()).isEqualTo(1);
        assertThat(actual.get().collect(Collectors.toList()).get(0)).isEqualTo(expected);
    }

    @Test
    @Transactional
    public void whenGetItemsByPageWithCategory_thenReturnExpectedItemsByPage() {

        Product[] products = new Product[6];
        initDB(products);

        ProductDTO expected = productMapper.productToProductDTO(products[4]);

        Page<ProductDTO> actual = productServicePaged.getItemsByPage(
                BigDecimal.valueOf(2), BigDecimal.valueOf(5), 2, 1, "name", 1);

        assertThat(actual.get().collect(Collectors.toList()).size()).isEqualTo(1);
        assertThat(actual.get().collect(Collectors.toList()).get(0)).isEqualTo(expected);
    }

    private void initDB(Product[] products) {

        Category category = new Category();
        category.setName("cat 1");
        category = categoryRepository.save(category);

        products[0] = new Product();
        products[0].setName("prod 1");
        products[0].setIsActive(true);
        products[0].setCategory(category);
        products[0].setPrice(BigDecimal.valueOf(1L));
        products[0] = productRepository.save(products[0]);

        products[1] = new Product();
        products[1].setName("prod 2");
        products[1].setIsActive(true);
        products[1].setCategory(category);
        products[1].setPrice(BigDecimal.valueOf(2L));
        products[1] = productRepository.save(products[1]);

        products[2] = new Product();
        products[2].setName("prod 3");
        products[2].setIsActive(false);
        products[2].setCategory(category);
        products[2].setPrice(BigDecimal.valueOf(3L));
        products[2] = productRepository.save(products[2]);

        products[3] = new Product();
        products[3].setName("prod 4");
        products[3].setIsActive(true);
        products[3].setPrice(BigDecimal.valueOf(3L));
        products[3] = productRepository.save(products[3]);

        products[4] = new Product();
        products[4].setName("prod 5");
        products[4].setIsActive(true);
        products[4].setCategory(category);
        products[4].setPrice(BigDecimal.valueOf(4L));
        products[4] = productRepository.save(products[4]);

        products[5] = new Product();
        products[5].setName("prod 5");
        products[5].setIsActive(true);
        products[5].setPrice(BigDecimal.valueOf(100500L));
        products[5] = productRepository.save(products[5]);
    }
}
