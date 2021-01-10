package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Role;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.repository.RoleRepository;
import com.dursuneryilmaz.duscrumtool.service.RoleService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import com.dursuneryilmaz.duscrumtool.shared.enums.ExceptionMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    Utils utils;

    @Override
    public Role createRole(Role role) {
        role.setRoleId(utils.generatePublicId(32));
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public Role getRoleById(String roleId) {
        Role role = roleRepository.findByRoleId(roleId);
        if (role != null) return role;
        throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
    }

    @Override
    public Boolean deleteRoleById(String roleId) {
        try {
            roleRepository.delete(getRoleById(roleId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
