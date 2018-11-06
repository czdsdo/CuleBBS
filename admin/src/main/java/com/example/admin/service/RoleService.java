package com.example.admin.service;

import com.example.common.base.BaseService;
import com.example.common.dto.CuleResult;
import com.example.common.entity.Permission;
import com.example.common.entity.Role;
import org.springframework.data.domain.Page;

public interface RoleService extends BaseService<Role> {
    CuleResult findRolesAndSelected(Integer id);
    Page<Role> findByPage(int pageNo, int length);
    void saveRolePermission(Integer roleid, Permission[] pers);
}
