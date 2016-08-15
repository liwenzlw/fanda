package com.yisi.weixin.pay.service;

import javax.servlet.http.HttpServletRequest;

import com.yisi.weixin.pay.bean.GoodDetails;


public interface PayService {
	String unifiedorder(GoodDetails goodDetails,HttpServletRequest request);
	GoodDetails genOrderDetails();
}
