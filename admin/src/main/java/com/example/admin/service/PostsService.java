package com.example.admin.service;

import com.example.common.base.BaseService;
import com.example.common.entity.Posts;
import org.springframework.data.domain.Page;

public interface PostsService extends BaseService<Posts> {
    /**
     * 翻页条件查询帖子
     * @param posts
     * @param pageNo
     * @param length
     * @return
     */
    Page<Posts> findByPage(Posts posts, int pageNo, int length);

    /**
     * 批量修改帖子的top
     * @param ids
     */
    void changeTop(Integer[]ids);

    /**
     * 批量修改帖子的GOOD
     * @param ids
     */
    void changeGood(Integer[]ids);
}
