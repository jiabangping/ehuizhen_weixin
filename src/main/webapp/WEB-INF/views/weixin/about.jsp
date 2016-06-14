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
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <title>加入我们</title>

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
	.am-header-fixed {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		width: 100%;
		z-index: 1010;
	}
	
	.button-top {
		border:1px solid #AAAAAA;
		background:#EEEE00;
	};
	
	.button-bottom {
		border:1px solid #AAAAAA;
	};
  </style>
</head>

<body>
<div class="am-g am-g-fixed" style="background:#DDDDDD;">
  <div class="am-fl am-padding-left"><img src="<%=basePath%>img/head.jpg" alt="" class="am-comment-avatar" width="25" height="25"/>百万销售成长营</div>
  <div class="am-fr am-padding-right am-kai"><a href="http://mp.weixin.qq.com/s?__biz=MzI0OTAxMTAzMw==&mid=401512650&idx=1&sn=8a6533b7730ff59a71a6e95709d9982b&scene=0&previewkey=CGd3omQnDmCGNe65BEWqv8NS9bJajjJKzz%2F0By7ITJA%3D#wechat_redirect" id="btnAttention">关注</a></div>
</div>
<article data-am-widget="paragraph" class="am-paragraph am-paragraph-default" data-am-paragraph="{ tableScrollable: true, pureview: true }">
	<p class=paragraph-default-p><strong><h1 style="text-align:center">加入百万销售成长营</h1></strong></p>
	<p class=paragraph-default-p><strong><h2 style="text-align:center">认识销售的规律，了解销售的奥秘</strong></h2></p>
	<p style="text-align:center" class=paragraph-default-p><h3 style="text-align:right;">（文/仲崇玉）</h3></p>
	<img alt="" src="http://114.55.32.114:9998/wx_back/AboutUs/1454581920319.png" style="height:250px; width:100%" />
</article>

<br>
<article data-am-widget="paragraph" class="am-paragraph am-paragraph-default" data-am-paragraph="{ tableScrollable: true, pureview: true }">
<div class="am-form-group">
<p class="paragraph-default-p"><span style="font-size:20px"><strong>A. 优秀的销售是如何成长起来的</strong></span></p>
<p class=paragraph-default-p><span style="font-size:17px">六步甩出几条街——销售代表成长训练</span></p>
<ul>
  	<li style="font-size:18px">第一步，初阶：怎么说+怎么做。现实场景，直接给答案。</li>
  	<li style="font-size:18px">第二步，进阶：学习答案相关的技巧，方法+工具，直接给方法。</li>
  	<li style="font-size:18px">第三步，高阶：学习向内探索，思维习惯的自我洞察，直接给反馈。</li>
  	<li style="font-size:18px">第四步，锋芒：开始向外突破，思维模式的自我调整，拥有体验。</li>
  	<li style="font-size:18px">第五步，优势：设计自己的接触点+推荐力，优势少数，开始收获。</li>
  	<li style="font-size:18px">第六步，传播：思维模式的纵向持续+横向延展，形成节点（品牌）。</li>
</ul>
<p class="paragraph-default-p"><span style="font-size:20px">有品牌的你，还畏惧销售？你说是吗？</span></p>
	<p class="paragraph-default-p am-tab-panel am-active"><span style="font-size:18px">
	在新人不断涌入，而总数已经登顶的营销销售大军中，如何脱颖而出？
	各有各的招，各走各的道，每个人都在试图用自己的方式，破解这一行业阶段性难题。在现有的国情和管理语境下，谈论任何转型，都显得轻率。是不是？
	如何通过学习激发，迅速成为优势的少数，是破解这个阶段性难题的目标。这个目标，除了个人投资，公司是没有兴趣帮你实现的。你想成为优势的少数吗？
	关键是，你愿意与那些已经具有优势的同学交流互动吗？来加入百万销售成长营吧。
</span></p>
</div>
</article>

<br>
<article data-am-widget="paragraph" class="am-paragraph am-paragraph-default" data-am-paragraph="{ tableScrollable: true, pureview: true }">
<div class="am-form-group">
<p class="paragraph-default-p"><span style="font-size:20px"><strong>B.现实</strong></span></p>
<p class=paragraph-default-p><span style="font-size:17px">销售代表，无论你工作多久，都会在这个阶段开始重新思考自身的价值和将来的发展。谁拥有答案呢？No one！但是，无论什么难题，我们总是可以从现状的描述开始解决。</span></p>
<p class=paragraph-default-p><span style="font-size:17px">而在此刻，现实是：</span></p>
<ul>
  	<li style="font-size:18px">1. 从结果的角度：业绩，收入，提升机会，社会地位等等，都似乎触摸到天花板，再走就有撞到了墙的感觉。</li>
  	<li style="font-size:18px">2. 从自我感觉的角度：理直气壮的指数在下降。无论在客户面前，消费者面前，还是在老板面前或者下属面前，工作内容的意义都在受到质疑；乐趣指数在下降，面对压力，或者扛起压力，或者避开压力；面对日常任务，或者勤奋，或者应付，了无新意；面对公司制度。或者遵守，或者擦边，无非效率。</li>
  	<li style="font-size:18px">3. 从技能的角度：幻灯片演讲，拜访技巧，大客户管理，区域管理，再就是领导力，对了还有谈判技巧，可我们有谈判的资格吗？就算有，我们的技能，经得起盘点吗？</li>
  	<li style="font-size:18px">4. 从外向内看：话语权被压缩，活动的地理范围和时间范围都被压缩，无论是正常的线下社交，或者网络空间社交，都在一个局限的同行小圈子进行。</li>
</ul>
</div>
</article>

<br>
<article data-am-widget="paragraph" class="am-paragraph am-paragraph-default" data-am-paragraph="{ tableScrollable: true, pureview: true }">
<div class="am-form-group">
<p class="paragraph-default-p"><span style="font-size:20px"><strong>C.销售能力</strong></span></p>
<p class=paragraph-default-p><span style="font-size:17px">如果您现在都不好过了，说什么将来？
每天都忙不完，拿什么构建将来？
无论如何，销售代表，是该走出自我去看看的时候了。
</span></p>
<ul>
  	<li style="font-size:18px">1. 角色。销售代表只是个标签。如果客户不愿意给你交流，往往是因为你在他心目中只是某个商品的代表：你需要展示你的其他角色属性。</li>
  	<li style="font-size:18px">2. 属性。销售代表的本质属性，是借助商品给客户带来价值。</li>
  	<li style="font-size:18px">3. 行为。销售与交易，有本质上的不同。销售创造价值，而不仅仅是交易。销售代表的行为也不仅仅是服务。服务只是一种沟通的方式。</li>
  	<li style="font-size:18px">4. 价值。创造客户价值，既不是单纯的学术推广，也不是单纯的关系推广，学术和关系互为因果。</li>
  	<li style="font-size:18px">5. 洞察。客户价值的先决条件，是客户洞察，包括客户的共性和个性两个层面的洞察。</li>
  	<li style="font-size:18px">6. 优势。决定业绩的条件，也不仅仅是你给客户带来的价值，而是取决于客户的数量和结构。</li>
  	<li style="font-size:18px">7. 四大能力。销售代表需要随身携带四个能力，包括赢得注意力份额，制定可公开的拜访目标，频道切入技能，目标切换技能等。</li>
</ul>
<p class="paragraph-default-p am-tab-panel am-active"><span style="font-size:18px">
	销售代表获得新能力的根本障碍，不是智力，也不是经验，而是参照系统：习惯于参照过去的成功，参照其他人的成功，参照已经获得的成果，就让自己动弹不得。一句话，最大的障碍是对现状的解释。我们如何通过纵身一跃来跳出窠臼？
</span></p>
</div>
</article>

<br>
<article data-am-widget="paragraph" class="am-paragraph am-paragraph-default" data-am-paragraph="{ tableScrollable: true, pureview: true }">
<div class="am-form-group">
<p class="paragraph-default-p"><span style="font-size:20px"><strong>D.业绩</strong></span></p>
<p class=paragraph-default-p><span style="font-size:17px">结果说话
指标是硬的，奖金是硬的，老板的脸色也是硬的，销售代表用什么来说话？广泛一致的意见是业绩！什么时候的业绩？当然是短期的业绩！销售代表，如何不断拿到稳定的短期业绩？
</span></p>
<ul>
  	<li style="font-size:18px">1. 给老板业绩。业绩就是销售代表的水平。这个观点会让人不服气，以为此刻的业绩不一定就是将来的业绩，所以现在的业绩代表不了我的真正水平。问题是我能不能等得到将来业绩的转机。
</li>
  	<li style="font-size:18px">2. 给老板信心。没有短期的业绩，就一定要给老板信心。没有业绩也没有信心，就再也没有取得业绩的机会，因为老板不再为你等时间，也不再给你投资源。</li>
</ul>
<p class="paragraph-default-p am-tab-panel am-active"><span style="font-size:18px">
	加入百万销售成长营
认识销售的规律，了解销售的奥秘
</span></p>
<p class="paragraph-default-p am-tab-panel am-active"><span style="font-size:18px">
	<h1 style="text-align:center">（VIP会员年费 1999元/人）</h1>
</span></p>
</div>
</article>

<form:form action="${actionUrl}" class="am-form" id="doc-vld-msg" method="POST" modelAttribute="user">
	<div class="am-form-group">
      <input name="wxid" value="${user.wxid}" type="hidden" class="input-medium" id="doc-vld-wx-2-1" />
      <input name="wxurl" value="${user.wxurl}" type="hidden" class="input-medium" id="doc-vld-wxurl-2-1" />
      <input name="nickName" value="${user.nickName}" type="hidden" class="input-medium" id="doc-vld-nickname-2-1" />
    </div>
<div class="am-form-group">
   		<button class="am-btn am-btn-warning am-btn-block am-topbar-fixed-bottom button-bottom am-round" type="submit">${aboutbtn}</button>
</div>
</form:form>
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