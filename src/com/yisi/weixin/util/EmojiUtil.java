package com.yisi.weixin.util;

/**
 * Emoji表情工具类
 * 
 * @author liwen
 * @version 1.0
 */
public class EmojiUtil {

	/**
	 * 得到指定Unicode代码点的字符串（Emoji表情编码为Unicode）
	 * 
	 * @param codePoint
	 * @return
	 */
	private static String emoji(int codePoint) {
		return String.valueOf(Character.toChars(codePoint));
	}
}
