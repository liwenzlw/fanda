package com.yisi.weixin.menu;

/**
 * 菜单（最终返回给微信服务器的数据的抽象类）
 * 
 * @author liwen
 * @version 1.0
 */
public class Menu {
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
}
