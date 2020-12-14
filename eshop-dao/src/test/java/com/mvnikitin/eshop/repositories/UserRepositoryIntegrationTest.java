package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnUser() {

        User user = new User();
        user.setFirstName("Alexander");
        user.setMiddleName("Sergeevich");
        user.setLastName("Pushkin");
        user.setEmail("email@mail.ru");
        user.setUsername("nashevse");
        user.setIsBlocked(false);
        user.setPassword("Qwerty3@$");
        user.setPhone("+7 916 322 2233");

        // By some reason JPA returns the reference to the same object.
        // That is why I create a copy
        User expected = getExpected(user);

        entityManager.persistAndFlush(user);

        User actual = userRepository.findOneByUsername(user.getUsername());
        actual.setId(100);

        assertThat(actual).isEqualToIgnoringGivenFields(expected, "id");
    }

    private User getExpected(User user) {
        User expected = new User();
        expected.setFirstName(user.getFirstName());
        expected.setUsername(user.getUsername());
        expected.setLastName(user.getLastName());
        expected.setMiddleName(user.getMiddleName());
        expected.setPassword(user.getPassword());
        expected.setPhone(user.getPhone());
        expected.setIsBlocked(user.getIsBlocked());
        expected.setEmail(user.getEmail());

        return expected;
    }
}
