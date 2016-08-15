package com.yisi.weixin.message.response;

/**
 * 语音消息
 * 
 * @author liwen
 * @version 1.0
 */
public class VoiceMessage extends BaseMessage {
	// 语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
