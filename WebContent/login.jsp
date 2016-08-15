<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
</head>
<body>
	<form action='login' method='post'>
		<label for='username'>用户名：</label> <input id='username'
			name='username' type='text' /> <label for='pwd'>密码：</label> <input
			id='pwd' name='pwd' type='password' /> <input type='submit'
			value='登录' />
	</form>
</body>
</html>