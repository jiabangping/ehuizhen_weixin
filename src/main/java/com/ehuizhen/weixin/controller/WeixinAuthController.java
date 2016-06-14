package com.ehuizhen.weixin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ehuizhen.weixin.config.ConstantClass;
import com.ehuizhen.weixin.config.VerifyCodeUtils;
import com.ehuizhen.weixin.model.CourseModel;
import com.ehuizhen.weixin.model.FeedbackModel;
import com.ehuizhen.weixin.model.UserModel;
import com.ehuizhen.weixin.pojo.SNSUserInfo;
import com.ehuizhen.weixin.pojo.WeixinOauth2Token;
import com.ehuizhen.weixin.service.WeiXinService;
import com.ehuizhen.weixin.tools.AdvancedUtil;

@Controller
@SessionAttributes({"wxid", "wxurl", "nickName", "userid", "code", "rand", "teamrand"})
public class WeixinAuthController {
	
	@Autowired
	AdvancedUtil util;
	@Autowired
	WeiXinService weiXinService;
	
	@RequestMapping("/code")
	public void getCode(HttpServletRequest req, HttpServletResponse resp, ModelMap model)throws IOException {
		//生成随机字串  
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4).toLowerCase();  
		//存入会话session
		model.addAttribute("rand", verifyCode);
		//生成图片
		int w = 114, h = 38;
		VerifyCodeUtils.outputImage(w, h, resp.getOutputStream(), verifyCode);
	}
	
	@RequestMapping("/teamcode")
	public void getTeamCode(HttpServletRequest req, HttpServletResponse resp, ModelMap model)throws IOException {
		//生成随机字串  
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4).toLowerCase();  
		//存入会话session
		model.addAttribute("teamrand", verifyCode);
		//生成图片
		int w = 114, h = 38;
		VerifyCodeUtils.outputImage(w, h, resp.getOutputStream(), verifyCode);
	}
	
	@RequestMapping(value = "/feedback", method = RequestMethod.GET)
	public ModelAndView checkWeixinFeedback(HttpServletRequest request, ModelMap model) {
		String wxid = null;
		String wxurl = null;
		String nickName = null;
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		if (!"authdeny".equals(code)) {
			WeixinOauth2Token weixinOauth2Token = util.getOauth2AccessToken(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET, code);
			if (weixinOauth2Token != null) {
				String accessToken = weixinOauth2Token.getAccessToken();
				String openId = weixinOauth2Token.getOpenId();
				SNSUserInfo snsUserInfo = util.getSNSUserInfo(accessToken, openId);
				if (snsUserInfo != null) {
					wxid = snsUserInfo.getOpenId();
					wxurl = snsUserInfo.getHeadImgUrl();
					nickName = snsUserInfo.getNickname();
					model.addAttribute("wxid", wxid);
					model.addAttribute("wxurl", wxurl);
					model.addAttribute("nickName", nickName);
				}
			}
		}
		
		// 获取个人信息
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		if (user == null) {
			mav.setViewName("/weixin/register");
			mav.addObject("user", user);
			mav.addObject("actionUrl", "/weixin_test/weixin/register/insert");
		} else {
			FeedbackModel feedback = new FeedbackModel();
			// 给Model赋值
			mav.setViewName("/weixin/feedback");
			mav.addObject("actionUrl", "/weixin_test/weixin/feedback/insert");
			mav.addObject("user", user);
			mav.addObject("feedback", feedback);
		}
		return mav;
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView checkWeixinAbout(HttpServletRequest request, ModelMap model, HttpSession httpSession) {
		String wxid = "";
		String wxurl = null;
		String nickName = null;
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		if (!"authdeny".equals(code)) {
			WeixinOauth2Token weixinOauth2Token = util.getOauth2AccessToken(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET, code);
			if (weixinOauth2Token != null) {
				String accessToken = weixinOauth2Token.getAccessToken();
				String openId = weixinOauth2Token.getOpenId();
				SNSUserInfo snsUserInfo = util.getSNSUserInfo(accessToken, openId);
				if (snsUserInfo != null) {
					wxid = snsUserInfo.getOpenId();
					wxurl = snsUserInfo.getHeadImgUrl();
					nickName = snsUserInfo.getNickname();
					model.addAttribute("wxid", wxid);
					model.addAttribute("wxurl", wxurl);
					model.addAttribute("nickName", nickName);
				}
				UserModel user = new UserModel();
				user.setWxid(wxid);
				user.setWxurl(wxurl);
				user.setNickName(nickName);
				mav.addObject("user", user);
			}
		}
		
		if (wxid.equals("")) {
			wxid = (String)httpSession.getAttribute("wxid");
		}
		
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		if (user == null) {
			// 用户不存在
			mav.addObject("aboutbtn", "我要报名");
			mav.addObject("actionUrl", "/weixin_test/weixin/register");
			model.addAttribute("userid", "-1");
		} else {
			// 是VIP用户
			if (user.getLevel() == '1') {
				// 判断用户VIP是否过期
				UserModel vipuser = weiXinService.isVIPFailure(0, user.getId());
				// VIP过期
				if (vipuser != null) {
					// 是普通用户
					mav.addObject("aboutbtn", "欢迎继续学习");
					model.addAttribute("userid", String.valueOf(user.getId()));
					mav.addObject("actionUrl", "/weixin_test/weixin/courselist/0/"+user.getWxid());
					mav.setViewName("/weixin/about");
					return mav;
				}
			}
			// 是普通用户
			mav.addObject("aboutbtn", "成为VIP会员，学习全网课程");
			model.addAttribute("userid", String.valueOf(user.getId()));
			mav.addObject("actionUrl", "/weixin_test/weixin/viporder/"+user.getId());
		}
		
		mav.setViewName("/weixin/about");
		return mav;
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public ModelAndView checkWeixinPerson(HttpServletRequest request, ModelMap model) {
		String wxid = null;
		String wxurl = null;
		String nickName = null;
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		if (!"authdeny".equals(code)) {
			WeixinOauth2Token weixinOauth2Token = util.getOauth2AccessToken(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET, code);
			if (weixinOauth2Token != null) {
				String accessToken = weixinOauth2Token.getAccessToken();
				String openId = weixinOauth2Token.getOpenId();
				SNSUserInfo snsUserInfo = util.getSNSUserInfo(accessToken, openId);
				if (snsUserInfo != null) {
					wxid = snsUserInfo.getOpenId();
					wxurl = snsUserInfo.getHeadImgUrl();
					nickName = snsUserInfo.getNickname();
					model.addAttribute("wxid", wxid);
					model.addAttribute("wxurl", wxurl);
					model.addAttribute("nickName", nickName);
				}
				UserModel user = weiXinService.isWeiXinRegister(wxid);
				if (user != null) {
					user.setWxid(wxid);
					user.setWxurl(wxurl);
					user.setNickName(nickName);
					mav.addObject("user", user);
					if (user.getLevel() == '1') {
						// 判断用户VIP是否过期
						UserModel vipuser = weiXinService.isVIPFailure(0, user.getId());
						// VIP过期
						if (vipuser != null) {
							int viptime = ConstantClass.daysBetween(new Date(), user.getvIPExpireTime());
							mav.addObject("viptime", viptime);
						}
					}
				}
			}
		}
		mav.setViewName("/weixin/person");
		return mav;
	}
	
	@RequestMapping(value = "/salecourselist", method = RequestMethod.GET)
	public ModelAndView checkWeixinSaleCourselist(HttpServletRequest request, HttpSession httpSession, ModelMap model) {
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		SNSUserInfo snsUserInfo = null;
		String wxid = "";
		if (!"authdeny".equals(code)) {
			WeixinOauth2Token weixinOauth2Token = util.getOauth2AccessToken(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET, code);
			if (weixinOauth2Token != null) {
				String accessToken = weixinOauth2Token.getAccessToken();
				String openId = weixinOauth2Token.getOpenId();
				snsUserInfo = util.getSNSUserInfo(accessToken, openId);
				if (snsUserInfo != null) {
					wxid = snsUserInfo.getOpenId();
					model.addAttribute("wxid", snsUserInfo.getOpenId());
					model.addAttribute("wxurl", snsUserInfo.getHeadImgUrl());
					model.addAttribute("nickName", snsUserInfo.getNickname());
				} else {
					wxid = "error";
				}
			}
		}
		
		if (wxid.equals("")) {
			wxid = (String)httpSession.getAttribute("wxid");
		}
		
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		if (user == null) {
			// 用户不存在
			mav.addObject("actionUrl", "");
			model.addAttribute("userid", "-1");
		} else {
			model.addAttribute("userid", String.valueOf(user.getId()));
			mav.setViewName("/weixin/courselist");
			mav.addObject("actionUrl", "weixin/ispay/"+user.getId()+"/");
		}
		// 课程数据取得
		List<CourseModel> courses = weiXinService.getCourseList(0, 0);
		mav.addObject("courses", courses);
		// 课辅数据取得
//		List<CourseModel> educations = weiXinService.getEducationList(0, 0);
//		mav.addObject("educations", educations);
		mav.setViewName("/weixin/courselist");
		
		return mav;
	}
	
	@RequestMapping(value = "/managementcourselist", method = RequestMethod.GET)
	public ModelAndView checkWeixinManagementCourselist(HttpServletRequest request, HttpSession httpSession, ModelMap model) {
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		SNSUserInfo snsUserInfo = null;
		String wxid = "";
		if (!"authdeny".equals(code)) {
			WeixinOauth2Token weixinOauth2Token = util.getOauth2AccessToken(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET, code);
			if (weixinOauth2Token != null) {
				String accessToken = weixinOauth2Token.getAccessToken();
				String openId = weixinOauth2Token.getOpenId();
				snsUserInfo = util.getSNSUserInfo(accessToken, openId);
				if (snsUserInfo != null) {
					wxid = snsUserInfo.getOpenId();
					model.addAttribute("wxid", snsUserInfo.getOpenId());
					model.addAttribute("wxurl", snsUserInfo.getHeadImgUrl());
					model.addAttribute("nickName", snsUserInfo.getNickname());
				} else {
					wxid = "error";
				}
			}
		}
		
		if (wxid.equals("")) {
			wxid = (String)httpSession.getAttribute("wxid");
		}
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		if (user == null) {
			// 用户不存在
			model.addAttribute("userid", "-1");
			mav.addObject("actionUrl", "");
		} else {
			model.addAttribute("userid", String.valueOf(user.getId()));
			mav.setViewName("/weixin/courselist");
			mav.addObject("actionUrl", "weixin/ispay/"+user.getId()+"/");
		}
		// 课程数据取得
		List<CourseModel> courses = weiXinService.getCourseList(0, 1);
		mav.addObject("courses", courses);
		// 课辅数据取得
//		List<CourseModel> educations = weiXinService.getEducationList(0, 1);
//		mav.addObject("educations", educations);
		mav.setViewName("/weixin/courselist");
		
		return mav;
	}
	
	@RequestMapping(value = "/marketcourselist", method = RequestMethod.GET)
	public ModelAndView checkWeixinMarketCourselist(HttpServletRequest request, HttpSession httpSession, ModelMap model) {
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		SNSUserInfo snsUserInfo = null;
		String wxid = "";
		if (!"authdeny".equals(code)) {
			WeixinOauth2Token weixinOauth2Token = util.getOauth2AccessToken(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET, code);
			if (weixinOauth2Token != null) {
				String accessToken = weixinOauth2Token.getAccessToken();
				String openId = weixinOauth2Token.getOpenId();
				snsUserInfo = util.getSNSUserInfo(accessToken, openId);
				if (snsUserInfo != null) {
					wxid = snsUserInfo.getOpenId();
					model.addAttribute("wxid", snsUserInfo.getOpenId());
					model.addAttribute("wxurl", snsUserInfo.getHeadImgUrl());
					model.addAttribute("nickName", snsUserInfo.getNickname());
				} else {
					wxid = "error";
				}
			}
		}
		
		if (wxid.equals("")) {
			wxid = (String)httpSession.getAttribute("wxid");
		}
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		if (user == null) {
			// 用户不存在
			model.addAttribute("userid", "-1");
			mav.addObject("actionUrl", "");
		} else {
			model.addAttribute("userid", String.valueOf(user.getId()));
			mav.setViewName("/weixin/courselist");
			mav.addObject("actionUrl", "weixin/ispay/"+user.getId()+"/");
		}
		// 课程数据取得
		List<CourseModel> courses = weiXinService.getCourseList(0, 2);
		mav.addObject("courses", courses);
		// 课辅数据取得
//		List<CourseModel> educations = weiXinService.getEducationList(0, 2);
//		mav.addObject("educations", educations);
		mav.setViewName("/weixin/courselist");
		
		return mav;
	}
	
	@RequestMapping(value = "/peoplecourselist", method = RequestMethod.GET)
	public ModelAndView checkWeixinPeopleCourselist(HttpServletRequest request, HttpSession httpSession, ModelMap model) {
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		SNSUserInfo snsUserInfo = null;
		String wxid = "";
		if (!"authdeny".equals(code)) {
			WeixinOauth2Token weixinOauth2Token = util.getOauth2AccessToken(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET, code);
			if (weixinOauth2Token != null) {
				String accessToken = weixinOauth2Token.getAccessToken();
				String openId = weixinOauth2Token.getOpenId();
				snsUserInfo = util.getSNSUserInfo(accessToken, openId);
				if (snsUserInfo != null) {
					wxid = snsUserInfo.getOpenId();
					model.addAttribute("wxid", snsUserInfo.getOpenId());
					model.addAttribute("wxurl", snsUserInfo.getHeadImgUrl());
					model.addAttribute("nickName", snsUserInfo.getNickname());
				} else {
					wxid = "error";
				}
			}
		}
		
		if (wxid.equals("")) {
			wxid = (String)httpSession.getAttribute("wxid");
		}
		UserModel user = weiXinService.isWeiXinRegister(wxid);
		if (user == null) {
			// 用户不存在
			model.addAttribute("userid", "-1");
			mav.addObject("actionUrl", "");
		} else {
			model.addAttribute("userid", String.valueOf(user.getId()));
			mav.setViewName("/weixin/courselist");
			mav.addObject("actionUrl", "weixin/ispay/"+user.getId()+"/");
		}
		// 课程数据取得
		List<CourseModel> courses = weiXinService.getCourseList(0, 3);
		mav.addObject("courses", courses);
		// 课辅数据取得
//		List<CourseModel> educations = weiXinService.getEducationList(0, 3);
//		mav.addObject("educations", educations);
		mav.setViewName("/weixin/courselist");
		
		return mav;
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public ModelAndView checkWeixinAuth(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		if (!"authdeny".equals(code)) {
			WeixinOauth2Token weixinOauth2Token = util.getOauth2AccessToken(ConstantClass.WXPAY_APP_ID, ConstantClass.WXPAY_APP_SECRET, code);
			String accessToken = weixinOauth2Token.getAccessToken();
			String openId = weixinOauth2Token.getOpenId();
			SNSUserInfo snsUserInfo = util.getSNSUserInfo(accessToken, openId);
			//request.setAttribute("snsUserInfo", snsUserInfo);
			mav.addObject("snsUserInfo", snsUserInfo);
		}
		//snsUserInfo.getOpenId();
		//mav.setViewName("http://yiyaopx.duapp.com/wx_back/weixin/person");
		mav.setViewName("index");
		return mav;
	}
}
