package com.example.admin.service.impl;

import com.example.admin.service.ReplyService;
import com.example.common.base.BaseServicelmpl;
import com.example.common.dao.ReplyDao;
import com.example.common.entity.Posts;
import com.example.common.entity.Reply;
import com.example.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;

public class ReplyServiceImpl extends BaseServicelmpl<ReplyDao,Reply>implements ReplyService {
    @Override
    public Page<Reply> findByPage(Reply reply, int pageNo, int length) {
        PageRequest pageable=new PageRequest(pageNo,length);
        Specification<Posts>specification=new Specification<Posts>() {
            @Override
            public Predicate toPredicate(Root<Posts> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Integer>$id=root.get("id");
                Path<String>$content=root.get("content");
                Path<User>$user=root.get("user");
                ArrayList<Predicate>list=new ArrayList<>();
                if (reply.getId()!=null)list.add(cb.equal($id,reply.getId()));
                if (reply.getContent()!=null)list.add(cb.like($content,"%"+reply.getContent()+"%"));
                if (reply.getUser()!=null)list.add(cb.equal($user,reply.getUser()));
                Predicate predicate=cb.and(list.toArray(new Predicate[list.size()]));
                return  predicate;
            }
        };
        Page<Reply>page=repository.findAll(specification,pageable);
        return page;
    }
}
