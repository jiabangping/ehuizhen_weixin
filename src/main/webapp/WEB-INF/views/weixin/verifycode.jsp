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
	String wxurl = (String)session.getAttribute("wxurl");
	String nickName = (String)session.getAttribute("nickName");
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
  <title>获取验证码</title>

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
  
<script type="text/javascript" src="<%=basePath%>js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/example.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.swipebox.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.fitvids.js"></script>
<script type="text/javascript" src="<%=basePath%>js/email.js"></script>
  <script type="text/javascript">
	function verifycheck(){
		  var tel = document.getElementById("doc-vld-tel-2-1").value;
	 	  var verify = document.getElementById("doc-vld-verify-2-1").value;
	 	  
	 	  if (tel.length == 0) {
	 		  alert("请输入手机号！");
	 		  return false;
	 	  }
	 	  
	 	 if (verify.length == 0) {
			  alert("请输入验证码！");
			  return false;
		  }
	 	  
	 	 if (tel.length < 11) {
			  alert("手机号长度至少11位！");
			  return false;
		  }
		  
		  return true;
	  }
	
	function changecode(){
		var tel = document.getElementById("doc-vld-tel-2-1").value;
		if (tel.length == 0) {
	 		  alert("请输入手机号！");
	 		  return;
	 	}
		if (tel.length < 11) {
			  alert("手机号长度至少11位！");
			  return;
		}
		window.location.href = "<%=basePath%>weixin/send/" + tel + "/verifycode";
	}
	
	var InterValObj; //timer变量，控制时间
	var count = 60; //间隔函数，1秒执行
	var curCount;//当前剩余秒数

	function sendMessage() {
		var tel = document.getElementById("doc-vld-tel-2-1").value;
		if (tel.length == 0) {
	 		  alert("请输入手机号！");
	 		  return;
	 	}
		if (tel.length < 11) {
			  alert("手机号长度至少11位！");
			  return;
		}
		curCount = count;
		//设置button效果，开始计时
		$("#btnSendCode").attr("disabled", "true");
		$("#btnSendCode").val("等待验证码" + curCount + "秒");
		//启动计时器，1秒执行一次
		InterValObj = window.setInterval(SetRemainTime, 1000);
		//向后台发送处理数据
		$.ajax({
			url : '<%=basePath%>weixin/send/' + tel + '/verifycode',
			type : 'get',
			cache : false,
			dataType : 'text',
			success : function(data) {
				if (data != "OK") {
					alert(data);
					window.clearInterval(InterValObj);//停止计时器
		            $("#btnSendCode").removeAttr("disabled");//启用按钮
		            $("#btnSendCode").val("重新发送验证码");
				}
			},
			error : function(data) {
				alert("重新发送验证码");
				window.clearInterval(InterValObj);//停止计时器
	            $("#btnSendCode").removeAttr("disabled");//启用按钮
	            $("#btnSendCode").val("重新发送验证码");
			}
		});
	}

	//timer处理函数
	function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#btnSendCode").removeAttr("disabled");//启用按钮
            $("#btnSendCode").val("重新发送验证码");
        } else {
            curCount--;
            $("#btnSendCode").val("等待验证码" + curCount + "秒");
        }
    }
  </script>
  
  <style>
	.am-top {
		margin-top: 10px;
	}
		
	.user-img {
        /* position: absolute; */
        margin-top: 10px;
        width: 75px;
        height: 75px;
        left: 0;
        right: 0;
        margin: auto;
        border: 5px solid #9F9887;
        /* border-radius: 100%; */
    }

    .user-img img {
        width: 100%;
        height: 100%;
    }
    
    .weixin-number {
		margin-top: 10px;
		text-align:center;
	}
  </style>
</head>

<body>

<div class="weui_cells_title weixin-number"></div>
<div class="user-img">
    <img src="<%=wxurl%>">
</div>
<div class="weixin-number">
<a><%=nickName%></a>
</div>

<c:if test="${msg != null }">
<div class="am-alert am-alert-danger" data-am-alert>
  <button type="button" class="am-close">&times;</button>
  ${msg}
</div>
</c:if>

<form:form action="/weixin_test/weixin/send/validity" class="am-form am-form-horizontal am-top" id="doc-vld-msg" modelAttribute="sms">
  <fieldset>
  		<div class="am-form-group">
		    <div class="am-input-group">
			  <span class="am-input-group-label">手机号：</span>
			  <input name="mobile" value="${sms.mobile}" class="input-medium" type="tel" id="doc-vld-tel-2-1" minlength="11" placeholder="输入手机号" required/>
			</div>
		</div>

		<div class="am-form-group">
		    <div class="am-input-group">
		      <input name="verifyCode" type="text" value="${sms.verifyCode}" class="am-form-field" id="doc-vld-verify-2-1" required/>
		      <span class="am-input-group-btn">
		        <!-- <a id="verify" href="#" onclick="changecode()" class="am-btn am-btn-primary ">获取验证码</a> -->
		        <input class="am-btn am-btn-primary " id="btnSendCode" type="button" value="发送验证码" onclick="sendMessage()" />
		      </span>
		    </div>
		 </div>
		 
    <div class="am-form-group">
	    	<button type="submit" class="am-btn  am-btn-primary am-btn-block" onclick="return verifycheck();">下一步</button>
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