package com.yisi.weixin.pay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 和微信服务器进行支付交互
 * 
 * @author liwen
 * @version 1.0
 */
public class PayCommunication {

	/**
	 * 将  格式化了的xml数据  使用post方式发送到  微信支付平台 并获取 微信支付平台  返回的数据
	 * @param urlStr  微信支付平台 规定的 用来获取二维码数据  的 请求的路径
	 * @param params  post到 微信支付平台 的数据
	 * @return 微信支付平台  返回的 xml格式的 数据
	 * @throws Exception
	 */
	public static String postData(String urlStr,String params)throws Exception{
        URL url=new URL(urlStr);//"http://localhost:8080/wxPay/ShowSendData"
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(params.getBytes("utf-8"));
        outputStream.flush();
        BufferedReader in = null; 
        StringBuilder sb = new StringBuilder(); 
        try{   
            in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"utf-8") ); 
            String str = null;  
            while((str = in.readLine()) != null) {  
                sb.append( str );   
            }   
         } catch (Exception ex) { 
            throw ex; 
         } finally{  
          try{ 
              conn.disconnect();
              if(in!=null){
                  in.close();
              }
              if(outputStream!=null){
            	  outputStream.close();
              }
          }catch(IOException ex) {   
              throw ex; 
          }   
         }   
         return sb.toString(); 
    }
}
