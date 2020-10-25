package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAllClients();
    List<User> findAllAdmins();
}
