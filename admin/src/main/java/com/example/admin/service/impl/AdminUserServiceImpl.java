package com.example.admin.service.impl;

import com.example.admin.service.AdminUserService;
import com.example.admin.service.RoleService;
import com.example.admin.utils.PasswordHelper;

import com.example.common.base.BaseServicelmpl;
import com.example.common.dao.AdminUserDao;
import com.example.common.entity.AdminUser;
import com.example.common.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;


import static java.util.stream.Collectors.toSet;

@Service
@Transactional
public class AdminUserServiceImpl extends BaseServicelmpl<AdminUserDao, AdminUser> implements AdminUserService {
    @Autowired
    private RoleService roleService;

    @Override
    public AdminUser findByUserName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Page<AdminUser> findByPage(AdminUser adminUser, int pageNo, int length) {
        PageRequest pageable = new PageRequest(pageNo, length);
        Specification<AdminUser> specification = new Specification<AdminUser>() {
            @Override
            public Predicate toPredicate(Root<AdminUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Integer> $id = root.get("id");
                Path<String> $username = root.get("username");
                Path<Integer> $enable = root.get("enable");
                ArrayList<Predicate> list = new ArrayList<>();
                if (adminUser.getId() != null) list.add(cb.equal($id, adminUser.getId()));
                if (adminUser.getEnable() != null) list.add(cb.equal($enable, adminUser.getEnable()));
                if (adminUser.getUsername() != null) list.add(cb.like($username, "%" + adminUser.getUsername() + "%"));
                Predicate predicate = cb.and(list.toArray(new Predicate[list.size()]));
                return predicate;

            }
        };
        Page<AdminUser> page = repository.findAll(specification, pageable);
        return page;
    }

    @Override
    public void saveAdmin(AdminUser entity) {
        entity.setEnable(1);
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(entity);
        save(entity);

    }

    @Override
    public void saveAdminRoles(Integer uid, Integer[] roles) {
        AdminUser adminUser = findOne(uid);
        if (roles == null) {
            adminUser.setRoles(new HashSet<>());
        } else {
            Set<Role> roleSet = roleService.findAll(Arrays.asList(roles)).stream().collect(toSet());
            adminUser.setRoles(roleSet);
        }
        save(adminUser);
    }

    @Override
    public void saveAdminEnable(Integer[] ids) {
      List<AdminUser>all=findAll(Arrays.asList(ids));
      for (AdminUser user:all){
          if (user.getEnable()==1){
              user.setEnable(0);
          }else {
              user.setEnable(1);
          }
      }
      save(all);
    }
}
