package com.yisi.back.bean;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository(value="topic")
public class Topic {
	
    private Integer topicId;

    private String topicTitle;

    private String topicDetail;

    private Integer topicRes;

    private Date startTime;

    private Date updateTime;

    @Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicTitle=" + topicTitle
				+ ", topicDetail=" + topicDetail + ", topicRes=" + topicRes
				+ ", startTime=" + startTime + ", updateTime=" + updateTime
				+ "]";
	}

	public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle == null ? null : topicTitle.trim();
    }

    public String getTopicDetail() {
        return topicDetail;
    }

    public void setTopicDetail(String topicDetail) {
        this.topicDetail = topicDetail == null ? null : topicDetail.trim();
    }

    public Integer getTopicRes() {
        return topicRes;
    }

    public void setTopicRes(Integer topicRes) {
        this.topicRes = topicRes;
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