<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ include file="../common/common.jsp"%>  

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="static/css/patient/consultation.css"/>
<link rel="Stylesheet" type="text/css" href="static/css/common/alertDialog.css"/>

<title>我的会诊</title>

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
<div class="modal fade" id="noDataModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="noDataContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
               	暂无数据
            </div>
            <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-12' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		             	<div id="" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">确定</div>
 		              </div>
				</form>
            </div>
        </div>
    </div>
</div>



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


<div class="container backGround">
   <!-- <div class="row searchGroup navbar-fixed-top" >
      &lt;!&ndash;  <div class="col-xs-12" style="text-align:center;font-size:20px;">精彩病历列表
            <a href="doctor/doctorsPage" style="inline-block;right:20px;position:absolute;text-decoration:none;color:black;font-size:15px;top:5px;">专家列表</a>
        </div>&ndash;&gt;
        <div class="container">
            <div class="row searchBody" >
                <div class="outer">
                    <div class="inner">
                       <input id="searchInput"  placeholder="输入医生姓名搜索" >
                    </div>
                </div>
              &lt;!&ndash;  <button style="height: 26px;position: absolute;right: 20px;;top:9px;">查询</button>&ndash;&gt;
                <span id="searchBtn">查询</span>
            </div>
        </div>
    </div>-->


   
     <div id="content" class="content-wrap">
    
    
       <!-- 正在加载中 -->
		<div id="firstDataLoad" class="container" style="display: block">
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
               
              <!--  <li>
                <div class="row" >
                    <div class="col-xs-12 content-row-wrap" style="position: relative">
                        <div class="content-inner">
                            <div class="caseTitle"><span>2016-05-30 13:30</span></div>
                            <div style="margin-bottom: 2px"><span style="font-size: 15px;color: #666666">单号：</span><span class="manage" style="margin-right: 25px;">2016053000001</span></div>

                            <div style="height:21px;width:300px;overflow:hidden;margin-bottom:2px;font-size: 15px;color: #666666;"><span>主治：</span><span style="margin-right: 10px;">李晓东</span> <span >宝鸡市人民医院威尔而水电费收到方式</span></div>
                            <div style="height:21px;width:300px;overflow:hidden;font-size: 15px;color: #666666"><span>会诊：</span><span  style="margin-right: 10px;">赵刚</span><span>西京医院</span></div>
                            <div class="date"><span style="margin-right: 25px;">2016-03-10</span><span>李神经会诊中心</span></div>
                            <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                            <div style="position: absolute;right: 16px;top:39px;" ><span style="display:inline-block;width:60px;height: 30px;text-align:center;line-height:30px;background: #1fbaf3;border-radius:3px;font-size: 15px;color: #ffffff">支付</span></div>
                        </div>
                    </div>
                </div>
            </li>


            <li>
                <div class="row" >
                    <div class="col-xs-12 content-row-wrap" style="position: relative">
                        <div class="content-inner">
                            <div class="caseTitle"><span>2016-05-30 13:30</span></div>
                            <div style="margin-bottom: 2px"><span style="font-size: 15px;color: #666666">单号：</span><span class="manage" style="margin-right: 25px;">2016053000001</span></div>

                            <div style="height:21px;width:300px;overflow:hidden;margin-bottom:2px;font-size: 15px;color: #666666;"><span>主治：</span><span style="margin-right: 10px;">李晓东</span> <span >宝鸡市人民医院威尔而水电费收到方式</span></div>
                            <div style="height:21px;width:300px;overflow:hidden;font-size: 15px;color: #666666"><span>会诊：</span><span  style="margin-right: 10px;">赵刚</span><span>西京医院</span></div>
                            <div class="date"><span style="margin-right: 25px;">2016-03-10</span><span>李神经会诊中心</span></div>
                            <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                            <div style="position: absolute;right: 16px;top:39px;" ><span style="display:inline-block;width:60px;height: 30px;text-align:center;line-height:30px;background: #1fbaf3;border-radius:3px;font-size: 15px;color: #ffffff">支付</span></div>
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
	                    	<span id="moreLoadingAlert" class="" style="color:#999999">载入中...</span>
	                    </div>
	                </div>
	            </div>
	    </div>
	<!-- 正在加载中 -->
	
	
	<input id="allDataFinish" type='hidden' value="0">
	  <!-- 尾部载中 -->
		<div id="allDataFinishAlert" class="container" style="display: none">
	            <div class="col-xs-12 pos2" style="">
	                <div  style="width: 100%;height: 44px;line-height:44px;display:inline-block;position: relative;	background:white;border-top:1px solid #dddddd">
	                    <div class="">
	                    	<span id="allDataFinishAlertContent" class="" style="color:#999999">已经全部加载完毕</span>
	                    </div>
	                </div>
	            </div>
	    </div>
	<!-- 正在加载中 -->
	
<input type="hidden" id="payApiUrl" value="${payApiUrl}" />   
<input type="hidden" id="phoneNum" value="${phoneNum}" />   
<input type="hidden" id="currentPage" value="1"/>
<input type="hidden" id="departmentId" value="0"/>
</div>

<script src="static/js/patient/consultation.js"></script>
<script>
</script>
</body>
</html>