package com.yisi.back.test;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yisi.back.utils.RSAUtil;

@Controller
public class RsaTestController {

	@ResponseBody
	@RequestMapping(value="/rsaDecode")
	public String decode(String encode,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String result = encode;
		System.out.println("原文加密后为：");
		System.out.println(result);
		byte[] en_result = new BigInteger(result, 16).toByteArray();
		System.out.println("转成byte[]" + new String(en_result));
		String path = Thread.currentThread().getContextClassLoader().getResource("keypair"+File.separator+"RSAKey.txt").getPath();
		System.out.println("path:" + path);
		byte[] de_result = RSAUtil.decrypt(RSAUtil.getKeyPair(path).getPrivate(),
				en_result);
		System.out.println("还原密文：");
		System.out.println(URLDecoder.decode(StringUtils.reverse(new String(de_result))));
		return new String(de_result);
	}
}
