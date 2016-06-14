package com.ehuizhen.weixin.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ehuizhen.weixin.config.ConstantClass;
import com.ehuizhen.weixin.config.EnumClass.OrderStatus;
import com.ehuizhen.weixin.model.BrowseHistoryModel;
import com.ehuizhen.weixin.model.CourseAssistModel;
import com.ehuizhen.weixin.model.CourseModel;
import com.ehuizhen.weixin.model.FeedbackModel;
import com.ehuizhen.weixin.model.OrderModel;
import com.ehuizhen.weixin.model.SmsModel;
import com.ehuizhen.weixin.model.UserModel;
import com.ehuizhen.weixin.pojo.SNSUserInfo;
import com.ehuizhen.weixin.pojo.WeixinOauth2Token;
import com.ehuizhen.weixin.service.DoctorService;
import com.ehuizhen.weixin.service.WeiXinService;
import com.ehuizhen.weixin.tools.AdvancedUtil;
import com.ehuizhen.weixin.tools.CommonUtil;
import com.ehuizhen.weixin.wxpay.RequestHandler;
import com.ehuizhen.weixin.wxpay.client.TenpayHttpClient;
import com.ehuizhen.weixin.wxpay.util.Sha1Util;
import com.ehuizhen.weixin.wxpay.util.WXUtil;
import com.ehuizhen.weixin.wxpay.util.XMLUtil;

import net.sf.json.JSONObject;

@Controller
public class WeiXinController {
	@Autowired
	WeiXinService weiXinService;
	@Autowired
	AdvancedUtil util;
	
	@Autowired
	private DoctorService doctorService;
	
	/**
	 * 跳转到医生列表页面 
	 * @return
	 */
	/*@RequestMapping(value = "/weixin/doctorList", method = RequestMethod.GET)
	public ModelAndView doctorList( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {//@PathVariable("orderId") String orderId,
		ModelAndView model = new ModelAndView("/weixin/doctorList");
		// 获取用户信息
		String code = request.getParameter("code");
		return model;
	}*/
	
	/**
	
	 * @return
	 */
/*	@RequestMapping(value = "/weixin/getdoctorsJson", method = RequestMethod.GET)
	public  @ResponseBody JSONObject getJson(HttpServletRequest request,HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", "true");
		 
		JSONObject result =  doctorService.getDoctorList();
		 return result;
	}*/
	
	/*@RequestMapping(value = "/weixin/getdoctorsJson", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String getJson(HttpServletRequest request,HttpServletResponse response) {
		String result =  doctorService.getDoctorList();
			try {
		response.setCharacterEncoding("utf-8");
			PrintWriter w = response.getWriter();
			w.print(result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return result;
	}*/
	

	
	
	@RequestMapping(value = "/weixin/team/order", method = RequestMethod.POST)
	public @ResponseBody JSONObject teamPrePay(@Valid OrderModel order) {
		JSONObject jsonObject = new JSONObject();
		// 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
		String out_trade_no = ConstantClass.GenerateOutTradeNo("T");
		// 商户内部订单
		order.setOrderId(out_trade_no);
		order.setCourseId(-1);
		order.setStatus(OrderStatus.ORDER_PAYING.getValue());
		order.setInDate(new Date());
		try {
			// 生成商户内部订单
			weiXinService.CreateOrder(order);
			jsonObject.put("flag", "true");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("flag", "false");
		}
		return jsonObject;
	}

	@RequestMapping("/weixin/viporder/{userId:\\d+}")
	public ModelAndView vipPrePay(@PathVariable("userId") int userId,
			@RequestParam(value = "courseId", required = false, defaultValue = "-1") int courseId) {
		ModelAndView model = new ModelAndView();

		// 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
		String out_trade_no = ConstantClass.GenerateOutTradeNo("VIP");
		// 商户内部订单
		OrderModel order = new OrderModel();
		order.setOrderId(out_trade_no);
		order.setUserId(userId);
		order.setCourseId(courseId);
		order.setStatus(OrderStatus.ORDER_PAYING.getValue());
		order.setAmt(ConstantClass.VIP_FEE);
		order.setInDate(new Date());
		try {
			// 生成商户内部订单
			weiXinService.CreateOrder(order);
			model.setViewName("weixin/prePay");
			model.addObject("orderId", out_trade_no);
			model.addObject("appId", ConstantClass.WXPAY_APP_ID);
			model.addObject("title", "百万销售成长营VIP会员费朋友代付");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping("/weixin/courseorder/{userId:\\d+}/{courseId:\\d+}")
	public ModelAndView coursePrePay(@PathVariable("userId") int userId, @PathVariable("courseId") int courseId) {
		ModelAndView model = new ModelAndView();
		// 查询课程信息
		CourseModel course = weiXinService.getCourseDetail(courseId);
		// 查询到课程信息
		if (course != null) {
			// 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
			String out_trade_no = ConstantClass.GenerateOutTradeNo();
			// 商户内部订单
			OrderModel order = new OrderModel();
			order.setOrderId(out_trade_no);
			order.setUserId(userId);
			order.setCourseId(courseId);
			order.setStatus(OrderStatus.ORDER_PAYING.getValue());
			order.setAmt(course.getAmt());
			order.setInDate(new Date());
			try {
				// 生成商户内部订单
				weiXinService.CreateOrder(order);
				model.setViewName("weixin/normalPay");
				model.addObject("orderId", out_trade_no);
				model.addObject("appId", ConstantClass.WXPAY_APP_ID);
				model.addObject("title", "百万销售成长营课程费朋友代付");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return model;
	}

	@RequestMapping(value = "/weixin/signature", method = RequestMethod.POST)
	public @ResponseBody JSONObject wxJsAuthUrl(HttpServletRequest request, String url) {
		// 随机字符串
		String nonceStr = WXUtil.getNonceStr();
		// 时间戳
		String timeStamp = WXUtil.getTimeStamp();

		String token = CommonUtil.getToken(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET).getAccessToken();

		String tiket = CommonUtil.getJsApiTicket(token);

		String signStr = "jsapi_ticket=" + tiket + "&noncestr=" + nonceStr + "&timestamp=" + timeStamp + "&url=" + url;

		String signature = Sha1Util.getSha1(signStr);
		JSONObject ret = new JSONObject();
		ret.put("appId", ConstantClass.WXPAY_APP_ID);
		ret.put("nonceStr", nonceStr);
		ret.put("timestamp", timeStamp);
		ret.put("signature", signature);
		return ret;
	}

	/**
	 * 微信支付
	 * 
	 * @return
	 */
	@RequestMapping("/weixin/pay/{orderId}")
	public ModelAndView vippay(@PathVariable("orderId") String orderId, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView model = new ModelAndView("/weixin/jsPay");
		// 获取用户信息
		String code = request.getParameter("code");
		WeixinOauth2Token weixinOauth2Token = null;
		if (!"authdeny".equals(code)) {
			weixinOauth2Token = util.getOauth2AccessToken(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET,
					code);
			if (weixinOauth2Token == null) {
				model.addObject("errormsg", "授权失败");
				return model;
			}
		}

		String accessToken = weixinOauth2Token.getAccessToken();
		String openId = weixinOauth2Token.getOpenId();
		SNSUserInfo snsUserInfo = util.getSNSUserInfo(accessToken, openId);
		// 获取openID
		String curOpenid = snsUserInfo == null ? "" : snsUserInfo.getOpenId();

		// 查询本地订单
		OrderModel order = weiXinService.getOrderDetail(orderId);
		if (order == null) {
			model.addObject("errormsg", "支付失败");
			return model;
		}

		String orderOpenid = order.getUserInfo().getWxid();

		// 判断是否是代付
		if (orderOpenid.equals(curOpenid)) {
			model.addObject("btnText", "立即支付");
		} else {
			model.addObject("btnText", "立即代" + order.getUserInfo().getName() + "支付");
		}

		// 随机字符串
		String nonce_Str = WXUtil.getNonceStr();
		// 时间戳
		String timeStamp = WXUtil.getTimeStamp();
		// 构造请求数据
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", ConstantClass.WXPAY_APP_ID);
		parameters.put("mch_id", ConstantClass.WXPAY_MCHID);
		parameters.put("nonce_str", nonce_Str);

		// 如果是购买VIP
		if (orderId.startsWith("VIP")) {
			parameters.put("body", "购买VIP，单号：" + orderId);
			model.addObject("title", "百万销售成长营VIP会员费");
		} else {
			parameters.put("body", "购买课程，单号：" + orderId);
			model.addObject("title", "百万销售成长营课程费");
		}
		parameters.put("out_trade_no", orderId);
		Integer amt = Integer.parseInt(new java.text.DecimalFormat("0").format(order.getAmt() * 100));
		parameters.put("total_fee", amt.toString());
		parameters.put("spbill_create_ip", ConstantClass.getIp(request));
		parameters.put("notify_url", ConstantClass.WXPAY_NOTIFY_URL);
		parameters.put("trade_type", "JSAPI");
		parameters.put("openid", curOpenid);

		TenpayHttpClient client = new TenpayHttpClient();
		try {
			// 生成签名
			String sign = XMLUtil.createSign("UTF-8", parameters);
			parameters.put("sign", sign);
			// 生成请求XML
			String requestXML = XMLUtil.getRequestXml(parameters);
			// 发送请求，预约下单
			client.callHttpPost(ConstantClass.WXPAY_UNIFIED_ORDER_URL, requestXML);
			// 获取下单结果
			String result = client.getResContent();
			model.addObject("result", result);
			// 结果转换成MAP
			Map<Object, Object> orderMap = XMLUtil.doXMLParse(result);
			if (!orderMap.containsKey("appid") || !orderMap.containsKey("prepay_id")
					|| orderMap.get("prepay_id").toString().equals("")) {
				model.addObject("errormsg", "[" + orderMap.get("err_code_des").toString() + "]");
				return model;
			}
			// 得到预支付ID
			String prepay_id = orderMap.get("prepay_id").toString();
			String signType = "MD5";

			SortedMap<String, String> paySignParameters = new TreeMap<String, String>();
			paySignParameters.put("appId", ConstantClass.WXPAY_APP_ID);
			paySignParameters.put("timeStamp", timeStamp);
			paySignParameters.put("nonceStr", nonce_Str);
			paySignParameters.put("package", "prepay_id=" + prepay_id);
			paySignParameters.put("signType", signType);

			// 生成支付签名,这个签名 给 微信支付的调用使用
			RequestHandler reqHandler = new RequestHandler(request, response);
			reqHandler.init(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET, ConstantClass.WXPAY_KEY);
			String paySign = reqHandler.createSign(paySignParameters);

			// 返回到支付页面
			model.addObject("paySign", paySign);
			model.addObject("appid", ConstantClass.WXPAY_APP_ID);
			model.addObject("timeStamp", timeStamp);
			model.addObject("nonceStr", nonce_Str);
			model.addObject("prepay_id", prepay_id);
			model.addObject("signType", signType);
			model.addObject("amt", order.getAmt());
			model.addObject("courseId", order.getCourseId());

			return model;
		} catch (Exception e) {
			model.addObject("errormsg", "支付失败：" + e.getMessage());
			return model;
		}
	}

	@RequestMapping(name = "/weixin/wxpaynotify", method = RequestMethod.POST)
	public void wxPayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			InputStream inStream = request.getInputStream();
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();
			// 获取微信 返回信息
			String result = new String(outSteam.toByteArray(), "UTF-8");
			// 解析信息到Map对象
			Map<Object, Object> map = XMLUtil.doXMLParse(result);

			// 判断是否包含transaction_id
			if (!map.containsKey("transaction_id")) {
				response.getWriter().write(XMLUtil.setXML("FAIL", "支付结果中微信订单号不存在"));
			}
			String transactionId = map.get("transaction_id").toString();
			// 查询订单
			if (!OrderQuery(transactionId)) {
				response.getWriter().write(XMLUtil.setXML("FAIL", "订单查询失败"));
			} else {
				// 判断支付是否成功
				if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
					// 告诉微信服务器，我收到信息了，不要在调用回调了
					response.getWriter().write(XMLUtil.setXML("SUCCESS", ""));
					// e会诊订单ID
					String orderId = map.get("out_trade_no").toString();
					// 查询本地订单
					OrderModel order = weiXinService.getOrderDetail(orderId);
					// 判断本地订单状态 == 未支付
					if (order != null && order.getStatus() == OrderStatus.ORDER_PAYING.getValue()) {
						// 修改本地订单
						weiXinService.payOrder(transactionId, orderId);
						// 写入历史数据
						weiXinService.historyInsert(orderId);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 查询订单
	 * 
	 * @param WxPayData
	 *            inputObj 提交给查询订单API的参数
	 * @param int
	 *            timeOut 超时时间
	 * @throws WxPayException
	 * @return 成功时返回订单查询结果，其他抛异常
	 */
	public static boolean OrderQuery(String transaction_id) throws Exception {
		String url = "https://api.mch.weixin.qq.com/pay/orderquery";
		// 检测必填参数
		if (transaction_id == null || transaction_id.equalsIgnoreCase("")) {
		}
		// 构造请求数据
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();

		parameters.put("transaction_id", transaction_id);
		parameters.put("appid", ConstantClass.WXPAY_APP_ID);// 公众账号ID
		parameters.put("mch_id", ConstantClass.WXPAY_MCHID);// 商户号
		parameters.put("nonce_str", WXUtil.getNonceStr());// 随机字符串
		parameters.put("sign", XMLUtil.createSign("UTF-8", parameters));// 签名
		String xml = XMLUtil.getRequestXml(parameters);
		TenpayHttpClient client = new TenpayHttpClient();
		client.callHttpPost(url, xml);
		String result = client.getResContent();
		Map<Object, Object> resultMap = XMLUtil.doXMLParse(result);
		if (resultMap != null) {
			if (resultMap.get("return_code").toString().equalsIgnoreCase("SUCCESS")) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping("/example/example")
	public ModelAndView testExample() {
		ModelAndView model = new ModelAndView("/example/example");
		return model;
	}

	@RequestMapping("/weixin/login")
	public ModelAndView wxLogin() {
		ModelAndView model = new ModelAndView("/weixin/login");
		return model;
	}

	@RequestMapping("/weixin/teamregister")
	public ModelAndView wxTeamRegister() {
		ModelAndView model = new ModelAndView("/weixin/teamregister");
		return model;
	}

	@RequestMapping("/weixin/assist/{courseId}")
	public ModelAndView wxAssist(@PathVariable("courseId") int courseId) {
		ModelAndView model = new ModelAndView("/weixin/assist");
		// 课程数据取得
		List<CourseAssistModel> courses = weiXinService.getAssist(0, courseId);
		model.addObject("courses", courses);
		return model;
	}
	
	/**
	 * 课程一览
	 * 
	 * @return
	 */
	@RequestMapping("/weixin/courselist/{menuid}/{wxid}")
	public ModelAndView wxCourseList(@PathVariable("menuid") int menuid, @PathVariable("wxid") String wxid) {
		ModelAndView model = new ModelAndView();
		// 判断该用户是否是注册用户(微信号)
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		// 用户不存在
		if (user == null) {
			model.setViewName("/weixin/register");
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
			model.addObject("user", user);
		} else {
			model.setViewName("/weixin/courselist");
			// 课程数据取得
			List<CourseModel> courses = weiXinService.getCourseList(0, menuid);
			model.addObject("courses", courses);
			// 课辅数据取得
//			List<CourseModel> educations = weiXinService.getEducationList(0, menuid);
//			model.addObject("educations", educations);
		}
		return model;
	}

	@RequestMapping("/weixin/video/{courseId}")
	public ModelAndView wxVideo(@PathVariable("courseId") int courseId) {
		ModelAndView model = new ModelAndView("/weixin/video");
		CourseModel courseInfo = weiXinService.getCourseDetail(courseId);
		if (courseInfo == null) {
			model.addObject("ppts", new ArrayList<String>());
			model.addObject("video", "");
			model.addObject("voice", "");
			model.setViewName("/weixin/video");
			return model;
		}
		
		List<String> ppts = courseInfo.getPptImgs();
		if (ppts.size() == 0) {
			model.addObject("ppts", null);
		} else {
			model.addObject("ppts", ppts);
		}
		model.addObject("video", courseInfo.getVideoUrl());
		model.addObject("voice", courseInfo.getVoiceUrl());
		model.addObject("maintype", courseInfo.getType());
		model.addObject("title", courseInfo.getTitle());
		model.addObject("imgurl", courseInfo.getImgUrl());
		model.addObject("courseId", courseId);
		return model;
	}

	@RequestMapping("/weixin/pay")
	public ModelAndView wxPay() {
		ModelAndView model = new ModelAndView("/weixin/pay");
		return model;
	}

	@RequestMapping("/weixin/person")
	public ModelAndView wxPerson() {
		ModelAndView model = new ModelAndView("/weixin/person");
		
		return model;
	}

	@RequestMapping("/weixin/verifycode")
	public ModelAndView wxVerifycode() {
		SmsModel sms = new SmsModel();
		ModelAndView model = new ModelAndView("/weixin/verifycode");
		model.addObject("sms", sms);
		return model;
	}

	/**
	 * 关于
	 * 
	 * @return
	 */
	@RequestMapping("/weixin/about")
	public ModelAndView wxAbout(HttpSession httpSession) {
		ModelAndView model = new ModelAndView("/weixin/about");
		String wxid = (String) httpSession.getAttribute("wxid");
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		if (user == null) {
			// 用户不存在
			model.addObject("aboutbtn", "我要报名");
			model.addObject("actionUrl", "/weixin_test/weixin/register");
		} else {
			// 是VIP用户
			if (user.getLevel() == '1') {
				// 判断用户VIP是否过期
				UserModel vipuser = weiXinService.isVIPFailure(0, user.getId());
				// VIP过期
				if (vipuser != null) {
					// 是普通用户
					model.addObject("aboutbtn", "欢迎继续学习");
					model.addObject("actionUrl", "/weixin_test/weixin/courselist/0/" + user.getWxid());
					model.setViewName("/weixin/about");
					return model;
				}
			}
			// 是普通用户
			model.addObject("aboutbtn", "成为VIP会员，学习全网课程");
			model.addObject("actionUrl", "/weixin_test/weixin/pay/");
		}
		return model;
	}

	@RequestMapping("/weixin/issue")
	public ModelAndView wxIssue() {
		ModelAndView model = new ModelAndView("/weixin/issue");
		return model;
	}

	/**
	 * 获取验证码
	 * 
	 * @param mobile
	 */
	@RequestMapping(value = "/weixin/send/{phoneNum}/verifycode/{rand}", produces = "application/text;charset=UTF-8")
	public @ResponseBody String getVerifyCode(@PathVariable("phoneNum") String mobile,
			@PathVariable("rand") String rand, HttpSession httpSession) {
		if (mobile == null || mobile.equals("")) {
			return "手机号不能为空。";
		}
		if (!ConstantClass.isMobile(mobile)) {
			return "输入的手机号格式有误，请重新输入。";
		}
		String seesion_rand = (String) httpSession.getAttribute("rand");
		if (!seesion_rand.equals(rand.toLowerCase())) {
			return "输入的图片验证码有误，请重新输入。";
		}
		// 判断该用户是否注册
		if (!weiXinService.isRegistered(mobile)) {
			if (!weiXinService.getVerifyCode(mobile)) {
				return "验证码获取失败，请重稍后再试。";
			}
		} else {
			return "该手机号已经被注册，请重新输入新的手机号。";
		}
		return "OK";
	}

	/**
	 * 获取验证码
	 * 
	 * @param mobile
	 */
	@RequestMapping(value = "/weixin/send/{phoneNum}/verifycode/{rand}/team", produces = "application/text;charset=UTF-8")
	public @ResponseBody String getTeamVerifyCode(@PathVariable("phoneNum") String mobile,
			@PathVariable("rand") String rand, HttpSession httpSession) {
		if (mobile == null || mobile.equals("")) {
			return "手机号不能为空。";
		}
		if (!ConstantClass.isMobile(mobile)) {
			return "输入的手机号格式有误，请重新输入。";
		}
		String seesion_rand = (String) httpSession.getAttribute("teamrand");
		if (!seesion_rand.equals(rand.toLowerCase())) {
			return "输入的图片验证码有误，请重新输入。";
		}
		// 判断该用户是否注册
		if (!weiXinService.isRegistered(mobile)) {
			if (!weiXinService.getVerifyCode(mobile)) {
				return "验证码获取失败，请重稍后再试。";
			}
		} else {
			return "该手机号已经被注册，请重新输入新的手机号。";
		}
		return "OK";
	}

	/**
	 * 验证码有效性
	 * 
	 * @param mobile
	 */
	@RequestMapping(value = "/weixin/send/validity")
	public ModelAndView isVerifyCodeValidity(@Valid SmsModel sms) {

		ModelAndView model = new ModelAndView();
		// 验证有效性
		if (weiXinService.isVerifyCodeValidity(sms)) {
			// 给Model赋值
			UserModel user = new UserModel();
			user.setMobile(sms.getMobile());
			model.setViewName("/weixin/register");
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
			model.addObject("user", user);
		} else {
			model.setViewName("/weixin/verifycode");
			sms.setVerifyCode("");
			model.addObject("sms", sms);
			model.addObject("msg", "验证码已失效，请重获取。");
		}

		return model;
	}

	/**
	 * 用户是否要支付
	 * 
	 * @param userId
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/weixin/ispay/{userId}/{courseId}")
	public ModelAndView wxIsPay(@PathVariable("userId") int userId, @PathVariable("courseId") int courseId) {
		ModelAndView model = new ModelAndView();
		// 课程免费
		CourseModel courseInfo = weiXinService.getCourseDetail(courseId);
		if (courseInfo == null) {
			model.addObject("ppts", new ArrayList<String>());
			model.addObject("video", "");
			model.addObject("voice", "");
			model.setViewName("/weixin/video");
			return model;
		}

		// 课程收费
		if (courseInfo.getAmt() != 0) {
			// 判断该用户是否是注册用户
			UserModel user = weiXinService.getRegister(userId);
			if (user == null) {
				// 用户不存在
				model.setViewName("/weixin/register");
				model.addObject("user", user);
				model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
				return model;
			}

			
			// 用户是普通用户
			if (user.getLevel() == '0') {
				// 判断用户是否购买当前课程
				OrderModel orderNormal = weiXinService.getIsOrder(userId, courseId);
				
				boolean needPay = orderNormal == null;
				// 普通用户需要判断是否过期
				if(orderNormal != null){
					if(orderNormal.getStatus() != 2){
						needPay = true;
					}else{
						Date now = new Date();
						Calendar cal = Calendar.getInstance();
						cal.setTime(orderNormal.getInDate());
						cal.add(Calendar.YEAR, 1);
						Date exdate = cal.getTime();
						needPay = now.after(exdate);
					}
				}
				// 需要支付
				if(needPay) {
					model.setViewName("/weixin/listdetail");
					model.addObject("course", courseInfo);
					return model;
				}
			} else if (user.getLevel() == '1') {
				// 判断用户是否购买当前课程
				OrderModel order = weiXinService.getIsOrder(userId, courseId);
				// 判断用户VIP是否过期
				UserModel vipuser = weiXinService.isVIPFailure(0, userId);
				// VIP过期
				if (vipuser == null) {
					// 并且未支付
					if (order == null || order.getStatus() == 0) {
						model.setViewName("/weixin/listdetail");
						model.addObject("course", courseInfo);
						return model;
					}
				}
			}
		} else {
			// 判断该用户是否是注册用户
			UserModel user = weiXinService.getRegister(userId);
			if (user == null) {
				// 用户不存在
				model.setViewName("/weixin/register");
				model.addObject("user", user);
				model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
				return model;
			}
		}
		
		// 向历史记录添加一条记录
		BrowseHistoryModel history = weiXinService.isHistoryList(userId, courseId);
		if (history == null) {
			weiXinService.historyStudyInsert(userId, courseId);
		}
		
		List<String> ppts = courseInfo.getPptImgs();
		if (ppts.size() == 0) {
			model.addObject("ppts", null);
		} else {
			model.addObject("ppts", ppts);
		}
		model.addObject("video", courseInfo.getVideoUrl());
		model.addObject("voice", courseInfo.getVoiceUrl());
		model.addObject("maintype", courseInfo.getType());
		model.addObject("title", courseInfo.getTitle());
		model.addObject("imgurl", courseInfo.getImgUrl());
		model.addObject("courseId", courseId);
		model.setViewName("/weixin/video");
		return model;
	}

	/**
	 * 学习的历史记录
	 * 
	 * @return
	 */
	@RequestMapping("/weixin/historylist/{wxid}")
	public ModelAndView wxHistoryList(@PathVariable("wxid") String wxid) {
		// 判断该用户是否是注册用户
		ModelAndView model = new ModelAndView();
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		if (user == null) {
			model.setViewName("/weixin/register");
			model.addObject("user", user);
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
		} else {
			List<BrowseHistoryModel> historyModel = user.getHistoryInfo();
			// 给Model赋值
			model.setViewName("weixin/history");
			model.addObject("courses", historyModel);
			model.addObject("userId", user.getId());
		}
		return model;
	}

	/**
	 * 问题反馈画面接口
	 * 
	 * @return
	 */
	@RequestMapping("/weixin/feedback{wxid}")
	public ModelAndView wxFeedback(@PathVariable("wxid") String wxid) {
		ModelAndView model = new ModelAndView();
		// 获取个人信息
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		if (user == null) {
			model.setViewName("/weixin/register");
			model.addObject("user", user);
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
		} else {
			FeedbackModel feedback = new FeedbackModel();
			// 给Model赋值
			model.setViewName("/weixin/feedback");
			model.addObject("actionUrl", "/weixin_test/weixin/feedback/insert");
			model.addObject("feedback", feedback);
		}
		return model;
	}

	@RequestMapping("/weixin/feedback/insert")
	public @ResponseBody JSONObject feedbackInsert(@Valid FeedbackModel feedback) {
		JSONObject jsonObject = new JSONObject();
		// 判断提交是否成功
		if (weiXinService.feedbackInsert(feedback)) {
			//feedback = new FeedbackModel();
			//model.setViewName("/weixin/person");
			jsonObject.put("msg", "OK");
		} else {
			jsonObject.put("msg", "反馈意见提交失败，请稍后重试。");
		}
		// 给Model赋值
		return jsonObject;
	}

	/**
	 * 修改个人信息
	 * 
	 * @param wxid
	 * @return
	 */
	@RequestMapping("/update/register/{wxid}")
	public ModelAndView updateRegister(@PathVariable("wxid") String wxid) {
		// 获取个人信息
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		// 给Model赋值
		ModelAndView model = new ModelAndView();
		if (user == null) {
			model.setViewName("/weixin/register");
			model.addObject("user", user);
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
		} else {
			model.setViewName("/weixin/updateregister");
			model.addObject("actionUrl", "/weixin_test/weixin/register/update");
			model.addObject("user", user);
		}
		return model;
	}

	/**
	 * 修改个人信息update数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/weixin/register/update", method = RequestMethod.POST)
	public ModelAndView registerUpdate(@Valid UserModel user) {
		ModelAndView model = new ModelAndView();
		// 判断提交是否成功
		if (weiXinService.registerUpdate(user)) {
			// 给Model赋值
			model.setViewName("/weixin/person");
			model.addObject("user", user);
		} else {
			// 给Model赋值
			model.setViewName("/weixin/updateregister");
			model.addObject("user", user);
			model.addObject("actionUrl", "/weixin_test/weixin/register/update");
			model.addObject("msg", "注册信息修改失败，请稍后重试。");
		}
		return model;
	}

	/**
	 * 注册个人信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/weixin/register")
	public ModelAndView wxRegister(@Valid UserModel user) {
		// 给Model赋值
		ModelAndView model = new ModelAndView("/weixin/register");
		model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
		model.addObject("actionTeamUrl", "/weixin_test/weixin/register/team/insert");
		model.addObject("user", user);
		return model;
	}

	/**
	 * 先了解一下的注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/weixin/register/know/insert", method = RequestMethod.POST)
	public @ResponseBody JSONObject registerKnowInsert(@Valid UserModel user) {
		JSONObject jsonObject = new JSONObject();
		if (user.getMobile() == null || user.getMobile().equals("")) {
			jsonObject.put("msg", "手机号不能为空。");
			return jsonObject;
		}
		if (!ConstantClass.isMobile(user.getMobile())) {
			jsonObject.put("msg", "输入的手机号格式有误，请重新输入。");
			return jsonObject;
		}
		// 判断该用户是否已经注册
		UserModel userModel = weiXinService.isWeiXinRegister(user.getWxid());
		if (userModel != null) {
			jsonObject.put("msg", "该用户已经被注册，请重新注册。");
		} else {
			SmsModel sms = new SmsModel();
			sms.setMobile(user.getMobile());
			sms.setVerifyCode(user.getVerifyCode());
			// 验证有效性
			if (weiXinService.isVerifyCodeValidity(sms)) {
				// 判断提交是否成功
				int userid = weiXinService.registerInsert(user);
				if (userid > 0) {
					jsonObject.put("msg", "OK");
				} else {
					jsonObject.put("msg", "注册信息提交失败，请稍后重试。");
				}
			} else {
				jsonObject.put("msg", "验证码已失效，请重获取。");
			}
		}
		return jsonObject;
	}
	
	/**
	 * 注册个人信息ADD数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/weixin/register/insert", method = RequestMethod.POST)
	public ModelAndView registerInsert(@Valid UserModel user) {
		ModelAndView model = new ModelAndView();
		if (user.getMobile() == null || user.getMobile().equals("")) {
			model.setViewName("/weixin/register");
			model.addObject("user", user);
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
			model.addObject("msg", "手机号不能为空。");
			return model;
		}
		if (!ConstantClass.isMobile(user.getMobile())) {
			model.setViewName("/weixin/register");
			model.addObject("user", user);
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
			model.addObject("msg", "输入的手机号格式有误，请重新输入。");
			return model;
		}
		// 判断该用户是否已经注册
		UserModel userModel = weiXinService.isWeiXinRegister(user.getWxid());
		if (userModel != null) {
			// 给Model赋值
			model.setViewName("/weixin/register");
			model.addObject("user", user);
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
			model.addObject("msg", "该用户已经被注册，请重新注册。");
		} else {
			SmsModel sms = new SmsModel();
			sms.setMobile(user.getMobile());
			sms.setVerifyCode(user.getVerifyCode());
			// 验证有效性
			if (weiXinService.isVerifyCodeValidity(sms)) {
				// 判断提交是否成功
				int userid = weiXinService.registerInsert(user);
				if (userid > 0) {
					return vipPrePay(userid, -1);
				} else {
					// 给Model赋值
					model.setViewName("/weixin/register");
					model.addObject("user", user);
					model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
					model.addObject("msg", "注册信息提交失败，请稍后重试。");
				}
			} else {
				model.setViewName("/weixin/register");
				model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
				model.addObject("user", user);
				model.addObject("msg", "验证码已失效，请重获取。");
			}
		}

		return model;
	}

	/**
	 * 注册团队信息ADD数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/weixin/register/team/insert", method = RequestMethod.POST)
	public ModelAndView registerTeamInsert(@Valid UserModel user) {
		ModelAndView model = new ModelAndView();
		if (user.getMobile() == null || user.getMobile().equals("")) {
			model.setViewName("/weixin/register");
			model.addObject("user", user);
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
			model.addObject("msg", "手机号不能为空。");
			return model;
		}
		if (!ConstantClass.isMobile(user.getMobile())) {
			model.setViewName("/weixin/register");
			model.addObject("user", user);
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
			model.addObject("msg", "输入的手机号格式有误，请重新输入。");
			return model;
		}
		// 判断该用户是否已经注册
		UserModel userModel = weiXinService.isWeiXinRegister(user.getWxid());
		if (userModel != null) {
			// 给Model赋值
			model.setViewName("/weixin/register");
			model.addObject("user", user);
			model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
			model.addObject("msg", "该用户已经被注册，请重新注册。");
		} else {
			SmsModel sms = new SmsModel();
			sms.setMobile(user.getMobile());
			sms.setVerifyCode(user.getVerifyCode());
			// 验证有效性
			if (weiXinService.isVerifyCodeValidity(sms)) {
				// 判断提交是否成功
				int userid = weiXinService.registerInsert(user);
				if (userid > 0) {
					// 给Model赋值
					model.setViewName("/weixin/teamregister");
					model.addObject("userId", userid);
					model.addObject("amt", "9995.00");
					model.addObject("actionUrl", "/weixin_test/weixin/team/order");
				} else {
					// 给Model赋值
					model.setViewName("/weixin/register");
					model.addObject("user", user);
					model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
					model.addObject("msg", "注册信息提交失败，请稍后重试。");
				}
			} else {
				model.setViewName("/weixin/register");
				model.addObject("actionUrl", "/weixin_test/weixin/register/insert");
				model.addObject("user", user);
				model.addObject("msg", "验证码已失效，请重获取。");
			}
		}

		return model;
	}
}
