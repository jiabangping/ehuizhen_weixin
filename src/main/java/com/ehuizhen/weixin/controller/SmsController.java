package com.ehuizhen.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ehuizhen.weixin.init.ServerConfigConst;
import com.ehuizhen.weixin.model.PatientModel;
import com.ehuizhen.weixin.service.SmsService;

@Controller
public class SmsController {
	
	@Autowired
	private SmsService smsService;
	
	/**跳转到会诊消息*/
	@RequestMapping(value = "/sms/smsPage", method = RequestMethod.GET)
	public ModelAndView smsPage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		ModelAndView model = new ModelAndView("/weixin/sms/smsPage");
		PatientModel sessionAttr = (PatientModel)session.getAttribute(ServerConfigConst.patientInfoSessionAttr);
		if(sessionAttr != null) {
			model.addObject("phoneNum", sessionAttr.getPhoneNum());
		}
		return model;
	}
	
	@RequestMapping(value = "/api/v1/sms/query/{phoneNum}", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String query(@PathVariable("phoneNum") String mobile,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
			HttpSession session) throws Exception {
		
		return smsService.queryByMobile(mobile, pageIndex);
	}
}
