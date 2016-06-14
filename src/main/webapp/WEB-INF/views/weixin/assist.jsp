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
<title>课辅资料</title>

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
<link rel="stylesheet" href="<%=basePath%>style/weui.css" />
<link rel="stylesheet" href="<%=basePath%>style/example.css" />

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
	  function checkIsPay(courseId){
		  window.location.href='<%=basePath%>weixin/ispay/<%=userid%>/'+courseId;
	  }
</script>

<style>
.list-btn {
	padding-left: 0;
	padding-right: 0;
	width: 100px;
	display: inline-block;
	margin-bottom: 0;
	padding: .2em 0em;
	/* font-size: 1.6rem; */
	font-weight: 400;
	/* line-height: 1.2; */
	text-align: center;
	white-space: nowrap;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 0;
	cursor: pointer;
	outline: 0;
	-webkit-appearance: none;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	-webkit-transition: background-color .3s ease-out, border-color .3s
		ease-out;
	transition: background-color .3s ease-out, border-color .3s ease-out;
}

.am-list-news-default .am-list-item-thumb-left .am-list-thumb,
	.am-list-news-default .am-list-item-thumb-right .am-list-thumb {
	max-height: 100px;
	overflow: hidden;
}

.am-header-fixed {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	/* width: 100%; */
	z-index: 1010;
}

.top {
		margin-top: 10px;
	}
	
.button-top {
	border:1px solid #AAAAAA;
	background:#EEEE00;
};

.button-bottom {
	border:1px solid #AAAAAA;
};
.course-list-btn {
	display:inlin-block;/*设置为行内块*/
	width:100px；/*设置a标签宽度为100px*/
}
.list-btn-left a{ float:left;width:90px;height:30px;border:1px solid #AAAAAA;}
.list-btn-right a{ float:right;width:90px;height:30px;border:1px solid #AAAAAA;} 
</style>
</head>

<body>
	<div data-am-widget="tabs" class="am-tabs am-tabs-d2">
			<div data-tab-panel-1 class="am-tab-panel ">
				<div data-am-widget="list_news"
					class="am-list-news am-list-news-default">
					<div class="am-list-news-bd">
						<ul class="am-list">
							<li>
							<dl class="top">
							  <%-- <dt><span class="am-text-truncate">${course.title}</span></dt> --%>
							  <c:if test="${courses.size() == 0}">
							  <blockquote>
								  <p>暂无课辅资料</p>
							  </blockquote>
							  </c:if>
							  <c:forEach items="${courses}" var="education" varStatus="status">
							  <blockquote>
								  <p>${education.descript}</p>
								  <br>
								  <c:if test="${education.type == 1}">
								  <small><a href="${education.url}" class="am-text-truncate">(使用其他浏览器)点击下载课辅资料</a></small>
								  </c:if>
								  <c:if test="${education.type == 0}">
								  <small><a href="${education.url}" class="am-text-truncate">查看课辅资料</a></small>
								  </c:if>
							  </blockquote>
							  </c:forEach>
							</dl>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=basePath%>assets/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="<%=basePath%>assets/js/amazeui.min.js"></script>
</body>
</html>