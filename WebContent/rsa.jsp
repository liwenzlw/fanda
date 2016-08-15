<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="true">
<head>
<base href="<%=basePath%>">
<title>login</title>

<script type="text/javascript" src="js/RSA.js"></script>
<script type="text/javascript" src="js/BigInt.js"></script>
<script type="text/javascript" src="js/Barrett.js"></script>

<script type="text/javascript">
	function rsalogin() {
		var thisPwd = document.getElementById("password").value;
		bodyRSA();
		var result = encryptedString(key, encodeURIComponent(thisPwd));
		//alert(encodeURIComponent(thisPwd)+"\r\n"+result);
		loginForm.action = "<%=basePath%>rsaDecode?encode=" + result;
		loginForm.submit();
	}

	var key;
	function bodyRSA() {
		setMaxDigits(130);
		key = new RSAKeyPair(
				"10001",
				"",
				"b48a8f410bf705266845db6cda9e93810ac048100e7ff601d78b861cc6bd9a0de909ef070cd87ac527c2b5e0af30c82390ffb33b2693e41985410b5824cc0b553f82381e9dfc01da2a20ead71ca6f9fb84ba5de2e5df467f5b1e4286184e313fc6a3bb04abe32e3f1f3d6542d4d5d46290bd7db53de06ce7bd093d6c6c61e71f");
	}
</script>
</head>

<body>
	<form method="post" name="loginForm" target=_blank>
		<table border="0">
			<tr>
				<td>Password:</td>
				<td><input type='text' name="password" id=password
					style='width: 400px' value="my passwd" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button"
					value="SUBMIT" onclick="rsalogin();" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
