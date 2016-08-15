package com.yisi.weixin.message.request;

/**
 * 文本消息
 * 
 * @author liwen
 * @version 1.0
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
