package com.example.admin.service.impl;

import com.example.admin.service.UserService;
import com.example.common.base.BaseServicelmpl;
import com.example.common.dao.UserDao;
import com.example.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserServiceImpl extends BaseServicelmpl<UserDao,User>implements UserService {
    @Override
    public Page<User> findByPage(User user, int pageNo, int length) {
        PageRequest pageRequest=new PageRequest(pageNo,length);
        Specification<User>specification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Integer>$id=root.get("id");
                Path<String>$username=root.get("username");
                Path<String>$email=root.get("email");
                Path<Integer>$enable=root.get("enable");
                ArrayList<Predicate>list=new ArrayList<>();
                if (user.getId()!=null)list.add(cb.equal($id,user.getId()));
                if (user.getEnable()!=null)list.add(cb.equal($enable,user.getEnable()));
                if (user.getUsername()!=null)list.add(cb.like($username,"%"+user.getUsername()+"%"));
                if (user.getEmail()!=null)list.add(cb.like($email,"%"+user.getEmail()+"%"));
                Predicate predicate=cb.and(list.toArray(new Predicate[list.size()]));
                 return predicate;
            }
        };
        Page<User>page=repository.findAll(specification,pageRequest);
        return page;
    }

    @Override
    public void saveUserEnable(Integer[] id) {
        List<User>all=findAll(Arrays.asList(id));
        for (User user:all){
            if (user.getEnable()==1){
                user.setEnable(0);
            }else user.setEnable(1);
        }
        save(all);
    }
}
