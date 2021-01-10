package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.RoleDTO;
import com.mvnikitin.eshop.mappers.RoleMapper;
import com.mvnikitin.eshop.model.Role;
import com.mvnikitin.eshop.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleMapper.rolesToRoleDTOs((List<Role>) roleRepository.findAll());
    }
}
