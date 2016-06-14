<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <title>常见问题</title>

  <!-- Set render engine for 360 browser -->
  <meta name="renderer" content="webkit">

  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>

  <link rel="icon" type="image/png" href="<%=basePath%>assets/i/favicon.png">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="<%=basePath%>assets/i/app-icon72x72@2x.png">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
  <link rel="apple-touch-icon-precomposed" href="<%=basePath%>assets/i/app-icon72x72@2x.png">

  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <meta name="msapplication-TileImage" content="<%=basePath%>assets/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">

  <link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css">
  <link rel="stylesheet" href="<%=basePath%>assets/css/app.css">
  
<script type="text/javascript" src="<%=basePath%>js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/example.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.swipebox.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.fitvids.js"></script>
<script type="text/javascript" src="<%=basePath%>js/email.js"></script>

  <script type="text/javascript">
  
  </script>
  
  <style>

  </style>
</head>
<body>

<section data-am-widget="accordion" class="am-accordion am-accordion-gapped" data-am-accordion='{  }'>
      <dl class="am-accordion-item am-active">
        <dt class="am-accordion-title">
          	我们公司想组织一场内训，怎么办？
        </dt>
        <dd class="am-accordion-bd am-collapse am-in">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
         	 答：请联系我们内训部负责人，黄老师15005164308微信号同手机号。
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          	VIP会员有什么权限？
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            	答：可以学习本平台所有线上课程（包括现有和后续更新），对线下活动具有优先权。
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          	VIP会员怎么计算会员期限？
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            	答：从付费成功的这天开始，到明年的今天为止。举例：2016年3月5号付款成功，会员有效期到2017年3月5日24：00为止。
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          	是否可以只听一个课程？
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            	答：可以，只需点击该课程，付费即可。付费成功后，一年内您可在任何时间重复收听此课程。
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          	短信验证码没有收到？
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            	答：系统验证码一般在10秒之内到达，但受到网络信号和通讯设备的影响，少部分用户会有延迟。
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          	账号可以转借吗？
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            	答：不可以，仅支持付款微信账号听课，具有唯一性。
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          	可以退款吗？
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            	答：本课程为线上课程，不支持中途退款。
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          	我们公司一次购买多个会员，优惠吗？
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            	答：请点击团购报名,享受买5赠1。
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          	发票怎么开具？
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
               	答：付费成功后，如需开具发票请发邮件给2102256142@qq.com，邮件主题请写明“申请发票”，
	      		并备注发票抬头和金额，收件人姓名，电话，地址，工作人员核对后开具发票并快递。
	      		（发票金额可累计，500元以下的发票需支付5元快递费）。
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          	除了录制好的课程外，还有其他培训吗？
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            	答：有，可以登录医药培训圈网站（http://www.yiyaopx.com/）或者关注医药培训圈微信公众号了解更多培训。
          </div>
        </dd>
      </dl>
</section>
<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=basePath%>assets/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="<%=basePath%>assets/js/amazeui.min.js"></script>
</body>
</html>