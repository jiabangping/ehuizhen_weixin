<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String patha = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ patha + "/";
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
  <title>问题反馈</title>

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
  <link rel="stylesheet" href="<%=basePath%>assets/css/app.css">
  <link rel="stylesheet" href="<%=basePath%>style/weui.css"/>
  <link rel="stylesheet" href="<%=basePath%>style/example.css"/>
  
<script type="text/javascript" src="<%=basePath%>js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/example.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.swipebox.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.fitvids.js"></script>
<script type="text/javascript" src="<%=basePath%>js/email.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jweixin-1.0.0.js"></script>

  <script type="text/javascript">
  var qsData = {'url': location.href.split('#')[0] };
  $.ajax({
      type: "POST",
      url: '<%=basePath%>weixin/signature',
		data : qsData,
		dataType : "json",
		success : function(data) {
			wx.config({
				debug : false,
				appId : data.appId,
				timestamp : data.timestamp,
				nonceStr : data.nonceStr,
				signature : data.signature,
				jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
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
						'addCard', 'chooseCard', 'openCard' ]
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		},
		complete : function(XMLHttpRequest, textStatus) {
			this;
		}
	});
  
  function ation(){
	  var description = $("#doc-description-1").val();
      var advice = $("#doc-advice-1").val();
      if (description.length == 0) {
    	  alert("还没填写问题描述内容哦?");
    	  return;
      }
      if (advice.length == 0) {
    	  alert("还没填写建议内容哦?");
    	  return;
      }
	  
		//向后台发送处理数据
		$.ajax({
			url : '<%=basePath%>/weixin/feedback/insert',
			type : 'post',
			cache : false,
			dataType : 'json',
			data : {
			      'userName' : $("#doc-vld-name-2-1").val(),
			      'userMobile' :$("#doc-vld-tel-2-1").val(),
			      'description' :$("#doc-description-1").val(),
			      'advice' :$("#doc-advice-1").val()
			     },
			success : function(data) {
				if (data.msg == "OK") {
					alert("问题反馈提交完成。");
					wx.closeWindow();
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("问题反馈提交失败，请稍后提交。");
			}
		});
	  }
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
	
	.am-top {
		margin-top: 20px;
		}
		
	.button-top {
		border:1px solid #AAAAAA;
	};	
  </style>
</head>

<body>

<c:if test="${msg != null }">
<div class="am-alert am-alert-danger" data-am-alert>
  <button type="button" class="am-close">&times;</button>
  ${msg}
</div>
</c:if>

<form:form action="${actionUrl}" class="am-form" id="doc-vld-msg" method="POST" modelAttribute="feedback">
  <fieldset>
    <div class="am-form-group am-top">
      <label for="doc-vld-name-2-1">姓名：</label>
      <input name="userName" value="${user.name}" type="text" class="input-medium" id="doc-vld-name-2-1" disabled="disabled" placeholder="输入姓名" required/>
    </div>

    <div class="am-form-group">
      <label for="doc-vld-name-2-1">手机号码：</label>
      <input name="userMobile" value="${user.mobile}" class="input-medium" type="tel" id="doc-vld-tel-2-1"  disabled="disabled" placeholder="输入手机号" required/>
    </div>

	<div class="am-form-group">
      <label for="doc-ta-1">问题描述：</label>
      <textarea name="description" value="${feedback.description}" class="" rows="5" id="doc-description-1" placeholder="输入问题描述" required></textarea>
    </div>
    
    <div class="am-form-group">
      <label for="doc-ta-1">我的建议：</label>
      <textarea name="advice" value="${feedback.advice}" class="" rows="5" id="doc-advice-1" placeholder="输入建议" required></textarea>
    </div>

    <div class="am-form-group">
	    	<button type="button" class="am-btn am-btn-warning am-btn-block am-round button-top" onclick="ation();">提交</button>
  	</div>
  </fieldset>
</form:form>

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