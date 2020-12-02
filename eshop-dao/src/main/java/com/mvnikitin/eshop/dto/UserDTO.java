package com.mvnikitin.eshop.dto;

import com.mvnikitin.eshop.model.Role;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private Boolean isBlocked;

    private Set<Role> roles;

    public boolean isAdmin() {
        return roles.stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"));
    }

    public String showRoles() {
        return roles.stream().map(Role::getName)
                .collect(Collectors.joining(", "));
    }
}
