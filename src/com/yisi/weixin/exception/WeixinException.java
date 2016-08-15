package com.yisi.weixin.exception;

/**
 * 微信异常
 * @author liwen
 * @version 1.0
 */
public class WeixinException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public WeixinException(String msg) {
		super(msg);
	}

}
