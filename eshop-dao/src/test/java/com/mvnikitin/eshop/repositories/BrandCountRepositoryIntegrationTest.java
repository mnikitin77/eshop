package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.model.Brand;
import com.mvnikitin.eshop.model.IBrandCount;
import com.mvnikitin.eshop.model.Product;
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
public class BrandCountRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BrandCountRepository brandCountRepository;

    @Test
    public void whenCountTotalProductsByBrandNative_thenEverythingIsAsExpected() {

        Brand brand = new Brand();
        brand.setName("Abibas");
        entityManager.persistAndFlush(brand);

        Product product = new Product();
        product.setBrand(brand);
        product.setName("prod1");
        entityManager.persist(product);

        product = new Product();
        product.setBrand(brand);
        product.setName("prod2");
        entityManager.persist(product);
        entityManager.flush();

        IBrandCount expected = new IBrandCount() {
            @Override
            public Integer getId() {
                return brand.getId();
            }

            @Override
            public String getName() {
                return brand.getName();
            }

            @Override
            public Integer getTotal() {
                return 2;
            }
        };

        List<IBrandCount> actual =
                brandCountRepository.countTotalProductsByBrandNative();

        assertThat(actual.get(0).getId()).isEqualTo(expected.getId());
        assertThat(actual.get(0).getName()).isEqualTo(expected.getName());
        assertThat(actual.get(0).getTotal()).isEqualTo(expected.getTotal());
    }

}
