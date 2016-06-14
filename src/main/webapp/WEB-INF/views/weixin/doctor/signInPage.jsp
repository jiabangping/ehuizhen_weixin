<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>  
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="Stylesheet" type="text/css" href="static/css/common/alertDialog.css"/>
    <script src="static/aspwx/js/jquery.metadata.js"></script>
    <script src="static/aspwx/js/jquery.validate.js"></script>
    <script src="static/aspwx/js/global.js"></script>
<title>医生账号绑定</title>
	<style>
	.top {
	  margin-top: 13px;;
	}
	</style>
	<style>
 	.form-control {
 	  outline:none;
 	  border:1px solid #ccc;
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
		outline:none;
		border:none;
		border:1px solid #ccc;
	  	border-color: none;
	  	-webkit-box-shadow: none;
	          box-shadow: none;
	}

</style>
</head>

<%@ include file="../common/notOpenInWeiXin.jsp"%>  
<%@ include file="../common/modalDialog.jsp"%> 


<!-- auditNotSuccess -->
<div class="modal fade" id="auditNotSuccess" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="auditNotSuccessContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
               	未通过
            </div>
            <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-12' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		             	<div id="auditNotSuccessBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">确定</div>
 		              </div>
				</form>
            </div>
        </div>
    </div>
</div>

<body>
	<!-- 正在加载中 -->
	<div id="dataLoad" class="container" style="display: none">
            <div class="col-xs-12 pos" style="position:fixed">
                <div  style="display:inline-block;width: 200px;height: 100px;display:inline-block;position: relative;border-radius: 4px;padding-top: 21px;background: white;">
                    <div class="">
                       <img src="static/img/refresh.gif" />
                    </div>
                    <span id="" class="" style="color:#999999">校验中，请稍后......</span>
                </div>
            </div>
    </div>
	<!-- 正在加载中 -->
    <div id="maskAlert" style="display:none" class="container">
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
    
<div class="container top" id="body">
 	<div class="row" style="margin-bottom:20px;">
    	 <div class="col-xs-12" style="text-align:center;font-size:20px;">医生账号绑定
    	 <!-- <a href="doctor/signUpPage?protocolConfirm=1" style="inline-block;right:20px;position:absolute;text-decoration:none;color:black;font-size:15px;top:5px;">注册</a> -->
    	 </div>
    </div>
	<span id="warn" style="color:red;font-size:13px;margin-bottom:8px;display:block;"></span>
    
    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-xs-12">
                <input type="tel" class="form-control" id="patientNameOrPhoneNum" name="patientNameOrPhoneNum" placeholder="手机号">
            </div>
        </div>
        
        <div class="form-group">
            <div class="col-xs-12">
                <input type="password" class="form-control" id="passWord" name="passWord" placeholder="密码">
            </div>
        </div>

       <!--  <div class="form-group">
            <div class="col-xs-12">
                <input type="password" class="form-control" id="passWord" name="passWord" placeholder="密码">
            </div>
        </div> -->
        <button id="signIn" type="button" class="btn btn-primary btn-lg btn-block" style="background:#00abec;border:none;outline:none;">绑定</button>
        
        <div class="form-group" style="margin-top:13px;">
           <div class="col-xs-12">
	         	<div style="text-align: right;padding-right: 7px;">
	         	<a href="doctor/signUpPage?protocolConfirm=1" style="display:inline-block;float:left;margin-left: 7px;font-size: 15px;text-decoration: none;color:#00abec">注册</a>
	           	<a style="color:#00abec;font-size: 15px;" href="doctor/resetPasswdPage;jsessionid=73E6B2470C91A433A6698C7681FD44F4">忘记密码</a>
	            </div>
            </div>
       	</div>
    </form>
</div>

<script src="static/js/doctor/doctorSignIn.js"></script>

</body>
</html>