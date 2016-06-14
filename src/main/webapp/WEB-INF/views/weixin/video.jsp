<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String wxid = (String)session.getAttribute("wxid");
%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <title>课程学习</title>

  <!-- Set render engine for 360 browser -->
  <meta name="renderer" content="webkit">

  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>

  <link rel="icon" type="image/png" href="<%=basePath%>assets/i/favicon.png">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="<%=basePath%>assets/i/app-icon72x72@2x.png">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
  <link rel="apple-touch-icon-precomposed" href="<%=basePath%>assets/i/app-icon72x72@2x.png">

  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <meta name="msapplication-TileImage" content="<%=basePath%>assets/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">

  <link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css">
  <link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.videojs.css"/>
  <link rel="stylesheet" href="<%=basePath%>assets/css/app.css">
  <link rel="stylesheet" href="<%=basePath%>style/weui.css"/>
  <link rel="stylesheet" href="<%=basePath%>style/example.css"/>
  
<script type="text/javascript" src="<%=basePath%>js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/example.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.swipebox.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.fitvids.js"></script> --%>
<script type="text/javascript" src="<%=basePath%>js/email.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jweixin-1.0.0.js"></script>
<script>
var qsData = {'url': location.href.split('#')[0] };
$.ajax({
    type: "POST",
    url: '<%=basePath%>weixin/signature',
    data: qsData,
    dataType: "json",
    success: function (data) {
        wx.config({
            debug: false,
            appId: data.appId,
            timestamp:data.timestamp,
            nonceStr: data.nonceStr,
            signature: data.signature,
            jsApiList: [
						'checkJsApi', 'onMenuShareTimeline',
						'onMenuShareAppMessage', 'onMenuShareQQ',
						'onMenuShareWeibo', 'hideMenuItems', 'showMenuItems',
						'hideAllNonBaseMenuItem', 'showAllNonBaseMenuItem',
						'translateVoice', 'startRecord', 'stopRecord',
						'onRecordEnd', 'playVoice', 'pauseVoice', 'stopVoice',
						'uploadVoice', 'downloadVoice', 'chooseImage',
						'previewImage', 'uploadImage', 'downloadImage',
						'getNetworkType', 'openLocation', 'getLocation',
						'hideOptionMenu', 'showOptionMenu', 'closeWindow',
						'scanQRCode', 'chooseWXPay', 'openProductSpecificView',
						'addCard', 'chooseCard', 'openCard'
            ]
        });
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
    },
    complete: function (XMLHttpRequest, textStatus) {
        this;
    }
});
wx.ready(function() {
	wx.hideAllNonBaseMenuItem();
	wx.showMenuItems({
	    menuList: ["menuItem:share:appMessage"] 
	});
	wx.onMenuShareAppMessage({
		title : $("#title").val(),
		desc : $("#title").val(),
		link : 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx27d4abed41a17c84&redirect_uri=http%3A%2F%2Fweixin.yypxq.com%2Fweixin_test%2Fweixin%2Fvideo%2F'+$("#courseId").val()+'&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect',
						imgUrl : $("#imgurl").val(),
						trigger : function(res) {
						},
						success : function(res) {
						},
						cancel : function(res) {
						},
						fail : function(res) {
							alert(JSON.stringify(res));
						}
					});
		});
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
		text-align:center;
	}
	.am-paragraph p {
	    margin: 10px 10px;
	    margin-top: 10px;
	    margin-right: 10px;
	    margin-bottom: 10px;
	    margin-left: 10px;
	    text-align: justify;
	}
  </style>
</head>

<body>
<input type="hidden" id="maintype" value="${maintype}" />
<input type="hidden" id="title" value="${title}" />
<input type="hidden" id="imgurl" value="${imgurl}" />
<input type="hidden" id="courseId" value="${courseId}" />
<!-- <div class="weui_cells_title">课程PPT</div> -->
<div class="weui_cells weui_cells_access">
<div data-am-widget="slider" class="am-slider am-slider-b2" data-am-slider='{&quot;controlNav&quot;:false}' data-am-flexslider="{directionNav: false, slideshow: false}" >
  <ul class="am-slides">
  	  <c:if test="${ppts == null}">
  	  <li><img src="<%=basePath%>img/404.jpg"></li>
  	  </c:if>
      <c:forEach items="${ppts}" var="ppt" varStatus="status">
      <li>
        	<img src="${ppt}">
      </li>
      </c:forEach>
  </ul>
</div>
</div>

<c:if test="${video != ''}">
<!-- <div class="weui_cells_title">课程视频</div> -->
<div class="weui_cells weui_cells_access">
<video id="example_video_1" class="video-js vjs-amazeui" controls preload="none" width="100%" height="250px"
       poster="http://video-js.zencoder.com/oceans-clip.png">
  <source src="${video}" type='video/mp4' />
  <!-- <source src="http://192.168.5.100:9998/wx_back/upload/course/VIDEO/1455858719564.mp4" type='video/mp4' />
  <source src="http://video-js.zencoder.com/oceans-clip.webm" type='video/webm' />
  <source src="http://video-js.zencoder.com/oceans-clip.ogv" type='video/ogg' /> -->
  <%-- <track kind="captions" src="<%=basePath%>assets/js/demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
  <track kind="subtitles" src="<%=basePath%>assets/js/demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 --> --%>
</video>
</div>
</c:if>

<c:if test="${voice != ''}">
<!-- <div class="weui_cells_title">课程语音</div> -->
<div class="weui_cells weui_cells_access">
<audio src="${voice}" style="width: 100%" controls="controls">
</audio>
</div>
</c:if>

<script src="<%=basePath%>assets/js/video.js"></script>
<script>
  videojs.options.flash.swf = "<%=basePath%>assets/js/video-js.swf";
</script>

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