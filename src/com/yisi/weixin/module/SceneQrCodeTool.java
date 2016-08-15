package com.yisi.weixin.module;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.yisi.weixin.bean.SceneQRCode;
import com.yisi.weixin.exception.WeixinException;
import com.yisi.weixin.util.CommonUtil;

/**
 * 二维码操作工具类
 * 
 * @position 账号管理-->生成带参数的二维码
 * @author liwen
 * @version 1.0
 */
public class SceneQrCodeTool {

	private static Logger logger = LoggerFactory.getLogger(SceneQrCodeTool.class);

	/**
	 * 创建临时带参二维码
	 * 
	 * @position 账号管理-->生成带参数的二维码-->创建二维码ticket-->临时二维码请求说明
	 * @param accessToken
	 *            接口访问凭证
	 * @param expireSeconds
	 *            二维码有效时间，单位为秒，最大不超过1800
	 * @param sceneId
	 *            场景ID
	 * @return WeixinQRCode
	 */
	public static SceneQRCode createTemporaryQRCode(String accessToken,
			int expireSeconds, int sceneId) throws Exception {
		SceneQRCode weixinQRCode = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建临时带参二维码
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, expireSeconds, sceneId));

		try {
			weixinQRCode = new SceneQRCode();
			weixinQRCode.setTicket(jsonObject.getString("ticket"));
			weixinQRCode.setExpireSeconds(jsonObject
					.getInteger("expire_seconds"));
			weixinQRCode.setUrl(jsonObject.getString("url"));
			logger.info("创建临时带参二维码成功 ticket:{} expire_seconds:{}",
					weixinQRCode.getTicket(), weixinQRCode.getExpireSeconds());
		} catch (Exception e) {
			weixinQRCode = null;
			int errorCode = jsonObject.getInteger("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			logger.error("创建临时带参二维码失败 errcode:{} errmsg:{}", errorCode,
					errorMsg);
		}
		return weixinQRCode;
	}

	/**
	 * 创建永久带参二维码
	 * 
	 * @position 账号管理-->生成带参数的二维码-->创建二维码ticket-->永久二维码请求说明
	 * @param accessToken
	 *            接口访问凭证
	 * @param sceneId
	 *            场景ID
	 * @return ticket
	 */
	public static String createPermanentQRCode(String accessToken, int sceneId)
			throws Exception {
		String ticket = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建永久带参二维码
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, sceneId));

		try {
			ticket = jsonObject.getString("ticket");
			logger.info("创建永久带参二维码成功 ticket:{}", ticket);
		} catch (Exception e) {
			int errorCode = jsonObject.getInteger("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			logger.error("创建永久带参二维码失败 errcode:{} errmsg:{}", errorCode,
					errorMsg);
		}
		return ticket;
	}

	/**
	 * 根据ticket换取二维码
	 * 
	 * @position 账号管理-->生成带参数的二维码-->通过ticket换取二维码
	 * @param ticket
	 *            二维码ticket
	 * @param savePath
	 *            保存路径
	 */
	public static String getQRCode(String ticket, String savePath) throws Exception {
		String filePath = null;
		// 拼接请求地址
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		requestUrl = requestUrl.replace("TICKET",
				CommonUtil.urlEncodeUTF8(ticket));
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 将ticket作为文件名
			filePath = savePath + CommonUtil.urlEncodeUTF8(ticket) + ".jpg";

			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			logger.info("根据ticket换取二维码成功，filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			logger.error("根据ticket换取二维码失败：{}", e);
			throw new WeixinException("根据ticket换取二维码失败" + e.getMessage());
		}
		return filePath;
	}
}
