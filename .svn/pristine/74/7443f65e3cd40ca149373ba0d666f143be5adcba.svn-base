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
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>医生列表</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>/static/bootstrap-submenu-2.0.4-dist/css/bootstrap-submenu.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .test {
            font-size: 13px;
        }

        .top {
            /*margin-top: 20px;;*/
        }

        .topNavBar {
            /*border: 1px solid red;*/
            text-align: center;
            height: 42px;
            line-height: 42px;
            border-top: 2px solid darkgray;
            border-bottom: 1px solid darkgray;

        }
        .province{}
    </style>

</head>
<body>
<div class="container top">
<!--<div class="row">
    <div class="col-xs-3" style="border: 1px solid #ffff67">患者姓名</div>
    <div class="col-xs-9" style="border: 1px solid #3dc0ff"><input style="width: 100%;"></div>
</div>-->
<div class="row navbar-fixed-top" style="background: #ffffff"><!--navbar-fixed-top-->
    <!-- <div class="col-xs-4 topNavBar" >.col-md-1</div>
     <div class="col-xs-4 topNavBar" >.col-md-1</div>
     <div class="col-xs-4 topNavBar">.col-md-1</div>-->
    <div class="col-xs-4 topNavBar" style="border-right: 1px solid darkgray">
      <!--  <div class="btn-group">
            <button type="button" id="province" class="btn btn-default dropdown-toggle"
                    data-toggle="dropdown" style="border:none">
                西安市 <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu" id="provinceChild">
                <li><a href="#">功能</a></li>
                <li class="divider"></li>
                <li><a href="#">另一个功能</a></li>
                <li class="divider"></li>
                <li><a href="#">其他</a></li>
                <li class="divider"></li>
                <li><a href="#">分离的链接</a></li>
            </ul>
        </div>-->
        <div class="btn-group">
        <ul class="nav nav-pills" >

            <li class="dropdown" style="height:32px;line-height: 32px;" >
           
                <a class="province" tabindex="0" data-toggle="dropdown"  data-submenu="" aria-expanded="false" style="height:32px;padding-top: 0;padding-bottom: 0;padding-left: 10px;padding-right: 10px;color: #000000">
                   西安市<span class="caret"></span><!-- 默认选中的-->
                </a>
			
                <ul class="dropdown-menu" id="provinceChild">
                
                
                
                     <!-- <li class="dropdown-submenu">
                        <a tabindex="0">陕西省</a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="0">西安市</a></li>
                            <li class="divider"></li>
                            
                            <li><a tabindex="0">宝鸡市</a></li>
                            <li class="divider"></li>
                            
                            <li><a tabindex="0">咸阳市</a></li>
                            <li class="divider"></li>
                            
                        </ul>
                    </li>
                    <li class="divider"></li>
                    


                    <li class="dropdown-submenu">
                        <a tabindex="0">山西省</a>
                        <ul class="dropdown-menu">
                            <li><a tabindex="0">Sub ac</a></li>
                            <li class="divider"></li>
                            
                            <li><a tabindex="0">Another </a></li>
                            <li class="divider"></li>
                            
                            <li><a tabindex="0">Somethin</a></li>
                            <li class="divider"></li>
                            
                        </ul>
                    </li>
                    <li class="divider"></li> -->
                    

 
 
 
 <!-- <li class='dropdown-submenu'><a tabindex='0' id='110000'>北京</a><ul class='dropdown-menu'><li><a tabindex='0' id='110100'>北京市</a></li><li class='divider'></li></ul></li><li class='divider'></li> -->
 
 
                </ul>
            </li>
        </ul>
    </div>
    </div>

    <div class="col-xs-4 topNavBar" style="border-right: 1px solid darkgray">
        <div class="btn-group">
            <button data-flag="hospitals" type="button" class="btn btn-default dropdown-toggle"
                    data-toggle="dropdown" style="border:none" id="hospital">
                全部医院 <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu" data-flag="hospitalChild">
                <li><a href="#">功能</a></li>
                <li class="divider"></li>
                <li><a href="#">其他</a></li>
                <li class="divider"></li>
            </ul>
        </div>
    </div>

    <div class="col-xs-4 topNavBar">
        <div class="btn-group">
            <button data-flag="deparments" id="" type="button" class="btn btn-default dropdown-toggle"
                    data-toggle="dropdown" style="border:none" id="dept">
                全部科室 <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu" data-flag="deparmentChild" id="">
                <li><a href="#">功能</a></li>
                <li><a href="#">另一个功能</a></li>
                <li><a href="#">其他</a></li>
                <li class="divider"></li>
                <li><a href="#">分离的链接</a></li>
            </ul>
        </div>
    </div>
</div>

<!-- 内容-->
<div class="content">
    <form class="form-horizontal" id="doctors">
       <%--  <div class="form-group" style="margin-top: 60px">
            <label class="col-xs-2 control-label test"><img src="<%=basePath %>/static/img/icon.png"
                                                            style="width: 65px;width:64px;margin-bottom: 2px;"></label>

            <div class="col-xs-10">
                <!-- <input type="text" class="form-control" id="name" name="name">-->
                <div style="margin-left: 15%;">
                    <div style="font-size: 20px;font-weight:bold">赵刚</div>
                    <div style="font-size: 14px;"><span>赵刚</span> | <span>主任医师</span></div>
                    <div style="font-size: 14px;">西京医院</div>
                </div>
            </div>
        </div>
        <hr>


        <div class="form-group">
            <label class="col-xs-2 control-label test"><img src="<%=basePath %>/static/img/icon.png"
                                                            style="width: 65px;width:64px;"></label>

            <div class="col-xs-10">
                <!-- <input type="text" class="form-control" id="name" name="name">-->
                <div style="margin-left: 15%;">
                    <div style="font-size: 20px;font-weight:bold">赵刚</div>
                    <div style="font-size: 14px;"><span>赵刚</span> | <span>主任医师</span></div>
                    <div style="font-size: 14px;">西京医院</div>
                </div>
            </div>
        </div>
        <hr>

        <div class="form-group">
            <label class="col-xs-2 control-label test"><img src="<%=basePath %>/static/img/icon.png"
                                                            style="width: 65px;width:64px;"></label>

            <div class="col-xs-10">
                <!-- <input type="text" class="form-control" id="name" name="name">-->
                <div style="margin-left: 15%;">
                    <div style="font-size: 20px;font-weight:bold">赵刚</div>
                    <div style="font-size: 14px;"><span>赵刚</span> | <span>主任医师</span></div>
                    <div style="font-size: 14px;">西京医院</div>
                </div>
            </div>
        </div>
        <hr>
        <div class="form-group">
            <label class="col-xs-2 control-label test"><img src="<%=basePath %>/static/img/icon.png"
                                                            style="width: 65px;width:64px;"></label>

            <div class="col-xs-10">
                <!-- <input type="text" class="form-control" id="name" name="name">-->
                <div style="margin-left: 15%;">
                    <div style="font-size: 20px;font-weight:bold">赵刚</div>
                    <div style="font-size: 14px;"><span>赵刚</span> | <span>主任医师</span></div>
                    <div style="font-size: 14px;">西京医院</div>
                </div>
            </div>
        </div>
        <hr>

        <div class="form-group">
            <label class="col-xs-2 control-label test"><img src="<%=basePath %>/static/img/icon.png"
                                                            style="width: 65px;width:64px;"></label>

            <div class="col-xs-10">
                <!-- <input type="text" class="form-control" id="name" name="name">-->
                <div style="margin-left: 15%;">
                    <div style="font-size: 20px;font-weight:bold">赵刚</div>
                    <div style="font-size: 14px;"><span>赵刚</span> | <span>主任医师</span></div>
                    <div style="font-size: 14px;">西京医院</div>
                </div>
            </div>
        </div>
        <hr>


        <div class="form-group">
            <label class="col-xs-2 control-label test"><img src="<%=basePath %>/static/img/icon.png"
                                                            style="width: 65px;width:64px;"></label>

            <div class="col-xs-10">
                <!-- <input type="text" class="form-control" id="name" name="name">-->
                <div style="margin-left: 15%;">
                    <div style="font-size: 20px;font-weight:bold">赵刚</div>
                    <div style="font-size: 14px;"><span>赵刚</span> | <span>主任医师</span></div>
                    <div style="font-size: 14px;">西京医院</div>
                </div>
            </div>
        </div>
        <hr> --%>
        
     <div id="dataLoad" style="position:relative;top:100px;"><!--页面载入显示-->
	    <table width=100% height=100% border=0 align=center valign=middle>
	        <tr height=50%><td align=center>&nbsp;</td></tr>
	        <tr><td align=center><img src="<%=basePath %>/static/img/loading.gif"/></td></tr>
	        <tr><td align=center>数据载入中，请稍后......</td></tr>
	        <tr height=50%><td align=center>&nbsp;</td></tr>
	    </table>
	</div>
    </form>
</div>



<!--<ul class="nav nav-pills" style="margin-bottom: 200px;">
    <li class="active"><a tabindex="0">Regular link</a></li>
    <li class="dropdown">
        <a tabindex="0" data-toggle="dropdown" data-submenu="" aria-expanded="false">
            Dropdown<span class="caret"></span>
        </a>

        <ul class="dropdown-menu">
            <li class="dropdown-submenu">
                <a tabindex="0">Action</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">Sub action</a></li>
                    <li class="dropdown-submenu">
                        <a tabindex="0">Another sub action</a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="0">Sub action</a></li>
                            <li><a tabindex="0">Another sub action</a></li>
                            <li><a tabindex="0">Something else here</a></li>
                        </ul>
                    </li>
                    <li><a tabindex="0">Something else here</a></li>
                    <li class="disabled"><a tabindex="-1">Disabled action</a></li>
                    <li class="dropdown-submenu">
                        <a tabindex="0">Another action</a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="0">Sub action</a></li>
                            <li><a tabindex="0">Another sub action</a></li>
                            <li><a tabindex="0">Something else here</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="dropdown-header">Dropdown header</li>
            <li class="dropdown-submenu">
                <a tabindex="0">Another action</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">Sub action</a></li>
                    <li><a tabindex="0">Another sub action</a></li>
                    <li><a tabindex="0">Something else here</a></li>
                </ul>
            </li>
            <li><a tabindex="0">Something else here</a></li>
            <li class="divider"></li>
            <li><a tabindex="0">Separated link</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a tabindex="0" data-toggle="dropdown" data-submenu="" aria-expanded="false">
            Dropdown 2<span class="caret"></span>
        </a>

        <ul class="dropdown-menu">
            <li class="dropdown-submenu">
                <a tabindex="0">Action</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">Sub action</a></li>
                    <li class="dropdown-submenu">
                        <a tabindex="0">Another sub action</a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="0">Sub action</a></li>
                            <li><a tabindex="0">Another sub action</a></li>
                            <li><a tabindex="0">Something else here</a></li>
                        </ul>
                    </li>
                    <li><a tabindex="0">Something else here</a></li>
                    <li class="disabled"><a tabindex="-1">Disabled action</a></li>
                    <li class="dropdown-submenu">
                        <a tabindex="0">Another action</a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="0">Sub action</a></li>
                            <li><a tabindex="0">Another sub action</a></li>
                            <li><a tabindex="0">Something else here</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="dropdown-header">Dropdown header</li>
            <li class="dropdown-submenu">
                <a tabindex="0">Another action</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">Sub action</a></li>
                    <li><a tabindex="0">Another sub action</a></li>
                    <li><a tabindex="0">Something else here</a></li>
                </ul>
            </li>
            <li><a tabindex="0">Something else here</a></li>
            <li class="divider"></li>
            <li><a tabindex="0">Separated link</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a tabindex="0" data-toggle="dropdown" data-submenu="">
            Dropdown 3<span class="caret"></span>
        </a>

        <ul class="dropdown-menu">
            <li class="dropdown-submenu">
                <a tabindex="0">Action</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">Sub action</a></li>
                    <li class="dropdown-submenu">
                        <a tabindex="0">Another sub action</a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="0">Sub action</a></li>
                            <li><a tabindex="0">Another sub action</a></li>
                            <li><a tabindex="0">Something else here</a></li>
                        </ul>
                    </li>
                    <li><a tabindex="0">Something else here</a></li>
                    <li class="disabled"><a tabindex="-1">Disabled action</a></li>
                    <li class="dropdown-submenu">
                        <a tabindex="0">Another action</a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="0">Sub action</a></li>
                            <li><a tabindex="0">Another sub action</a></li>
                            <li><a tabindex="0">Something else here</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="dropdown-header">Dropdown header</li>
            <li class="dropdown-submenu">
                <a tabindex="0">Another action</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">Sub action</a></li>
                    <li><a tabindex="0">Another sub action</a></li>
                    <li><a tabindex="0">Something else here</a></li>
                </ul>
            </li>
            <li><a tabindex="0">Something else here</a></li>
            <li class="divider"></li>
            <li><a tabindex="0">Separated link</a></li>
        </ul>
    </li>
</ul>-->


<ul class="nav nav-pills" style="margin-bottom: 200px;">

    <li class="dropdown">
        <a tabindex="0" data-toggle="dropdown" data-submenu="" aria-expanded="false">
            Dropdown 2<span class="caret"></span><!-- 默认选中的-->
        </a>

        <ul class="dropdown-menu">
            <li class="dropdown-submenu">
                <a tabindex="0">陕西省</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">西安市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">宝鸡市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">咸阳市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                </ul>
            </li>
            <li class="divider"></li>
            <!--分割线-->


            <li class="dropdown-submenu">
                <a tabindex="0">山西省</a>
                <ul class="dropdown-menu">
                    <li><a tabindex="0">Sub ac</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">Another </a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">Somethin</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                </ul>
            </li>
            <li class="divider"></li>
            <!--分割线-->

            <li class="dropdown-submenu">
                <a tabindex="0">陕西省</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">西安市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">宝鸡市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">咸阳市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                </ul>
            </li>
            <li class="divider"></li>
            <!--分割线-->

            <li class="dropdown-submenu">
                <a tabindex="0">陕西省</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">西安市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">宝鸡市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">咸阳市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                </ul>
            </li>
            <li class="divider"></li>
            <!--分割线-->

            <li class="dropdown-submenu">
                <a tabindex="0">陕西省</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">西安市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">宝鸡市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">咸阳市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                </ul>
            </li>
            <li class="divider"></li>
            <!--分割线-->

            <li class="dropdown-submenu">
                <a tabindex="0">陕西省</a>

                <ul class="dropdown-menu">
                    <li><a tabindex="0">西安市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">宝鸡市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                    <li><a tabindex="0">咸阳市</a></li>
                    <li class="divider"></li>
                    <!--分割线-->
                </ul>
            </li>
            <li class="divider"></li>
            <!--分割线-->
        </ul>
    </li>
</ul>

</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!--  <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script> -->
<script src="<%=basePath %>/static/jquery-1.11.3/jquery-1.11.3.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

<script src="<%=basePath %>/static/bootstrap-submenu-2.0.4-dist/js/bootstrap-submenu.min.js" defer></script>
<script>
var d;
	
	//region
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
		
	  	//var child = "<li class='dropdown-submenu'><a tabindex='0' id='110000'>nnnn</a><ul class='dropdown-menu'><li><a tabindex='0' id='110100'>北京市</a></li><li class='divider'></li></ul></li><li class='divider'></li>";
		$("#provinceChild").append(e1);
		//$("#provinceChild").html(child);
		$('[data-submenu]').submenupicker();//这个只能用一次
		
		$("#provinceChild").find("ul>li>a").click(function () {
            
  			 var ccc = "<a class='province' id='"+$(this).attr('id')+"' tabindex='0' data-toggle='dropdown' data-submenu="+''+" aria-expanded='false' style='height:32px;padding-top: 0;padding-bottom: 0;padding-left: 10px;padding-right: 10px;color: #000000'>"+
   			$(this).text()+" <span class='caret'></span></a>";
                
            //var text = $(this).text() + " <span class='caret'></span>";
          
          	// 	 alert(text);
         	//  var id = $(this).id();
            //alert(id+","+text);
            // alert(text);
      		// $("#province").html(ccc);
      	 $('.province').replaceWith(ccc); 
            //在这里发送请求获取医生列表            
      		var regionId = $(".province").attr('id');
			var hospitalId = $("[data-flag='hospitals']").attr('id');
			var departmentId = $("[data-flag='hospitals']").attr('id');
		
			getDoctors();
       });
	}
	
	function showDoctors(data) {
		 //<form class="form-horizontal" id="doctors">
		 var content = '';
		 for(var i=0;i<data.length;i++) {
			 content +=
		     "<div class='form-group' style='margin-top: 60px'>"+
		     	"<label class='col-xs-2 control-label test'>"+
		     		"<img src='"+data[i].photo+"' style='width: 65px;width:64px;margin-bottom: 2px;'>"+
		     	"</label>"+
	
	         	"<div class='col-xs-10'>"+
	              	"<div style='margin-left: 15%;'>"+
	                   "<div style='font-size: 20px;font-weight:bold'>"+data[i].doctorName+"</div>"+
	                   "<div style='font-size: 14px;'><span>"+data[i].doctorName+"</span> | <span>"+data[i].positionName+"</span></div>"+
	                   "<div style='font-size: 14px;'>"+data[i].hospitalName+"</div>"+
	               "</div>"+
	           "</div>"+
	       "</div>"+
	       "<hr>";
		 }
		 $("#doctors").html(content);
	}
	
	function getDoctors() {
		var regionId = $(".province").attr('id');
		var hospitalId = $("[data-flag='hospitals']").attr('id');
		var departmentId = $("[data-flag='hospitals']").attr('id');
		
		//getDoctors(regionId,hospitalId,departmentId);
		var curPage = 1;
		//alert("regionId="+regionId+",hospitalId="+hospitalId+",departmentId="+departmentId);	
		
		var getDoctorsUrl = "<%=basePath%>doctor/getDoctors/"+regionId;
console.log(getDoctorsUrl);
		var warn = 
		"<div id='dataLoad' style='position:relative;top:100px;'>"+
			"<table width='100%;height=100%;border=0;align=center;valign=middle;'>"+
				"<tr height='50%'><td align='center'>&nbsp;</td></tr>"+
				"<tr><td align='center'><img src='<%=basePath %>/static/img/loading.gif'/></td></tr>"+
				"<tr><td align='center'>数据载入中，请稍后......</td></tr>"+
				"<tr height='50%'><td align='center'>&nbsp;</td></tr>"+
			"</table>"+
		"</div>";
		$("#doctors").html(warn);
		$("#dataLoad").show();
		 //向后台发送处理数据
		 $.ajax({
			url : getDoctorsUrl,
			type : 'get',
			cache : false,
			dataType : 'json',
			/* data : {
			      'userName' : $("#doc-vld-name-2-1").val(),
			      'userMobile' :$("#doc-vld-tel-2-1").val(),
			      'description' :$("#doc-description-1").val(),
			      'advice' :$("#doc-advice-1").val()
			     }, */
	    	 data : {
		      'departmentId' : departmentId,
		      'hospitalId' : hospitalId,
		      'currentPage' : curPage
		     },
			success : function(data) {
				 $("#dataLoad").hide();
				d = data;
				console.log(data);
				if(data.result = 'success') {
					//alert("数据="+data.data.length);
					showDoctors(data.data);
				}else {
					alert("获取医生列表失败，请稍后再试！");
				}
				/* initArea(data.areas);
				initHospitals(data.hospitals);
				initDeparments(data.deparments);
				initFirstData(data);//初始化选中参数 */
				/* if (data.msg == "OK") {
					alert("问题反馈提交完成。");
					wx.closeWindow();
				} else {
					alert(data.msg);
				} */
			},
			error : function(data) {
				alert("获取医生列表失败，请稍后再试。");
			}
		});  
		
	}
	
	//医院
	function initHospitals(hospitals) {
	
     var childs = '';
		for(var i=0;i<hospitals.length;i++) {
			childs += 
			"<li><a href='#' id='"+hospitals[i].id+"'>"+hospitals[i].hospitalName+"</a></li>"+
	        "<li class='divider'></li>";
		}
		$("[data-flag='hospitalChild']").html(childs);
		$("[data-flag='hospitalChild']").find("a").each(function() {
			$(this).click(function() {
				$("[data-flag='hospitals']").text($(this).text());
				$("[data-flag='hospitals']").attr('id',$(this).attr('id'));
				
				var regionId = $(".province").attr('id');
				var hospitalId = $("[data-flag='hospitals']").attr('id');
				var departmentId = $("[data-flag='hospitals']").attr('id');
				
//	getDoctors(regionId,hospitalId,departmentId);
			});
		})
	}
	
	//科室
	function initDeparments(deparments) {
		 var childs = '';
		 for(var i=0;i<deparments.length;i++) {//<ul class="dropdown-menu" role="menu" class="deparmentChild" id="">
			childs += 
			 "<li><a href='#' id='"+deparments[i].id+"'>"+deparments[i].departmentName+"</a></li>"+
             "<li class='divider'></li>";
		}
		//$(".deparmentChild").html(childs); 
		$("[data-flag='deparmentChild']").html(childs);
		$("[data-flag='deparmentChild']").find("a").each(function() {
			$(this).click(function() {
				$("[data-flag='deparments']").text($(this).text());
				$("[data-flag='deparments']").attr('id',$(this).attr('id'));
				getDoctors();
			});
		})
	}
	
	function initFirstData(data) {
		$(".province").attr('id',data.currentProvinceId);
		$("[data-flag='hospitals']").attr('id',data.currentHospitalId);
		$("[data-flag='deparments']").attr('id',data.currentDepartmentId);
	}

    $(function () {

        //省
        /*  $("#province").click(function () {
         $("#provinceChild").find("li").each(function () {
         $(this).remove();
         })
         //这里异步请求获取省列表
         var child = "<li><a href='#' >西安市</a></li><li><a href='#' >北京市</a></li>";
         //$(this).appendChild(child);
         $("#provinceChild").append(child);

         $("#provinceChild").find("a").click(function () {
         //alert($(this).)
         var text = $(this).text() + " <span class='caret'></span>";
         // alert(text);
         $("#province").html(text);
         //在这里发送请求获取医生列表
         })
         });*/

      //向后台发送处理数据 //dataType : 'json',
      /* data : {
				      'userName' : $("#doc-vld-name-2-1").val(),
				      'userMobile' :$("#doc-vld-tel-2-1").val(),
				      'description' :$("#doc-description-1").val(),
				      'advice' :$("#doc-advice-1").val()
				     }, */
				     
	/* $.getJSON("http://192.168.5.100:8080/ehuizhen/api/v1/init_v2?id=0&jsoncallback=?",function(json){ 
		alert(json);
	}); */
	
	
		<%--  $.ajax(
			{
				url : '<%=basePath%>/weixin/getdoctorsJson',
				type : 'get',
				
				success : function(data) {
					ok(data);
					/* if (data.msg == "OK") {
						alert("问题反馈提交完成。");
						wx.closeWindow();
					} else {
						alert(data.msg);
					} */
				},
				error : function(data) {
					alert("问题反馈提交失败，请稍后提交。");
				}
			}
		);  --%>

		//向后台发送处理数据
		 $.ajax({
			url : '<%=basePath%>/doctor/initData',
			type : 'get',
			cache : false,
			dataType : 'json',
			/* data : {
			      'userName' : $("#doc-vld-name-2-1").val(),
			      'userMobile' :$("#doc-vld-tel-2-1").val(),
			      'description' :$("#doc-description-1").val(),
			      'advice' :$("#doc-advice-1").val()
			     }, */
			success : function(data) {
				if(data.result = 'success') {
					d = data;
					console.log(data);
					initArea(data.areas);
					initHospitals(data.hospitals);
					initDeparments(data.deparments);
					initFirstData(data);//初始化选中参数
					getDoctors();
				}else {
					alert("初始化数据失败，请稍后再试。");
				}
				/* if (data.msg == "OK") {
					alert("问题反馈提交完成。");
					wx.closeWindow();
				} else {
					alert(data.msg);
				} */
			},
			error : function(data) {
				alert("初始化数据失败，请稍后再试。");
			}
		}); 
		
        
         
        /*  $.ajax(  
        		    {  
        		        type:'get',  
        		        url : 'http://www.youxiaju.com/validate.php?loginuser=lee&loginpass=123456',  
        		        dataType : 'jsonp',  
        		        jsonp:"jsoncallback",  
        		        success  : function(data) {  
        		            alert("用户名："+ data.user +" 密码："+ data.pass);  
        		        },  
        		        error : function() {  
        		            alert('fail');  
        		        }  
        		    }  
        		);   */
         
         
        //医院
        $("#hospital").click(function () {
            $("#hospitalChild").find("li").each(function () {
                $(this).remove();
            })
            //这里异步请求获取省列表
            var child = "<li><a href='#' >西京医院</a></li> <li class='divider'></li><li><a href='#' >唐都医院</a></li> <li class='divider'></li>";
            //$(this).appendChild(child);
            $("#hospitalChild").append(child);

            $("#hospitalChild").find("a").click(function () {
                //alert($(this).)
                var text = $(this).text() + " <span class='caret'></span>";
                // alert(text);
                $("#hospital").html(text);
                //在这里发送请求获取医生列表
            })
        });


        //科室
        $("#dept").click(function () {
            $("#deptChild").find("li").each(function () {
                $(this).remove();
            })
            //这里异步请求获取省列表
            var child = "<li><a href='#' >西安市</a></li><li class='divider'></li><li><a href='#' >北京市</a></li><li class='divider'></li>";
            //$(this).appendChild(child);
            $("#deptChild").append(child);

            $("#deptChild").find("a").click(function () {
                //alert($(this).)
                var text = $(this).text() + " <span class='caret'></span>";
                // alert(text);
                $("#dept").html(text);
                //在这里发送请求获取医生列表
            })
        });

    });


</script>
</body>
</html>