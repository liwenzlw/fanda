package com.yisi.weixin.corecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yisi.weixin.bean.Oauth2Token;
import com.yisi.weixin.bean.SNSUserInfo;
import com.yisi.weixin.module.Oauth2Tool;
import com.yisi.weixin.util.Configure;

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
public class OAuth2Controller {

	private static Logger logger = LoggerFactory
			.getLogger(OAuth2Controller.class);

	@RequestMapping(value = "/oauth", method = RequestMethod.GET)
	public String oauth(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			// 用户同意授权后，能获取到code
			String code = request.getParameter("code");

			// 如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
			if (!StringUtils.isEmpty(code)) {
				// 获取网页授权access_token
				Oauth2Token token = Oauth2Tool.getOauth2AccessToken(
						Configure.APPID, Configure.APPSECRET, code);

				// 网页授权作用域为snsapi_userinfo，（弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）。
				if ("snsapi_userinfo".equalsIgnoreCase(token.getScope())) {
					// 网页授权接口访问凭证
					String accessToken = token.getAccessToken();
					// 用户标识
					String openId = token.getOpenId();
					// 获取用户信息
					SNSUserInfo snsUserInfo = Oauth2Tool.getSNSUserInfo(
							accessToken, openId);
					// 设置要传递的参数
					request.setAttribute("snsUserInfo", snsUserInfo);
					// 跳转到index.jsp
					return "forward:/weixin/test/index.jsp";
				} else {
					// 网页授权作用域为snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid）
				}
			} else {
				// 用户拒绝授权处理,若用户禁止授权，则重定向后不会带上code参数，仅会带上state参数redirect_uri?state=STATE
				return "forward:/weixin/error/error.jsp";
			}
		} catch (Exception e) {
			// TODO 跳转到错误界面
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
