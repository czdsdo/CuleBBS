package com.example.common.dto;

import java.io.Serializable;

/**
 * WebSocket通知消息类
 */
public class Socketmessage implements Serializable {
    private  Integer notice;
    private  String message;

    public Integer getNotice() {
        return notice;
    }

    public void setNotice(Integer notice) {
        this.notice = notice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Socketmessage(Integer notice, String message) {

        this.notice = notice;
        this.message = message;
    }

    public Socketmessage(Integer notice) {
        this.notice = notice;
    }
    public  static Socketmessage bulid(Integer notice){return  new Socketmessage(notice);}
    public static Socketmessage build(Integer notice,String message){return  new Socketmessage(notice,message);}
}
