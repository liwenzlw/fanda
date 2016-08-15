package com.yisi.weixin.module;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yisi.weixin.exception.WeixinException;
import com.yisi.weixin.message.response.Article;
import com.yisi.weixin.message.response.Music;
import com.yisi.weixin.util.CommonUtil;

/**
 * 客服 工具类
 * 
 * @position 消息管理-->发送消息-客服消息
 * @author liwen
 * @version 1.0
 */
public class CustomServiceTool {

	private static Logger logger = LoggerFactory
			.getLogger(CustomServiceTool.class);

	/**
	 * 组装文本客服消息
	 * 
	 * @position 消息管理-->发送消息-客服消息-->客服接口-发消息 -->发送文本消息
	 * @param openId
	 *            消息发送对象
	 * @param content
	 *            文本消息内容
	 * @return
	 */
	public static String makeTextCustomMessage(String openId, String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		jsonMsg = String.format(jsonMsg, openId, content);
		logger.debug(jsonMsg);
		return jsonMsg;
	}

	/**
	 * 组装图片客服消息
	 * 
	 * @position 消息管理-->发送消息-客服消息-->客服接口-发消息 -->发送图片消息
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeImageCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装语音客服消息
	 * 
	 * @position 消息管理-->发送消息-客服消息-->客服接口-发消息 -->发送语音消息
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeVoiceCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装视频客服消息
	 * 
	 * @position 消息管理-->发送消息-客服消息-->客服接口-发消息 -->发送视频消息
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @param thumbMediaId
	 *            视频消息缩略图的媒体id
	 * @return
	 */
	public static String makeVideoCustomMessage(String openId, String mediaId,
			String thumbMediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId, thumbMediaId);
	}

	/**
	 * 组装音乐客服消息
	 * 
	 * @position 消息管理-->发送消息-客服消息-->客服接口-发消息 -->发送音乐消息
	 * @param openId
	 *            消息发送对象
	 * @param music
	 *            音乐对象
	 * @return
	 */
	public static String makeMusicCustomMessage(String openId, Music music) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSON.toJSONString(music));
		// 将jsonMsg中的thumbmediaid替换为thumb_media_id
		jsonMsg = jsonMsg.replace("thumbmediaid", "thumb_media_id");
		return jsonMsg;
	}

	/**
	 * <pre>
	 * 组装图文客服消息
	 * 1.（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
	 * 2. 发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
	 * </pre>
	 * 
	 * @position 消息管理-->发送消息-客服消息-->客服接口-发消息 -->发送图文消息
	 * @param openId
	 *            消息发送对象
	 * @param articleList
	 *            图文消息列表
	 * @return
	 */
	public static String makeNewsCustomMessage(String openId,
			List<Article> articleList) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(jsonMsg, openId,
				JSONArray.toJSONString(articleList).replaceAll("\"", "\\\""));
		// 将jsonMsg中的picUrl替换为picurl
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		logger.debug(jsonMsg);
		return jsonMsg;
	}

	/**
	 * 发送客服消息
	 * 
	 * @position 消息管理-->发送消息-客服消息-->客服接口-发消息
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式的客服消息（包括touser、msgtype和消息内容）
	 * @return true | false
	 */
	public static boolean sendCustomMessage(String accessToken, String jsonMsg) throws WeixinException,Exception {
		logger.info("消息内容：{}", jsonMsg);
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				jsonMsg);

		int errorCode = jsonObject.getInteger("errcode");
		String errorMsg = jsonObject.getString("errmsg");
		if (0 == errorCode) {
			result = true;
			logger.info("客服消息发送成功 errcode:{} errmsg:{}", errorCode, errorMsg);
		} else {
			logger.error("客服消息发送失败 errcode:{} errmsg:{}", errorCode, errorMsg);
		}
		return result;
	}

}
