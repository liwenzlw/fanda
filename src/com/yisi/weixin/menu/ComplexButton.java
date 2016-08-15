package com.yisi.weixin.menu;

/**
 * 复合类型的按钮(具有子菜单的接口)
 * 
 * @author liwen
 * @version 1.0
 */
public class ComplexButton extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}
