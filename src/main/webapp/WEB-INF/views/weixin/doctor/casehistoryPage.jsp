<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>  	

<title>精彩病历列表</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="stylesheet"
	href="static/pullToRefresh/reset.css" />
<link rel="stylesheet"
	href="static/pullToRefresh/pullToRefresh.css" />
<script src="static/pullToRefresh/iscroll.js"></script>
<script src="static/pullToRefresh/pullToRefresh.js"></script>
<link rel="Stylesheet" type="text/css" href="static/css/common/alertDialog.css"/>
<link rel="stylesheet" type="text/css" href="static/css/doctor/casehistoryPage.css"/>
<style>
   /*  @media (max-width: 400px)
		.modal-dialog {
    	width: 100px;
    	margin: 30px auto;
	} */
	
	@media screen and (max-width: 600px) { /*当屏幕尺寸小于600px时，应用下面的CSS样式*/
	 	.modal-dialog {
	   		width: 250px;
	   		margin: 30px auto;
		}
	}
	
</style>

</head>

<!-- 模态框（Modal）下边单按钮 -->
<div class="modal fade" id="noDataAlertModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="noDataAlertModalContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
               	暂无更多数据
            </div>
            <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-12' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		             	<div id="noDataAlertModal-btn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">确定</div>
 		              </div>
				</form>
            </div>
        </div>
    </div>
</div>
<body>
<div id="maskAlert" class="container">
        <div class="col-xs-12 pos" style="position:fixed;top:30%;">
            <div class="body" style="background-image:url(static/img/bg.png);">
                <div class="head">
                    <span id="alertTitle" class="title">提示</span>
                    <a id="alertCloseBtn" href="javascript:void(0)" title="关闭窗口" class="close_btn" style="text-decoration: none">×</a>
                </div>
                <span id="alertContent" class="alertContent"></span>
            </div>
        </div>
</div>

<div class="container backGround">
    <div class="row searchGroup navbar-fixed-top" >
      <!--  <div class="col-xs-12" style="text-align:center;font-size:20px;">精彩病历列表
            <a href="doctor/doctorsPage" style="inline-block;right:20px;position:absolute;text-decoration:none;color:black;font-size:15px;top:5px;">专家列表</a>
        </div>-->
        <div class="container">
            <div class="row searchBody" >
                <div class="outer">
                    <div class="inner">
                       <input id="searchInput" style="color:#aaaaaa;"  placeholder="输入医生姓名搜索" >
                    </div>
                </div>
              <!--  <button style="height: 26px;position: absolute;right: 20px;;top:9px;">查询</button>-->
                <span id="searchBtn">查询</span>
            </div>
        </div>
    </div>


   


     <div id="content" class="content-wrap">
    
    
       <!-- 正在加载中 -->
		<div id="firstDataLoad" class="container" style="display: none">
	            <div class="col-xs-12 pos" style="position:fixed">
	                <div  style="display:inline-block;width: 200px;height: 100px;display:inline-block;position: relative;border-radius: 4px;">
	                    <div class="">
	                       <img src="static/img/refresh.gif" />
	                    </div>
	                    <span id="" class="" style="color:#999999">数据载入中，请稍后......</span>
	                </div>
	            </div>
	    </div>
	<!-- 正在加载中 -->

		<div id="page">
               
              <!-- <li>
                <div class="row">
                    <div class="col-xs-12 content-row-wrap">
                        <div class="content-inner" onclick="jump(this)">
                            <div class="caseTitle"><span>神经纤维瘤病的诊断标准及治疗是的发送到sdfsdfdsfsdf</span></div>
                            <div class="manage">主持：<span style="margin-right: 25px;">赵刚</span> 整理：<span>王一</span></div>
                            <div class="date"><span style="margin-right: 25px;">2016-03-10</span><span>李神经会诊中心</span></div>
                            <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                        </div>
                    </div>
                </div>
            </li> -->
		</div>
</div><!-- content结束 -->
     <!-- 尾部载中 -->
		<div id="moreLoading" class="container" style="display: none">
	            <div class="col-xs-12 pos2" style="">
	                <div  style="width: 100%;height: 44px;line-height:44px;display:inline-block;position: relative;	background:white;border-top:1px solid #dddddd">
	                    <div class="">
	                       <img src="static/img/refresh.gif" style="width: 21px;height: 21px" />
	                    	<span id="" class="" style="color:#999999">载入中...</span>
	                    </div>
	                </div>
	            </div>
	    </div>
	<!-- 正在加载中 -->
    
<input type="hidden" id="currentPage" value="1"/>
<input type="hidden" id="departmentId" value="0"/>
</div>

<script src="static/autohidingnavbar/jquery.bootstrap-autohidingnavbar.min.js"></script>
<script src="static/js/doctor/casehistoryPage.js"></script>
<script>
</script>
</body>
</html>