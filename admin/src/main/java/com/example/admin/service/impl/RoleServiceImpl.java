package com.example.admin.service.impl;

import com.example.admin.service.AdminUserService;
import com.example.admin.service.PermissionService;
import com.example.admin.service.RoleService;
import com.example.common.base.BaseServicelmpl;
import com.example.common.dao.RoleDao;
import com.example.common.dto.CuleResult;
import com.example.common.entity.Permission;
import com.example.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoleServiceImpl extends BaseServicelmpl<RoleDao, Role> implements RoleService {
    @Autowired
    private AdminUserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public CuleResult findRolesAndSelected(Integer id) {
        Set<Role> userRole = userService.findOne(id).getRoles();
        List<Role> roles = findAll();
        for (Role r : roles) {
            if (userRole.contains(r)) r.setSelsected(1);
        }
        return CuleResult.ok(roles);
    }

    @Override
    public Page<Role> findByPage(int pageNo, int length) {
        PageRequest pageRequest = new PageRequest(pageNo, length);
        Page<Role> page = repository.findAll(pageRequest);
        return page;
    }

    @Override
    public void saveRolePermission(Integer roleid, Permission[] pers) {
        Role role = findOne(roleid);
        if (pers == null) role.setPermissions(new HashSet<>());
        else role.setPermissions(Stream.of(pers).collect(Collectors.toSet()));
        save(role);
    }

}
