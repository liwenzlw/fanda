package com.yisi.back.bean;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository(value="pay")
public class Pay {
    private Integer id;

    private String payNo;

    private Integer orderId;

    private String payOpenid;

    private Float payFee;

    private Integer type;

    private String details;

    private Date payTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPayOpenid() {
        return payOpenid;
    }

    public void setPayOpenid(String payOpenid) {
        this.payOpenid = payOpenid == null ? null : payOpenid.trim();
    }

    public Float getPayFee() {
        return payFee;
    }

    public void setPayFee(Float payFee) {
        this.payFee = payFee;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}