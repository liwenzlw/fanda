package com.yisi.weixin.pay.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yisi.weixin.pay.bean.GoodDetails;
import com.yisi.weixin.pay.service.PayService;

@Controller
@RequestMapping(value="/weixin")
public class PayController {
	
	@Autowired
	private PayService payService;
	
	@RequestMapping(value="genOrder")
	public String genOrder(GoodDetails goodDetails,HttpServletRequest request){
		
		payService.unifiedorder(goodDetails, request);
		return null;
	}
	
	@RequestMapping(value="weixinPayCallback")
	public String weixinCallback(String s){
		
		return null;
	}
}
