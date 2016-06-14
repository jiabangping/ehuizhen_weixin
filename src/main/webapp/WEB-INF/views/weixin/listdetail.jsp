<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userid = (String) session.getAttribute("userid");
%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>详细介绍</title>

<!-- Set render engine for 360 browser -->
<meta name="renderer" content="webkit">

<!-- No Baidu Siteapp-->
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="icon" type="image/png" href="<%=basePath%>assets/i/favicon.png">

<!-- Add to homescreen for Chrome on Android -->
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192" href="<%=basePath%>assets/i/app-icon72x72@2x.png">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="apple-touch-icon-precomposed" href="<%=basePath%>assets/i/app-icon72x72@2x.png">

<!-- Tile icon for Win8 (144x144 + tile color) -->
<meta name="msapplication-TileImage" content="<%=basePath%>assets/i/app-icon72x72@2x.png">
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
<script type="text/javascript"
	src="<%=basePath%>assets/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>assets/js/amazeui.min.js"></script>
<script type="text/javascript">
	
</script>

<style>
.am-header-fixed {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	width: 100%;
	z-index: 1010;
}

.title {
	margin-top: 10px;
	text-align: center;
}

.am-paragraph p {
	margin: 10px 10px;
	margin-top: 10px;
	margin-right: 10px;
	margin-bottom: 10px;
	margin-left: 10px;
	text-align: justify;
}

.button-top {
	border:1px solid #AAAAAA;
	background:#EEEE00;
};

.button-bottom {
	border:1px solid #AAAAAA;
};
</style>
</head>

<body>
	<div class="title">
		<h2>${course.title}</h2>
	</div>
	<article data-am-widget="paragraph"
		class="am-paragraph am-paragraph-default"
		data-am-paragraph="{ tableScrollable: true, pureview: true }">
		<div>
			<p class="am-text-left am-fl">主讲：${course.speaker}</p>
			<p class="am-text-right am-fl">时间：${course.inDate}</p>
		</div>
		<img class="am-img-responsive" src="${course.imgUrl}">
		<p class=paragraph-default-p>${course.describe}</p>
	</article>
	<div class="am-intro-more-bottom">
		<div class="am-form-group">
			<a href="<%=basePath %>weixin/viporder/${userId}?courseId=${course.id}"
				class="am-btn am-btn-block am-round button-top" role="button">购买VIP会员</a>
			<a href="<%=basePath %>weixin/courseorder/${userId}/${course.id}"
				class="am-btn am-btn-warning am-btn-block am-round button-bottom" role="button">购买当前课程</a>
		</div>
	</div>
</body>
</html>