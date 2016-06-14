<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>  	

<title>精彩病历列表</title>
<link rel="stylesheet"
	href="static/pullToRefresh/reset.css" />
<link rel="stylesheet"
	href="static/pullToRefresh/pullToRefresh.css" />
<script src="static/pullToRefresh/iscroll.js"></script>
<script src="static/pullToRefresh/pullToRefresh.js"></script>
     
<link rel="Stylesheet" type="text/css" href="static/css/common/alertDialog.css"/>
   
    <style>
        .test {
            font-size: 13px;
        }
        .top {
            margin-top: 20px;;
        }
    </style>
</head>
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

<div class="container">
    <div class="row navbar-fixed-top" style="border-radius:3px;background-color: #ffffff;margin-bottom: 0;border-bottom: 1px solid darkgrey">
        <div class="col-xs-12" style="text-align:center;font-size:20px;">精彩病历列表
            <a href="doctor/doctorsPage" style="inline-block;right:20px;position:absolute;text-decoration:none;color:black;font-size:15px;top:5px;">专家列表</a>
        </div>
      <div class="container">
	        <div class="form-group">
	            <div class="col-xs-8">
	                <input type="text" class="form-control" id="" placeholder="">
	            </div>
	            <button  type="button" class="col-xs-3 btn btn-default">查询</button>
	        </div>
    </div>
    </div>

    <div class="row " style="border-radius:3px;background-color: #ffffff;margin-bottom: 0;top: 29px;border-bottom: 1px solid darkgrey">
    </div>


    <div id="content" class="content" style="margin-top: 62px">
    
    
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


		<ul id="page">
               <li>
                   <div class="row"  style="border-bottom: 1px solid darkgray">
                       <div class="col-xs-12" style="margin-bottom: 5px;padding-left: 5px;padding-right: 5px;">
                           <div onclick="jump(this)" style="border: 1px solid white;border-radius:10px;display: block;width:90%;margin:0 auto;background-color: #ffffff;font-size: 16px;   word-wrap:break-word;word-break:normal;overflow:hidden">
                               <div><span style="font-size: 20px;font-weight: bold">神经纤维瘤病的诊断标准及治疗</span></div>
                               <div>主持：<span>赵刚</span> <span>西京医院</span> 整理：<span>王一</span></div>
                               <div><span>2016-03-10</span></div>
                               <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                           </div>

                       </div>
                   </div>
               </li>

              <li>
                  <div class="row"  style="border-bottom: 1px solid darkgray">
                      <div class="col-xs-12" style="margin-bottom: 5px;padding-left: 5px;padding-right: 5px;">
                          <div onclick="jump(this)" style="border: 1px solid white;border-radius:10px;display: block;width:90%;margin:0 auto;background-color: #ffffff;font-size: 16px;   word-wrap:break-word;word-break:normal;overflow:hidden">
                              <div><span style="font-size: 20px;font-weight: bold">神经纤维瘤病的诊断标准及治疗</span></div>
                              <div>主持：<span>赵刚</span> <span>西京医院</span> 整理：<span>王一</span></div>
                              <div><span>2016-03-10</span></div>
                              <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                          </div>

                      </div>
                  </div>
              </li>  
              
               <li>
                   <div class="row"  style="border-bottom: 1px solid darkgray">
                       <div class="col-xs-12" style="margin-bottom: 5px;padding-left: 5px;padding-right: 5px;">
                           <div onclick="jump(this)" style="border: 1px solid white;border-radius:10px;display: block;width:90%;margin:0 auto;background-color: #ffffff;font-size: 16px;   word-wrap:break-word;word-break:normal;overflow:hidden">
                               <div><span style="font-size: 20px;font-weight: bold">神经纤维瘤病的诊断标准及治疗</span></div>
                               <div>主持：<span>赵刚</span> <span>西京医院</span> 整理：<span>王一</span></div>
                               <div><span>2016-03-10</span></div>
                               <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                           </div>

                       </div>
                   </div>
               </li>

              <li>
                  <div class="row"  style="border-bottom: 1px solid darkgray">
                      <div class="col-xs-12" style="margin-bottom: 5px;padding-left: 5px;padding-right: 5px;">
                          <div onclick="jump(this)" style="border: 1px solid white;border-radius:10px;display: block;width:90%;margin:0 auto;background-color: #ffffff;font-size: 16px;   word-wrap:break-word;word-break:normal;overflow:hidden">
                              <div><span style="font-size: 20px;font-weight: bold">神经纤维瘤病的诊断标准及治疗</span></div>
                              <div>主持：<span>赵刚</span> <span>西京医院</span> 整理：<span>王一</span></div>
                              <div><span>2016-03-10</span></div>
                              <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                          </div>

                      </div>
                  </div>
              </li>  
              <li>
                   <div class="row"  style="border-bottom: 1px solid darkgray">
                       <div class="col-xs-12" style="margin-bottom: 5px;padding-left: 5px;padding-right: 5px;">
                           <div onclick="jump(this)" style="border: 1px solid white;border-radius:10px;display: block;width:90%;margin:0 auto;background-color: #ffffff;font-size: 16px;   word-wrap:break-word;word-break:normal;overflow:hidden">
                               <div><span style="font-size: 20px;font-weight: bold">神经纤维瘤病的诊断标准及治疗</span></div>
                               <div>主持：<span>赵刚</span> <span>西京医院</span> 整理：<span>王一</span></div>
                               <div><span>2016-03-10</span></div>
                               <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                           </div>

                       </div>
                   </div>
               </li>

              <li>
                  <div class="row"  style="border-bottom: 1px solid darkgray">
                      <div class="col-xs-12" style="margin-bottom: 5px;padding-left: 5px;padding-right: 5px;">
                          <div onclick="jump(this)" style="border: 1px solid white;border-radius:10px;display: block;width:90%;margin:0 auto;background-color: #ffffff;font-size: 16px;   word-wrap:break-word;word-break:normal;overflow:hidden">
                              <div><span style="font-size: 20px;font-weight: bold">神经纤维瘤病的诊断标准及治疗</span></div>
                              <div>主持：<span>赵刚</span> <span>西京医院</span> 整理：<span>王一</span></div>
                              <div><span>2016-03-10</span></div>
                              <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                          </div>

                      </div>
                  </div>
              </li>  
              
               <li>
                   <div class="row"  style="border-bottom: 1px solid darkgray">
                       <div class="col-xs-12" style="margin-bottom: 5px;padding-left: 5px;padding-right: 5px;">
                           <div onclick="jump(this)" style="border: 1px solid white;border-radius:10px;display: block;width:90%;margin:0 auto;background-color: #ffffff;font-size: 16px;   word-wrap:break-word;word-break:normal;overflow:hidden">
                               <div><span style="font-size: 20px;font-weight: bold">神经纤维瘤病的诊断标准及治疗</span></div>
                               <div>主持：<span>赵刚</span> <span>西京医院</span> 整理：<span>王一</span></div>
                               <div><span>2016-03-10</span></div>
                               <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                           </div>

                       </div>
                   </div>
               </li>

              <li>
                  <div class="row"  style="border-bottom: 1px solid darkgray">
                      <div class="col-xs-12" style="margin-bottom: 5px;padding-left: 5px;padding-right: 5px;">
                          <div onclick="jump(this)" style="border: 1px solid white;border-radius:10px;display: block;width:90%;margin:0 auto;background-color: #ffffff;font-size: 16px;   word-wrap:break-word;word-break:normal;overflow:hidden">
                              <div><span style="font-size: 20px;font-weight: bold">神经纤维瘤病的诊断标准及治疗</span></div>
                              <div>主持：<span>赵刚</span> <span>西京医院</span> 整理：<span>王一</span></div>
                              <div><span>2016-03-10</span></div>
                              <input type="hidden" id="caseUrl" value="http://192.168.5.100:9998/upload/post/1457056641201.html">
                          </div>

                      </div>
                  </div>
              </li>  
	</ul>

    </div><!-- content结束 -->
<input type="hidden" id="currentPage" value="1"/>
<input type="hidden" id="departmentId" value="0"/>
</div>

<script src="static/autohidingnavbar/jquery.bootstrap-autohidingnavbar.min.js"></script>
<script src="static/js/doctor/casehistoryPage.js"></script>
<script>
</script>
</body>
</html>