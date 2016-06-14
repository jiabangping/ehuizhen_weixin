<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userid = (String)session.getAttribute("userid");
%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>支付页面</title>

<!-- Set render engine for 360 browser -->
<meta name="renderer" content="webkit">

<!-- No Baidu Siteapp-->
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="icon" type="image/png"
	href="<%=basePath%>assets/i/favicon.png">

<!-- Add to homescreen for Chrome on Android -->
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192"
	href="<%=basePath%>assets/i/app-icon72x72@2x.png">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="apple-touch-icon-precomposed"
	href="<%=basePath%>assets/i/app-icon72x72@2x.png">

<!-- Tile icon for Win8 (144x144 + tile color) -->
<meta name="msapplication-TileImage"
	content="<%=basePath%>assets/i/app-icon72x72@2x.png">
<meta name="msapplication-TileColor" content="#0e90d2">

<link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>assets/css/app.css">

<script type="text/javascript" src="<%=basePath%>js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/example.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.swipebox.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.fitvids.js"></script>
<script type="text/javascript" src="<%=basePath%>js/email.js"></script>
<style>
html {
	background: url(<%=basePath%>img/vip.jpg) repeat;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: 100% 100%;
}
</style>
</head>
<body >
	<div  class="am-navbar  am-cf am-navbar-default " id="">
      <!-- <div class="am-form-group am-top"> -->
		 	<a href="<%=basePath %>weixin/viporder/${userid}"
			class="am-btn am-btn-warning am-btn-block" role="button">购买VIP会员（1999元/年）</a>
	<!-- </div> -->
      </div>
	<script src="<%=basePath%>assets/js/jquery.min.js"></script>
	<script src="<%=basePath%>assets/js/amazeui.min.js"></script>
</body>
</html>