package com.example.common.dao;

import com.example.common.entity.Label;
import com.example.common.entity.Posts;
import com.example.common.entity.User;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.util.List;

@Repository
@CacheConfig(cacheNames = "postses")
public interface PostsDao extends JpaRepository<Posts,Integer> ,JpaSpecificationExecutor {
    @Cacheable
    @Override
    List<Posts> findAll();
    @Query(value = "SELECT  p.id,p.title,p.reply_count FROM  cule_posts p WHERE  DATE_SUB(CURDATE(),INTERVAL 30 DAY) <=DATE(p.init_time) ORDER BY reply_count desc limit 10",nativeQuery = true)
    List<Object>findHot();
    Page<Posts> findByUser(User user, Pageable pageable);
    Page<Posts>findByLabel(Label label ,Pageable pageable);
}
