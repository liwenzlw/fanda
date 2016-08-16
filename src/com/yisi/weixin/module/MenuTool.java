package com.yisi.weixin.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yisi.weixin.exception.WeixinException;
import com.yisi.weixin.menu.Menu;
import com.yisi.weixin.util.CommonUtil;

/**
 * 自定义菜单工具类(创建，查询，删除)
 * 
 * @position 自定义菜单
 * @author liwen
 * @version 1.0
 */
public class MenuTool {

	private static Logger logger = LoggerFactory.getLogger(MenuTool.class);

	// 菜单创建（POST）
	public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 菜单查询（GET）
	public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 菜单删除（DELETE）
	public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            凭证
	 * @return true成功 false失败
	 */
	public static boolean createMenu(Menu menu, String accessToken)
			{
		boolean result = false;
		String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSON.toJSONString(menu);
		// 发起POST请求创建菜单，并返回微信服务器响应的数据
		JSONObject jsonObject = null;
		try {
			jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

		int errorCode = jsonObject.getInteger("errcode");
		String errorMsg = jsonObject.getString("errmsg");
		if (0 == errorCode) {
			result = true;
		} else {
			result = false;
			logger.error("创建菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
		}
		return result;
	}

	/**
	 * 查询菜单
	 * 
	 * @param accessToken
	 *            凭证
	 * @return 微信服务器响应的菜单数据
	 */
	public static String getMenu(String accessToken) {
		String result = null;
		String requestUrl = MENU_GET_URL.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求查询菜单，返回微信服务器响应的数据
		JSONObject jsonObject = null;
		try {
			jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

		result = jsonObject.toString();
		return result;
	}

	/**
	 * 删除菜单
	 * 
	 * @param accessToken
	 *            凭证
	 * @return true成功 false失败
	 */
	public static boolean deleteMenu(String accessToken) {
		boolean result = false;
		String requestUrl = MENU_DELETE_URL
				.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求删除菜单
		JSONObject jsonObject;
		try {
			jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

		int errorCode = jsonObject.getInteger("errcode");
		String errorMsg = jsonObject.getString("errmsg");
		if (0 == errorCode) {
			result = true;
		} else {
			result = false;
			logger.error("删除菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
		}
		return result;
	}
}