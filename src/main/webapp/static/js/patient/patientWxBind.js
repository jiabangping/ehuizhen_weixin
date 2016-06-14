	

	function signIn() {
		$("#dataSubmit").show();
		var signInUrl = "patient/wxBind";
   		 $.ajax({ 
	  			url : signInUrl,
	  			type : 'post',
	  			cache : false,
	  			dataType : 'json',
			 	data : {
	     		      'patientNameOrPhoneNum' : $("#patientNameOrPhoneNum").val().trim(),
	     		     	'passWord' : $("#passWord").val().trim()
	     	     },
	  			success : function(data) {
	  				$("#dataSubmit").hide();
	  				if(data.result == 'success') {
	  					//$("#alertContent").text("绑定成功");
	  					$("#singleBtnModalContent").text("绑定成功");
	  					$('#singleBtnModal').modal({backdrop: 'static', keyboard: false});
	  					$("#singleBtnModal").modal("show");
	  				} else if("openInWeixinPlease" == data.result) {
	  					$("#singleBtnModalContent").text("请在微信中打开");
	  					$('#singleBtnModal').modal({backdrop: 'static', keyboard: false});
	  					$("#singleBtnModal").modal("show");
	  				}else if(data.result == "accountPasswdError") {
	  					//$("#warn").text("账号密码错误");
	  					$("#accountPasswdErrorContent").text("账号密码错误");
	  					$('#accountPasswdErrorModal').modal({backdrop: 'static', keyboard: false});
	  					$("#accountPasswdErrorModal").modal("show");
	  				}else if(data.result == 'notExist' ){//账号不存在请注册
	  				//	$("#warn").text("您所绑定的账号不存在");
	  				
	  					$("#2btnModalContent").text("您所绑定的账号不存在");
	  					$('#2btnModal').modal({backdrop: 'static', keyboard: false});
	  					$("#2btnModal").modal("show");
	  				}else if(data.result == 'alreadyBind') {//已绑定,请勿重复绑定
	  					//$("#warn").text("已绑定,请勿重复操作");
	  					//singleBtnModal
	  					$("#singleBtnModalContent").text("已绑定,请勿重复操作");
	  					$('#singleBtnModal').modal({backdrop: 'static', keyboard: false});
	  					$("#singleBtnModal").modal("show");
	  				}else {
	  					//$("#warn").text("账号密码不正确");
	  					$("#accountPasswdErrorContent").text("失败,请重试");
	  					$('#accountPasswdErrorModal').modal({backdrop: 'static', keyboard: false});
	  					$("#accountPasswdErrorModal").modal("show");
	  				}
	  			},
	  			error : function(data) {
	  				$("#dataSubmit").hide();
	  				//$("#warn").text("绑定失败");
	  				$("#accountPasswdErrorContent").text("失败,请重试");
  					$('#accountPasswdErrorModal').modal({backdrop: 'static', keyboard: false});
  					$("#accountPasswdErrorModal").modal("show");
	  			}
	  		});  
	}
	
	
	function checkMobile(phoneNum) {
		if(!(/^1[3|4|5|7|8]\d{9}$/.test(phoneNum))){ 
	        return false; 
	    } 
		return true;
	}

    $(function () {
    	/*if(!is_weixn()) {
    		$("#page-wrap").css({"display":"none"})
    		$('#notOpenInWx').modal({backdrop: 'static', keyboard: false});
    		$("#notOpenInWx").modal("show");
    	}*/
    	
    	
    	//账号密码错误提示modal
    	$("#accountPasswdErrorModal-left").text("退出").click(function() {
    		$("#accountPasswdErrorModal").modal("hide");
    		 WeixinJSBridge.call('closeWindow');
    	});
    	$("#accountPasswdErrorModal-right").text("重试").click(function() {
    		$("#accountPasswdErrorModal").modal("hide");
    		
    	})
    	
    	$("#2btnModal-leftBtn").text("注册").click(function() {
    		$("#2btnModal").hide();
    	    window.location.href="patient/signUpPage";
    	})
    	$("#2btnModal-rightBtn").text("重试").click(function() {
    		$("#2btnModal").hide();
    		window.location.href="patient/wxBindPage?timestamp="+new Date().getTime();
    	})
    	
    	$("#singleBtn").text("确定").click(function() {
    		 WeixinJSBridge.call('closeWindow');
    	})
    	
    	$("#signIn").click(function() {
    		if($("#patientNameOrPhoneNum").val().trim() == '') {
    			$("#warn").text("请输入手机号码");
    			$("#patientNameOrPhoneNum").focus();
    			return;
    		}
    		if(!checkMobile ($("#patientNameOrPhoneNum").val().trim())) {
    			$("#patientNameOrPhoneNum").focus();
    			$("#warn").text("请输入正确的手机号码");
    			return;
    		}
    		
    		if($("#passWord").val().trim() == '') {
    			$("#passWord").focus();
    			$("#warn").text("请输入密码");
    			return;
    		}
    		
    		//$("#warn").text("账号密码校验中...");
    		signIn();
    	});
    	
    })