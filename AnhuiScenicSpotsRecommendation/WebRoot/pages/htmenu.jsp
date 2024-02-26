
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${sessionScope.identity == '管理员' }">
		<ul class="nav nav-pills nav-stacked side-navigation">
		
			<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
					<span>个人中心</span></a>
				<ul class="child-list">
					<li><a href="adminindex.action">个人中心</a></li>
				</ul></li>
		
			<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
					<span>管理员管理</span></a>
				<ul class="child-list">
					<li><a href="addadmin.action"> 添加管理员</a></li>
					<li><a href="adminmanage.action"> 管理员管理</a></li>
				</ul></li>

			<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
					<span>景点管理</span></a>
				<ul class="child-list">
					<li><a href="addscenicspot.action"> 添加景点</a></li>
					<li><a href="scenicspotmanage.action"> 景点管理</a></li>
				</ul></li>

			<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
					<span>收藏管理</span></a>
				<ul class="child-list">
					<li><a href="addhistory.action"> 添加收藏</a></li>
					<li><a href="historymanage.action"> 收藏管理</a></li>
				</ul></li>

			<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
					<span>用户管理</span></a>
				<ul class="child-list">
					<li><a href="adduser.action"> 添加用户</a></li>
					<li><a href="usermanage.action"> 用户管理</a></li>
				</ul></li>


			<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
					<span>退出系统</span></a>
				<ul class="child-list">
					<li><a href="exitsystem.action"> 退出系统</a></li>
				</ul></li>
		</ul>
	</c:if>
	<c:if test="${sessionScope.identity == '用户' }">
		<ul class="nav nav-pills nav-stacked side-navigation">
			
			<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
				<span>个人中心</span></a>
				<ul class="child-list">
					<li><a href="userindex.action">个人中心</a></li>
				</ul></li>

			<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
				<span>景点查看</span></a>
				<ul class="child-list">
					<li><a href="scenicspotview.action"> 景点查看</a></li>
					<li><a href="cainixihuan.action?userID=${sessionScope.id }"> 景点推荐</a></li>
				</ul></li>

			<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
				<span>我的收藏</span></a>
				<ul class="child-list">
					<li><a href="userhistorymanage.action">我的收藏</a></li>
				</ul></li>
			
			<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
					<span>退出系统</span></a>
				<ul class="child-list">
					<li><a href="exitsystem.action"> 退出系统</a></li>
				</ul></li>
		</ul>
	</c:if>

</body>
</html>

