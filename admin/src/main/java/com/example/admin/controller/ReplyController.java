package com.example.admin.controller;

import com.example.admin.service.ReplyService;
import com.example.admin.service.UserService;
import com.example.common.base.BaseController;
import com.example.common.dto.CuleResult;
import com.example.common.dto.PageResult;
import com.example.common.entity.Reply;
import com.example.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/replys")
public class ReplyController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReplyService replyService;

    @GetMapping
    public PageResult getAll(Reply reply, Integer uid, String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length) {
        int pageNo = start / length;
        if (uid != null) {
            User user = userService.findOne(uid);
            reply.setUser(user);
        }
        Page<Reply> page = replyService.findByPage(reply, pageNo, length);
        PageResult<List<Reply>> result = new PageResult<>(draw,
                page.getTotalElements(),
                page.getTotalElements(),
                page.getContent());
        return result;

    }
    @PostMapping("/delete")
    public CuleResult delete(@RequestParam(value = "id[]")Reply[]id){
        CuleResult result=restProcessor(()->{
            replyService.deleteInBatch(Arrays.asList(id));
            return CuleResult.ok();
        });
        return result;
    }
}
