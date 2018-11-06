package com.example.admin.controller;

import com.example.admin.service.AdminUserService;
import com.example.common.base.BaseController;
import com.example.common.dto.CuleResult;
import com.example.common.dto.PageResult;
import com.example.common.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminUserController extends BaseController {
    @Autowired
    private AdminUserService adminUserService;
    @GetMapping
    public PageResult getAll(AdminUser adminUser, String draw, @RequestParam(required = false,defaultValue = "1")int start,
                             @RequestParam(required = false,defaultValue = "10")int length){
        int pageNo=start/length;
        Page<AdminUser>page=adminUserService.findByPage(adminUser,pageNo,length);
        PageResult<List<AdminUser>>result=new PageResult<>(
                draw,page.getTotalElements(),page.getTotalElements(),page.getContent()
        );
        return result;
    }
    @PostMapping("/delete")
    public CuleResult deleteAdmin(@RequestParam(value = "id[]")AdminUser[] id){
        CuleResult result=restProcessor(()->{
            List<AdminUser>collect= Arrays.asList(id);
            adminUserService.deleteInBatch(collect);
            return  CuleResult.ok();
        });
        return result;
    }
    @PostMapping("/saveAdminRoles")
    public  CuleResult saveAdminRoles(Integer uid,Integer[]id){
        CuleResult result=restProcessor(()->{
            adminUserService.saveAdminRoles(uid,id);
            return CuleResult.ok();
        });
        return result;
    }
    @PostMapping("/saveAdminEnable")
    public CuleResult saveAdminEnable(@RequestParam(value = "id[]")Integer[] id){
        CuleResult result=restProcessor(()->{
            adminUserService.saveAdminEnable(id);
            return  CuleResult.ok();
        });
        return result;
    }
}
