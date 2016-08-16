<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%
	String path = request.getContextPath();
	String basePath = "https" + "://"
			+ request.getServerName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>授权链接</title>
</head>
<body>
	<a
		href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx30c5e57aa157f573&redirect_uri=<%=URLEncoder.encode(basePath+"/weixin/oauth", "utf-8") %>&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect'>授权</a>
</body>
</html>