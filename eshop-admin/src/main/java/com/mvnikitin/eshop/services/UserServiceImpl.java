package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.entities.User;
import com.mvnikitin.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllClients() {
        return userRepository.findAllClients();
    }

    @Override
    public List<User> findAllAdmins() {
        return userRepository.findAllAdmins();
    }
}
