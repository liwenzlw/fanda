package com.yisi.weixin.bean;

/**
 * 媒体文件信息（素材上传成功后微信服务器返回的数据）
 * 
 * @position 素材管理-->新增临时素材
 * @author liwen
 * @version 1.0
 */
public class Media {
	
	// 媒体文件类型
	private String type;
	// 媒体文件标识或缩略图的媒体文件标识
	private String mediaId;
	// 媒体文件上传的时间
	private int createdAt;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public int getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}
}
