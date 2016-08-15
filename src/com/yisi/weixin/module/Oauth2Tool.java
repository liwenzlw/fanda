package com.yisi.weixin.module;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yisi.weixin.bean.SNSUserInfo;
import com.yisi.weixin.bean.Oauth2Token;
import com.yisi.weixin.exception.WeixinException;
import com.yisi.weixin.util.CommonUtil;

/**
 * 微信网页授权 工具类
 * 
 * @position 微信网页开发-->微信网页授权
 * @author liwen
 * @version 1.0
 */
public class Oauth2Tool {

	private static Logger logger = LoggerFactory.getLogger(Oauth2Tool.class);

	/**
	 * 获取网页授权凭证
	 * 
	 * @position 微信网页开发-->微信网页授权-->第二步：通过code换取网页授权access_token
	 * @param appId
	 *            公众账号的唯一标识
	 * @param appSecret
	 *            公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public static Oauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code) throws Exception {
		Oauth2Token token = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				token = new Oauth2Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInteger("expires_in"));
				token.setRefreshToken(jsonObject.getString("refresh_token"));
				token.setOpenId(jsonObject.getString("openid"));
				token.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				token = null;
				int errorCode = jsonObject.getInteger("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
				throw new WeixinException("获取网页授权凭证失败");
			}
		}
		return token;
	}

	/**
	 * 刷新网页授权凭证
	 * 
	 * @position 微信网页开发-->微信网页授权-->第三步：刷新access_token（如果需要）
	 * @param appId
	 *            公众账号的唯一标识
	 * @param refreshToken
	 * @return WeixinAouth2Token
	 */
	public static Oauth2Token refreshOauth2AccessToken(String appId,
			String refreshToken) throws Exception {
		Oauth2Token token = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		// 刷新网页授权凭证
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		try {
			token = new Oauth2Token();
			token.setAccessToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInteger("expires_in"));
			token.setRefreshToken(jsonObject.getString("refresh_token"));
			token.setOpenId(jsonObject.getString("openid"));
			token.setScope(jsonObject.getString("scope"));
		} catch (Exception e) {
			token = null;
			int errorCode = jsonObject.getInteger("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			logger.error("刷新网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			throw new WeixinException("刷新网页授权凭证失败");
		}
		return token;
	}

	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @position 微信网页开发-->微信网页授权-->第四步：拉取用户信息(需scope为 snsapi_userinfo)
	 * @param accessToken
	 *            网页授权接口调用凭证
	 * @param openId
	 *            用户标识
	 * @return SNSUserInfo
	 */
	@SuppressWarnings("unchecked")
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId)
			throws Exception {
		SNSUserInfo snsUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 通过网页授权获取用户信息
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		try {
			snsUserInfo = new SNSUserInfo();
			// 用户的标识
			snsUserInfo.setOpenId(jsonObject.getString("openid"));
			// 昵称
			snsUserInfo.setNickname(jsonObject.getString("nickname"));
			// 性别（1是男性，2是女性，0是未知）
			snsUserInfo.setSex(jsonObject.getInteger("sex"));
			// 用户所在国家
			snsUserInfo.setCountry(jsonObject.getString("country"));
			// 用户所在省份
			snsUserInfo.setProvince(jsonObject.getString("province"));
			// 用户所在城市
			snsUserInfo.setCity(jsonObject.getString("city"));
			// 用户头像
			snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			// 用户特权信息
			snsUserInfo.setPrivilegeList(JSONArray.toJavaObject(
					jsonObject.getJSONArray("privilege"), List.class));
		} catch (Exception e) {
			snsUserInfo = null;
			int errorCode = jsonObject.getInteger("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			throw new WeixinException("网页授权获取用户信息失败");
		}
		return snsUserInfo;
	}
}
