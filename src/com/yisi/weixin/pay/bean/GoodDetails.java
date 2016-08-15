package com.yisi.weixin.pay.bean;

/**
 * 商品详情
 * 
 * @author liwen
 * @version 1.0
 */
public class GoodDetails {
	
	//必填 32 商品的编号
	private String goods_id;
	//可选 32 微信支付定义的统一商品编号
	private String wxpay_goods_id;
	//必填 256 商品名称
	private String goods_name;
	//Int 必填 商品数量
	private int goods_num;
	//必填 商品单价，单位为分
	private int price;
	//可选 32 商品类目ID
	private String goods_category;
	//可选 1000 商品描述信息
	private String body;
	
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getWxpay_goods_id() {
		return wxpay_goods_id;
	}
	public void setWxpay_goods_id(String wxpay_goods_id) {
		this.wxpay_goods_id = wxpay_goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_category() {
		return goods_category;
	}
	public void setGoods_category(String goods_category) {
		this.goods_category = goods_category;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
