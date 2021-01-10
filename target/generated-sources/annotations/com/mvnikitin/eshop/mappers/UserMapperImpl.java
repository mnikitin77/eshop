package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.UserDTO;
import com.mvnikitin.eshop.model.Role;
import com.mvnikitin.eshop.model.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-06T21:43:51+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setMiddleName( user.getMiddleName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setPhone( user.getPhone() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setIsBlocked( user.getIsBlocked() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDTO.setRoles( new HashSet<Role>( set ) );
        }

        return userDTO;
    }

    @Override
    public User userDTOToUser(UserDTO UserDTO) {
        if ( UserDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( UserDTO.getId() );
        user.setUsername( UserDTO.getUsername() );
        user.setFirstName( UserDTO.getFirstName() );
        user.setMiddleName( UserDTO.getMiddleName() );
        user.setLastName( UserDTO.getLastName() );
        user.setPhone( UserDTO.getPhone() );
        user.setEmail( UserDTO.getEmail() );
        user.setIsBlocked( UserDTO.getIsBlocked() );
        Set<Role> set = UserDTO.getRoles();
        if ( set != null ) {
            user.setRoles( new HashSet<Role>( set ) );
        }

        return user;
    }

    @Override
    public List<UserDTO> usersToUserDTOs(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( User user : users ) {
            list.add( userToUserDTO( user ) );
        }

        return list;
    }

    @Override
    public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
        if ( userDTOs == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDTOs.size() );
        for ( UserDTO userDTO : userDTOs ) {
            list.add( userDTOToUser( userDTO ) );
        }

        return list;
    }

    @Override
    public void updateUserFromUserDTO(UserDTO userDTO, User user) {
        if ( userDTO == null ) {
            return;
        }

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setFirstName( userDTO.getFirstName() );
        user.setMiddleName( userDTO.getMiddleName() );
        user.setLastName( userDTO.getLastName() );
        user.setPhone( userDTO.getPhone() );
        user.setEmail( userDTO.getEmail() );
        user.setIsBlocked( userDTO.getIsBlocked() );
        if ( user.getRoles() != null ) {
            Set<Role> set = userDTO.getRoles();
            if ( set != null ) {
                user.getRoles().clear();
                user.getRoles().addAll( set );
            }
            else {
                user.setRoles( null );
            }
        }
        else {
            Set<Role> set = userDTO.getRoles();
            if ( set != null ) {
                user.setRoles( new HashSet<Role>( set ) );
            }
        }
    }
}
