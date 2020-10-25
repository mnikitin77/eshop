package com.mvnikitin.eshop.repositories;

import com.mvnikitin.eshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);

    @Query("select u from User u inner join u.roles r where r.name = 'ROLE_CLIENT'")
    List<User> findAllClients();

    @Query("select u from User u inner join u.roles r where r.name = 'ROLE_ADMIN'")
    List<User> findAllAdmins();
}
