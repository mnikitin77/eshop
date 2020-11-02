package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.RoleDTO;
import com.mvnikitin.eshop.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO roleToRoleDTO(Role role);

    Role roleDTOToRole(RoleDTO roleDTO);

    List<RoleDTO> rolesToRoleDTOs(List<Role> roles);

    List<Role> roleDTOsToRoles(List<RoleDTO> roleDTOs);

}
