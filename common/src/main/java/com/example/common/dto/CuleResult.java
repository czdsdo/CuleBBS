package com.example.common.dto;

import com.example.common.enums.StateEnum;

import java.io.Serializable;

/**
 * Rest响应数据
 */

public class CuleResult implements Serializable {
    private  Integer status;
    private  Object data;
    private  String error;
    private  Integer total;
    private long pageSize;

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 请求成功
     * @param status
     */
    public CuleResult(Integer status) {
        this.status = status;
    }

    /**
     * 请求失败
     * @param status
     * @param error
     */
    public CuleResult(Integer status, String error) {
        this.status = status;
        this.error = error;
    }

    /**
     *请求成功被携带参数与页数
     * @param status
     * @param data
     * @param pageSize
     * @param total
     */
    public CuleResult(Integer status, Object data, long pageSize,Integer total) {
        this.status = status;
        this.data = data;
        this.total = total;
        this.pageSize = pageSize;
    }


    /**
     * 请求成功并携带数据
     * @param status
     * @param data
     */
    public CuleResult(Integer status, Object data) {

        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    public static  CuleResult ok(){return  new CuleResult(StateEnum.SUCCESS.getState());}
    public static  CuleResult ok(Object data){return  new CuleResult(StateEnum.SUCCESS.getState(),data);}
    public static CuleResult warn(String warn){return  new CuleResult(StateEnum.WARN.getState(),warn);}
    public static CuleResult error(String error){return  new CuleResult(StateEnum.ERROR.getState(),error);}
    public static CuleResult ok(Object data,long pageSize,Integer total){
        return new CuleResult(StateEnum.SUCCESS.getState(),data,pageSize,total);
    }
}
