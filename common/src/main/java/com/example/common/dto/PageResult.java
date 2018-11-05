package com.example.common.dto;

import java.io.Serializable;

/**
 * 后台翻页响应数据
 */
public class PageResult<T> implements Serializable {
    public PageResult(){}

    private  String draw;
    private Long recordsTotal;
    private  Long recordsFiltered;
    private  T data;

    public PageResult(String draw, Long recordsTotal, Long recordsFiltered, T data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
