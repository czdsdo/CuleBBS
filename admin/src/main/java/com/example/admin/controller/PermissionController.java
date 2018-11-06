package com.example.admin.controller;

import com.example.admin.service.PermissionService;
import com.example.admin.shiro.ShiroService;
import com.example.common.base.BaseController;
import com.example.common.dto.CuleResult;
import com.example.common.dto.PageResult;
import com.example.common.entity.Permission;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.CubicCurve2D;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ShiroService shiroService;

    @PostMapping("/loadMenu")
    public List<Permission> loadMenu() {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("AdminSessionId");
        List<Permission> list = permissionService.loadUserPermissionByType(userId, 1);
        return list;
    }

    @PostMapping("/PermissionWithSelected")
    public CuleResult PermissionWithSelected(Integer roleId) {
        CuleResult result = restProcessor(() -> {
            List<Permission> data = permissionService.findPermissionsAndSelected(roleId);
            return CuleResult.ok(data);
        });
        return result;
    }

    @GetMapping
    public PageResult getAll(String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length) {
        int pageNo = start / length;
        Page<Permission> page = permissionService.findByPage(pageNo, length);
        PageResult<List<Permission>> result = new PageResult<>(
                draw,
                page.getTotalElements(),
                page.getTotalElements(),
                page.getContent());
        return result;
    }
    @PostMapping("/add")
    public CuleResult add(Permission permission){
        CuleResult result=restProcessor(()->{
            permissionService.save(permission);
            shiroService.updatePermission();
            return CuleResult.ok();
        });
        return result;
    }
    @PostMapping("/delete")
    public CuleResult delete(@RequestParam(value = "id[]")Permission[] id){
        CuleResult result=restProcessor(()->{
            permissionService.deleteInBatch(Arrays.asList(id));
            shiroService.updatePermission();
            return CuleResult.ok();
        });
        return result;
    }

}
