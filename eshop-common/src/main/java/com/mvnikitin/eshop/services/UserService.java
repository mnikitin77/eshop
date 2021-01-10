package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO findById(Integer id);
    List<UserDTO> findAll();
    UserDTO save(UserDTO userDTO);
    UserDTO saveWithoutPassword(UserDTO userDTO);
    UserDTO newUser(UserDTO userDTO);
    void deleteById(Integer id);
}
