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
  <title>个人中心</title>

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
  <link rel="stylesheet" href="<%=basePath%>style/weui.css"/>
  <link rel="stylesheet" href="<%=basePath%>style/example.css"/>
<%--   <link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css">
  <link rel="stylesheet" href="<%=basePath%>assets/css/app.css"> --%>
  
<script type="text/javascript" src="<%=basePath%>js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/example.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.swipebox.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.fitvids.js"></script>
<script type="text/javascript" src="<%=basePath%>js/email.js"></script>
<script type="text/javascript" src="<%=basePath%>js/vendor/bootstrap.min.js"></script>

  <script type="text/javascript">
  $(function() {
		var Accordion = function(el, multiple) {
			this.el = el || {};
			this.multiple = multiple || false;

			// Variables privadas
			var links = this.el.find('.link');
			// Evento
			links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
		}

		Accordion.prototype.dropdown = function(e) {
			var $el = e.data.el;
				$this = $(this),
				$next = $this.next();

			$next.slideToggle();
			$this.parent().toggleClass('open');

			if (!e.data.multiple) {
				$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
			};
		}	

		var accordion = new Accordion($('#accordion'), false);
	});
  </script>
  
  <style>
        

    </style>
  
  <style>
	.am-header-fixed {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		width: 100%;
		z-index: 1010;
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
	
	 .submenu {
	 	display: none;
	 	/* background: #444359; 
	 	font-size: 14px;*/
	 }

  </style>
</head>

<body>
<div class="weui_cells_title"></div>
<div class="user-img">
    <img src="<%=wxurl%>">
</div>
<div class="weixin-number">
<a><%=nickName%></a>
</div>
<c:if test="${viptime > 0}">
	<div class="weixin-number">
		<a>离到期时间还有${viptime}天</a>
	</div>
</c:if>
<div class="weui_cells_title"></div>
<div class="weui_cells weui_cells_access">
<a class="weui_cell" href="<%=basePath%>weixin/historylist/<%=wxid%>">
    <div class="weui_cell_hd"><img src="" alt="" style="width:20px;margin-right:5px;display:block"></div>
    <div class="weui_cell_bd weui_cell_primary">
        <p>已经学习的课程</p>
    </div>
    <div class="weui_cell_ft"></div>
</a>
<a class="weui_cell" href="<%=basePath%>update/register/<%=wxid%>">
    <div class="weui_cell_hd"><img src="" alt="" style="width:20px;margin-right:5px;display:block"></div>
    <div class="weui_cell_bd weui_cell_primary">
        <p>修改资料</p>
    </div>
    <div class="weui_cell_ft"></div>
</a>
<a class="weui_cell" href="http://www.xslp.cn">
    <div class="weui_cell_hd"><img src="" alt="" style="width:20px;margin-right:5px;display:block"></div>
    <div class="weui_cell_bd weui_cell_primary">
        <p>我的工具</p>
    </div>
    <div class="weui_cell_ft"></div>
</a>
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