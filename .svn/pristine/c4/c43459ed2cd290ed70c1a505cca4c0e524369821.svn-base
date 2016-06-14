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
  
<script type="text/javascript" src="<%=basePath%>js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/example.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.min.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/my-app.js"></script> --%>
<script type="text/javascript" src="<%=basePath%>js/jquery.swipebox.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.fitvids.js"></script>
<script type="text/javascript" src="<%=basePath%>js/email.js"></script>

  <script type="text/javascript">
 /*  $(function() {
	  $('#doc-vld-msg').validator({
	    onValid: function(validity) {
	      $(validity.field).closest('.am-form-group').find('.am-alert').hide();
	    },

	    onInValid: function(validity) {
	      var $field = $(validity.field);
	      var $group = $field.closest('.am-form-group');
	      var $alert = $group.find('.am-alert');
	      // 使用自定义的提示信息 或 插件内置的提示信息
	      var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

	      if (!$alert.length) {
	        $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
	          appendTo($group);
	      }

	      $alert.html(msg).show();
	      
	    }
	  });
	}); */
  
  function wxcheck(id){
	  var name = document.getElementById("doc-vld-name-2-1").value;
	  var tel = document.getElementById("doc-vld-tel-2-1").value;
 	  var wxid = document.getElementById("doc-vld-wx-2-1").value;
 	  
 	  if (name.length == 0) {
 		  alert("请输入姓名！");
 		  return false;
 	  }
 	  
 	 if (tel.length == 0) {
		  alert("请输入手机号！");
		  return false;
	  }
 	 
 	  if (wxid.length == 0) {
		  alert("请输入微信号！");
		  return false;
	  }
 	  
 	 if (tel.length < 11) {
		  alert("手机号长度至少11位！");
		  return false;
	  }
 	  
	  if (wxid != <%=wxid%>) {
		  alert("请输入正确的微信号！");
		  return false;
	  }
	  
	  return true;
  }
	
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
  </style>
</head>

<body>
<!-- <header data-am-widget="header" class="am-header am-header-default am-header-fixed">
    <div class="am-header-left am-header-nav">
        <a href="#left-link" class="">
              <i class=""></i>
        </a>
        <a href="#phone-link" class="">
              <i class=""></i>
        </a>
    </div>
    <h1 class="am-header-title">填写注册信息</h1>
    <div class="am-header-right am-header-nav">
        <a href="#cart-link" class="">
              <i class=""></i>
        </a>
        <a href="#user-link" class="">
              <i class=""></i>
        </a>
    </div>
</header> -->

<c:if test="${msg != null }">
<div class="am-alert am-alert-danger" data-am-alert>
  <button type="button" class="am-close">&times;</button>
  ${msg}
</div>
</c:if>

<form:form action="${actionUrl}" class="am-form" id="doc-vld-msg" method="POST" modelAttribute="user">
  <fieldset>
    <div class="am-form-group">
      <label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 姓名：</label>
      <input name="name" value="${user.name}" type="text" class="input-medium" id="doc-vld-name-2-1" placeholder="输入姓名" required/>
    </div>

    <div class="am-form-group">
      <label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 手机号码：</label>
      <input name="mobile" value="${user.mobile}" class="input-medium" type="tel" id="doc-vld-tel-2-1" readonly="readonly" minlength="11" placeholder="输入手机号" required/>
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
      <label for="doc-vld-name-2-1"><a style="color:#F00">*</a> 微信号：</label>
      <!-- <input type="text" id="doc-vld-name-2-1" placeholder="输入微信号" /> -->
      <input name="wxid" value="<%=wxid%>" type="text" class="input-medium" id="doc-vld-wx-2-1" placeholder="输入微信号" required/>
      <input name="wxurl" value="${user.wxurl}" type="hidden" class="input-medium" id="doc-vld-wxurl-2-1" />
      <input name="nickName" value="${user.nickName}" type="hidden" class="input-medium" id="doc-vld-nickname-2-1" />
    </div>

	<div class="am-form-group">
		<label for="doc-select-1">性别：</label>
		<select name="sex" id="doc-select-3" class="input-small">
			<option value="0" ${user.sex.toString() == "0" ? "selected='selected'":"" }>男</option>
			<option value="1" ${user.sex.toString() == "1" ? "selected='selected'":"" }>女</option>
		</select>
      <span class="am-form-caret"></span>
    </div>

	<div class="am-form-group">
      <label for="doc-vld-name-2-1">签名：</label>
      <!-- <input type="text" id="doc-vld-name-2-1" placeholder="输入签名" /> -->
      <input name="signature" value="${user.signature}" type="text" class="input-medium" id="doc-vld-name-2-1" placeholder="输入签名" />
    </div>

	<div class="am-form-group">
      <label for="doc-vld-name-2-1">公司：</label>
      <!-- <input type="text" id="doc-vld-name-2-1" placeholder="输入公司" /> -->
      <input name="companyName" value="${user.companyName}" type="text" class="input-medium" id="doc-vld-name-2-1" placeholder="输入公司" />
    </div>

	<div class="am-form-group">
      <label for="doc-vld-name-2-1">职位：</label>
      <!-- <input type="text" id="doc-vld-name-2-1" placeholder="输入职位" /> -->
      <input name="position" value="${user.position}" type="text" class="input-medium" id="doc-vld-name-2-1" placeholder="输入职位" />
    </div>
    
    <div class="am-form-group">
      <label for="doc-vld-name-2-1">业务：</label>
      <!-- <input type="text" id="doc-vld-name-2-1" placeholder="输入业务" /> -->
      <input name="business" value="${user.business}" type="text" class="input-medium" id="doc-vld-name-2-1" placeholder="输入业务" />
    </div>
    
    <div class="am-form-group">
      <label for="doc-vld-name-2-1">爱好：</label>
      <!-- <input type="text" id="doc-vld-name-2-1" placeholder="输入爱好" /> -->
      <input name="hobby" value="${user.hobby}" type="text" class="input-medium" id="doc-vld-name-2-1" placeholder="输入爱好" />
    </div>
    
    <div class="am-form-group">
      <label for="doc-vld-email-2-1">邮箱：</label>
      <!-- <input type="email" id="doc-vld-email-2-1" placeholder="输入邮箱" /> -->
      <input name="email" value="${user.email}" type="email" class="input-medium" id="doc-vld-name-2-1" placeholder="输入邮箱" />
    </div>
    
    <div class="am-form-group">
      <label for="doc-vld-name-2-1">地址：</label>
      <!-- <input type="text" id="doc-vld-name-2-1" placeholder="输入地址" /> -->
      <input name="address" value="${user.address}" type="text" class="input-medium" id="doc-vld-name-2-1" placeholder="输入地址" />
    </div>

    <div class="am-form-group">
      <label for="doc-select-1">星座：</label>
      <select name="signs" id="doc-select-2" class="input-small">
        <option value="0" ${user.signs == '0' ? "selected='selected'":"" }>请选择...</option>
		<option value="1" ${user.signs == '1' ? "selected='selected'":"" }>白羊座</option>
        <option value="2" ${user.signs == '2' ? "selected='selected'":"" }>金牛座</option>
        <option value="3" ${user.signs == '3' ? "selected='selected'":"" }>双子座</option>
        <option value="4" ${user.signs == '4' ? "selected='selected'":"" }>巨蟹座</option>
        <option value="5" ${user.signs == '5' ? "selected='selected'":"" }>狮子座</option>
        <option value="6" ${user.signs == '6' ? "selected='selected'":"" }>处女座</option>
        <option value="7" ${user.signs == '7' ? "selected='selected'":"" }>天秤座</option>
        <option value="8" ${user.signs == '8' ? "selected='selected'":"" }>天蝎座</option>
        <option value="9" ${user.signs == '9' ? "selected='selected'":"" }>射手座</option>
        <option value="10" ${user.signs == '10' ? "selected='selected'":"" }>摩羯座</option>
        <option value="11" ${user.signs == '11' ? "selected='selected'":"" }>水瓶座</option>
        <option value="12" ${user.signs == '12' ? "selected='selected'":"" }>双鱼座</option>
	</select>
      <span class="am-form-caret"></span>
    </div>

    <button class="am-btn  am-btn-primary am-btn-block am-topbar am-topbar-inverse am-topbar-fixed-bottom" type="submit"  onclick="return wxcheck('${user.wxid}');">提交</button>
  </fieldset>
</form:form>

<div class="weui_dialog_alert" id="dialog2" style="display: none;">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">弹窗标题</strong></div>
        <div class="weui_dialog_bd">弹窗内容，告知当前页面信息等</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary">确定</a>
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