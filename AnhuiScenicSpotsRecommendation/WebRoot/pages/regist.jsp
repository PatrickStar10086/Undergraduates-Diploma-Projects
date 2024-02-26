
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Admin Template">
<meta name="keywords"
	content="admin dashboard, admin, flat, flat ui, ui kit, app, web app, responsive">
<link rel="shortcut icon" href="resource/houtai/img/ico/favicon.png">
<title>注册</title>

<!-- Base Styles -->
<link href="resource/houtai/css/style.css" rel="stylesheet">
<link href="resource/houtai/css/style-responsive.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="resource/houtai/js/html5shiv.min.js"></script>
    <script src="resource/houtai/js/respond.min.js"></script>
    <![endif]-->
<script src="resource/houtai/js/jquery-1.10.2.min.js"></script>

</head>

<body class="login-body">

	<div class="login-logo">
		景点推荐
	</div>
	<!--注册-->
	<h2 class="form-heading">注册</h2>
	<div class="container log-row">
		<form class="form-signin" action="registact.action" method="post">
			<div class="login-wrap">
				<input type="text" class="form-control" name="username"
					placeholder="请输入用户名" required autofocus> <input
					type="password" class="form-control" name="password"
					placeholder="请输入密码" required> <input type="password"
					class="form-control" name="repassword" placeholder="请确认密码" required>

				<select type="password" class="form-control" name="identity"><option>用户</option></select>
				<button qw-c="operationbutton"
					class="btn btn-lg btn-success btn-block" type="submit"
					style="margin-top: 15px;">注册</button>

				<div class="registration"
					style="margin-top: 20px; text-align: center;">
					已有账号，点击<a href="login.action">登录</a>
				</div>

			</div>

		</form>
	</div>

	<!--jquery-1.10.2.min-->
	<script src="resource/houtai/js/jquery-1.11.1.min.js"></script>
	<!--Bootstrap Js-->
	<script src="resource/houtai/js/bootstrap.min.js"></script>
	<script src="resource/houtai/js/jrespond..min.js"></script>
	<script>
          if("${message}"){
              alert("${message}");
          }
      </script>
</body>
</html>

