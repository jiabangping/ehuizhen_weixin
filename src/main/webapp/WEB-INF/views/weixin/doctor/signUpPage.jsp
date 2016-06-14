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
	<base href="<%=basePath %>">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="static/aspwx/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/aspwx/css/bootstrap-theme.min.css">
	<link rel="Stylesheet" type="text/css" href="static/css/common/alertDialog.css"/>
	<link rel="Stylesheet" type="text/css" href="static/css/doctor/signUp.css"/>
	<style>
		.requiredField{
			    color: red;
			    font-size: 19px;
			    position: absolute;
			    margin-left: 5px;
		}
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
	
  <!--  <script src="static/aspwx/js/jquery-1.9.1.js"></script> -->
    <script src="static/ajaxfileupload/jquery-1.9.1.js"></script>

 	<!-- <script src="static/jquery-1.11.3/jquery-1.11.3.min.js"></script> -->
    <script src="static/aspwx/js/bootstrap.min.js"></script>
    <script src="static/aspwx/js/jquery.metadata.js"></script>
    <script src="static/aspwx/js/jquery.validate.js"></script>
    <script src="static/aspwx/js/global.js"></script>
    <script src="static/aspwx/js/jweixin-1.0.0.js"></script>
    
    <!-- ajaxfileupload begin -->
    <script  src="static/ajaxfileupload/ajaxfileupload.js"></script>
	<!-- ajaxfileupload end -->
	
	<script  src="static/js/cookie/cookieCommonOperate.js"></script>
	<script src="static/js/doctor/doctorSignUp.js"></script>
	
  <link href="static/icheck-1.x/skins/all.css?v=1.0.2" rel="stylesheet">
  
  <script src="static/icheck-1.x/icheck.min.js?v=1.0.2"></script>
  
    <title>
	医生注册
</title></head>

<%@ include file="../common/modalDialog.jsp"%> 

<input id="firstOverSea" type="hidden" value="${firstOverSea}">
<input id="firstWhich" type="hidden" value="${firstWhich}">
<input id="firstDoctorName" type="hidden" value="${doctorName}">
<input id="signUpAgain" type="hidden" value="-1">


<input id="which" type="hidden" value="">
<!-- 重新获取激活邮件 -->
<div id="dataUpload2" class="container" style="display: none">
           <div class="col-xs-12 pos" style="position:fixed;top:26%;">
               <div  style="display:inline-block;width: 200px;height: 100px;display:inline-block;position: relative;border-radius: 4px;padding-top: 21px;background: white;">
                   <div class="">
                      <img src="static/img/refresh.gif" />
                   </div>
                   <span id=""style="font-size:13px;color:#999999">获取中，请稍后...</span>
               </div>
           </div>
   </div>


<!-- 重新获取激活邮件 -->
<div class="modal fade" id="getEmailValidResponseModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="getEmailValidResponseModalContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
            
            </div>
            <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-12' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		             	<div id="getEmailValidResponseModal-btn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">确定</div>
 		              </div>
				</form>
            </div>
        </div>
    </div>
</div>




<!-- 已存在请绑定 -->
<div class="modal fade" id="existBindPleaseModel" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
        <input type="hidden" id="waitForValidEmailStatus" value="${result}">
            <div id="existBindPleaseModelContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
              ${msg }
            </div>
          	<div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-6' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		            <!-- 	<button id="signUpBtn" type="button" class="btn btn-primary btn-lg btn-block" style="border-radius:0;border:none;background:white" >注册</button> -->
 		           		<div id="existBindPleaseModel-leftBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">返回</div>
 		              </div>
 		            <div class='col-xs-6' style="text-align:center;padding:0">
 		            	<!-- <button id="retryBtn" type="button" class="btn btn-primary btn-lg btn-block" style="border-radius:0" >重试</button> -->
 		            	<div id="existBindPleaseModel-rightBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">绑定</div> 
 		            </div>
				</form>
            </div>
        </div>
    </div>
</div>


<!-- 审核未通过 调整2 -->
<div class="modal fade" id="auditFailModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="auditFailModalContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
               		${msg}	
            </div>
            <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-12' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		             	<div id="auditFailModalBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">确定</div>
 		              </div>
				</form>
            </div>
        </div>
    </div>
</div>



<!-- 审核未通过 -->
<div class="modal fade" id="auditNoSuccessModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="auditNoSuccessModalContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
               		${msg }	
            </div>
            <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-6' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		            <!-- 	<button id="signUpBtn" type="button" class="btn btn-primary btn-lg btn-block" style="border-radius:0;border:none;background:white" >注册</button> -->
 		           		<div id="auditNoSuccessModal-leftBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">返回</div>
 		              </div>
 		            <div class='col-xs-6' style="text-align:center;padding:0">
 		            	<!-- <button id="retryBtn" type="button" class="btn btn-primary btn-lg btn-block" style="border-radius:0" >重试</button> -->
 		            	<div id="auditNoSuccessModal-rightBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">补全资料</div> 
 		            </div>
				</form>
            </div>
        </div>
    </div>
</div>

<!-- 模态框（Modal）下边单按钮等待审核 -->
<div class="modal fade" id="waitAuditModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="waitAuditModalContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
               	${msg}	
            </div>
            <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-12' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		             	<div id="waitAuditModalBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">确定</div>
 		              </div>
				</form>
            </div>
        </div>
    </div>
</div>


<!-- 邮箱注册用户等待验证邮箱中 -->
<div class="modal fade" id="waitForValidEmail" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
        <input type="hidden" id="waitForValidEmailStatus" value="${result}">
            <div id="waitForValidEmailContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
              ${msg }
            </div>
          	<div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-6' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		            <!-- 	<button id="signUpBtn" type="button" class="btn btn-primary btn-lg btn-block" style="border-radius:0;border:none;background:white" >注册</button> -->
 		           		<div id="waitForValidEmail-leftBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">返回</div>
 		              </div>
 		            <div class='col-xs-6' style="text-align:center;padding:0">
 		            	<!-- <button id="retryBtn" type="button" class="btn btn-primary btn-lg btn-block" style="border-radius:0" >重试</button> -->
 		            	<div id="waitForValidEmail-rightBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">重新下发激活邮件</div> 
 		            </div>
				</form>
            </div>
        </div>
    </div>
</div>

<!-- 上次只完成第一步提示 -->
<div class="modal fade" id="onlyFinishFirstStepModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
        <input type="hidden" id="first-status" value="${result}">
            <div id="onlyFinishFirstStepModalContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
              ${msg }
            </div>
          	<div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-6' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		            <!-- 	<button id="signUpBtn" type="button" class="btn btn-primary btn-lg btn-block" style="border-radius:0;border:none;background:white" >注册</button> -->
 		           		<div id="onlyFinishFirstStepModal-leftBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">重新注册</div>
 		              </div>
 		            <div class='col-xs-6' style="text-align:center;padding:0">
 		            	<!-- <button id="retryBtn" type="button" class="btn btn-primary btn-lg btn-block" style="border-radius:0" >重试</button> -->
 		            	<div id="onlyFinishFirstStepModal-rightBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">完善资料</div> 
 		            </div>
				</form>
            </div>
        </div>
    </div>
</div>



<!-- 模态框（Modal）下边单按钮 -->
<div class="modal fade" id="signSuccessModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="signSuccessModalContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
               	绑定成功	
            </div>
            <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-12' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		             	<div id="signSuccessBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point"></div>
 		              </div>
				</form>
            </div>
        </div>
    </div>
</div>

<!-- 注册过，cookie中有缓存时弹出此框 -->
<div class="modal fade" id="alreadyRegisteModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="alreadyRegisteModalContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
               	您已经完成注册,账号等待审核中
            </div>
           <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-12' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		             	<div id="alreadyRegisteModalBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point"></div>
 		              </div>
				</form>
            </div>
        </div>
    </div>
</div>


<!-- 注册过，查询账号审核状态 
<div class="modal fade" id="queryAccountModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="top:75px">
        <div class="modal-content">
            <div id="queryAccountModalContent" class="modal-body" style="font-size: 15px;text-align: center;color:#333333;">
               	手机号
            </div>
           <div class="modal-footer" style="padding:0">
               <form class="form-horizontal"  style="">
					  <div class='col-xs-12' style="text-align:center;padding:0;border-right:1px solid #E5E5E5">
 		             	<div id="queryAccountModalBtn" class="btn-lg btn-block" style="font-size:16px;color:#3C95FE;margin-top: 2px;margin-bottom: 2px;cursor:point">关闭</div>
 		              </div>
				</form>
            </div>
        </div>
    </div>
</div>
-->


<body>
<!-- 正在加载中 -->
<div id="dataLoad" class="container" style="display: none">
            <div class="col-xs-12 pos" style="position:fixed">
                <div  style="display:inline-block;width: 200px;height: 100px;display:inline-block;position: relative;border-radius: 4px;padding-top: 21px;background: white;">
                    <div class="">
                       <img src="static/img/refresh.gif"/>
                    </div>
                    <span id="" class="" style="color:#999999">载入中，请稍后......</span>
                </div>
            </div>
    </div>
<!-- 正在加载中 -->

<!-- 正在上传图片 -->
<div id="dataUpload" class="container" style="display: none">
           <div class="col-xs-12 pos" style="position:fixed">
               <div  style="display:inline-block;width: 200px;height: 100px;display:inline-block;position: relative;border-radius: 4px;padding-top: 21px;background: white;">
                   <div class="">
                      <img src="static/img/refresh.gif" />
                   </div>
                   <span id="" class="" style="color:#999999">上传中，请稍后......</span>
               </div>
           </div>
   </div>
<!-- 正在加载中 -->
<!-- 提交中 -->
	<div id="dataSubmit" class="container" style="display: none">
            <div class="col-xs-12 pos" style="position:fixed">
                <div  style="display:inline-block;width: 200px;height: 100px;display:inline-block;position: relative;border-radius: 4px;padding-top: 21px;background: white;">
                    <div class="">
                       <img src="static/img/refresh.gif" />
                    </div>
                    <span id="" class="" style="color:#999999">提交中，请稍后......</span>
                </div>
            </div>
    </div>
<!-- 正在加载中 -->


<div id="maskAlert" class="container">
            <div class="col-xs-12 pos" style="position:fixed">
                <div class="body" id="maskAlert" style="display:inline-block;background-image:url(static/img/bg.png)">
                    <div class="head">
                        <span id="alertTitle" class="title">提示</span>
                        <a id="alertCloseBtn" href="javascript:void(0)" title="关闭窗口" class="close_btn" style="text-decoration: none">×</a>
                    </div>
                    <span id="alertContent" class="alertContent"></span>
                </div>
            </div>
    </div>

   <%--  <form method="post" action="" id="InfoInput"> --%>
        <div id="Div1" class="container">
            <h2 class="text-center" style="margin-top:13px;font-size:20px;margin-bottom: 20px">医生注册</h2>
         <div id="first" >   
         <form id="InfoInput1" >
           <!--  <div class="form-group ">
            	<label class="control-label" >所在地(*)</label>
            	
	            <label class="radio-inline" style="">
				  <input type="radio" style="position:static" name="Oversea" checked id="Oversea0" value="0">国内
				</label>
				<label class="radio-inline">
				  <input type="radio" style="position:static" name="Oversea" id="Oversea1" value="1">海外
				</label>
            </div>
            
            <div class="form-group ">
           		 <label class="control-label">性别(*)</label>
            	
	            <label class="radio-inline">
				  <input type="radio" style="position:static;" name="Sex" checked id="Sex1" value="1">男
				</label>
				<label class="radio-inline">
				  <input type="radio" style="position:static;" name="Sex" id="Sex2" value="2">女
				</label>
            </div> -->
            
            
            
            
            
            <div class="form-group" id="addrRadioGroup">
            	<label class="control-label" >所在地<span class="requiredField">*</span></label>
            	
	            <label class="radio-inline" style="">
				  <input type="radio" style="position:static" name="Oversea" checked id="Oversea0" value="0"><label  style="font-weight:normal;padding-left: 10px;" for="Oversea0">国内</label>
				</label>
				<label class="radio-inline">
				  <input type="radio" style="position:static" name="Oversea" id="Oversea1" value="1"><label style="font-weight:normal;padding-left: 10px;" for="Oversea1">海外</label>
				</label>
            </div>
            
            <div class="form-group" id="sexRadioGroup">
           		 <label class="control-label" style="margin-right:13px;">性别<span class="requiredField">*</span></label>
            	
	            <label class="radio-inline">
				  <input type="radio" style="position:static;" name="Sex" checked id="Sex1" value="1"><label  style="font-weight:normal;margin-right:14px;padding-left: 10px;" for="Sex1">男</label>
				</label>
				<label class="radio-inline">
				  <input type="radio" style="position:static;" name="Sex" id="Sex2" value="2"><label style="font-weight:normal;padding-left: 10px;" for="Sex2">女</label>
				</label>
            </div>
            
            
            
            
            <div class="form-group ">
                <label class="control-label" for="inputError1">医生姓名<span class="requiredField">*</span></label>
                <input placeholder="必填项" name="DoctorName" id="DoctorName" class="form-control {required:true,minlength:2,maxlength:20}" type="text">
            </div>
            
           <input id="overseaEmailGroup" type="hidden">
            
             <div class="form-group " id="phoneNumGroup">
                <label class="control-label" for="inputError1">手机号码<span class="requiredField">*</span></label>
                <input placeholder="必填项" name="PhoneNum" id="PhoneNum" class="form-control {required:true,mobile:true}" type="tel">
            </div>
            
            <div id="verifyCodeGroup" class="row" style="margin-bottom:15px;">
        		<label class="control-label" style="margin-left:15px;" for="inputError1">验证码<span class="requiredField">*</span></label>
        		<div>
	            	<div class="col-xs-8" style="padding-right:10px;">
	               		<input placeholder="必填项" type="number" class="form-control {required:true,verifyCode:true}" id="verifyCode" name="verifyCode">
	            	</div>
	            	<div class="col-xs-4" style="padding-left:0;">
	               	 	<a id="getVerifyCode" class="form-control btn-primary" style="text-decoration: none;text-align:center;font-size: 12px;padding-left:0;padding-right:0;margin-left:0;margin-right:0;background:#00abec;border: none;outline: none;cursor: pointer;padding-top:9px;color: white;text-shadow:none">获取验证码</a>
            		</div>
       		 	</div>
    		</div>
            
            <div class="form-group " id="idCardGroup">
                <label class="control-label" for="inputError1">身份证号码<span class="requiredField">*</span></label>
                <input placeholder="必填项" name="IDCard" id="IDCard" class="form-control {required:true,isIdCardNo:true}" type="number">
            </div>
              <div id="chargingStandardGroup"><!-- 海外医生具有自己的收费标准 -->
	          <!--   <div class="form-group id="chargingStandard">
	                <label class="control-label" for="inputError1">收费标准</label>
	                <input name="DoctorName" id="ChargingStandard" class="form-control {required:true,maxlength:20}" type="text">
	            </div> -->
            </div>
            <input id="nextStep" name="nextStep" type="button" value="下一步"  class="btn btn-success btn-lg btn-block" style="background:#00abec;border: none;outline: none;text-shadow: none;margin-bottom:25px;"> 
           <!--  <button id="nextStep" name="nextStep" class="btn btn-success btn-lg btn-block" >下一步</button> -->
        </form>        
        </div><!-- 第一步 -->   
   
            
    
      <div id="second" style="display:none">  
      <p style="margin-top:10px;"> 
      尊敬的用户<span id="userName" style="font-size:15px;font-weight:bold;color:red"></span>请您接下来完善如下信息完成注册</p>
      
          <form id="InfoInput2">       
            <div class="form-group ">
                <label class="control-label" for="inputError1">所在医院<span class="requiredField">*</span></label>
                <input placeholder="必填项" name="hospital" id="hospital" class="form-control {required:true,minlength:4,maxlength:20}" type="text">
            </div>
            <div class="form-group ">
                <label class="control-label" for="inputError1">所在科室<span class="requiredField">*</span></label>
                <input placeholder="必填项" name="department" id="department" class="form-control {required:true,minlength:2,maxlength:20}" type="text">
            </div>
            <div class="form-group ">
                <label class="control-label" for="inputError1">职称<span class="requiredField">*</span></label>
                <input placeholder="必填项" name="position" id="position" class="form-control {required:true,minlength:2,maxlength:20}" type="text">
            </div>
            
            <div class="form-group ">
                <label class="control-label" for="inputError1">擅长领域</label>
                <input placeholder="选填项" name="skilledField" id="skilledField" class="form-control {minlength:4,maxlength:20}" type="text">
            </div>
        
            <div class="form-group ">
                <label class="control-label" for="inputError1">医师资格证号码</label>
                <input placeholder="选填项" name="doctorCertNo" id="doctorCertNo" class="form-control {minlength:5,maxlength:20}" type="text">
            </div>
            
             <!-- <div class="form-group ">
                <label class="control-label" for="inputError1">收费标准</label>
                <input name="chargingStandard" id="chargingStandard" class="form-control" type="text">
            </div> -->
            
            <div id="emailGroup" class="form-group ">
                <label class="control-label" for="inputError1">电子邮箱</label>
                <input name="email" id="email" class="form-control {email:true}" type="email">
            </div>
            
             <!--  <div class="form-group ">
                <label class="control-label" for="inputError1">个人介绍(*)</label>
                <input name="selfIntro" id="selfIntro" class="form-control {required:true}" type="textarea">
            </div> -->
            
            <div class="form-group ">
                <label class="control-label" for="inputError1">个人介绍<span class="requiredField">*</span></label>
                <textarea  name="selfIntro" id="selfIntro" rows="3" class="form-control {required:true,intro:20,maxlength:100}" placeholder="必填项    20-100字"></textarea>
            </div>
            
            <!--  <div class="row" style="margin-bottom:15px;">
        		<label class="control-label" style="margin-left:15px;" for="inputError1">个人照片(*)</label>
        		<div>
        			<input id="selfPhotoUrlHidden" type="hidden" >
	            	<div class="col-xs-8"  id="fileToUploadGroup">
	               		<input  id="fileToUpload" name="file1" type="file" class="form-control" style="padding-top:3px;padding-bottom:3px;    margin-bottom: 2px;">
	            	</div>
	            	<div class="col-xs-4" >
	               	 	<a id="uploadBtn" class="form-control btn-primary" style="text-decoration: none;text-align:center;font-size: 12px;padding-left:0;padding-right:0;margin-left:0;margin-right:0;">上传照片</a>
            		</div>
       		 	</div>
    		</div> -->
    		
    		  <div class="row" style="margin-bottom:0;">
        		<label class="control-label" style="margin-left:15px;" for="inputError1">个人照片<span class="requiredField">*</span></label>
        		<div>
        			<input id="selfPhotoUrlHidden" type="hidden" >
        			<div class="col-xs-8" id="fileToUploadGroup" style="padding-right:0;position:relative;" >
        				 <input placeholder="必选项" id="fileName" type="text"  class="form-control"  ><!--border-top-right-radius:0; border-bottom-right-radius:0; -->
        				 <div class="fileBox" id="fileBox" style="">
	               		<img src="static/img/Folder.png" style="width:24px;height:21px;margin-bottom:4px;"><span style="margin-left:6px">选择...</span>
    						<input id="fileToUpload" name="file1" type="file" class="inputstyle" >
						</div>
        			</div>
	            	<div class="col-xs-4" id="uploadBtnGroup">
	               	 	<a id="uploadBtn" class="form-control btn-primary" style="text-decoration: none;text-align:center;font-size: 13px;padding-left:0;padding-right:0;margin-left:0;padding-top:8px;margin-right:0;background:#00abec;border: none;outline: none;color:white;text-shadow:none;">点击上传</a>
            		</div>
       		 	</div>
    		</div> 
    		
    		<!-- <div id="fileName" class="row" style="height:22px;width:300px;overflow:hidden;padding-left:20px;">
    		</div> -->
    		
    		
    		<div class="row" style="margin:0 auto;margin-top:15px;margin-bottom:5px;">
        		<div style="">
        			<img width="200px;" height="200px" id="photo" style="margin-top:15px;" src="static/img/default.png">
        		</div>
    		</div>
    		 
    		 <div style="margin-top:20px;margin-bottom:15px;">
    		 	<p>点击提交默认接受<a style="text-decoration: none;" id="protocol">《e会诊医生使用协议》</a><p>
    		 <!-- 	<p>默认将此账号与当前微信号绑定</p> -->
    		 	
    		 </div>
    		 
           <!--  <button name="secondSubmit" id="secondSubmit" class="btn btn-success btn-lg btn-block" >提交</button> -->
           <input name="secondSubmit" id="secondSubmit" type="button" class="btn btn-success btn-lg btn-block" style="background:#00abec;border: none;outline: none;margin-bottom:25px;"  value="提交">
        </form>   
      </div><!-- 第二步结束 -->
		<input id="phoneNumHidden" type="hidden"> 
     </div>
   <%--  </form> --%>
 <!--    
 <img id="loading" src="static/ajaxfileupload/img/refresh.gif" style="display:none;">
	用户信息：  <br/>
	姓名：<input id="name" name="name" type="text"> <br/>
	附件：<input id="fileToUpload", name="file1" type="file" class="input"> <br/>
	<br><br>
	<input type="button" onclick="ajaxFileUpload();" value="上传"><br/>
	上传进度：<label id="fileUploadProcess"></label>

<div><img id="test"/></div> -->  

</body>
<script>
$("#protocol").click(function() {
	window.open("doctor/protocolConfirmPage","_self");//,"_self"
	return false;
})
</script>

</html>