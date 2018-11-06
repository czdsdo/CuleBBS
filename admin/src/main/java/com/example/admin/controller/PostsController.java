package com.example.admin.controller;

import com.example.admin.service.PostsService;
import com.example.admin.service.UserService;
import com.example.common.base.BaseController;
import com.example.common.dto.CuleResult;
import com.example.common.dto.PageResult;
import com.example.common.entity.Posts;
import com.example.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostsService postsService;

    @GetMapping
    public PageResult getAll(Posts posts, Integer uid, String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length) {
        int pageNo = start / length;
        if (uid != null) {
            User user = userService.findOne(uid);
            posts.setUser(user);
        }
        Page<Posts> page = postsService.findByPage(posts, pageNo, length);
        PageResult<List<Posts>> result = new PageResult<>(
                draw,
                page.getTotalElements(),
                page.getTotalElements(),
                page.getContent()
        );
        return result;
    }
    @PostMapping("/saveTop")
    public CuleResult saveTop(@RequestParam(value = "id[]")Integer[] id){
        CuleResult result=restProcessor(()->{
            postsService.changeTop(id);
            return CuleResult.ok();
        });
        return result;
    }
    @PostMapping("/saveGood")
    public CuleResult saveGood(@RequestParam(value = "id[]")Integer[] id){
        CuleResult result=restProcessor(()->{
            postsService.changeGood(id);
            return CuleResult.ok();
        });
        return result;
    }
    @PostMapping("/delete")
    public CuleResult deletePosts(@RequestParam(value = "id[]")Posts[]id){
        CuleResult result=restProcessor(()->{
            postsService.deleteInBatch(Arrays.asList(id ));
            return CuleResult.ok();
        });
        return result;
    }
}
