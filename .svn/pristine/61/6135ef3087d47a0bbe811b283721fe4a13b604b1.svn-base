<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + path + "/";
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>微信支付</title>
<link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>assets/css/app.css">
<script type="text/javascript" src="<%=basePath%>js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/example.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/vendor/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>assets/js/amazeui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jweixin-1.0.0.js"></script>
<style>
	html {
		background: url(<%=basePath%>img/normal.jpg) repeat;
		-webkit-background-size: cover;
		-moz-background-size: cover;
		-o-background-size: cover;
		background-size: 100% 100%;
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
	<input type="hidden" id="orderId" value="${orderId }" />
	<input type="hidden" id="appId" value="${appId }" />
	<input type="hidden" id="title" value="${title }" />
	<div class="am-g am-g-fixed am-navbar">
		<div class="am-u-sm-6">
			<a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appId}&redirect_uri=<%=basePath%>weixin/pay/${orderId }&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect'
				class="am-btn am-btn-block am-round button-top" style="width: 100%"
				role="button">微信支付</a>
		</div>
		<div class="am-u-sm-6">
			<a href="#" onclick="alert('点击右上角“发送给朋友”，让朋友代付')"
				class="am-btn am-btn-warning am-btn-block am-round button-bottom" style="width: 100%"
				role="button">找人代付</a>
		</div>
	</div>
</body>

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
			link : 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=' + $("#appId").val() + '&redirect_uri=<%=basePath%>weixin/pay/'
									+ $("#orderId").val()
									+ '&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect',
							imgUrl : '',
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
</html>