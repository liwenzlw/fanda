package com.yisi.back.dao;

import com.yisi.back.bean.Topic;

public interface TopicDao {
	
    int deleteTopicById(int topicId);
    
    Topic queryTopicById(int topicId);
    
    int insertTopic(Topic topic);
    
    int updateTopic(Topic topic);
}