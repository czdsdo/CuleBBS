package com.example.common.entity;

import com.example.common.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cule_posts")
public class Posts implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "label_id")
    private Label label;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(columnDefinition = "text")
    private String content;
    @Column(nullable = false)
    @JsonFormat(pattern = Constants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date initTime;
    private boolean top;
    private boolean good;
    @ManyToOne
    @JoinColumn(nullable = false,name = "user_id")
    private  User user;
    @Column(name = "reply_count")
    private int replyCount=0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public String toString() {
        return "Posts{"+
                "id="+id+
                ",label="+label+
                ",title="+title+
                ",content="+content+
                ",initTime="+initTime+
                ",top="+top+
                ",good="+good+
                ",user="+user+
                ",replyCount="+replyCount+
                "}";
    }
}
