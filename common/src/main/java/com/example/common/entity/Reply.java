package com.example.common.entity;

import com.example.common.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.geometry.Pos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cule_reply")
public class Reply implements Serializable {
    @Id
    @GeneratedValue
    private  Integer id;
    @Column(columnDefinition = "text",nullable = false)
    private  String content;
    @JsonFormat(pattern = Constants.DATETIME_FORMAT,timezone = "GMT+8")
    private Date initTime;
    private Integer up=0;
    @ManyToOne
    @JoinColumn(nullable = false,name = "posts_id")
    @JsonIgnore
    private Posts posts;
    @ManyToOne
    @JoinColumn(nullable = false,name = "user_id")
    private  User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
