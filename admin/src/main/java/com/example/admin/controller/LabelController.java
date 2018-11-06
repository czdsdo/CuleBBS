package com.example.admin.controller;

import com.example.admin.service.LabelService;
import com.example.common.base.BaseController;
import com.example.common.dto.CuleResult;
import com.example.common.dto.PageResult;
import com.example.common.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/labels")
public class LabelController extends BaseController {
    @Autowired
    private LabelService labelService;
    @GetMapping
    public PageResult getAll(String draw,
                             @RequestParam(required = false,defaultValue = "1")int start,
                             @RequestParam(required = false,defaultValue = "10")int length){
        int pageNo=start/length;
        Page<Label> page=labelService.findByPage(pageNo,length);
        PageResult<List<Label>>result=new PageResult<>(draw,
                page.getTotalElements(),
                page.getTotalElements(),
                page.getContent());
        return result;
    }
    @PostMapping("/delete")
    public CuleResult deleteLabels(@RequestParam(value = "id[]")Label[] id){
        CuleResult result=restProcessor(()->{
            labelService.deleteInBatch(Arrays.asList(id));
            return  CuleResult.ok();
        });
        return  result;
    }
    @PostMapping("/add")
    public CuleResult addLabels(Label label){
        CuleResult result=restProcessor(()->{
            labelService.save(label);
            return CuleResult.ok();
        });
        return result;
    }
}
