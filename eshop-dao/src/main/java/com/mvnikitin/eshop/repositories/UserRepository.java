package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByUsername(String username);
}
