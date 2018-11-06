package com.example.admin.service.impl;

import com.example.admin.service.PostsService;
import com.example.common.base.BaseServicelmpl;
import com.example.common.dao.PostsDao;
import com.example.common.entity.Posts;
import com.example.common.entity.User;
import javafx.geometry.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostsServiceImpl  extends BaseServicelmpl<PostsDao,Posts>implements PostsService {
    @Override
    public Page<Posts> findByPage(Posts posts, int pageNo, int length) {
        PageRequest pageable=new PageRequest(pageNo,length);
        Sort.Order order=new Sort.Order(Sort.Direction.ASC,"id");
        Sort sort=new Sort(order);
        Specification<Posts>specification=new Specification<Posts>() {
            @Override
            public Predicate toPredicate(Root<Posts> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Integer>$id=root.get("id");
                Path<String>$title=root.get("title");
                Path<User>$user=root.get("user");
                Path<Boolean>$top=root.get("top");
                Path<Boolean>$good=root.get("good");
                ArrayList<Predicate>list=new ArrayList<>();
                if (posts.getId()!=null)list.add(cb.equal($id,posts.getId()));
                if (posts.getTitle()!=null)list.add(cb.like($title,"%"+posts.getTitle()+"%"));
                if (posts.getUser()!=null)list.add(cb.equal($user,posts.getUser()));
                if (posts.isTop()==true)list.add(cb.equal($top,true));
                if (posts.isGood()==true)list.add(cb.equal($good,true));
                Predicate predicate=cb.and(list.toArray(new Predicate[list.size()]));
                return  predicate;

            }
        };
        Page<Posts>page=repository.findAll(specification,pageable);
        return page;
    }

    @Override
    public void changeTop(Integer[] ids) {
        List<Posts>all=findAll(Arrays.asList(ids));
        for (Posts p:all){
            if (p.isTop()==false)p.setTop(true);
            else p.setTop(false);
        }
        save(all);
    }

    @Override
    public void changeGood(Integer[] ids) {
        List<Posts>all=findAll(Arrays.asList(ids));
        for (Posts p:all){
            if (p.isGood()==false)p.setGood(true);
            else p.setGood(false);
        }
        save(all);
    }
}
