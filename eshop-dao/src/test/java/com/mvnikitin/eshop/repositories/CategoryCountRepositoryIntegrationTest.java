package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryCountRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryCountRepository categoryCountRepository;

    @Test
    public void whenCountTotalProductsByCategoryNative_thenEverythingIsAsExpected() {

        Category parent = new Category();
        parent.setName("Parent category");
        entityManager.persist(parent);

        Category child1 = new Category();
        child1.setName("Child category 1");
        child1.setParent(parent);
        entityManager.persist(child1);
        entityManager.flush();

        Product product = new Product();
        product.setName("prod1");
        product.setCategory(child1);
        entityManager.persist(product);

        product = new Product();
        product.setName("prod2");
        product.setCategory(child1);
        entityManager.persist(product);
        entityManager.flush();

        ICategoryCount expected = new ICategoryCount() {
            @Override
            public Integer getId() {
                return child1.getId();
            }

            @Override
            public Integer getParentId() {
                return child1.getParent().getId();
            }

            @Override
            public String getName() {
                return child1.getName();
            }

            @Override
            public Integer getTotal() {
                return 2;
            }
        };

        List<ICategoryCount> actual =
                categoryCountRepository.countTotalProductsByCategoryNative();

        assertThat(actual.get(0).getId()).isEqualTo(expected.getId());
        assertThat(actual.get(0).getParentId()).isEqualTo(expected.getParentId());
        assertThat(actual.get(0).getName()).isEqualTo(expected.getName());
        assertThat(actual.get(0).getTotal()).isEqualTo(expected.getTotal());
    }
}
