package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Role;

public interface RoleService {
    Role createRole(Role role);
    Role getRoleByName(String roleName);
    Boolean deleteRoleById(String roleId);
    Role getRoleById(String roleId);
}
