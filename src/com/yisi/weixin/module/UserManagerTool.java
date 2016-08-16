package com.yisi.weixin.module;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yisi.weixin.bean.UserGroup;
import com.yisi.weixin.bean.UserInfo;
import com.yisi.weixin.bean.UserList;
import com.yisi.weixin.util.CommonUtil;

/**
 * 用户管理 工具类
 * 
 * @position 用户管理
 * @author liwen
 * @version 1.0
 */
public class UserManagerTool {

	private static Logger logger = LoggerFactory
			.getLogger(UserManagerTool.class);

	/**
	 * 获取用户信息
	 * 
	 * @position 用户管理-->获取用户基本信息（UnionID机制）
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @return WeixinUserInfo
	 */
	public static UserInfo getUserInfo(String accessToken, String openId) {
		UserInfo weixinUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = null;

		try {
			CommonUtil.httpsRequest(requestUrl, "GET", null);
			weixinUserInfo = new UserInfo();
			// 用户的标识
			weixinUserInfo.setOpenId(jsonObject.getString("openid"));
			// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
			weixinUserInfo.setSubscribe(jsonObject.getInteger("subscribe"));
			// 用户关注时间
			weixinUserInfo.setSubscribeTime(jsonObject
					.getString("subscribe_time"));
			// 昵称
			weixinUserInfo.setNickname(jsonObject.getString("nickname"));
			// 用户的性别（1是男性，2是女性，0是未知）
			weixinUserInfo.setSex(jsonObject.getInteger("sex"));
			// 用户所在国家
			weixinUserInfo.setCountry(jsonObject.getString("country"));
			// 用户所在省份
			weixinUserInfo.setProvince(jsonObject.getString("province"));
			// 用户所在城市
			weixinUserInfo.setCity(jsonObject.getString("city"));
			// 用户的语言，简体中文为zh_CN
			weixinUserInfo.setLanguage(jsonObject.getString("language"));
			// 用户头像
			weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			// 用户头像
			weixinUserInfo.setRemark(jsonObject.getString("remark"));
			// 用户头像
			weixinUserInfo.setGroupid(jsonObject.getString("groupid"));
			// 用户头像
			weixinUserInfo.setUnionid(jsonObject.getString("unionid"));
		} catch (Exception e) {
			if (0 == weixinUserInfo.getSubscribe()) {
				logger.error("用户{}已取消关注", weixinUserInfo.getOpenId());
			} else {
				int errorCode = jsonObject.getInteger("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return weixinUserInfo;
	}

	/**
	 * 获取用户列表
	 * 
	 * @position 用户管理-->获取用户列表
	 * @param accessToken
	 *            调用接口凭证
	 * @param nextOpenId
	 *            第一个拉取的openId，不填默认从头开始拉取
	 * @return WeixinUserList
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static UserList getUserList(String accessToken, String nextOpenId) {
		UserList weixinUserList = null;

		if (null == nextOpenId)
			nextOpenId = "";

		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"NEXT_OPENID", nextOpenId);
		logger.debug(requestUrl);
		JSONObject jsonObject = null;
		// 如果请求成功
		try {
			// 获取关注者列表
			jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
			weixinUserList = new UserList();
			weixinUserList.setTotal(jsonObject.getInteger("total"));
			weixinUserList.setCount(jsonObject.getInteger("count"));
			weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
			JSONObject dataObject = (JSONObject) jsonObject.get("data");
			weixinUserList.setOpenIdList(JSONArray.toJavaObject(
					dataObject.getJSONArray("openid"), List.class));
		} catch (Exception e) {
			weixinUserList = null;
			int errorCode = jsonObject.getInteger("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			logger.error("获取关注者列表失败 errcode:{} errmsg:{}", errorCode, errorMsg);
		}
		return weixinUserList;
	}

	/**
	 * 查询分组
	 * 
	 * @position 用户管理-->用户分组管理-->查询所有分组
	 * @param accessToken
	 *            调用接口凭证
	 */
	public static List<UserGroup> getGroups(String accessToken) {
		List<UserGroup> weixinGroupList = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 查询分组
		JSONObject jsonObject = null;

		try {
			jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
			weixinGroupList = JSON.parseArray(jsonObject.getString("groups"),
					UserGroup.class);
		} catch (Exception e) {
			weixinGroupList = null;
			int errorCode = jsonObject.getInteger("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			logger.error("查询分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
		}
		return weixinGroupList;
	}

	/**
	 * 创建分组
	 * 
	 * @position 用户管理-->用户分组管理-->创建分组
	 * @param accessToken
	 *            接口访问凭证
	 * @param groupName
	 *            分组名称
	 * @return
	 */
	public static UserGroup createGroup(String accessToken, String groupName) {
		UserGroup weixinGroup = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"group\" : {\"name\" : \"%s\"}}";
		// 创建分组
		JSONObject jsonObject = null;

		try {
			jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
					String.format(jsonData, groupName));
			weixinGroup = new UserGroup();
			weixinGroup.setId(jsonObject.getJSONObject("group")
					.getInteger("id"));
			weixinGroup.setName(jsonObject.getJSONObject("group").getString(
					"name"));
		} catch (Exception e) {
			weixinGroup = null;
			int errorCode = jsonObject.getInteger("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			logger.error("创建分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
		}
		return weixinGroup;
	}

	/**
	 * 修改分组名
	 * 
	 * @position 用户管理-->用户分组管理-->修改分组名
	 * @param accessToken
	 *            接口访问凭证
	 * @param groupId
	 *            分组id
	 * @param groupName
	 *            修改后的分组名
	 * @return true | false
	 */
	public static boolean updateGroup(String accessToken, int groupId,
			String groupName) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"group\": {\"id\": %d, \"name\": \"%s\"}}";
		// 修改分组名
		JSONObject jsonObject = null;
		try {
			CommonUtil.httpsRequest(requestUrl, "POST",
					String.format(jsonData, groupId, groupName));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

		int errorCode = jsonObject.getInteger("errcode");
		String errorMsg = jsonObject.getString("errmsg");
		if (0 == errorCode) {
			result = true;
			logger.info("修改分组名成功 errcode:{} errmsg:{}", errorCode, errorMsg);
		} else {
			logger.error("修改分组名失败 errcode:{} errmsg:{}", errorCode, errorMsg);
		}
		return result;
	}

	/**
	 * 移动用户分组(将用户移动到其他组)
	 * 
	 * @position 用户管理-->用户分组管理-->移动用户分组
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @param groupId
	 *            分组id
	 * @return true | false
	 */
	public static boolean updateMemberGroup(String accessToken, String openId,
			int groupId) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"openid\":\"%s\",\"to_groupid\":%d}";
		// 移动用户分组
		JSONObject jsonObject = null;

		try {
			CommonUtil.httpsRequest(requestUrl, "POST",
					String.format(jsonData, openId, groupId));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

		int errorCode = jsonObject.getInteger("errcode");
		String errorMsg = jsonObject.getString("errmsg");
		if (0 == errorCode) {
			result = true;
			logger.info("移动用户分组成功 errcode:{} errmsg:{}", errorCode, errorMsg);
		} else {
			logger.error("移动用户分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
		}
		return result;
	}

}
