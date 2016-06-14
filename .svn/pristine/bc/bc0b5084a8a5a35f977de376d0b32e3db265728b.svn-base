<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>  

<title>会诊消息列表</title>
<link rel="stylesheet"
	href="static/pullToRefresh/reset.css" />
<link rel="stylesheet"
	href="static/pullToRefresh/pullToRefresh.css" />
<script src="static/pullToRefresh/iscroll.js"></script>
<script src="static/pullToRefresh/pullToRefresh.js"></script>
    
<link rel="Stylesheet" type="text/css" href="static/css/common/alertDialog.css"/>
    
</head>
<body >
  <div id="maskAlert" class="container">
     <div class="col-xs-12 pos">
         <div class="body" style="background-image:url(static/img/bg.png)">
             <div class="head">
                 <span id="alertTitle" class="title">提示</span>
                 <a id="alertCloseBtn" href="javascript:void(0)" title="关闭窗口" class="close_btn" style="text-decoration: none">×</a>
             </div>
             <span id="alertContent" class="alertContent">获取医生列表失败</span>
         </div>
     </div>
  </div>

<div class="container">
    <div class="row navbar-fixed-top" style="margin-bottom:10px;border-radius:3px;background-color: #ffffff">
        <div class="col-xs-12" style="text-align:center;font-size:20px;">会诊消息列表
         <a href="doctor/doctorsPage" style="inline-block;right:20px;position:absolute;text-decoration:none;color:black;font-size:15px;top:5px;">专家列表</a>
        </div>
    </div>
 
 
   	<div id="content" class="content" style="margin-top: 40px">
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
	  	 <input type="hidden" id="phoneNum" value="${phoneNum}"/>
	  	 <input type="hidden" id="pageIndex" value=""/>
	  	
	  	 <div id="wrapper" style="left: 0; margin-left: 0;background-color: rgba(6, 5, 6, 0.20)">
	 		 <ul id="page">
	        </ul> 
	 	</div>
    </div>
</div>
</body>
<script src="static/js/sms/smsPage.js"></script>
</html>