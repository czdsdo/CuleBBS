package com.example.common.dao;

import com.example.common.entity.Reply;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "replies")
public interface ReplyDao extends JpaRepository<Reply,Integer>,JpaSpecificationExecutor {
    @Cacheable
    @Override
    List<Reply>findAll();
}
