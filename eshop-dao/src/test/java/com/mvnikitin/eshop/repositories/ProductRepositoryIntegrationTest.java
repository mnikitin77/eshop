package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.model.Category;
import com.mvnikitin.eshop.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    private Pageable pageable = PageRequest.of(1, 1, Sort
            .sort(Product.class)
            .by("name")
            .ascending());


    @Test
    public void whenFindAllByIsActiveTrue_thenReturnActiveProducts() {

        List<Product> expected = new ArrayList<>();

        Product product = new Product();
        product.setName("prod 1");
        entityManager.persist(product);

        product = new Product();
        product.setName("prod 2");
        product.setIsActive(true);
        entityManager.persist(product);

        expected.add(product);

        product = new Product();
        product.setName("prod 3");
        product.setIsActive(true);
        entityManager.persist(product);

        entityManager.flush();

        expected.add(product);

        List<Product> actual = productRepository.findAllByIsActiveTrue();

        assertThat(actual.size()).isEqualTo(expected.size());

        for(int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i)).isIn(expected.toArray());
        }
    }

    @Test
    public void whenFindAllByCategoryIdAndIsActiveTrue_thenReturnAllActiveProductsByGivenCategory() {

        List<Product> expected = new ArrayList<>();

        Category cat1 = new Category();
        cat1.setName("cat 1");
        entityManager.persist(cat1);

        Category cat2 = new Category();
        cat2.setName("cat 2");
        entityManager.persist(cat2);

        entityManager.flush();

        Product product = new Product();
        product.setName("prod 1");
        product.setCategory(cat1);
        entityManager.persist(product);

        product = new Product();
        product.setName("prod 2");
        product.setIsActive(true);
        product.setCategory(cat2);
        entityManager.persist(product);

        product = new Product();
        product.setName("prod 3");
        product.setIsActive(true);
        product.setCategory(cat1);
        entityManager.persist(product);

        expected.add(product);

        product = new Product();
        product.setName("khrenamba");
        product.setIsActive(true);
        product.setCategory(cat1);
        entityManager.persist(product);

        expected.add(product);

        entityManager.flush();

        List<Product> actual = productRepository
                .findAllByCategoryIdAndIsActiveTrue(
                        cat1.getId(),
                        Sort.sort(Product.class).by("name").ascending());

        assertThat(actual.size()).isEqualTo(expected.size());
        assertThat(actual).isSortedAccordingTo(Comparator.comparing(Product::getName));

        for(int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i)).isIn(expected.toArray());
        }
    }

    @Test
    public void whenFindByIsActiveTrueAndPriceBetween_thenReturnAllActiveProdictsFilteredByPrice() {

        List<Product> expected = new ArrayList<>();

        Product product1 = new Product();
        product1.setName("prod 1");
        product1.setIsActive(true);
        product1.setPrice(BigDecimal.valueOf(1L));
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setName("prod 2");
        product2.setIsActive(true);
        product2.setPrice(BigDecimal.valueOf(2L));
        entityManager.persist(product2);

        expected.add(product2);

        Product product3 = new Product();
        product3.setName("prod 3");
        product3.setIsActive(false);
        product3.setPrice(BigDecimal.valueOf(3L));
        entityManager.persist(product3);

        Product product4 = new Product();
        product4.setName("prod 4");
        product4.setIsActive(true);
        product4.setPrice(BigDecimal.valueOf(3L));
        entityManager.persist(product4);

        expected.add(product3);

        Product product5 = new Product();
        product5.setName("prod 5");
        product5.setIsActive(true);
        product5.setPrice(BigDecimal.valueOf(4L));
        entityManager.persist(product5);

        expected.add(product5);

        Product product6 = new Product();
        product6.setName("prod 5");
        product6.setIsActive(true);
        product6.setPrice(BigDecimal.valueOf(100500L));
        entityManager.persist(product6);

        entityManager.flush();

        Page<Product> actual = productRepository.
                findByIsActiveTrueAndPriceBetween(
                        BigDecimal.valueOf(2L),
                        BigDecimal.valueOf(5L),
                        pageable);

        assertThat(actual.getTotalElements()).isEqualTo(3);
        assertThat(actual.getTotalPages()).isEqualTo(3);
        assertThat(actual.toList().size()).isEqualTo(1);

        assertThat(actual.toList().get(0)).isEqualTo(product4);
    }

    @Test
    public void whenFindByIsActiveTrueAndCategoryIdAndPriceBetween_thenReturnAllActiveProdictsFilteredByCategoryAndPrice() {

        List<Product> expected = new ArrayList<>();

        Category category = new Category();
        category.setName("cat 1");
        entityManager.persistAndFlush(category);

        Product product1 = new Product();
        product1.setName("prod 1");
        product1.setIsActive(true);
        product1.setCategory(category);
        product1.setPrice(BigDecimal.valueOf(1L));
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setName("prod 2");
        product2.setIsActive(true);
        product2.setCategory(category);
        product2.setPrice(BigDecimal.valueOf(2L));
        entityManager.persist(product2);

        expected.add(product2);

        Product product3 = new Product();
        product3.setName("prod 3");
        product3.setIsActive(false);
        product3.setCategory(category);
        product3.setPrice(BigDecimal.valueOf(3L));
        entityManager.persist(product3);

        Product product4 = new Product();
        product4.setName("prod 4");
        product4.setIsActive(true);
        product4.setPrice(BigDecimal.valueOf(3L));
        entityManager.persist(product4);

        Product product5 = new Product();
        product5.setName("prod 5");
        product5.setIsActive(true);
        product5.setCategory(category);
        product5.setPrice(BigDecimal.valueOf(4L));
        entityManager.persist(product5);

        expected.add(product5);

        Product product6 = new Product();
        product6.setName("prod 5");
        product6.setIsActive(true);
        product6.setPrice(BigDecimal.valueOf(100500L));
        entityManager.persist(product6);

        entityManager.flush();

        Page<Product> actual = productRepository.
                findByIsActiveTrueAndCategoryIdAndPriceBetween(
                        category.getId(),
                        BigDecimal.valueOf(2L),
                        BigDecimal.valueOf(5L),
                        pageable);

        assertThat(actual.getTotalElements()).isEqualTo(2);
        assertThat(actual.getTotalPages()).isEqualTo(2);
        assertThat(actual.toList().size()).isEqualTo(1);

        assertThat(actual.toList().get(0)).isEqualTo(product5);
    }
}
