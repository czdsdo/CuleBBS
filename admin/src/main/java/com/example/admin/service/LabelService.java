package com.example.admin.service;

import com.example.common.base.BaseService;
import com.example.common.entity.Label;
import org.springframework.data.domain.Page;

public interface LabelService extends BaseService<Label>  {
    /**
     * 翻页查询
     * @param pageNo
     * @param length
     * @return
     */
    Page<Label> findByPage(int pageNo, int length);
}
