package com.yisi.weixin.test;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Main {
	public static void main(String[] args) {
		JSONObject obj1 = new JSONObject();
		JSONArray obj2 = new JSONArray();
		obj2.add("1");
		obj2.add("2");
		obj2.add("3");
		obj1.put("p", obj2);
		System.out.println(obj1.getString("p"));
		System.out.println(JSONArray.toJavaObject(obj1.getJSONArray("p"), List.class));;
	}
}
