 $("div.navbar-fixed-top").autoHidingNavbar();
	var height=0;  
	var scr = 0 
	var result;
	//loading 提示框
	var warn = 
		"<div id='dataLoad' style='z-index:9999;position:fixed;left:40%;top:43%;'>"+
			"<table width='100%;height=100%;border=0;align=center;valign=middle;'>"+
				"<tr height='50%'><td align='center'>&nbsp;</td></tr>"+
				"<tr><td align='center'><img src='static/img/loading.gif'/></td></tr>"+
				"<tr><td align='center'>数据载入中，请稍后......</td></tr>"+
				"<tr height='50%'><td align='center'>&nbsp;</td></tr>"+
			"</table>"+
		"</div>";
		
		//初始化页面下拉加载更多插件
		/* refresher.init({
            id: "wrapper",//<------------------------------------------------------------------------------------┐
            pullDownAction: Refresh,
            pullUpAction: Load
		}); */
		
        function Refresh() {
			wrapper.refresh();
        }

        //页面下拉加载更多
        function Load() {
           	var regionId = $(".province").attr('id');
     		var hospitalId = $("[data-flag='hospitals']").attr('id');
     		var departmentId = $("[data-flag='hospitals']").attr('id');
     		var curPage = $("#curPage").val()*1 + 1;
     		
     		$("#curPage").val(curPage);

     		var getDoctorsUrl = "doctor/getDoctors/"+regionId;
     		$("#dataLoad").show();
     		 $.ajax({
     			url : getDoctorsUrl,
     			type : 'get',
     			cache : false,
     			dataType : 'json',
     	    	 data : {
     		      'departmentId' : departmentId,
     		      'hospitalId' : hospitalId,
     		      'currentPage' : curPage
     		     },
     			success : function(data) {
     				 $("#dataLoad").hide();
     				if(data.result == 'success') {
     					var childs = showDoctors(data.data);
     					$("#doctors").append(childs);
     					$("#dataLoad").hide();
     				}else {
     				}
     			},
     			error : function(data) {
     			}
     		});  
        }
	
 	function hideMaskAlert() {
	        $("#maskAlert").hide();
	 }
 	function showMaskAlert() {
     $("#maskAlert").fadeIn("slow");
     $("#maskAlert").show();
     setTimeout(hideMaskAlert, 1500);
 	}
        
      //页面下加载更多数据时调用此方法
      function showDoctors(data) {
    	  if(data.length<=0) {
  			$("#alertContent").text("无更多数据");
  			showMaskAlert();
  			return;
  		}
   		 var content = '';
   		 for(var i=0;i<data.length;i++) {//height:42px;
   			 content += 
   			"<li style='background-color:white'>"+
   		        "<div class='form-group' >"+   
   		            "<label class='col-xs-5 control-label content'><img src='"+data[i].photo+
   		                       "' style='width: 64px;width:64px;display:block;margin:0 auto;border-radius:50%; overflow:hidden;'></label>"+
   		            "<div class='col-xs-7'>"+
   		                "<div style='margin-left: 8%;'>"+
   		                    "<div style='font-size: 20px;font-weight:bold'>"+data[i].doctorName+"</div>"+
   		                    "<div style='font-size: 14px;'><span>"+data[i].departmentName+"</span> | <span>"+data[i].positionName+"</span></div>"+
   		                    "<div style='font-size: 14px;'>"+data[i].hospitalName+"</div>"+
   		                "</div>"+
   		            "</div>"+
   		        "</div>"+
   		         ""+
   		    "</li><hr>";
   		 }
   		 return content;
   	}    
        
        
	//初始加载医生列表与下拉列表change时
	function firstShowDoctors(data) {
		 var content = '';
		 for(var i=0;i<data.length;i++) {//height:42px; 
			 content += 
			"<li style='background-color:white'>"+
		        "<div class='form-group' >"+   
		            "<label class='col-xs-5 control-label content'><img src='"+data[i].photo+
		                       "' style='width: 64px;width:64px;display:block;margin:0 auto;border-radius:50%; overflow:hidden;'></label>"+
		            "<div class='col-xs-7'>"+
		                "<div style='margin-left: 8%;'>"+
		                    "<div style='font-size: 20px;font-weight:bold'>"+data[i].doctorName+"</div>"+
		                    "<div style='font-size: 14px;'><span>"+data[i].departmentName+"</span> | <span>"+data[i].positionName+"</span></div>"+
		                    "<div style='font-size: 14px;'>"+data[i].hospitalName+"</div>"+
		                "</div>"+
		            "</div>"+
		        "</div>"+
		         ""+
		    "</li><hr>";
		 }
		 $("#doctors").html(content);
	}
	
	
	//获取医生列表信息
	function getDoctors(status) {//status 1:首次加载 
		var regionId = $(".province").attr('id');
		var hospitalId = $("[data-flag='hospitals']").attr('id');
		var departmentId = $("[data-flag='hospitals']").attr('id');
		var curPage = 1;
		if(status == 1) {
			curPage = 1;
			$("#curPage").val(curPage);
		}else {
			curPage = $("#curPage").val()*1 + 1;
			$("#curPage").val(curPage);
		}
		var getDoctorsUrl = "doctor/getDoctors/"+regionId;
		$("#dataLoad").show();
		 $.ajax({
			url : getDoctorsUrl,
			type : 'get',
			cache : false,
			dataType : 'json',
    	 	data : {
		      'departmentId' : departmentId,
		      'hospitalId' : hospitalId,
		      'currentPage' : curPage
		     },
			success : function(data) {
				 $("#dataLoad").hide();
				if(data.result == 'success') {
					result = data;
					firstShowDoctors(data.data);
				}else {
				}
			},
			error : function(data) {
			}
		});  
		
	}
	
	//初始化省市下拉列表
	function initArea(areas) {
		var e1 = '';
		for(var i=0;i<areas.length;i++) {
			var citys = areas[i].citys;
			var e2 = '';
			for(var j=0;j<citys.length;j++) {
				  e2 += 
					"<li><a tabindex='0' id='"+citys[j].cityId+"'>"+citys[j].cityName+"</a></li>"+
	                "<li class='divider'></li>";
			}
			
		 	e1 += "<li class='dropdown-submenu'>"+
            "<a tabindex='0' id='"+areas[i].provinceId+"'>"+areas[i].provinceName+"</a>"+
			
            "<ul class='dropdown-menu'>"+e2+
            "</ul>"+
        	"</li>"+
        	"<li class='divider'></li>";
		}
		
		$("#provinceChild").append(e1);
		$('[data-submenu]').submenupicker();//这个只能用一次
		
		$("#provinceChild").find("ul>li>a").click(function () {
			var t = $(this).text();
			var replaceStr = '';
			try{
				if(t.length>4){
					replaceStr+=t.substring(0,4);
				}else{
					replaceStr = t;
				}
			}catch(e){
			}
 			var ccc = "<a class='province' id='"+$(this).attr('id')+"' tabindex='0' data-toggle='dropdown' data-submenu="+''+" aria-expanded='false' style='height:32px;padding-top: 0;padding-bottom: 0;padding-left: 10px;padding-right: 10px;color: #000000'>"+
 			replaceStr+" <span class='caret'></span></a>";
      	$('.province').replaceWith(ccc); 
      		var regionId = $(".province").attr('id');
			var hospitalId = $("[data-flag='hospitals']").attr('id');
			var departmentId = $("[data-flag='hospitals']").attr('id');
			getDoctors(1);
       });
	}
	
	//初始化医院下拉列表
	function initHospitals(hospitals) {
     var childs = '';
		for(var i=0;i<hospitals.length;i++) {
			childs += 
			"<li><a href='#' id='"+hospitals[i].id+"'>"+hospitals[i].hospitalName+"</a></li>"+
	        "<li class='divider'></li>";
		}
		$("[data-flag='hospitalChild']").append(childs);
		$("[data-flag='hospitalChild']").find("a").each(function() {
			$(this).click(function() {
				$("[data-flag='hospitals']").html($(this).text());
				$("[data-flag='hospitals']").attr('id',$(this).attr('id'));
				getDoctors(1);
			});
		})
	}
	
	//初始化科室下拉列表
	function initDeparments(deparments) {
		 var childs = '';
		 for(var i=0;i<deparments.length;i++) {//<ul class="dropdown-menu" role="menu" class="deparmentChild" id="">
			childs += 
			 "<li><a href='#' id='"+deparments[i].id+"'>"+deparments[i].departmentName+"</a></li>"+
             "<li class='divider'></li>";
		}
		$("[data-flag='deparmentChild']").append(childs);
		$("[data-flag='deparmentChild']").find("a").each(function() {
			$(this).click(function() {
				$("[data-flag='deparments']").html($(this).text());//+" <span class='caret'></span></a>"
				$("[data-flag='deparments']").attr('id',$(this).attr('id'));
				getDoctors(1);
			});
		})
	}
	
	function initFirstData(data) {
		$(".province").attr('id',data.currentProvinceId);
		$("[data-flag='hospitals']").attr('id',data.currentHospitalId);
		$("[data-flag='deparments']").attr('id',data.currentDepartmentId);
	}

    $(function () {
    	
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

   	 	var range = 10;             //距下边界长度/单位px
        var num = 1;
        var totalheight = 0;
        var main = $("#content");                     //主体元素
        $(window).scroll(function(){
            var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)
            totalheight = parseFloat($(window).height()) + parseFloat(srollPos);
            if(($(document).height()-range) <= totalheight) {
            	var regionId = $(".province").attr('id');
         		var hospitalId = $("[data-flag='hospitals']").attr('id');
         		var departmentId = $("[data-flag='hospitals']").attr('id');
         		var curPage = $("#curPage").val()*1 + 1;
         		
         		$("#curPage").val(curPage);

         		var getDoctorsUrl = "doctor/getDoctors/"+regionId;
         		$("#dataLoad").show();
         		 $.ajax({
         			url : getDoctorsUrl,
         			type : 'get',
         			cache : false,
         			dataType : 'json',
         	    	 data : {
         		      'departmentId' : departmentId,
         		      'hospitalId' : hospitalId,
         		      'currentPage' : curPage
         		     },
         			success : function(data) {
         				 $("#dataLoad").hide();
         				if(data.result == 'success') {
         					var childs = showDoctors(data.data);
         					$("#doctors").append(childs);
         					$("#dataLoad").hide();
         				}else {
         					alert("获取医生列表失败，请稍后再试！");
         				}
         			},
         			error : function(data) {
         				alert("获取医生列表失败，请稍后再试。");
         			}
         		});  
            }
        });
    	
    	
		//初始化下拉菜单数据
		 $.ajax({
			url : 'doctor/initData',
				type : 'get',
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data.result == 'success') {
						initArea(data.areas);
						initHospitals(data.hospitals);
						initDeparments(data.deparments);
						initFirstData(data);//初始化选中参数
						getDoctors(1);
					} else {
						alert("初始化数据失败，请稍后再试。");
					}
				},
				error : function(data) {
					alert("初始化数据失败，请稍后再试。");
				}
			});

});