package com.yisi.back.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yisi.back.bean.Topic;
import com.yisi.back.dao.TopicDao;
import com.yisi.back.service.StartTest;

@Service(value="startTestImpl")
public class StartTestImpl implements StartTest {
	
	@Autowired
	private TopicDao topicDao;
	
	@Override
	public String queryTopicById(int id) {
		Topic topic = topicDao.queryTopicById(id);
		return topic.toString();
	}

	@Override
	public int insertTopic(Topic topic) {
		return topicDao.insertTopic(topic);
	}

	@Override
	public int updateTopic(Topic topic) {
		return topicDao.updateTopic(topic);
	}

	@Override
	public int deleteTopic(int id) {
		return topicDao.deleteTopicById(id);
	}
}
