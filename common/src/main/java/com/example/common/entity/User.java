package com.example.common.entity;

import com.example.common.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cule_user")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private  Integer id;
    @Column(nullable = false)
    private  String email;
    @Column(nullable = false,unique = true)
    private  String username;
    @Column(nullable = false)
    @JsonIgnore
    private  String password;
    private String icon="http://127.0.0.1/images/upload/default.png";
    private String signature;
    @Column(nullable = false)
    @JsonFormat(pattern = Constants.DATE_FORMAT,timezone = "GMT+8")
    private Date initTime;
    private Integer sex=0;//男0女1
    @Column(nullable = false)
    private Integer enable=1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "User{"+
                "id="+id+
                ",username='"+username+"\'"+
                ",password='"+password+"\'"+
                ",Icon='"+icon+'\''+
                ",signature='"+signature+'\''+
                ",initTime='"+initTime+'\''+
                ",sex='"+sex+"\'"+
                ",enable='"+enable+"\'"+
                "}";
    }
}
