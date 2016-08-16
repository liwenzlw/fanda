package com.yisi.weixin.corecontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yisi.weixin.service.CoreService;
import com.yisi.weixin.util.SignUtil;

/**
 * 微信到公众号服务器后台的入口
 * 
 * @author liwen
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/weixin")
public class CoreController {

	private static Logger logger = LoggerFactory.getLogger(CoreController.class);
	/**
	 * 请求校验（确认请求来自微信服务器）
	 * 
	 * @position 开始开发-->接入指南--> 第二步：验证服务器地址的有效性
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/coreCheck", method = RequestMethod.GET)
	public void coreCheck(HttpServletRequest request,
			HttpServletResponse response){
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
				out.flush();
			}
		} catch (IOException e) {
			logger.error("服务器地址的无效");
			out.close();
			out = null;
		} finally {
			out.close();
			out = null;
		}
	}

	/**
	 * 对微信服务器发来的消息进行反馈 
	 * 
	 * @position 开始开发-->接入指南--> 第三步：依据接口文档实现业务逻辑
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/coreCheck", method = RequestMethod.POST)
	public void feedback(HttpServletRequest request, HttpServletResponse response)
			{
		PrintWriter out = null;
		try {
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			// 接收参数微信加密签名、 时间戳、随机数
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");

			out = response.getWriter();
			// 请求校验
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				// 调用核心服务类接收处理请求
				String respXml = CoreService.processRequest(request);
				out.print(respXml);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			out.close();
			out = null;
		}
	}
}
