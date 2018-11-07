package com.example.admin.controller;

import com.example.admin.service.UserService;
import com.example.common.base.BaseController;
import com.example.common.dto.CuleResult;
import com.example.common.dto.PageResult;
import com.example.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @GetMapping
    public PageResult getAll(User user, String draw,
                             @RequestParam(required = false,defaultValue = "1")int start,
                             @RequestParam(required = false,defaultValue = "10")int length){
        int pageNo=start/length;
        Page<User>page=userService.findByPage(user,pageNo,length);
        PageResult<List<User>>result=new PageResult<>(
                draw,page.getTotalElements(),page.getTotalElements(),page.getContent());
        return result;
    }
    @PostMapping("/saveUserEnable")
    public CuleResult saveUserEnable(@RequestParam(value = "id[]")Integer[]id){
        CuleResult result=restProcessor(()->{
            userService.saveUserEnable(id);
            return  CuleResult.ok();
        });
        return result;
    }
}
