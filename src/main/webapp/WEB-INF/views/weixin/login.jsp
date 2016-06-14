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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head lang="en">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
<link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css"/>
</head>
<body>
<header data-am-widget="header" class="am-header am-header-default">
    <div class="am-header-left am-header-nav">
        <a href="#left-link" class="">
              <i class="am-header-icon am-icon-home"></i>
        </a>
        <a href="#phone-link" class="">
              <i class=""></i>
        </a>
    </div>
    <h1 class="am-header-title">登录</h1>
    <div class="am-header-right am-header-nav">
        <a href="#cart-link" class="">
              <i class=""></i>
        </a>
        <a href="#user-link" class="">
              <i class="am-header-icon am-icon-user"></i>
        </a>
    </div>
</header>
<div class="header">
  <div class="am-g">
    <figure data-am-widget="figure" class="am am-figure am-figure-default "   data-am-figure="{  pureview: 'true' }">
      <img src="<%=basePath%>images/logo.png"/>
  </figure>
  </div>
  <hr/>
</div>
<div class="am-g">
  <div class="am-u-md-8 am-u-sm-centered">
    <form class="am-form">
		<fieldset class="am-form-set">
		</fieldset>
	    
	    <div class="am-form-group">
		    <div class="am-input-group">
			  <span class="am-input-group-label"><i class="am-icon-user am-icon-fw"></i></span>
			  <input type="text" class="am-form-field" placeholder="用户名">
			</div>
		</div>

		<div class="am-form-group">
			<div class="am-input-group">
			  <span class="am-input-group-label"><i class="am-icon-lock am-icon-fw"></i></span>
			  <input type="text" class="am-form-field" placeholder="密码">
			</div>
		</div>

	    <div class="am-form-group">
	    	<button type="submit" class="am-btn am-btn-primary am-btn-block">登录</button>
  		</div>
    </form>
    
    <div class="am-form-group">
   		<a href="./verifycode" class="am-btn am-btn-primary am-btn-block" role="button">注册新用户</a>
	</div>
	
	<div class="am-form-group">
		<a href="./about" class="am-btn am-btn-primary am-btn-block" role="button">关于</a>
    </div>
  </div>
</div>
<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
<footer data-am-widget="footer" class="am-footer am-footer-default" data-am-footer="{  }">
	<div class="am-footer-miscs ">
	    <p>由 <a href="http://www.yunshipei.com/" title="诺亚方舟" target="_blank" class="">诺亚方舟</a>提供技术支持</p>
	    <p>CopyRight©2014  AllMobilize Inc.</p>
	    <p>京ICP备13033158</p>
	</div>
</footer>
</body>
</html>