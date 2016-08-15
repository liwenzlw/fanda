package com.yisi.back.service;

import com.yisi.back.bean.Topic;

public interface StartTest {
	
	public String queryTopicById(int id);
	
	public int insertTopic(Topic topic);
	
	public int updateTopic(Topic topic);
	
	public int deleteTopic(int id);
}
