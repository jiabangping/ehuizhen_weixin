<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ include file="../common/common.jsp"%>  	
<title>待审核</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="static/bootstrap-submenu-2.0.4-dist/css/bootstrap-submenu.min.css">
<link rel="stylesheet" href="static/pullToRefresh/reset.css" />
<link rel="stylesheet" href="static/pullToRefresh/pullToRefresh.css" />
<link rel="Stylesheet" type="text/css" href="static/css/common/alertDialog.css"/>
<script src="static/pullToRefresh/iscroll.js"></script>
<script src="static/pullToRefresh/pullToRefresh.js"></script>

<script  src="static/js/cookie/cookieCommonOperate.js"></script>

<style>
.content {
	font-size: 13px;
}

.topNavBar {
	text-align: center;
	height: 42px;
	line-height: 42px;
	border-top: 2px solid darkgray;
	border-bottom: 1px solid darkgray;
}

</style>
<script>
		
</script>
</head>
<body><!-- style="background:darkgray -->
	<input type="hidden" id="curPage">	
	<div class="container top">

   <!--  <div id="maskAlert" class="container">
            <div class="col-xs-12 pos" style="position:fixed">
                <div class="body" id="maskAlert" style="width:330px;height:104px;display:inline-block;background-image:url(static/img/bg.png)">
                    <div class="head">
                        <span id="alertTitle" class="title">提示</span>
                        <a id="alertCloseBtn" href="javascript:void(0)" title="关闭窗口" class="close_btn" style="text-decoration: none">×</a>
                    </div>
                    <span id="alertContent" class="alertContent" style="font-size:13px;"><span style="font-size:15px;color:red" id="doctorName"></span>您好,您的资料提交成功,等待审核中。</span>
                </div>
            </div>
    </div> -->
    
    <!-- 模态框（Modal） -->
<div class="modal fade" id="myModal2" tabindex=-1"" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button id="closeBtn" type="button" class="close" data-dismiss="modal" aria-hidden="false">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	提示
                </h4>
            </div>
            <div class="modal-body" style="font-size: 15px;text-align: center">
               	您好,您的资料提交成功,等待审核中。
            </div>
            <div class="modal-footer" >
             <!--   <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>-->
                <button id="confirmBtn" type="button"  data-dismiss="modal" class="btn btn-primary">
                    	确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
    

<!-- 提交中 -->
	<div id="dataLoding" class="container" style="display: none">
            <div class="col-xs-12 pos" style="position:fixed">
                <div  style="display:inline-block;width: 200px;height: 100px;display:inline-block;position: relative;border-radius: 4px;padding-top: 21px;background: white;">
                    <div class="">
                       <img src="static/img/refresh.gif" />
                    </div>
                    <span id="" class="" style="color:red">加载中......</span>
                </div>
            </div>
    </div>
<!-- 正在加载中 -->

	<script src="static/autohidingnavbar/jquery.bootstrap-autohidingnavbar.min.js"></script>
	<script
		src="static/bootstrap-submenu-2.0.4-dist/js/bootstrap-submenu.min.js"
		defer></script>
	<script src="static/js/doctor/waitAudit.js"></script>
	<script>
	$("#confirmBtn").click(function() {
		WeixinJSBridge.call('closeWindow');	
	})
	$("#closeBtn").click(function() {
		WeixinJSBridge.call('closeWindow');
	});
	</script>
</body>
</html>