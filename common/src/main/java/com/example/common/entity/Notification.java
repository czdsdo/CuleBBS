package com.example.common.entity;

import com.example.common.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cule_notification")
public class Notification implements Serializable {
    @Id
    @GeneratedValue
    private  Integer id;
    @Column(name = "is_read")
    private boolean isRead=false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "touser_id")
    private User touser;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "posts_id")
    private Posts posts;
    @Column(nullable = false)
    @JsonFormat(pattern = Constants.DATETIME_FORMAT,timezone="GMT+8")
    private Date initTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public User getTouser() {
        return touser;
    }

    public void setTouser(User touser) {
        this.touser = touser;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }
}
