<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String nickName = (String)session.getAttribute("nickName");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"">
<title>精彩病历列表</title>
<link rel="stylesheet"
	href="<%=basePath %>/static/pullToRefresh/reset.css" />
<link rel="stylesheet"
	href="<%=basePath %>/static/pullToRefresh/pullToRefresh.css" />
<script src="<%=basePath %>/static/pullToRefresh/iscroll.js"></script>
<script src="<%=basePath %>/static/pullToRefresh/pullToRefresh.js"></script>
     <!-- Bootstrap -->
    <link href="<%=basePath %>/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
 <link rel="Stylesheet" type="text/css" href="<%=basePath %>/static/css/common/alertDialog.css"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
</head>
<body style="">
<!--<div id='maskAlert' class="maskAlert"> 遮罩 -->
    <div id="maskAlert" class="container">
            <div class="col-xs-12 pos">
                <div class="body" style="background-image:url(<%=basePath %>/static/img/bg.png)">
                    <div class="head">
                        <span id="alertTitle" class="title">提示</span>
                        <a id="alertCloseBtn" href="javascript:void(0)" title="关闭窗口" class="close_btn" style="text-decoration: none">×</a>
                    </div>
                    <span id="alertContent" class="alertContent">获取医生列表失败</span>
                </div>
            </div>
    </div>
<!--</div>-->

<div class="container">
    <!--<div class="row">
        <div class="col-xs-3" style="border: 1px solid #ffff67">患者姓名</div>
        <div class="col-xs-9" style="border: 1px solid #3dc0ff"><input style="width: 100%;"></div>
    </div>-->
    <div class="row navbar-fixed-top" style="margin-bottom:10px;border-radius:3px;background-color: #ffffff">
        <div class="col-xs-12" style="text-align:center;font-size:20px;">会诊消息列表
         <a href="<%=basePath %>doctor/doctorPage" style="inline-block;right:20px;position:absolute;text-decoration:none;color:black;font-size:15px;top:5px;">专家列表</a>
 <!-- <a id="titleWarn" style="inline-block;left:25px;position:absolute;text-decoration:none;color:black;font-size:15px;top:12px;">提示框</a> -->  
        </div>
    </div>
 
 
   <div id="content" class="content" style="margin-top: 40px">
   <%--遮罩层--%>
		<%-- <div id="dataLoad"
			style="z-index: 9999; position: fixed; left: 30%; top: 20%; display: none">
			<!--页面载入显示-->
			<table width=100% height=100% border=0 align=center valign=middle>
				<tr height=50%>
					<td align=center>&nbsp;</td>
				</tr>
				<tr>
					<td align=center><img
						src="<%=basePath %>/static/img/loading.gif"
						style="background-color: #333" /></td>
				</tr>
				<tr>
					<td align="center" style="color:red">数据载入中，请稍后......</td>
				</tr>
				<tr height=50%>
					<td align=center>&nbsp;</td>
				</tr>
			</table>
		</div> --%>
	<!-- 正在加载中 -->
	<div id="dataLoad" class="container" style="display: none">
	            <div class="col-xs-12 pos" style="position:fixed">
	                <div  style="display:inline-block;width: 200px;height: 100px;display:inline-block;position: relative;border-radius: 4px;">
	                    <div class="">
	                       <img
							src="<%=basePath %>/static/img/loading.gif"
							style="background-color: #333" />
	                    </div>
	                    <span id="" class="" style="color:red">数据载入中，请稍后......</span>
	                </div>
	            </div>
	    </div>
	<!-- 正在加载中 -->
	
   	
  	 <!-- <span id="warn" style="color:red;font-size:13px;margin-bottom:0px;display:block;"></span> -->
  	 <input type="hidden" id="phoneNum" value="${phoneNum}"/>
  	 <input type="hidden" id="pageIndex" value=""/>
  	
  	 <div id="wrapper" style="left: 0; margin-left: 0;background-color: rgba(6, 5, 6, 0.20)">
 		 <ul id="page">
            <!--  <li>
            	<div class="row">
        			<div class="col-xs-12" style="text-align: center;margin-bottom: 15px;padding-left: 5px;padding-right: 5px;">
            			<span style="border: 2px solid yellow;border-radius:10px;display: block;width:90%;margin:0 auto;background-color: #ffffff;font-size: 16px;   word-wrap:break-word;word-break:normal;overflow:hidden">
             				【众盈医疗】尊敬的41，您的会诊订单E02048463826已预约成功，订单在24小时内有效。请打开<a href="http://121.42.212.104:8080/ehuizhen/api/v1/common/pay/E02048463826">http://121.42.212.104:8080/ehuizhen/api/v1/common/pay/E02048463826</a> ，进行支付，如非本人操作请删除并勿透漏给他人。感谢您的支持！详询4008883918
          		 		</span>
        			</div>
     			</div>
			</li> -->
        </ul> 
 	</div><!-- wrapp结束 -->
  	 
  	 
     <!-- <div class="row">
        <div class="col-xs-12" style="text-align: center;margin-bottom: 15px;padding-left: 5px;padding-right: 5px;">
            <span style="border: 2px solid #ffffff;border-radius:10px;display: block;background-color: #ffffff;font-size: 16px;">
             【众盈医疗】尊敬的41，您的会诊订单E02048463826已预约成功，订单在24小时内有效。请打开<a href="http://121.42.212.104:8080/ehuizhen/api/v1/common/pay/E02048463826">http://121.42.212.104:8080/ehuizhen/api/v1/common/pay/E02048463826</a> ，进行支付，如非本人操作请删除并勿透漏给他人。感谢您的支持！详询4008883918
           </span>
        </div>
     </div>

    <div class="row">
        <div class="col-xs-12" style="text-align: center;margin-bottom: 15px;padding-left: 5px;padding-right: 5px;">
            <span style="border: 2px solid #ffffff;border-radius:10px;display: block;background-color: #ffffff;font-size: 16px;">
             【众盈医疗】尊敬的41，您的会诊订单E02048463826已预约成功，订单在24小时内有效。请打开<a href="http://121.42.212.104:8080/ehuizhen/api/v1/common/pay/E02048463826">http://121.42.212.104:8080/ehuizhen/api/v1/common/pay/E02048463826</a> ，进行支付，如非本人操作请删除并勿透漏给他人。感谢您的支持！详询4008883918
           </span>
        </div>
    </div> -->

    <!-- 灰色方式
    <div class="col-xs-12" style="text-align: center;margin-bottom: 5px;padding-left: 5px;padding-right: 5px;">
          <span style="border: 2px solid rgba(61, 56, 81, 0.15);border-radius:10px;display: block;background-color: rgba(226, 211, 150, 0.07)">
           【众盈医疗】尊敬的41，您的会诊订单E02048463826已预约成功，订单在24小时内有效。请打开<a href="http://121.42.212.104:8080/ehuizhen/api/v1/common/pay/E02048463826">http://121.42.212.104:8080/ehuizhen/api/v1/common/pay/E02048463826</a> ，进行支付，如非本人操作请删除并勿透漏给他人。感谢您的支持！详询4008883918
         </span>
      </div>-->


    </div><!-- content结束 -->
    

</div>

<script src="<%=basePath %>/static/jquery-1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath %>/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script>

	//初始化页面下拉加载更多插件
 	 refresher.init({
   	 	id: "wrapper",//<------------------------------------------------------------------------------------┐
   		 pullDownAction: Refresh,
   		 pullUpAction: Load
	});
 	//wrapper.refresh();

	function Refresh() {
		wrapper.refresh();
	}

	//页面下拉加载更多
	function Load() {
		$("#dataLoad").show();
	   var pageIndex = $("#pageIndex").val()*1 +1;
	   //alert("pageIndex="+pageIndex);
		$("#pageIndex").val(pageIndex);
		//alert("pageIndex="+pageIndex);
		var tel = "13991326043";//+$("#phoneNum").val().trim()
		var getSmsDataUrl = "<%=basePath %>/api/v1/sms/query/"+$("#phoneNum").val().trim();
		 $.ajax({ 
  			url : getSmsDataUrl,
  			type : 'get',
  			cache : false,
  			dataType : 'json',
  			data:{"pageIndex":pageIndex},
  			success : function(data) { 
  				if(data.result == 'success') {
  					console.log(data);
  					showData(data.data);
  					wrapper.refresh();
  					$("#dataLoad").hide();
  				}else if(data.result == 'noData') {
  				//	alert("无更多数据");	
  					$("#dataLoad").hide();
  					$("#alertContent").text("无更多数据");
  					showMaskAlert();
  				}else {
  					alert("获取数据失败");	
  				}
  			},
  			error : function(data) {
  				alert("获取数据失败");
  			}
  		});  
	     wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
	}



	function getMySms() {
		var tel = "13991326043";//+$("#phoneNum").val().trim()
		var getSmsDataUrl = "<%=basePath %>/api/v1/sms/query/"+$("#phoneNum").val().trim();
		 $.ajax({ 
  			url : getSmsDataUrl,
  			type : 'get',
  			cache : false,
  			dataType : 'json',
  			success : function(data) {
  				if(data.result == 'success') {
  					console.log(data);
  					showData(data.data);
  					$("#dataLoad").hide();
  				}else if(data.result == 'noData') {
  				//	alert("无更多数据");
  					$("#dataLoad").hide();
  					$("#alertContent").text("无更多数据");
  					showMaskAlert();
  				}
  				else {
  					alert("获取数据失败");	
  					$("#dataLoad").hide();
  				}
  			},
  			error : function(data) {
  				alert("获取数据失败");
  			}
  		});  
	}

	function showData(datas) {
		if(datas.length<=0) {
			//alert("无更多数据");
			$("#alertContent").text("无更多数据");
			showMaskAlert();
			return;
		}
		var e = '';
		for(var i=0;i<datas.length;i++) {
			e+=
			"<li><div class='row'>"+
				"<div class='col-xs-12' style='text-align: center;margin-bottom: 15px;padding-left: 5px;padding-right: 5px;'>"+
	    			"<span style='border: 2px solid #367998;border-radius:10px;display: block;width:90%;margin:0 auto;background-color: #ffffff;font-size: 16px;word-wrap:break-word;word-break:normal;overflow:hidden'>"+
	    			datas[i].noteContent+
	   			"</span>"+
				"</div>"+
			"</div></li>";
		}
		//$("#content").append(e);
		$("#page").append(e);
	}
	
	$("#dataLoad").show();
	
	 function hideMaskAlert() {
	        $("#maskAlert").hide();
    }
    function showMaskAlert() {
        $("#maskAlert").fadeIn("slow");
        $("#maskAlert").show();
        setTimeout(hideMaskAlert, 1500);
    }
    $(function () {
    	 // 关闭
        $(".close_btn").hover(
                function () {
                    $(this).css(
                            {
                                color: 'black'
                            }
                    )
                }
                , function () {
                    $(this).css(
                            {
                                color: '#999'
                            }
                    )
                }
        ).on('click', function () { 
                    $("#maskAlert").css(
                            {
                                display: 'none'
                            }
                    );
                }
        );
    	
    	
    	/* $("#titleWarn").click(function() {
    		//$("#popAlert").fadeIn(500);
    		showMaskAlert();
    	}); */
    	
    	
    	if( $("#phoneNum").val().trim() == '' || $("#phoneNum").val().trim().length <11 ) {
    		//$("#warn").text("请使用手机号登录以获取会诊消息");
    	//	alert("请使用手机号登录以获取会诊消息");
    	$("#dataLoad").hide();
			$("#alertContent").text("请使用手机号登录");
			showMaskAlert();
    		return;
    	}
    	getMySms();
    })
</script>
</body>
</html>