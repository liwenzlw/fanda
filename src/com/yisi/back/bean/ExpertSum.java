package com.yisi.back.bean;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository(value="expertSum")
public class ExpertSum {
    private Integer id;

    private Integer expertId;

    private Float incomeSum;

    private Float paySum;

    private Float mealsSum;

    private Integer numberWant;

    private Integer numberMeet;

    private Integer numberBreak;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public Float getIncomeSum() {
        return incomeSum;
    }

    public void setIncomeSum(Float incomeSum) {
        this.incomeSum = incomeSum;
    }

    public Float getPaySum() {
        return paySum;
    }

    public void setPaySum(Float paySum) {
        this.paySum = paySum;
    }

    public Float getMealsSum() {
        return mealsSum;
    }

    public void setMealsSum(Float mealsSum) {
        this.mealsSum = mealsSum;
    }

    public Integer getNumberWant() {
        return numberWant;
    }

    public void setNumberWant(Integer numberWant) {
        this.numberWant = numberWant;
    }

    public Integer getNumberMeet() {
        return numberMeet;
    }

    public void setNumberMeet(Integer numberMeet) {
        this.numberMeet = numberMeet;
    }

    public Integer getNumberBreak() {
        return numberBreak;
    }

    public void setNumberBreak(Integer numberBreak) {
        this.numberBreak = numberBreak;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}