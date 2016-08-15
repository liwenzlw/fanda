package com.yisi.back.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.jmx.LoggerDynamicMBean;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yisi.back.bean.Topic;
import com.yisi.back.service.StartTest;

@Controller(value="startTestController")
@RequestMapping(value="/test")
public class StartTestController {
	
	Logger logger = Logger.getLogger(StartTestController.class);
	
	@Autowired
	private StartTest startTest;

	@ResponseBody
	@RequestMapping(value="/get")
	public String startTestGet(int id) {
		String topicString = startTest.queryTopicById(id);
		logger.info(topicString);
		return topicString;
	}
	
	@ResponseBody
	@RequestMapping(value="/insert")
	public String startTestInsert() {
		Topic topic = new Topic();
		topic.setTopicId(1);
		topic.setStartTime(new Date());
		topic.setTopicDetail("插入数据");
		topic.setTopicRes(50);
		int count = startTest.insertTopic(topic);
		return count+"";
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String startTestUpdate(int id) {
		Topic topic = new Topic();
		topic.setTopicId(id);
		topic.setStartTime(new Date());
		topic.setTopicDetail("更新数据");
		topic.setTopicRes(10);
		int count= startTest.updateTopic(topic);
		return count+"";
	}
	@ResponseBody
	@RequestMapping(value="/delete")
	public String startTestDelete(int id) {
		return startTest.deleteTopic(id)+"";
	}
	
}
