package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.UserDTO;
import com.mvnikitin.eshop.mappers.UserMapper;
import com.mvnikitin.eshop.model.User;
import com.mvnikitin.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapper.usersToUserDTOs((List<User>)userRepository.findAll());
    }

    @Override
    public UserDTO findById(Integer id) {
        return userMapper.userToUserDTO(
                userRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("User [id:" +
                                id + "] not found")));
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        return userMapper.userToUserDTO(
                userRepository.save(userMapper.userDTOToUser(userDTO)));
    }

    @Override
    @Transactional
    public UserDTO saveWithoutPassword(UserDTO userDTO) {

        User userToSave = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("User [id:" +
                        userDTO.getId() + "] not found"));

        userMapper.updateUserFromUserDTO(userDTO, userToSave);

        return userMapper.userToUserDTO(
                userRepository.save(userToSave));
    }
}
