package com.yisi.weixin.pay.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.yisi.weixin.pay.bean.GoodDetails;
import com.yisi.weixin.pay.bean.PreOrder;
import com.yisi.weixin.pay.service.PayService;
import com.yisi.weixin.pay.util.Configure;
import com.yisi.weixin.pay.util.MyXppDriver;
import com.yisi.weixin.pay.util.PayCommunication;
import com.yisi.weixin.pay.util.RandomStringGenerator;
import com.yisi.weixin.pay.util.Signature;
import com.yisi.weixin.pay.util.Util;
import com.yisi.weixin.pay.util.XStreamFactory;

@Service
public class PayServiceImpl implements PayService {

	@Override
	public String unifiedorder(GoodDetails goodDetails,HttpServletRequest request) {
		
		PreOrder preOrder = new PreOrder();
		preOrder.setAppid(Configure.getAppid());
		preOrder.setMch_id(Configure.getMchid());
		preOrder.setDevice_info("WEB");
		preOrder.setNonce_str(RandomStringGenerator.getRandomStringByLength(24));
		
		preOrder.setBody("专家还是砖家");
		preOrder.setAttach("亿思分店");
		preOrder.setOut_trade_no(Util.getOutTradeNo());
		preOrder.setTotal_fee(300000);
		preOrder.setSpbill_create_ip(request.getRemoteHost());
		preOrder.setTime_start(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		preOrder.setNotify_url(request.getContextPath()+"/weixinPayCallback");
		preOrder.setTrade_type("JSAPI");
		preOrder.setProduct_id("1111111111");
		preOrder.setOpenid("234567897754345657");
		
		JSONObject goodJson = new JSONObject();
		ArrayList<GoodDetails> list = new ArrayList<GoodDetails>();
		list.add(goodDetails);
		goodJson.put("goods_detail", list);
		preOrder.setDetail(goodJson.toJSONString());
		
		try {
			preOrder.setSign(Signature.getSign(preOrder));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XStream xstream = XStreamFactory.xstream;
		xstream.alias("xml", PreOrder.class);
		String xmlData = xstream.toXML(preOrder);
		System.out.println(xmlData);
		String resXml = null;
		try {
			resXml = PayCommunication.postData(Configure.unifiedorder, xmlData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resXml);
		return null;
	}

	@Override
	public GoodDetails genOrderDetails() {
		
		GoodDetails goodDetails = new GoodDetails();
		return null;
	}

}
