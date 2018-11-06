package com.example.admin.service.impl;

import com.example.admin.service.LabelService;
import com.example.common.base.BaseServicelmpl;
import com.example.common.dao.LabelDao;
import com.example.common.entity.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class LabelServiceImpl extends BaseServicelmpl<LabelDao,Label>implements LabelService {
    @Override
    public Page<Label> findByPage(int pageNo, int length) {
        PageRequest pageRequest=new PageRequest(pageNo,length);
        Page<Label>page=repository.findAll(pageRequest);
        return  page;
    }
}
