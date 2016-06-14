<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ include file="../common/common.jsp"%>  	
<title>医生列表</title>

<link rel="stylesheet" href="static/bootstrap-submenu-2.0.4-dist/css/bootstrap-submenu.min.css">
<link rel="stylesheet" href="static/pullToRefresh/reset.css" />
<link rel="stylesheet" href="static/pullToRefresh/pullToRefresh.css" />
<link rel="Stylesheet" type="text/css" href="static/css/common/alertDialog.css"/>
<link rel="Stylesheet" type="text/css" href="static/css/doctor/doctorsPage.css"/>
</head>
<body >
	<input type="hidden" id="curPage">	
	
	<div class="container top" style="background: #EEEEEE;">
		<!-- 下拉列表開始 -->
		<div class="row navbar-fixed-top" style="background: #ffffff;"><!-- navbar-fixed-top此属性导致fixed 下拉列表多余的出不来 -->
			<div class="col-xs-4 topNavBar"
				style="border-right: 1px solid darkgray">
				<div class="btn-group">
					<ul class="nav nav-pills">
						<li class="dropdown" style="height: 32px; line-height: 32px;">
							<a class="province" tabindex="0" data-toggle="dropdown"
							data-submenu="" aria-expanded="false"
							style="height: 32px; padding-top: 0; padding-bottom: 0; padding-left: 10px; padding-right: 10px; color: #000000">
								西安市 <span class="caret"></span>
						</a>
							<ul class="dropdown-menu" id="provinceChild" style="height:300px;overflow:scroll;">
							</ul>
						</li>
					</ul>
				</div>
			</div>

			<div class="col-xs-4 topNavBar"
				style="border-right: 1px solid darkgray">
					<div class="btn-group">
					<button  type="button"
						class="btn btn-default dropdown-toggle" data-toggle="dropdown"
						style="border: none;height:32px;line-height:32px;padding:0 0;top:5px;" id="" >
						<span id="hospital" data-flag="hospitals" style="display:inline-block;width:60px;text-overflow:ellipsis;">全部医院</span>
					</button>
					 <span class="caret" style=""></span>
					
					<ul class="dropdown-menu" role="menu" data-flag="hospitalChild" style="height:300px;overflow:scroll;position:fixed;top:40px;left:4%;">
						<li><a  id='-1'>全部医院</a></li>
						<li class='divider'></li>
					</ul>
				</div>
			</div>

			<div class="col-xs-4 topNavBar">
					<div class="btn-group">
					<button  id="" type="button"
						class="btn btn-default dropdown-toggle" data-toggle="dropdown"
						style="border: none;height:32px;line-height:32px;padding:0 0;top:5px;" id="dept">
						<span data-flag='deparments' style="display:inline-block;margin:0;padding:0;width:60px;text-overflow:ellipsis;">全部科室</span> 
						
					</button>
					<span class="caret"></span>
					
					<ul class="dropdown-menu" role="menu" data-flag="deparmentChild"
						id="" style="height:300px;overflow:scroll;position:fixed;top:40px;left:4%;">
						<li><a id='-1'>全部科室</a></li>
						<li class='divider'></li>
					</ul>
				</div>
			</div>
		</div>

		<!-- 医生列表内容-->
		<div class="content" style="margin-top:45px;">
			<form class="form-horizontal" id="doctors" style="">
				<!-- <ul id="doctors">
				</ul> -->
			</form>
		</div>
	</div>
    <div id="maskAlert" class="container">
            <div class="col-xs-12 pos" style="position:fixed">
                <div class="body" id="maskAlert" style="display:inline-block;background-image:url(static/img/bg.png)">
                    <div class="head">
                        <span id="alertTitle" class="title">提示</span>
                        <a id="alertCloseBtn" href="javascript:void(0)" title="关闭窗口" class="close_btn" style="text-decoration: none">×</a>
                    </div>
                    <span id="alertContent" class="alertContent">获取医生列表失败</span>
                </div>
            </div>
    </div>

<!-- 正在加载中 -->
<div id="dataLoad" class="container" style="display: none">
            <div class="col-xs-12 pos" style="position:fixed">
                <div  style="display:inline-block;width: 200px;height: 100px;display:inline-block;position: relative;border-radius: 4px;">
                    <div class="">
                       <img
						src="static/img/loading.gif"
						style="background-color: #333" />
                    </div>
                    <span id="" class="" style="color:red">数据载入中，请稍后......</span>
                </div>
            </div>
    </div>
<!-- 正在加载中 -->

	
</body>
<script src="static/pullToRefresh/iscroll.js"></script>
<script src="static/pullToRefresh/pullToRefresh.js"></script>
<script src="static/autohidingnavbar/jquery.bootstrap-autohidingnavbar.min.js"></script>
<script
src="static/bootstrap-submenu-2.0.4-dist/js/bootstrap-submenu.min.js"
		defer></script>
<script src="static/js/doctor/doctorsPage.js"></script>
</html>