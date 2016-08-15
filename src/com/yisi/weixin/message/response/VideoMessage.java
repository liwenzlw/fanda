package com.yisi.weixin.message.response;

/**
 * 视频消息
 * 
 * @author liwen
 * @version 1.0
 */
public class VideoMessage extends BaseMessage {
	// 视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
