package com.example.chat.service;

import com.example.common.base.BaseService;
import com.example.common.entity.User;

/**
 * @Author : chenxu
 */
public interface ChatService extends BaseService<User>{

    /**
     * 根据Token获取用户
     * @param token
     * @return
     */
    User getUserByToken(String token);

    /**
     * 验证用户
     * @param id
     * @return
     */
    boolean authUser(Integer id);
}
