package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.RoleDTO;
import com.mvnikitin.eshop.model.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-06T21:43:50+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setName( role.getName() );

        return roleDTO;
    }

    @Override
    public Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDTO.getId() );
        role.setName( roleDTO.getName() );

        return role;
    }

    @Override
    public List<RoleDTO> rolesToRoleDTOs(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( roles.size() );
        for ( Role role : roles ) {
            list.add( roleToRoleDTO( role ) );
        }

        return list;
    }

    @Override
    public List<Role> roleDTOsToRoles(List<RoleDTO> roleDTOs) {
        if ( roleDTOs == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( roleDTOs.size() );
        for ( RoleDTO roleDTO : roleDTOs ) {
            list.add( roleDTOToRole( roleDTO ) );
        }

        return list;
    }
}
