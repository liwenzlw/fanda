package com.yisi.back.bean;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository(value="theme")
public class Theme {
    private Integer themeId;

    private String themeTitle;

    private String themeOutline;

    private String themeImg;

    private String themeUrl;

    private Integer themeExpert;

    private Date startTime;

    private Date updateTime;

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle == null ? null : themeTitle.trim();
    }

    public String getThemeOutline() {
        return themeOutline;
    }

    public void setThemeOutline(String themeOutline) {
        this.themeOutline = themeOutline == null ? null : themeOutline.trim();
    }

    public String getThemeImg() {
        return themeImg;
    }

    public void setThemeImg(String themeImg) {
        this.themeImg = themeImg == null ? null : themeImg.trim();
    }

    public String getThemeUrl() {
        return themeUrl;
    }

    public void setThemeUrl(String themeUrl) {
        this.themeUrl = themeUrl == null ? null : themeUrl.trim();
    }

    public Integer getThemeExpert() {
        return themeExpert;
    }

    public void setThemeExpert(Integer themeExpert) {
        this.themeExpert = themeExpert;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}