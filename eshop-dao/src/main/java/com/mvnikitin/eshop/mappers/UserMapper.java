package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.UserDTO;
import com.mvnikitin.eshop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    UserDTO userToUserDTO(User user);

    @Mapping(target = "password", ignore = true)
    User userDTOToUser(UserDTO UserDTO);

    List<UserDTO> usersToUserDTOs(List<User> users);

    List<User> userDTOsToUsers(List<UserDTO> userDTOs);

    @Mapping(target = "password", ignore = true)
    void updateUserFromUserDTO(UserDTO userDTO, @MappingTarget User user);
}
