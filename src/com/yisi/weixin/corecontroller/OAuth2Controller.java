package com.yisi.weixin.corecontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mchange.v2.beans.BeansUtils;
import com.yisi.weixin.bean.SNSUserInfo;
import com.yisi.weixin.bean.Oauth2Token;
import com.yisi.weixin.module.Oauth2Tool;

/**
 * 授权回调请求处理程序
 * 
 * <pre>
 *   如果要在网页中得到用户信息，就必须先引导用户进入网页授权页面；用户同意授权后
 *   会跳转到回调地址redirect_uri,redirect_uri是授权回调请求处理程序的访问地址；
 *   在处理程序中，开发中能够获取到code，再通过code获取access_token，最终得到用户信息。
 * </pre>
 * 
 * @author liwen
 * @version 1.0
 */

@Controller
@RequestMapping("/weixin")
public class OAuth2Controller{

	@RequestMapping(value="/oauth",method=RequestMethod.GET)
	public void oauth(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");

		// 用户同意授权
		if (!StringUtils.isEmpty(code)) {
			// 获取网页授权access_token
			Oauth2Token token = Oauth2Tool.getOauth2AccessToken(
					"APPID", "APPSECRET", code);
			// 网页授权接口访问凭证
			String accessToken = token.getAccessToken();
			// 用户标识
			String openId = token.getOpenId();
			// 获取用户信息
			SNSUserInfo snsUserInfo = Oauth2Tool.getSNSUserInfo(accessToken,
					openId);
			if(null == snsUserInfo) {
				//TODO 获取用户信息失败处理
				return;
			} else {
				// 设置要传递的参数
				request.setAttribute("snsUserInfo", snsUserInfo);
				// 跳转到index.jsp
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			//TODO 用户拒绝授权处理
		}
	}
}
