package com.yisi.weixin.bean;

/**
 * 临时二维码信息
 * 
 * @position 账号管理-->生成带参数的二维码
 * @author liwen
 * @version 1.0
 */
public class SceneQRCode {
	// 获取的二维码ticket
	private String ticket;
	// 二维码的有效时间，单位为秒，最大不超过1800.
	private int expireSeconds;
	// 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	private String url;

	public String getTicket() {
		return ticket;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String string) {
		this.url = string;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
}
