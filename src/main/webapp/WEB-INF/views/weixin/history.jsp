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
<title>已经学习的课程</title>

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
	width: 100%;
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
.list-btn-left {width:50%;}
.list-btn-right {width:50%;}
.list-btn-left a{ float:left;width:95%;height:30px;border:1px solid #AAAAAA;}
.list-btn-right a{ float:right;width:95%;height:30px;border:1px solid #AAAAAA;} 
</style>
</head>

<body>
	<div data-am-widget="list_news"
		class="am-list-news am-list-news-default">
		<div class="am-list-news-bd">
			<ul class="am-list">
				<c:forEach items="${courses}" var="course" varStatus="status">
					<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left" >
						<div class="am-u-sm-4">
								<img height="120" width="90" src="${course.courseInfo.imgUrl}" alt="" />
						</div>
						<div class=" am-u-sm-8 am-list-main">
							<h3 class="am-list-item-hd">
								<a href="#" class="">${course.courseInfo.title}</a>
							</h3>
							<c:if test="${course.courseInfo.amt != 0 }">
								<div class="am-list-item-text">金额：${course.courseInfo.amt}</div>
								<div class="am-list-item-text">主讲人：${course.courseInfo.speaker}</div>
								<div class="am-list-item-text">到期时间：<fmt:formatDate value="${course.expireTime}" type="both" pattern="yyyy-MM-dd " /></div>
							</c:if>
							<c:if test="${course.courseInfo.amt == 0 }">
								<div class="am-list-item-text">免费</div>
								<div class="am-list-item-text">主讲人：${course.courseInfo.speaker}</div>
								<div class="am-list-item-text">时间：<fmt:formatDate value="${course.courseInfo.inDate}" type="both" pattern="yyyy-MM-dd " /></div>
							</c:if>
							<div class="am-list-item-text">
							<div class="am-g am-g-fixed">
							  <div class="am-fl list-btn-left">
							  	<a href="#" onclick="checkIsPay('${course.courseInfo.id}');" class="am-btn am-btn-default am-btn-xs am-btn-block button-top am-round" role="button">查看课程</a>
							  </div>
							  <c:if test="${course.courseInfo.assistInfo.size() != 0}">
							  <div class="am-fr list-btn-right">
							  	<a href="<%=basePath%>weixin/assist/${course.courseInfo.id}" class="am-btn am-btn-default am-btn-xs am-btn-block am-btn-warning am-round" role="button">课辅资料</a>
							  </div>
							  </c:if>
							</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>