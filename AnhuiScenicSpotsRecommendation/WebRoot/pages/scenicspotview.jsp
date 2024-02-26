
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

<title>景点查看</title>

<!--right slidebar-->
<link href="resource/houtai/css/slidebars.css" rel="stylesheet">

<!--switchery-->
<link href="resource/houtai/js/switchery/switchery.min.css"
	rel="stylesheet" type="text/css" media="screen" />

<!--Data Table-->
<link href="resource/houtai/js/data-table/css/jquery.dataTables.css"
	rel="stylesheet">
<link href="resource/houtai/js/data-table/css/dataTables.tableTools.css"
	rel="stylesheet">
<link href="resource/houtai/js/data-table/css/dataTables.colVis.min.css"
	rel="stylesheet">
<link href="resource/houtai/js/data-table/css/dataTables.responsive.css"
	rel="stylesheet">
<link href="resource/houtai/js/data-table/css/dataTables.scroller.css"
	rel="stylesheet">
<!-- Base Styles -->

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

				<!--sidebar nav start-->
				<c:import url="htmenu.jsp"></c:import>
				<!--sidebar nav end-->
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
				<!--toggle button start-->
				<a class="toggle-btn"><i class="fa fa-outdent"></i></a>
				<!--toggle button end-->
			</div>
			<!-- header section end-->
			<!--body wrapper start-->
			<div class="wrapper">

				<div class="row">
					<div class="col-sm-12">
						<section class="panel">
							<header class="panel-heading ">
								景点查看 <span class="tools pull-right"> <a
									class="fa fa-repeat box-refresh" href="javascript:;"></a> <a
									class="t-close fa fa-times" href="javascript:;"></a>
								</span>
							</header>
							<table class="table convert-data-table data-table">
								<thead>
									<th>名称</th>
									<th>操作</th>
								</thead>
								<tbody>
									<c:if test="${ !empty scenicspotall}">
										<c:forEach items="${ scenicspotall}" var="item">
											<tr>
												<td>${item.name }</td>
												<td>
													<a target="_blank" href="scenicspotdetails.action?id=${item.id}">详情</a>
												</td>
											</tr>

										</c:forEach>

									</c:if>

								</tbody>
							</table>
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


	<!--Data Table-->
	<script src="resource/houtai/js/data-table/js/jquery.dataTables.min.js"></script>
	<script
		src="resource/houtai/js/data-table/js/dataTables.tableTools.min.js"></script>
	<script src="resource/houtai/js/data-table/js/bootstrap-dataTable.js"></script>
	<script src="resource/houtai/js/data-table/js/dataTables.colVis.min.js"></script>
	<script
		src="resource/houtai/js/data-table/js/dataTables.responsive.min.js"></script>
	<script
		src="resource/houtai/js/data-table/js/dataTables.scroller.min.js"></script>
	<!--data table init-->
	<script src="resource/houtai/js/data-table-init.js"></script>
	
	<!--common scripts for all pages-->
	<script src="resource/houtai/js/scripts.js"></script>

	<script>
        if("${message}"){
            alert("${message}");
        }
    </script>
</body>
</html>

