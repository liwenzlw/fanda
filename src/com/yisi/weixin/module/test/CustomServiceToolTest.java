package com.yisi.weixin.module.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yisi.weixin.exception.WeixinException;
import com.yisi.weixin.message.response.Article;
import com.yisi.weixin.message.response.TextMessage;
import com.yisi.weixin.module.CustomServiceTool;
import com.yisi.weixin.util.MessageTool;

public class CustomServiceToolTest {

	// appid : wx30c5e57aa157f573
	// appsecret : 506607f36b91bfee920a9f324bc04093
	private static String accessToken = null;
	private static String openid = null;

	/**
	 * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass");
		accessToken = "H-FmhgPy8rCOWJdsYnmaudYx8VghYSNVO7F_RWHhNLaCGvUGCyHHM0OiG3BLOePapPusqmIiaWC729HXaVhzwupY74Zjdfdw_1HKbDCD4gKWjDSE3iByJpENKgSuBjTCNODaAAAJKZ";
		openid = "olwTcwe9n-CtaxGmqnodH2cVmZ1o";
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("setUp");
	}

	@Test
	public void testSendNewsMessage() throws Exception{

		List<Article> articleList = new ArrayList<Article>();
		Article art1 = new Article();
		art1.setTitle("唐山");
		art1.setDescription("肉很好吃");
		art1.setUrl("http://m.baidu.com/");
		Article art2 = new Article();
		art2.setTitle("唐山");
		art2.setDescription("肉很好吃");
		art2.setUrl("www.baidu.com");
		Article art3 = new Article();
		art3.setTitle("唐山");
		art3.setDescription("肉很好吃");
		art3.setUrl("www.baidu.com");
		Article art4 = new Article();
		art4.setTitle("唐山");
		art4.setDescription("肉很好吃");
		art4.setUrl("www.baidu.com");
		Article art5 = new Article();
		art5.setTitle("唐山");
		art5.setDescription("肉很好吃");
		art5.setUrl("www.baidu.com");
		Article art6 = new Article();
		art6.setTitle("唐山");
		art6.setDescription("肉很好吃");
		art6.setUrl("www.baidu.com");
		Article art7 = new Article();
		art7.setTitle("唐山");
		art7.setDescription("肉很好吃");
		art7.setUrl("www.baidu.com");
		Article art8 = new Article();
		art8.setTitle("唐山");
		art8.setDescription("肉很好吃");
		art8.setUrl("www.baidu.com");
		Article art9 = new Article();
		art9.setTitle("唐山");
		art9.setDescription("肉很好吃");
		art9.setUrl("www.baidu.com");
		Article art10 = new Article();
		art10.setTitle("唐山");
		art10.setDescription("肉很好吃");
		art10.setUrl("www.baidu.com");
		articleList.add(art1);
		articleList.add(art2);
		articleList.add(art3);
		articleList.add(art4);
		articleList.add(art5);
		articleList.add(art6);
		articleList.add(art7);
		articleList.add(art8);
		// articleList.add(art9);
		// articleList.add(art10);
		String jsonMsg = CustomServiceTool.makeNewsCustomMessage(openid,
				articleList);
		try {
			CustomServiceTool.sendCustomMessage(accessToken, jsonMsg);
		} catch (WeixinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSendTextMenu() throws Exception{
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好，我是小q，请回复数字选择服务").append("\n");
		buffer.append("2 公交查询").append("\n");
		buffer.append("3 周边搜索").append("\n");
		buffer.append("4 歌曲点播").append("\n");
		buffer.append("5 经典游戏").append("\n");
		buffer.append("6 美女电台").append("\n");
		buffer.append("7 人脸识别").append("\n");
		buffer.append("8 <a href=\"http://m.baidu.com/\">聊天</a>")
				.append("\n\n");
		buffer.append(" 回复 “？” 显示此帮助菜单").append("\n");
		String sendMsg = buffer.toString();

		System.out.println("openid:" + openid);
		sendMsg = CustomServiceTool.makeTextCustomMessage(openid, sendMsg);
		try {
			CustomServiceTool.sendCustomMessage(accessToken, sendMsg);
		} catch (WeixinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
