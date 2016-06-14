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
  <title>团队报名</title>

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

  <link rel="stylesheet" href="<%=basePath%>style/weui.css">
  <link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css">
  <link rel="stylesheet" href="<%=basePath%>assets/css/app.css">
  
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
  
  $(document).ready(function(){
	//获得文本框对象
	   var t = $("#text_box");
	//初始化数量为1,并失效减
	$('#min').attr('disabled',true);
	    //数量增加操作
	    $("#add").click(function(){
	        t.val(parseInt(t.val())+1)
	        if (parseInt(t.val())!=5){
	            $('#min').attr('disabled',false);
	        }
	        if (parseInt(t.val())>=100){
	            $('#add').attr('disabled',true);
	        }
	        $("#total").text("¥ "+(parseInt(t.val())*1999).toFixed(2));
	        document.getElementById("doc-vld-amt-2-1").value = (parseInt(t.val())*1999).toFixed(2);
	    })
	    //数量减少操作
	    $("#min").click(function(){
	        t.val(parseInt(t.val())-1);
	        if (parseInt(t.val())==5){
	            $('#min').attr('disabled',true);
	        }
	        if (parseInt(t.val())<100){
	            $('#add').attr('disabled',false);
	        }
	        $("#total").text("¥ "+(parseInt(t.val())*1999).toFixed(2));
	        document.getElementById("doc-vld-amt-2-1").value = (parseInt(t.val())*1999).toFixed(2);
	    })
	});
  
  function ation(){
	//向后台发送处理数据
	$.ajax({
		url : '<%=basePath%>/weixin/team/order',
		type : 'post',
		cache : false,
		dataType : 'json',
		data : {
		      'amt' : $("#doc-vld-amt-2-1").val(),
		      'userId' :$("#doc-vld-userId-2-1").val()
		     },
		success : function(data) {
			if (data.flag == "true") {
				$("#msg").html("订单已提交成功");
				var $dialog = $('#dialog2');
				$dialog.show();
				$dialog.find('.weui_btn_dialog').on('click',function() {
					wx.closeWindow();
				});
			} else {
				$("#msg").html("订单提交失败");
				var $dialog = $('#dialog2');
				$dialog.show();
				$dialog.find('.weui_btn_dialog').on('click',function() {
					$dialog.hide();
				});
			}
		},
		error : function(data) {
			$("#msg").html("订单提交失败");
			var $dialog = $('#dialog2');
			$dialog.show();
			$dialog.find('.weui_btn_dialog').on('click',function() {
				$dialog.hide();
			}); 
		}
	});
  }
  
  </script>
  
  <style>
	.top{
		margin-top: 10px;
	}
	input.text{
		text-align:center;
	}
	.button-top {
		border:1px solid #AAAAAA;
		background:#EEEE00;
	};
  </style>
</head>
<body>
<div class="am-text-center top">
<p class="am-kai">百万销售成长营VIP会员费</p>
<p id="total" class="am-text-warning am-text-xxxl">¥ 9995.00</p>
</div>
<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
<div class="am-g am-g-fixed">
  <div class="am-fl am-padding-left am-text-success am-text-sm">收款方</div>
  <div class="am-fr am-padding-right am-kai">百万销售成长营</div>
</div>
<div class="am-g am-g-fixed top">
  <div class="am-fl am-padding-left am-text-success am-text-sm">商品</div>
  <div class="am-fr am-padding-right am-kai">百万销售成长营VIP会员</div>
</div>
<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
<div class="am-g am-g-fixed">
  <div class="am-fl am-padding-left am-text-success am-text-sm">单价</div>
  <div class="am-fr am-padding-right">¥ 1999.00</div>
</div>
<div class="am-g am-g-fixed top">
  <div class="am-fl am-padding-left am-text-success am-text-sm">数量</div>
  <div class="am-fr am-padding-right">
	<input id="min" name="" type="button" value="-" style="width:30px;" />  
	<input id="text_box" class="text" name="" type="text" value="5" style="width:50px;" disabled="value"/>  
	<input id="add" name="" type="button" value="+" style="width:30px;" /> 
  </div>
</div>
<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
<div class="am-form-group">
     <input name="userId" value="${userId}" type="hidden" class="input-medium" id="doc-vld-userId-2-1" />
     <input name="amt" value="${amt}" type="hidden" class="input-medium" id="doc-vld-amt-2-1" />
</div>
<div class="am-form-group">
   		<button class="am-btn am-btn-block am-round button-top" type="button" onclick="ation();">提交订单</button>
</div>

<div class="weui_dialog_alert" id="dialog2" style="display: none;">
	<div class="weui_mask"></div>
	<div class="weui_dialog">
		<div class="weui_dialog_hd">
			<strong class="weui_dialog_title">订单结果</strong>
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