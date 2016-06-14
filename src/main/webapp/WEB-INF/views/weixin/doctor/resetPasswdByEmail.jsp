<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ include file="../common/common.jsp"%>  
<title>找回密码</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="Stylesheet" type="text/css" href="static/css/common/alertDialog.css"/>
<link rel="Stylesheet" type="text/css" href="static/css/patient/signUp.css"/> 
    <script src="static/aspwx/js/jquery.metadata.js"></script>
    <script src="static/aspwx/js/jquery.validate.js"></script>
    <script src="static/aspwx/js/global.js"></script>
   <style>
	  .form-horizontal .form-group{
	  	margin-left:0;margin-right:0;
	  }
	  .col-xs-8{
	  	padding-right:0;
	  }
   </style>
   <style>
		.form-control {
		  display: block;
		  width: 100%;
		  height: 34px;
		  padding: 6px 12px;
		  font-size: 14px;
		  line-height: 1.42857143;
		  color: #555;
		  background-color: #fff;
		  background-image: none;
		 /*  border: 1px solid #ccc; */
		  border-radius: 4px;
		  -webkit-box-shadow: none;
		          box-shadow: none;
		  -webkit-transition: none;
		       -o-transition: none;
		          transition: none;
		}
		.form-control:focus {
		  border-color: none;
		  outline: none;
		  -webkit-box-shadow: none;
		          box-shadow: none;
		} 
   </style>
</head>

<%@ include file="../common/modalDialog.jsp"%> 

<!-- 模态框（Modal）下边单按钮 -->
<div class="modal fade" id="validVerifyCodeModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
     <input type="hidden" id="flag" value="${result}">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="validVerifyCodeModalContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
               	${result}	
            </div>
            <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-12' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		             	<div id="validVerifyCodeModalBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">确定</div>
 		              </div>
				</form>
            </div>
        </div>
    </div>
</div>


<body>
	<!-- 提交中 -->
	<div id="dataSubmit" class="container" style="display: none">
            <div class="col-xs-12 pos" style="position:fixed">
                <div  style="display:inline-block;width: 200px;height: 100px;display:inline-block;position: relative;border-radius: 4px;padding-top: 21px;background: white;">
                    <div class="">
                       <img src="static/img/refresh.gif"/>
                    </div>
                    <span id="" class="" style="color:#999999">提交中，请稍后......</span>
                </div>
            </div>
    </div>
<!-- 正在加载中 -->


   <div id="maskAlert" class="container">
         <div class="col-xs-12 pos">
             <div class="body" style="background-image:url(static/img/bg.png)">
                 <div class="head">
                     <span id="alertTitle" class="title">提示</span>
                     <a id="alertCloseBtn" href="javascript:void(0)" title="关闭窗口" class="close_btn" style="text-decoration: none">×</a>
                 </div>
                 <span id="alertContent" class="alertContent"></span>
             </div>
         </div>
   </div>
	<div class="container top">
    <div class="row headTitle">
    	 <div class="col-xs-12 titleDesc">重置密码
    	<!--  <a href="patient/signInPage" class="right">登录</a> -->
    	<!--  <a href="patient/wxBindPage" class="right">绑定</a> -->
    	 </div>
    </div>
	<span id="warn"></span>
    
    <form class="form-horizontal">
       <!--  <div class="form-group wrap">
            <div class="col-xs-4 control-label rowLeft" >
            	<div class="desc">姓名</div>
            </div>
            
            <div class="col-xs-7 right" >
                <input  type="text" class="form-control" placeholder="请输入姓名" id="patientName" name="patientName">
            </div>
           
        </div> -->

        <div class="form-group wrap">
            <div for="tel" class="col-xs-3 control-label rowLeft" >
            <div class="desc">邮箱</div>
           </div>

            <div class="col-xs-9 right">
                <input type="tel" placeholder="请输入邮箱"  class="form-control" id="phoneNum" name="phoneNum">
            </div>
        </div>

        <div class="form-group wrap">
            <div for="validPassword" class="col-xs-3 control-label rowLeft">
            	<div class="desc">验证码</div>
           	</div>

            <div class="col-xs-5 right" style="padding-right:4px;">
                <input type="text" placeholder="请输入验证码"  class="form-control" id="verifyCode" name="verifyCode">
            </div>
            <div class="col-xs-4 verifyCodeBtn" >
                <a id="getVerifyCode" class="form-control btn-primary" style="background:#00abec;border:none;outline:none;padding-top:8px;cursor:pointer">获取验证码</a>
            </div> 
        </div>

        <div class="form-group wrap">
            <div for="password" type="password" class="col-xs-3 control-label rowLeft" >
           		<div class="desc">新密码</div>
           	</div>

            <div class="col-xs-9 right">
                <input type="password" placeholder="请输入6-15位新密码" class="form-control col-xs-10" id="passWord" name="passWord">
            </div>
        </div>
        
         <div class="form-group wrap">
            <div for="password" type="password" class="col-xs-3 control-label rowLeft" >
           		<div class="desc">密码确认</div>
           	</div>

            <div class="col-xs-9 right">
                <input type="password" placeholder="请输入确认密码" class="form-control col-xs-10" id="passWordConfirm" name="passWord">
            </div>
        </div>
        

     <!--    <div>
        	<p>如果点击注册默认接受<a id="protocol" style="text-decoration: none;">《e会诊患者使用协议》</a></p>
        	<p>将此账号与当前微信号绑定<p>
        	</div> -->
        <button id="signUpBtn" type="button" class="btn btn-primary btn-lg btn-block" style="border:none;outline:none;margin-top:10px;" >重置</button>
    </form>
</div>
<input type="hidden" id="token" value="${token}">

<script src="static/js/doctor/resetPasswdByEmail.js"></script>



<script>
$("#protocol").click(function() {
	window.open("doctor/protocolConfirmPage","_self");//,"_self"
	return false;
})
</script>
</body>
</html>