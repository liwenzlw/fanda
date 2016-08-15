package com.yisi.weixin.message.request;

/**
 * 图片消息
 * 
 * @author liwen
 * @version 1.0
 */
public class ImageMessage extends BaseMessage {
	// 图片链接
	private String PicUrl;
	
	// 媒体ID
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
