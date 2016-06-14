<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>支付确认</title>
<link rel="stylesheet" href="<%=basePath%>style/weui.css">
<link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>assets/css/app.css">
<script type="text/javascript"
	src="<%=basePath%>js/vendor/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jweixin-1.0.0.js"></script>
<style>
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
</style>
</head>
<body>
	<input type="hidden" id="appid" value="${appid}" />
	<input type="hidden" id="timeStamp" value="${timeStamp}" />
	<input type="hidden" id="nonceStr" value="${nonceStr}" />
	<input type="hidden" id="prepay_id" value="${prepay_id}" />
	<input type="hidden" id="signType" value="${signType}" />
	<input type="hidden" id="paySign" value="${paySign}" />
	<input type="hidden" id="courseId" value="${courseId}" />
	

	<div class="am-text-center top">
		<p class="am-kai">${title }</p>
		<p class="am-text-warning am-text-xxxl">￥${amt }</p>
	</div>
	<hr data-am-widget="divider" style=""
		class="am-divider am-divider-default" />
	<div class="am-g am-g-fixed">
		<div class="am-fl am-padding-left am-text-success am-text-sm">收款方</div>
		<div class="am-fr am-padding-right am-kai">医药培训圈微课堂</div>
	</div>
	<div class="am-g am-g-fixed top">
		<div class="am-fl am-padding-left am-text-success am-text-sm">商&nbsp;品</div>
		<div class="am-fr am-padding-right am-kai">${title }</div>
	</div>
	<hr data-am-widget="divider" style=""
		class="am-divider am-divider-default" />
	<div class="am-form-group weixin-number">
		<a id="btnPay" href="#" class="am-btn am-btn-block am-round button-top"
			role="button">${btnText }</a>
	</div>

	<div class="weui_dialog_alert" id="dialog2" style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog">
			<div class="weui_dialog_hd">
				<strong class="weui_dialog_title">支付结果</strong>
			</div>
			<div class="weui_dialog_bd" id="msg"></div>
			<div class="weui_dialog_ft">
				<a href="#" class="weui_btn_dialog primary">确定</a>
			</div>
		</div>
	</div>
</body>
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

	wx.ready(function() {
		wx.hideAllNonBaseMenuItem();
		$("#btnPay").click(function() {
			wx.chooseWXPay({
				timestamp : $("#timeStamp").val(),
				nonceStr : $("#nonceStr").val(),
				package : 'prepay_id=' + $("#prepay_id").val(),
				signType : $("#signType").val(),
				paySign : $("#paySign").val(),
				success : function(errMsg) {
					$("#msg").html("支付成功，请返回课程列表进行学习");
					var $dialog = $('#dialog2');
					$dialog.show();
					$dialog.find('.weui_btn_dialog').on('click',function() {
						var courseId = $("#courseId").val();
						if(courseId == -1){
							wx.closeWindow();
						}else{
							window.location.href="<%=basePath%>weixin/video/"+courseId;
						}
					}); 
				},
				cancel : function(errMsg) {
					$("#msg").html("您取消了支付");
					var $dialog = $('#dialog2');
					$dialog.show();
					$dialog.find('.weui_btn_dialog').on('click',function() {
						$dialog.hide();
					}); 
					
				},
				fail : function(errMsg) {
					$("#msg").html("支付失败");
					var $dialog = $('#dialog2');
					$dialog.show();
					$dialog.find('.weui_btn_dialog').on('click',function() {
						$dialog.hide();
					}); 
				}
			});
		});
	});
</script>
</html>