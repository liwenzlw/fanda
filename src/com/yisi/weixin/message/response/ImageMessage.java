package com.yisi.weixin.message.response;

/**
 * 图片消息
 * 
 * @author liwen
 * @version 1.0
 */
public class ImageMessage extends BaseMessage {
	// 图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}