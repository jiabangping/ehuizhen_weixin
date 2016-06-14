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
  <title>注册信息</title>

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
  <link rel="stylesheet" href="<%=basePath%>style/weui.css">
  
<script type="text/javascript" src="<%=basePath%>js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/example.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.min.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/my-app.js"></script> --%>
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
  
  	// 更换图片验证码
	function changeImg() {
		var time = new Date().getTime();
	    document.getElementById('imgObj').src= '<%=basePath%>code?'+time;
	}

	// 更换图片验证码
	function changeTeamImg() {
		var time = new Date().getTime();
	    document.getElementById('imgTeamObj').src= '<%=basePath%>teamcode?'+time;
	}
  	
	// 团队报名表单验证
	function wxteamcheck(id){
		alert("asdf");
		var company = document.getElementById("doc-vld-team-company-2-1").value;
		var name = document.getElementById("doc-vld-team-name-2-1").value;
		var tel = document.getElementById("doc-vld-team-tel-2-1").value;
		var verify = document.getElementById("doc-vld-team-verify-2-1").value;
		var rand = document.getElementById("doc-vld-team-rand-2-1").value;
		if (company.length == 0) {
			alert("请输入公司名称！");
			return false;
		}
		if (name.length == 0) {
			alert("请输入姓名！");
			return false;
		}
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
		if (rand.length == 0) {
	 		  alert("请输入图片验证码！");
	 		  return false;
	 	}
		return true;
	}
  	
  	// 个人报名表单验证
	function wxcheck(id){
		var name = document.getElementById("doc-vld-name-2-1").value;
		var tel = document.getElementById("doc-vld-tel-2-1").value;
		var verify = document.getElementById("doc-vld-verifyCode-2-1").value;
		var rand = document.getElementById("doc-vld-rand-2-1").value;
		if (name.length == 0) {
			alert("请输入姓名！");
			return false;
		}
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
		if (rand.length == 0) {
	 		  alert("请输入图片验证码！");
	 		  return false;
	 	}
		return true;
	}

	function wxknowcheck(id){
		var name = document.getElementById("doc-vld-name-2-1").value;
		var tel = document.getElementById("doc-vld-tel-2-1").value;
		var verify = document.getElementById("doc-vld-verifyCode-2-1").value;
		var rand = document.getElementById("doc-vld-rand-2-1").value;
		if (name.length == 0) {
			alert("请输入姓名！");
			return;
		}
		if (tel.length == 0) {
			alert("请输入手机号！");
			return;
		}
		if (verify.length == 0) {
			alert("请输入验证码！");
			return;
		}
		if (tel.length < 11) {
			alert("手机号长度至少11位！");
			return;
		}
		if (rand.length == 0) {
	 		  alert("请输入图片验证码！");
	 		  return;
	 	}
		
		//向后台发送处理数据
		$.ajax({
			url : '<%=basePath%>weixin/register/know/insert',
			type : 'post',
			cache : false,
			dataType : 'json',
			data : {
			      'wxid' : $("#doc-vld-wxid-2-1").val(),
			      'name' :$("#doc-vld-name-2-1").val(),
			      'mobile' : $("#doc-vld-tel-2-1").val(),
			      'wxurl' :$("#doc-vld-wxurl-2-1").val(),
			      'nickName' : $("#doc-vld-nickName-2-1").val(),
			      'verifyCode' :$("#doc-vld-verifyCode-2-1").val(),
			      'companyName' : $("#doc-vld-companyName-2-1").val(),
			      'position' :$("#doc-vld-position-2-1").val()
			     },
			success : function(data) {
				if (data.msg == "OK") {
					alert("注册完成，可以观看免费教程。");
					wx.closeWindow();
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("注册失败，请稍后再试。");
			}
		});
	}
			
			
	var InterValObj; //timer变量，控制时间
	var count = 60; //间隔函数，1秒执行
	var curCount;//当前剩余秒数

	function sendMessage() {
		var tel = document.getElementById("doc-vld-tel-2-1").value;
		var rand = document.getElementById("doc-vld-rand-2-1").value;
		if (tel.length == 0) {
	 		  alert("请输入手机号！");
	 		  return;
	 	}
		if (tel.length < 11) {
			  alert("手机号长度至少11位！");
			  return;
		}
		if (rand.length == 0) {
	 		  alert("请输入图片验证码！");
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
			url : '<%=basePath%>weixin/send/' + tel + '/verifycode/' + rand,
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

	function sendTeamMessage() {
		var tel = document.getElementById("doc-vld-team-tel-2-1").value;
		var rand = document.getElementById("doc-vld-team-rand-2-1").value;
		if (tel.length == 0) {
	 		  alert("请输入手机号！");
	 		  return;
	 	}
		if (tel.length < 11) {
			  alert("手机号长度至少11位！");
			  return;
		}
		if (rand.length == 0) {
	 		  alert("请输入图片验证码！");
	 		  return;
	 	}
		curCount = count;
		//设置button效果，开始计时
		$("#btnTeamSendCode").attr("disabled", "true");
		$("#btnTeamSendCode").val("等待验证码" + curCount + "秒");
		//启动计时器，1秒执行一次
		InterValObj = window.setInterval(SetTeamRemainTime, 1000);
		//向后台发送处理数据
		$.ajax({
			url : '<%=basePath%>weixin/send/' + tel + '/verifycode/' + rand + '/team',
			type : 'get',
			cache : false,
			dataType : 'text',
			success : function(data) {
				if (data != "OK") {
					alert(data);
					window.clearInterval(InterValObj);//停止计时器
		            $("#btnTeamSendCode").removeAttr("disabled");//启用按钮
		            $("#btnTeamSendCode").val("重新发送验证码");
				}
			},
			error : function(data) {
				alert("重新发送验证码");
				window.clearInterval(InterValObj);//停止计时器
	            $("#btnTeamSendCode").removeAttr("disabled");//启用按钮
	            $("#btnTeamSendCode").val("重新发送验证码");
			}
		});
	}

	//timer处理函数
	function SetTeamRemainTime() {
      if (curCount == 0) {
          window.clearInterval(InterValObj);//停止计时器
          $("#btnTeamSendCode").removeAttr("disabled");//启用按钮
          $("#btnTeamSendCode").val("重新发送验证码");
      } else {
          curCount--;
          $("#btnTeamSendCode").val("等待验证码" + curCount + "秒");
      }
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

<c:if test="${msg != null }">
<div class="am-alert am-alert-danger" data-am-alert>
  <button type="button" class="am-close">&times;</button>
  ${msg}
</div>
</c:if>

<div data-am-widget="tabs" class="am-tabs am-tabs-default" >
    <ul class="am-tabs-nav am-cf am-btn-warning">
        <li class="am-active am-btn-warning"><a href="[data-tab-panel-0]">个人报名</a></li>
        <li class="am-btn-warning"><a href="[data-tab-panel-1]">团队报名</a></li>
    </ul>
    <div class="am-tabs-bd">
        <div data-tab-panel-0 class="am-tab-panel am-active">
          <form:form action="${actionUrl}" class="am-form" id="doc-vld-msg" method="POST" modelAttribute="user">
			  <fieldset>
			    <div class="am-form-group">
			      <label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 姓名：</label>
			      <input name="name" value="${user.name}" type="text" class="input-medium" id="doc-vld-name-2-1" placeholder="输入姓名" required/>
			    </div>
			
			    <div class="am-form-group">
			      <label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 手机号码：</label>
			      <input name="mobile" value="${user.mobile}" class="input-medium" type="tel" id="doc-vld-tel-2-1" minlength="11" placeholder="输入手机号" required/>
			      <input name="wxid" value="<%=wxid%>" type="hidden" class="input-medium" id="doc-vld-wxid-2-1" />
			      <input name="wxurl" value="${user.wxurl}" type="hidden" class="input-medium" id="doc-vld-wxurl-2-1" />
			      <input name="nickName" value="${user.nickName}" type="hidden" class="input-medium" id="doc-vld-nickname-2-1" />
			    </div>
			
				<div class="am-form-group">
					<label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 图片验证码：</label>
				    <div class="am-input-group">
				      <input name="" type="text" value="" class="am-form-field" id="doc-vld-rand-2-1" placeholder="点击图片可更换验证码" required/>
				      <span class="am-input-group-btn">
				      	<img id="imgObj" alt="验证码" src="<%=basePath%>code" onclick="changeImg()"/>
				      </span>
				    </div>
				</div>
			
				<div class="am-form-group">
					<label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 短信验证码：</label>
				    <div class="am-input-group">
				      <input name="verifyCode" type="text" value="${user.verifyCode}" class="am-form-field" id="doc-vld-verifyCode-2-1" placeholder="输入短信验证码" required/>
				      <span class="am-input-group-btn">
				        <input class="am-btn am-btn-warning " id="btnSendCode" type="button" value="发送验证码" onclick="sendMessage()" />
				      </span>
				    </div>
				 </div>
			
				<div class="am-form-group">
			      <label for="doc-vld-name-2-1">公司：</label>
			      <!-- <input type="text" id="doc-vld-name-2-1" placeholder="输入公司" /> -->
			      <input name="companyName" value="${user.companyName}" type="text" class="input-medium" id="doc-vld-companyName-2-1" placeholder="输入公司" />
			    </div>
			
				<div class="am-form-group">
			      <label for="doc-vld-name-2-1">职位：</label>
			      <!-- <input type="text" id="doc-vld-name-2-1" placeholder="输入职位" /> -->
			      <input name="position" value="${user.position}" type="text" class="input-medium" id="doc-vld-position-2-1" placeholder="输入职位" />
			    </div>
			
			    <button class="am-btn am-btn-block am-round button-top" type="submit"  onclick="return wxcheck('${user.wxid}');">支付1999元年费成为VIP会员</button>
			    <div class="am-form-group weixin-number">
					<%-- <a href="<%=basePath%>/weixin/about" class="am-btn am-btn-warning am-btn-block am-round button-bottom" role="button" onclick="return wxcheck('${user.wxid}');">先了解一下再说</a> --%>
					<button class="am-btn am-btn-warning am-btn-block am-round button-bottom" type="button"  onclick="wxknowcheck('${user.wxid}');">先了解一下再说</button>
				</div>
			  </fieldset>
			</form:form>
        </div>
        <div data-tab-panel-1 class="am-tab-panel ">
       	  <div class="am-form-group">
		   		<p class="am-text-danger am-kai">注：团队报名享受买5赠1</p>
		  </div>
          <form:form action="${actionTeamUrl}" class="am-form" id="doc-vld-msg" method="POST" modelAttribute="user">
			  <fieldset>
			    <div class="am-form-group">
			      <label for="doc-vld-name-2-1"><a style="color:#F00">*</a>公司：</label>
			      <!-- <input type="text" id="doc-vld-name-2-1" placeholder="输入公司" /> -->
			      <input name="companyName" value="${user.companyName}" type="text" class="input-medium" id="doc-vld-team-company-2-1" placeholder="输入公司" required/>
			    </div>
			    
			    <div class="am-form-group">
			      <label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 联系人：</label>
			      <input name="name" value="${user.name}" type="text" class="input-medium" id="doc-vld-team-name-2-1" placeholder="输入姓名" required/>
			    </div>

			    <div class="am-form-group">
			      <label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 手机号码：</label>
			      <input name="mobile" value="${user.mobile}" class="input-medium" type="tel" id="doc-vld-team-tel-2-1" minlength="11" placeholder="输入手机号" required/>
			      <input name="wxid" value="<%=wxid%>" type="hidden" class="input-medium" id="doc-vld-wx-2-1" />
			      <input name="wxurl" value="${user.wxurl}" type="hidden" class="input-medium" id="doc-vld-wxurl-2-1" />
			      <input name="nickName" value="${user.nickName}" type="hidden" class="input-medium" id="doc-vld-nickname-2-1" />
			    </div>
			
				<div class="am-form-group">
					<label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 图片验证码：</label>
				    <div class="am-input-group">
				      <input name="" type="text" value="" class="am-form-field" id="doc-vld-team-rand-2-1" placeholder="点击图片可更换验证码" required/>
				      <span class="am-input-group-btn">
				      	<img id="imgTeamObj" alt="验证码" src="<%=basePath%>teamcode" onclick="changeTeamImg()"/>
				      </span>
				    </div>
				</div>
			
				<div class="am-form-group">
					<label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 短信验证码：</label>
				    <div class="am-input-group">
				      <input name="verifyCode" type="text" value="${user.verifyCode}" class="am-form-field" id="doc-vld-team-verify-2-1" placeholder="输入短信验证码" required/>
				      <span class="am-input-group-btn">
				        <input class="am-btn am-btn-warning " id="btnTeamSendCode" type="button" value="发送验证码" onclick="sendTeamMessage()" />
				      </span>
				    </div>
				 </div>

			    <button class="am-btn am-btn-block am-round button-top" type="submit"  onclick="return wxteamcheck'${user.wxid}');">立即加入</button>
			  </fieldset>
			</form:form>
        </div>
    </div>
</div>

<div class="weui_dialog_alert" id="dialog2" style="display: none;">
	<div class="weui_mask"></div>
	<div class="weui_dialog">
		<div class="weui_dialog_hd">
			<strong class="weui_dialog_title">注册信息</strong>
		</div>
		<div class="weui_dialog_bd" id="msg"></div>
		<div class="weui_dialog_ft">
			<a href="#" class="weui_btn_dialog primary">确定</a>
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