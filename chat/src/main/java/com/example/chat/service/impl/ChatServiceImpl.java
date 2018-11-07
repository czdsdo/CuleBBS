package com.example.chat.service.impl;

import com.example.chat.service.ChatService;
import com.example.chat.service.RedisService;
import com.example.common.base.BaseServicelmpl;
import com.example.common.dao.UserDao;
import com.example.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author : chenxu
 */
@Service
public class ChatServiceImpl extends BaseServicelmpl<UserDao,User> implements ChatService{

    @Autowired
    private RedisService<User> redisService;

    @Value("${REDIS_USER_KEY}")
    private String REDIS_USER_KEY;


    @Override
    public User getUserByToken(String token) {
        User user = redisService.getString(REDIS_USER_KEY + token);
        return user;
    }

    @Override
    public boolean authUser(Integer id) {
        User user = repository.findOne(id);
        return user.getEnable() == 1;
    }


}
