package com.yisi.weixin.menu.main;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.yisi.weixin.bean.Token;
import com.yisi.weixin.exception.WeixinException;
import com.yisi.weixin.menu.Button;
import com.yisi.weixin.menu.ClickButton;
import com.yisi.weixin.menu.ComplexButton;
import com.yisi.weixin.menu.Menu;
import com.yisi.weixin.menu.ViewButton;
import com.yisi.weixin.module.MenuTool;
import com.yisi.weixin.util.CommonUtil;

/**
 * 菜单管理器类
 * 
 * @author liwen
 * @version 1.0
 */
public class MenuManager {
	
	private static Logger logger = Logger.getLogger(MenuManager.class);

	//bae服务器端地址
	private static String basePath = "ethink4fanda.duapp.com";
	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		ViewButton btnOauth = new ViewButton();
		btnOauth.setName("授权界面");
		btnOauth.setType("view");
		btnOauth.setUrl("http://ethink4fanda.duapp.com/weixin/test/oauth.jsp");

		ClickButton btnITeye = new ClickButton();
		btnITeye.setName("ITeye");
		btnITeye.setType("click");
		btnITeye.setKey("iteye");

		ViewButton vBtnIteye = new ViewButton();
		vBtnIteye.setName("CocoaChina");
		vBtnIteye.setType("view");
		vBtnIteye.setUrl("http://www.iteye.com");

		ViewButton vBtnTaobao = new ViewButton();
		vBtnTaobao.setName("淘宝");
		vBtnTaobao.setType("view");
		vBtnTaobao.setUrl("http://m.taobao.com");

		ViewButton vBtnJD = new ViewButton();
		vBtnJD.setName("京东");
		vBtnJD.setType("view");
		vBtnJD.setUrl("http://m.jd.com");

		ViewButton vBtnVipshop = new ViewButton();
		vBtnVipshop.setName("唯品会");
		vBtnVipshop.setType("view");
		vBtnVipshop.setUrl("http://m.vipshop.com");

		ViewButton vBtnDangdang = new ViewButton();
		vBtnDangdang.setName("当当网");
		vBtnDangdang.setType("view");
		vBtnDangdang.setUrl("http://m.dangdang.com");

		ViewButton vBtnSuning = new ViewButton();
		vBtnSuning.setName("苏宁易购");
		vBtnSuning.setType("view");
		vBtnSuning.setUrl("http://m.suning.com");

		ViewButton vBtnDuopao = new ViewButton();
		vBtnDuopao.setName("多泡");
		vBtnDuopao.setType("view");
		vBtnDuopao.setUrl("http://www.duopao.com");

		ViewButton vBtnYi588 = new ViewButton();
		vBtnYi588.setName("一窝88");
		vBtnYi588.setType("view");
		vBtnYi588.setUrl("http://www.yi588.com");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("技术交流");
		mainBtn1.setSub_button(new Button[] { btnOauth, btnITeye, vBtnIteye });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("购物");
		mainBtn2.setSub_button(new Button[] { vBtnTaobao, vBtnJD, vBtnVipshop, vBtnDangdang, vBtnSuning });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("网页游戏");
		mainBtn3.setSub_button(new Button[] { vBtnDuopao, vBtnYi588 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx30c5e57aa157f573";
		// 第三方用户唯一凭证密钥
		String appSecret = "506607f36b91bfee920a9f324bc04093";

		// 调用接口获取凭证
		Token token = null;
		try {
			token = CommonUtil.getToken(appId, appSecret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != token) {
			// 创建菜单
			boolean result = false;
			try {
				result = MenuTool.createMenu(getMenu(), token.getAccessToken());
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 判断菜单创建结果
			if (result)
				logger.info("菜单创建成功！");
			else
				logger.info("菜单创建失败！");
		}
	}
}
