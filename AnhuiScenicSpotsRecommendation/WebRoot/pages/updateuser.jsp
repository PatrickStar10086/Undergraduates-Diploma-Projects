
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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Mosaddek">
<meta name="keyword"
	content="slick, flat, dashboard, bootstrap, admin, template, theme, responsive, fluid, retina">
<link rel="shortcut icon" href="javascript:;" type="image/png">

<title>修改用户</title>

<!--right slidebar-->
<link href="resource/houtai/css/slidebars.css" rel="stylesheet">

<!--switchery-->
<link href="resource/houtai/js/switchery/switchery.min.css"
	rel="stylesheet" type="text/css" media="screen" />

<!--iCheck-->
<link href="resource/houtai/js/icheck/skins/all.css" rel="stylesheet">

<!--common style-->
<link href="resource/houtai/css/style.css" rel="stylesheet">
<link href="resource/houtai/css/style-responsive.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="resource/houtai/js/html5shiv.js"></script>
    <script src="resource/houtai/js/respond.min.js"></script>
    <![endif]-->
<script src="resource/houtai/js/jquery-1.10.2.min.js"></script>
</head>

<body class="sticky-header">

	<section>
		<!-- sidebar left start-->
		<div class="sidebar-left">
			<!--responsive view logo start-->
			<div class="logo dark-logo-bg visible-xs-* visible-sm-*">
				<a href="#"> <img
					src="resource/houtai/img/logo-icon.png" alt=""> <!--<i class="fa fa-maxcdn"></i>-->
					<span class="brand-name">景点推荐</span>
				</a>
			</div>
			<!--responsive view logo end-->

			<div class="sidebar-left-info">
				<!-- visible small devices start-->
				<div class=" search-field"></div>
				<!-- visible small devices end-->

				<c:import url="htmenu.jsp"></c:import>

			</div>
		</div>
		<!-- sidebar left end-->

		<!-- body content start-->
		<div class="body-content" style="min-height: 1000px;">

			<!-- header section start-->
			<div class="header-section">

				<!--logo and logo icon start-->
				<div class="logo dark-logo-bg hidden-xs hidden-sm">
					<a href="#"> <img
						src="resource/houtai/img/logo-icon.png" alt=""> <!--<i class="fa fa-maxcdn"></i>-->
						<span class="brand-name">景点推荐</span>
					</a>
				</div>

				<div class="icon-logo dark-logo-bg hidden-xs hidden-sm">
					<a href="#"> <img
						src="resource/houtai/img/logo-icon.png" alt=""> <!--<i class="fa fa-maxcdn"></i>-->
					</a>
				</div>
				<!--logo and logo icon end-->

				<!--toggle button start-->
				<a class="toggle-btn"><i class="fa fa-outdent"></i></a>
			</div>
			<!-- header section end-->

			<!--body wrapper start-->
			<div class="wrapper">

				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading"> 修改用户 </header>
							<div class="panel-body">
								<div class=" form">
									<form class="cmxform form-horizontal tasi-form"
										action="updateuseract.action" method="post"
										enctype="multipart/form-data">
										<input name='id' style='display: none;' value="${user.id}">
										<div class="form-group">
											<label for="">名字</label>
											<div class="controls">
												<!-- 定义名字input输入框，并从request中取值user.name赋值  -->

												<input name="name" value="${user.name}" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="">账号</label>
											<div class="controls">
												<!-- 定义username的输入框，并设置其为只读  -->

												<input readonly name="username" value="${user.username}"
													class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="">密码</label>
											<div class="controls">
												<!-- 定义密码input输入框，并从request中取值user.password赋值  -->

												<input name="password" value="${user.password}"
													class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="">性别</label>
											<div class="controls">
												<!-- 定义性别下拉框 -->

												<select id='sex' onchange="sexchange(this)" name="sex"
													class="form-control"><option>男</option>
													<option>女</option></select>
												<script>
 <!-- 将id为sex的组件赋值为request中的user.sex字段 -->  

$("#sex").val("${user.sex}")</script>

											</div>
										</div>
										<div class="form-group">
											<label for="">年龄</label>
											<div class="controls">
												<!-- 定义年龄input输入框，并从request中取值user.age赋值  -->

												<input name="age" value="${user.age}" class="form-control">
											</div>
										</div>
										<div class="form-group">

											<label for="">介绍</label>

											<textarea name="introduce" style="height: 200px;"
												class="form-control">${user.introduce}</textarea>
										</div>
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<button qw-c="operationbutton" class="btn btn-success"
													type="submit">修改用户</button>
											</div>
										</div>
									</form>
								</div>

							</div>
						</section>
					</div>
				</div>
			</div>
			<!--body wrapper end-->


			<!--footer section start-->
			<footer> 2022 &copy; write by PingAn. </footer>
			<!--footer section end-->
		</div>
		<!-- body content end-->
	</section>

	<!-- Placed js at the end of the document so the pages load faster -->
	<script src="resource/houtai/js/jquery-1.10.2.min.js"></script>
	<script src="resource/houtai/js/jquery-migrate.js"></script>
	<script src="resource/houtai/js/bootstrap.min.js"></script>
	<script src="resource/houtai/js/modernizr.min.js"></script>

	<!--Nice Scroll-->
	<script src="resource/houtai/js/jquery.nicescroll.js"
		type="text/javascript"></script>

	<!--right slidebar-->
	<script src="resource/houtai/js/slidebars.min.js"></script>

	<!--switchery-->
	<script src="resource/houtai/js/switchery/switchery.min.js"></script>
	<script src="resource/houtai/js/switchery/switchery-init.js"></script>

	<!--Sparkline Chart-->
	<script src="resource/houtai/js/sparkline/jquery.sparkline.js"></script>
	<script src="resource/houtai/js/sparkline/sparkline-init.js"></script>

	<!--form validation-->
	<script type="text/javascript"
		src="resource/houtai/js/jquery.validate.min.js"></script>

	<!--form validation init-->
	<script src="resource/houtai/js/form-validation-init.js"></script>

	<!--Icheck-->
	<script src="resource/houtai/js/icheck/skins/icheck.min.js"></script>

	<!--common scripts for all pages-->
	<script src="resource/houtai/js/scripts.js"></script>

	<script>
        $(document).ready(function(){
            $('.iCheck').iCheck({
                checkboxClass: 'icheckbox_minimal',
                radioClass: 'iradio_minimal',
                increaseArea: '20%' // optional
            });
            $('.iCheck-square').iCheck({
                checkboxClass: 'icheckbox_square',
                radioClass: 'iradio_square',
                increaseArea: '20%' // optional
            });
            $('.iCheck-flat').iCheck({
                checkboxClass: 'icheckbox_flat',
                radioClass: 'iradio_flat',
                increaseArea: '20%' // optional
            });
            $('.iCheck-polaris').iCheck({
                checkboxClass: 'icheckbox_polaris',
                radioClass: 'iradio_polaris',
                increaseArea: '20%' // optional
            });


        });
    </script>

	<script>
        if("${message}"){
            alert("${message}");
        }
    </script>
</body>
</html>

