<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
  <title>修改个人信息</title>

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
  function wxcheck(id){
	  var name = document.getElementById("doc-vld-name-2-1").value;
	  var tel = document.getElementById("doc-vld-tel-2-1").value;
 	  var wxid = document.getElementById("doc-vld-wx-2-1").value;
 	  var email = document.getElementById("doc-vld-email-2-1").value;
 	  
 	  if (name.length == 0) {
 		  alert("请输入姓名！");
 		  return false;
 	  }
 	  
 	 if (email.length != 0) {
		var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
		if (!pattern.test(email)) {
		    alert("请输入正确的邮箱地址。");   
		    return false;  
		}
	  }
 	  
 	 /* if (tel.length == 0) {
		  alert("请输入手机号！");
		  return false;
	  }
 	 
 	  if (wxid.length == 0) {
		  alert("请输入微信号！");
		  return false;
	  } */
 	  
 	 /* if (tel.length < 11) {
		  alert("手机号长度至少11位！");
		  return false;
	  }
 	  
	  if (wxid != id) {
		  alert("请输入正确的微信号！");
		  return false;
	  } */
	  
	  return true;
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
	.button-top {
		border:1px solid #AAAAAA;
		background:#EEEE00;
	};
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
      <label for="doc-vld-name-2-1">* 姓名：</label>
      <input name="name" value="${user.name}" type="text" class="input-medium" id="doc-vld-name-2-1" placeholder="输入姓名" required/>
    </div>

	<div class="am-form-group">
      <input name="mobile" value="${user.mobile}" class="input-medium" type="hidden" id="doc-vld-tel-2-1"  />
      <input name="wxid" value="${user.wxid}" type="hidden" class="input-medium" id="doc-vld-wx-2-1" />
      <input name="wxurl" value="${user.wxurl}" type="hidden" class="input-medium" id="doc-vld-wxurl-2-1" />
      <input name="nickName" value="${user.nickName}" type="hidden" class="input-medium" id="doc-vld-nickname-2-1" />
    </div>

	<div class="am-form-group">
		<label for="doc-select-1">性别：</label>
		<select name="sex" id="doc-select-3" class="input-small">
			<option value="0" >男</option>
			<option value="1" ${user.sex.toString() == "1" ? "selected='selected'":"" }>女</option>
		</select>
		<%-- <label class="am-radio-inline">
		    <input type="radio" name="sex" value="1" ${user.sex.toString() == "1" ? "checked='checked'":"" }> 男
		</label>
	  	<label class="am-radio-inline">
	    	<input type="radio" name="sex" value="0" ${user.sex.toString() == "0" ? "checked='checked'":"" }>女
	  	</label> --%>
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
      <input name="email" value="${user.email}" type="email" class="input-medium" id="doc-vld-email-2-1" placeholder="输入邮箱" />
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

    <button class="am-btn  am-btn-warning am-btn-block am-topbar-fixed-bottom am-round" type="submit"  onclick="return wxcheck('${user.wxid}');">提交</button>
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