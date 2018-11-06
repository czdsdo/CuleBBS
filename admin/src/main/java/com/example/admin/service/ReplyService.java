package com.example.admin.service;

import com.example.common.base.BaseService;
import com.example.common.entity.Reply;
import org.springframework.data.domain.Page;

public interface ReplyService extends BaseService<Reply> {
    /**
     * 翻页条件查询回复
     * @param reply
     * @param pageNo
     * @param length
     * @return
     */
    Page<Reply>findByPage(Reply reply,int pageNo,int length);
}
