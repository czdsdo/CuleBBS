package com.example.common.dto;

import java.io.Serializable;

public class UploadResult implements Serializable {
    private  Integer ocde;//O表示成功，其余失败
    private String msg;
    private  Data data;

    public Integer getOcde() {
        return ocde;
    }

    public void setOcde(Integer ocde) {
        this.ocde = ocde;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public static  class  Data{
        private  String src;
        private  String title;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Data(String src) {

            this.src = src;
        }
    }

    public UploadResult(Integer ocde, Data data) {
        this.ocde = ocde;
        this.data = data;
    }

    public UploadResult(Integer ocde, String msg) {

        this.ocde = ocde;
        this.msg = msg;
    }
}
