package com.example.admin.controller;

import com.example.admin.service.RoleService;
import com.example.common.base.BaseController;
import com.example.common.dto.CuleResult;
import com.example.common.dto.PageResult;
import com.example.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @GetMapping
    public PageResult getAll(String draw,
                             @RequestParam(required = false,defaultValue = "1")int start,
                             @RequestParam(required = false,defaultValue = "10")int length){
        int pageNo=start/length;
        Page<Role> page=roleService.findByPage(pageNo,length);
        PageResult<List<Role>>result=new PageResult<>(draw,
                page.getTotalElements(),
                page.getTotalElements(),
                page.getContent()
        );
        return result;
    }
    @PostMapping("/rolesWithSelected")
    public CuleResult rolesWithSelected(Integer uid){
        CuleResult result=restProcessor(()->{
            roleService.findRolesAndSelected(uid);
            return  CuleResult.ok();
        });
        return result;

    }
    @PostMapping("/add")
    public CuleResult add(Role role){
        CuleResult result=restProcessor(()->{
            roleService.save(role);
            return CuleResult.ok();
        });
        return result;
    }
    @PostMapping("/delete")

}
