package com.ehuizhen.weixin.controller;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ehuizhen.weixin.config.JsonConst;
import com.ehuizhen.weixin.config.VerifyCodeUtils;
import com.ehuizhen.weixin.config.WeixinBasicKit;
import com.ehuizhen.weixin.init.ServerConfigConst;
import com.ehuizhen.weixin.init.WeixinContext;
import com.ehuizhen.weixin.model.DoctorAuditModel;
import com.ehuizhen.weixin.model.DoctorModel;
import com.ehuizhen.weixin.model.DoctorPasswdModifyLinkToken;
import com.ehuizhen.weixin.service.DoctorAuditService;
import com.ehuizhen.weixin.service.DoctorService;
import com.ehuizhen.weixin.service.SmsService;
import com.ehuizhen.weixin.tools.MD5;
import com.ehuizhen.weixin.tools.ValidationTools;
import com.ehuizhen.weixin.util.EmailUtil;
import com.ehuizhen.weixin.util.PasswdModifyLinkValidPeriodUtil;
import com.ehuizhen.weixin.util.PasswdModifyLinkVerify;
import com.ehuizhen.weixin.util.SecurityHelper;

import net.sf.json.JSONObject;

@Controller
public class DoctorController {
	private static final Logger log = LoggerFactory.getLogger(DoctorController.class);
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private DoctorAuditService doctorAuditService;
	
	/**
	 * 跳转到医生列表页面 
	 * @return
	 */
	@RequestMapping(value = "/doctor/doctorsPage", method = RequestMethod.GET)
	public ModelAndView doctorPage( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView model = new ModelAndView("/weixin/doctor/doctorsPage");
		return model;
	}
	
	
	@RequestMapping(value = "doctor/initData", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String initData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String result =  doctorService.getInitData();
		return result;
	}
	
	@RequestMapping(value = "doctor/getHospitalsByCity", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String getHospitalsByCity(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "cityId", required = true, defaultValue = "-1") int cityId) throws Exception {
		String result =  doctorService.getHospitalsByCity(cityId);
		return result;
	}
	
	
	
	/**
	 * @param regionId 区域Id
	 * @param departmentId 科室Id
	 * @param hospitalId 医院Id
	 * @param currentPage 页码
	 * @return 
	 */
	@RequestMapping(value = "doctor/getDoctors/{regionId:\\d+}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String getDoctors(@PathVariable("regionId") int regionId,
			@RequestParam(value = "departmentId", required = false, defaultValue = "-1") int departmentId,
			@RequestParam(value = "hospitalId", required = false, defaultValue = "-1") int hospitalId,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			HttpServletRequest request,HttpServletResponse response) {
		String result =  doctorService.getDoctors(regionId,hospitalId,departmentId,currentPage);
		return result;
	}

	/**
	 * 跳转到精彩病历页面
	 * @return
	 */
	@RequestMapping(value = "doctor/casehistoryPage", method = RequestMethod.GET)
	public ModelAndView  casehistoryPage(
			HttpServletRequest request,HttpServletResponse response) {
		ModelAndView model = new ModelAndView("/weixin/doctor/casehistoryPage");
		return model;
	}
	
	
	@RequestMapping(value = "doctor/casehistoryList", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String casehistoryList(
			@RequestParam(value = "departmentId", required = false, defaultValue = "0") int departmentId,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			HttpServletRequest request,HttpServletResponse response) {
		String result =  doctorService.casehistoryList(departmentId,currentPage);
		return result;
	}
	

	/**
	 *医生注册协议确认 
	 * @return
	 */
	@RequestMapping(value = "doctor/protocolConfirmPage", method = RequestMethod.GET)
	public ModelAndView protocolPage(
			HttpServletRequest request,HttpServletResponse response) {
		ModelAndView model = new ModelAndView("/weixin/doctor/protocolConfirmPage");
		return model;
	}
	
	
	private ModelAndView handleDoctorSignUpPage(Object oid) {
		ModelAndView model = new ModelAndView("/weixin/doctor/signUpPage");
		try {
			DoctorModel doctor = doctorService.getDoctorByWxOpenId((String)oid);
			if( doctor == null ) {
				DoctorAuditModel doctorAudit = doctorAuditService.getDoctorAuditByWxId((String)oid);
				if(doctorAudit != null) {
					if(doctorAudit.getStatus() == 1) {//只完成第1步提交
						model = new ModelAndView("/weixin/doctor/signUpPage");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.addObject("firstOverSea",doctorAudit.getOversea());
						model.addObject("firstWhich",which);
						model.addObject("doctorName",doctorAudit.getDoctorName());
						model.addObject("msg", "您已注册账号:【"+which+"】继续完善资料");
						model.addObject(JsonConst.result, "onlyFinishFirstStep");
						return model;
					}
					
					if(doctorAudit.getStatus() == 2) {//邮箱注册用户待验证邮箱
						model = new ModelAndView("/weixin/common/signUpWaitValidEmailModalDialog");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.addObject("msg", "您已绑定账号:【"+which+"】，请到该邮箱中点击激活链接完成注册");
						model.addObject("which", which);
						model.addObject(JsonConst.result, JsonConst.waitForValidEmail);
						return model;
					}
					
					if(doctorAudit.getStatus() == 3) {//待审核
						model = new ModelAndView("/weixin/common/signUpWaitAuditModalDialog");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.addObject("msg", "您已绑定账号:【"+which+"】，请耐心等待审核通过");
						model.addObject(JsonConst.result, JsonConst.waitAudit);
						return model;
					}
					if(doctorAudit.getStatus() == 4) {//审核未通过
						model = new ModelAndView("/weixin/common/signUpAuditNoSuccessModalDialog");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.addObject("msg", "您绑定的账号:【"+which+"】审核未通过，如有问题，请联系客服400-888-3918");
						model.addObject(JsonConst.result, JsonConst.auditNoSuccess);
						return model;
					}
				}else {
					//不存在 可以注册
					//跳转到注册页面
					model = new ModelAndView("/weixin/doctor/signUpPage");//
					model.addObject(JsonConst.result, JsonConst.success);//可以继续下一步
					return model;
				}
			}else {
				//已经绑定过
				model = new ModelAndView("/weixin/common/alreadyBindSuccessModalDialog");
				String which = "";
				if(doctor.getOverSea() == 0) {
					which = doctor.getPhoneNum();
				}else {
					which = doctor.getEmail();
				}
				String msg = "您已经绑定账号:【"+which+"】请勿重复执行";
				model.addObject("msg", msg);
				model.addObject(JsonConst.result, JsonConst.alreadyBind);//已经绑定
				return model;
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return model;
		}
		return model;
	}
	
	
	/**
	 * @return
	 */
	@RequestMapping(value = "doctor/signUpPage", method = RequestMethod.GET)
	public ModelAndView doctorSignUpPage(
			HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@RequestParam(value = "protocolConfirm", required = false) String protocolConfirm){
		//TODO删除这里
//session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr, "ox39CwaThU6vFJA6zwtrFfzirGWA");
		if(protocolConfirm != null && "1".equals(protocolConfirm)) {
			ModelAndView model = new ModelAndView("/weixin/doctor/signUpPage");//输入
			
			Object oid = session.getAttribute(ServerConfigConst.wxOpenIdSessionAttr);
			if(oid == null) {
				String agent = request.getHeader("User-Agent");
				if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
					String code = request.getParameter("code");
					String state = request.getParameter("state");
					if(code!=null&&state!=null&&state.equals("1")) {
						//通过Code获取openid来进行授权
						String openid = WeixinBasicKit.queryOpenidByCode(code);
						if(null != openid) {
							session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr,openid);
							return handleDoctorSignUpPage(openid);
						}
					}
				}
			}else {
				return handleDoctorSignUpPage(oid);
			}
			return model;
		}
		ModelAndView model = new ModelAndView("redirect:/doctor/protocolConfirmPage");
		return model;
	}
	
	
	/**判断医生手机号是否已存在*/
	@RequestMapping(value = "doctor/existPhoneNum/{phoneNum}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String existPhoneNum(@PathVariable("phoneNum") String phoneNum,HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@RequestParam(value = "signUpAgain", required = false,defaultValue="-1") int signUpAgain) {
		if(!ValidationTools.isMobile(phoneNum)) {
			JSONObject result = new JSONObject();
			result.put(JsonConst.result, JsonConst.formatErr);
			return result.toString();
		}
		if(1== signUpAgain) {//重新注册
			JSONObject result = new JSONObject();
			result.put(JsonConst.result, JsonConst.notExist);
			return result.toString();
		}
		Object openId = session.getAttribute(ServerConfigConst.wxOpenIdSessionAttr);
		if(openId == null) {
			String agent = request.getHeader("User-Agent");
			if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				if(code!=null&&state!=null&&state.equals("1")) {
					String openid = WeixinBasicKit.queryOpenidByCode(code);
					if(null != openid) {
						session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr,openid);
					}
				}
			}else {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.openInWeixinPlease);
				return json.toString();
			}
		}
		
		
		DoctorModel doctor = doctorService.getDoctorByPhoneNum(phoneNum);
		if(doctor == null) {
			DoctorAuditModel doctorAudit = null;
			try {
				doctorAudit = doctorAuditService.getDoctorAuditByPhoneNum(phoneNum);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			if(doctorAudit != null) {
					if(doctorAudit.getStatus() == 1) {//只完成第1步提交
						//model = new ModelAndView("/weixin/doctor/signUpPage");
						JSONObject model = new JSONObject();
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.put("msg", "您注册的账号:【"+which+"】资料不全，请继续完善资料");
						
						model.put("firstOverSea", doctorAudit.getOversea());
						model.put("firstDoctorName",doctorAudit.getDoctorName());
						model.put("firstWhich", which);
						
						model.put(JsonConst.result, "onlyFinishFirstStep");
						return model.toString();
					}
					
					if(doctorAudit.getStatus() == 2) {//邮箱注册用户待验证邮箱
						//model = new ModelAndView("/weixin/common/signUpWaitValidEmailModalDialog");
						JSONObject model = new JSONObject();
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.put("msg", "您的账号:【"+which+"】等待验证邮箱");
						model.put("which", which);
						model.put(JsonConst.result, JsonConst.waitForValidEmail);
						return model.toString();
					}
					
					if(doctorAudit.getStatus() == 3) {//待审核
						JSONObject model = new JSONObject();
						//model = new ModelAndView("/weixin/common/signUpWaitAuditModalDialog");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.put("msg", "您的账号:【"+which+"】待审核中");
						model.put(JsonConst.result, JsonConst.waitAudit);
						return model.toString();
					}
					if(doctorAudit.getStatus() == 4) {//审核未通过
						JSONObject model = new JSONObject();
						//model = new ModelAndView("/weixin/common/signUpAuditNoSuccessModalDialog");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.put("msg", "您的账号:【"+which+"】审核未通过，如有问题，请联系客服400-888-3918");
						model.put(JsonConst.result, JsonConst.auditNoSuccess);
						return model.toString();
					}
				
				
				/*JSONObject result = new JSONObject();
				result.put(JsonConst.result, JsonConst.exist);
				return result.toString();*/
			}
			JSONObject result = new JSONObject();
			result.put(JsonConst.result, JsonConst.notExist);
			return result.toString();
		}else {//已存在
			if(doctor.getWeixinAppId() != null && !"".equals(doctor.getWeixinAppId() )) {//已绑定
				JSONObject result = new JSONObject();
				result.put(JsonConst.result, JsonConst.alreadyBind);//该号码已注册并绑定
				return result.toString();
			}else {
				JSONObject result = new JSONObject();
				result.put(JsonConst.result, JsonConst.existBindPlease);//已存在，请绑定
				return result.toString();
			}
		}
	}
	
	
	/**医生注册获取验证码*/
	@RequestMapping(value = "doctor/getVerifyCode/{phoneNum}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String getVerifyCode(@PathVariable("phoneNum") String phoneNum,HttpServletRequest request,HttpServletResponse response, HttpSession httpSession,
			@RequestParam(value = "signUpAgain", required = false,defaultValue="-1") int signUpAgain
			) {
		if(!ValidationTools.isMobile(phoneNum)) {
			JSONObject result = new JSONObject();
			result.put(JsonConst.result, JsonConst.formatErr);
			return result.toString();
		}
		if(signUpAgain != 1) {
			DoctorAuditModel model = null;
			try {
				model = doctorAuditService.getDoctorAuditByPhoneNum(phoneNum);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			if(model != null) {
				JSONObject result = new JSONObject();
				result.put(JsonConst.result, JsonConst.fail);//该手机号已存在，注册过
				return result.toString();
			}
		}
	
		String verifyCode = VerifyCodeUtils.generateVerifyCode(6,"0123456789");
		boolean result = smsService.getVerifyCode(phoneNum, verifyCode);
		
		httpSession.setAttribute(phoneNum+ServerConfigConst.doctorVerifyCodeSessionAttr, verifyCode);
		
		if(result) {
			JSONObject json = new JSONObject();
			json.put(JsonConst.result, JsonConst.success);
			json.put("verifyCode", verifyCode);
			return json.toString();	
		}else {
			JSONObject json = new JSONObject();
			json.put(JsonConst.result, JsonConst.getVerifyCodeLimit);
			return json.toString();	
		}
	}
	
	
	
	/**医生注册信息第一次提交*/
	@RequestMapping(value = "doctor/signUpFirstSubData", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String signUpFirstSubData(HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@RequestParam(value = "oversea", required = true) int oversea,
			@RequestParam(value = "sex", required = true) int sex,
			@RequestParam(value = "doctorName", required = true) String doctorName,
			@RequestParam(value = "phoneNum", required = true) String phoneNum,
			@RequestParam(value = "verifyCode", required = false) String verifyCode,
			@RequestParam(value = "idCard", required = true) String idCard,
			@RequestParam(value = "chargingStandard", required = false) String chargingStandard,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "signUpAgain", required = false,defaultValue="-1") int signUpAgain,
			 HttpSession httpSession) {
//TODO 删除这里httpSession.setAttribute(ServerConfigConst.wxOpenIdSessionAttr, "ox39CwaThU6vFJA6zwtrFfzirGWA");
		try {
			if(signUpAgain == 1) {//再次重新注册
				if(oversea == 0) {
					DoctorAuditModel exist = doctorAuditService.getDoctorAuditByPhoneNum(phoneNum);
					if(exist != null) {
						doctorAuditService.delete(exist);
					}
				}else {
					DoctorAuditModel exist = doctorAuditService.getDoctorAuditByKey("Email", email);
					if(exist != null) {
						doctorAuditService.delete(exist);
					}
				}
			}
			
			if(oversea == 0) {//国内
					String _verifyCode = (String)httpSession.getAttribute(phoneNum+ServerConfigConst.doctorVerifyCodeSessionAttr);
					if((_verifyCode != null && _verifyCode.equals(verifyCode)) ) {//验证码正确
						//status为1第一次提交
						if(doctorName == null || "".equals(doctorName) || 
							phoneNum == null || "".equals(phoneNum) ||
							verifyCode == null || "".equals(verifyCode) ||
							idCard == null || "".equals(idCard)
						) {
							JSONObject result = new JSONObject();
							result.put(JsonConst.result, JsonConst.lackRequiredField);
							return result.toString();
						}
					
						DoctorAuditModel exist = doctorAuditService.getDoctorAuditByPhoneNum(phoneNum);
						if(exist != null) {
							JSONObject result = new JSONObject();
							result.put(JsonConst.result, JsonConst.exist);
							return result.toString();
						}
						
						DoctorAuditModel docModel = new DoctorAuditModel(doctorName,phoneNum,sex,idCard,oversea, new Timestamp(System.currentTimeMillis()),1);//status=1为首次提交
						
						Object openId = httpSession.getAttribute(ServerConfigConst.wxOpenIdSessionAttr);
						if(openId == null) {
							String agent = request.getHeader("User-Agent");
							if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
								String code = request.getParameter("code");
								String state = request.getParameter("state");
								if(code!=null&&state!=null&&state.equals("1")) {
									String openid = WeixinBasicKit.queryOpenidByCode(code);
									if(null != openid) {
										session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr,openid);
										DoctorAuditModel audit = doctorAuditService.getDoctorAuditByWxId(openid);
										if(audit != null) {
											doctorAuditService.delete(audit);
										}
										docModel.setWxOpenId(openid);
										doctorAuditService.save(docModel);
										JSONObject json = new JSONObject();
										json.put(JsonConst.result, JsonConst.success);
										json.put("phoneNum", phoneNum);
										return json.toString();
									}
								}
							}else {
								JSONObject json = new JSONObject();
								json.put(JsonConst.result, JsonConst.openInWeixinPlease);
								return json.toString();
							}
						}else {
							DoctorAuditModel audit = doctorAuditService.getDoctorAuditByWxId((String)openId);
							if(audit != null) {
								doctorAuditService.delete(audit);
							}
							docModel.setWxOpenId((String)openId);
							doctorAuditService.save(docModel);
							JSONObject json = new JSONObject();
							json.put(JsonConst.result, JsonConst.success);
							json.put("phoneNum", phoneNum);
							return json.toString();
						}
					} else {
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.verifyError);
						return json.toString();
					}
			}else {//1海外
				DoctorModel model = doctorService.getDoctorByFieldValue("Email", email);
				if(model != null) {
					if(model.getWeixinAppId() != null && !"".equals(model.getWeixinAppId())) {
						JSONObject result = new JSONObject();
						result.put(JsonConst.result, JsonConst.alreadyBind);
						return result.toString();
					}else {
						JSONObject result = new JSONObject();
						result.put(JsonConst.result, JsonConst.existBindPlease);
						return result.toString();
					}
				}
				DoctorModel phoneAccount = doctorService.getDoctorByPhoneNum(phoneNum);
				if(phoneAccount != null) {
					if(phoneAccount.getWeixinAppId() != null && !"".equals(phoneAccount.getWeixinAppId())) {
						JSONObject result = new JSONObject();
						result.put(JsonConst.result, JsonConst.alreadyBind);
						return result.toString();
					}else {
						JSONObject result = new JSONObject();
						result.put(JsonConst.result, JsonConst.existBindPlease);
						return result.toString();
					}
				}
				
				DoctorAuditModel doctorAudit = doctorAuditService.getDoctorAuditByKey("Email",email);
				if(doctorAudit != null) {
					
					if(doctorAudit.getStatus() == 1) {//只完成第1步提交
						//model = new ModelAndView("/weixin/doctor/signUpPage");
						JSONObject json = new JSONObject();
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						json.put("msg", "您注册的账号:【"+which+"】资料不全，请继续完善资料");
						
						json.put("firstOverSea", doctorAudit.getOversea());
						json.put("firstDoctorName",doctorAudit.getDoctorName());
						json.put("firstWhich", which);
						
						json.put(JsonConst.result, "onlyFinishFirstStep");
						return json.toString();
					}
					
					if(doctorAudit.getStatus() == 2) {//邮箱注册用户待验证邮箱
						//model = new ModelAndView("/weixin/common/signUpWaitValidEmailModalDialog");
						JSONObject json = new JSONObject();
						/*String which = doctorAudit.getPhoneNum();
						if(which == null || "".equals(which)) {
							which = doctorAudit.getEmail();
						}*/
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						
						json.put("msg", "您的账号:【"+which+"】等待验证邮箱");
						json.put("which", which);
						json.put(JsonConst.result, JsonConst.waitForValidEmail);
						return json.toString();
					}
					
					if(doctorAudit.getStatus() == 3) {//待审核
						JSONObject json = new JSONObject();
						//model = new ModelAndView("/weixin/common/signUpWaitAuditModalDialog");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						json.put("msg", "您的账号:【"+which+"】待审核中");
						json.put(JsonConst.result, JsonConst.waitAudit);
						return json.toString();
					}
					if(doctorAudit.getStatus() == 4) {//审核未通过
						JSONObject json = new JSONObject();
						//model = new ModelAndView("/weixin/common/signUpAuditNoSuccessModalDialog");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						json.put("msg", "您的账号:【"+which+"】审核未通过，如有问题，请联系客服400-888-3918");
						json.put(JsonConst.result, JsonConst.auditNoSuccess);
						return json.toString();
					}
					
					/*JSONObject result = new JSONObject();
					result.put(JsonConst.result, JsonConst.exist);
					return result.toString();*/
				}
				
				DoctorAuditModel overSeaAccount = doctorAuditService.getDoctorAuditByPhoneNum(phoneNum);
				if(overSeaAccount != null) {
					JSONObject result = new JSONObject();
					result.put(JsonConst.result, JsonConst.overSeaPhoneNumExist);
					return result.toString();
				}
				
				
				DoctorAuditModel overSeaDoctor = new DoctorAuditModel(doctorName,phoneNum,sex,idCard,oversea,new Timestamp(System.currentTimeMillis()),1);
				overSeaDoctor.setEmail(email);
				overSeaDoctor.setChargingStandard(chargingStandard);
				Object openId = httpSession.getAttribute(ServerConfigConst.wxOpenIdSessionAttr);
				if(openId == null) {
					String agent = request.getHeader("User-Agent");
					if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
						String code = request.getParameter("code");
						String state = request.getParameter("state");
						if(code!=null&&state!=null&&state.equals("1")) {
							String openid = WeixinBasicKit.queryOpenidByCode(code);
							if(null != openid) {
								session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr,openid);
								DoctorAuditModel audit = doctorAuditService.getDoctorAuditByWxId(openid);
								if(audit != null) {
									doctorAuditService.delete(audit);
								}
								overSeaDoctor.setWxOpenId(openid);
								doctorAuditService.save(overSeaDoctor);
								JSONObject json = new JSONObject();
								json.put(JsonConst.result, JsonConst.success);
								json.put(JsonConst.emailAccount, JsonConst.emailAccount);
								json.put("phoneNum", email);
								return json.toString();
							}
						}
					}
				}else {
					DoctorAuditModel audit = doctorAuditService.getDoctorAuditByWxId((String)openId);
					if(audit != null) {
						doctorAuditService.delete(audit);
					}
					overSeaDoctor.setWxOpenId((String)openId);
					doctorAuditService.save(overSeaDoctor);
					JSONObject json = new JSONObject();
					json.put(JsonConst.result, JsonConst.success);
					json.put(JsonConst.emailAccount, JsonConst.emailAccount);
					json.put("phoneNum", email); 
					return json.toString();
				}
			}
		} catch (Exception e1) {
			log.error(e1.getMessage(),e1);
		}
		JSONObject json = new JSONObject();
		json.put(JsonConst.result, JsonConst.fail);
		return json.toString();
	}
	
	/**重新获取邮箱激活链接*/
	@RequestMapping(value = "doctor/getEmailValidAgain", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String getEmailValidAgain(HttpServletRequest request,HttpServletResponse response, HttpSession session,
			@RequestParam(value = "email", required = true) String email) {
		try {
			DoctorAuditModel first = doctorAuditService.getDoctorAuditByKey("Email", email);
			if(first == null) {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.notExist);
				return json.toString();
			}else {
				//下发邮件
				/*String sendMsg = "<a href=\""+WeixinContext.wxDeployBaseUrl+"/doctor/"+ first.getId()+"/verifyEmail\"/>尊敬的医生:"+first.getDoctorName()+"您好，请点击链接验证您的邮箱完成注册</a>";
				String subject = "e会诊医生注册邮箱验证";
				EmailUtil.send(subject,first.getEmail(), sendMsg);*/
				
				String msg = ServerConfigConst.doctorRegisteVerifyEmailMsgContentTeplate+"<br>  <a href=\""+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorRegisteVerifyEmailMsgUrlTeplate+"\"/>"+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorRegisteVerifyEmailMsgUrlTeplate+"</a><br><br>这是一封系统邮件，请勿直接回复。";
				msg = msg.replace(ServerConfigConst.DOCTORID,first.getId()+"").replace(ServerConfigConst.DOCTORNAME, first.getDoctorName());
				EmailUtil.sendRegisteEmail(ServerConfigConst.doctorRegisteVerifyEmailMsgSubjectTeplate,
						first.getEmail(), msg);
				
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.success);
				return json.toString();
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		JSONObject json = new JSONObject();
		json.put(JsonConst.result, JsonConst.fail);
		return json.toString();
		
	}
	
	
	
	/**医生注册信息第二次提交*/
	@RequestMapping(value = "doctor/signUpSecondSubData", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String signUpSecondSubData(HttpServletRequest request,HttpServletResponse response, HttpSession session,
			@RequestParam(value = "oversea", required = true) int oversea,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "overseaEmail", required = false) String overseaEmail,
			@RequestParam(value = "verifyCode", required = false) String verifyCode,
			@RequestParam(value = "hospital", required = true) String hospital,
			@RequestParam(value = "department", required = true) String department,
			@RequestParam(value = "position", required = true) String position,//职称
			@RequestParam(value = "skilledField", required = true) String skilledField,
			@RequestParam(value = "selfIntro", required = true) String selfIntro,
			@RequestParam(value = "doctorCertNo", required = true) String doctorCertNo,//医生职业证书编号
			@RequestParam(value = "chargingStandard", required = false) String chargingStandard,//收费标准
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "selfPhotoUrl", required = true) String selfPhotoUrl
		) {
//TODO删除这里
//session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr, "ox39CwaThU6vFJA6zwtrFfzirGWA");
		//String _verifyCode = (String)httpSession.getAttribute(phoneNum+ServerConfigConst.doctorVerifyCodeSessionAttr);
		//if(_verifyCode != null && _verifyCode.equals(verifyCode)) {//验证码正确
			//status为2第二次提交
			DoctorAuditModel first = null;
			try {
				if(oversea == 0) {
					first = doctorAuditService.getDoctorAuditByPhoneNum(phoneNum);
				}else {
					first = doctorAuditService.getDoctorAuditByKey("Email", overseaEmail);
				}
			} catch (Exception e1) {
				log.error(e1.getMessage(),e1);
			}
			first.setHospital(hospital);
			first.setDepartment(department);
			first.setPosition(position);
			first.setSkilledField(skilledField);
			first.setSelfIntro(selfIntro);
			first.setSelfPhotoUrl(selfPhotoUrl);
			first.setDoctorCertNo(doctorCertNo);
			first.setChargingStandard(chargingStandard);
			if(oversea == 0) {
				first.setEmail(email);
				first.setStatus(3);//第2次提交完毕 设置为待审核状态
			}else{
				first.setStatus(2);//第2次提交完毕 设置为待验证邮箱中
			}
			
			boolean result = false;
			try {
				result = doctorAuditService.update(first);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			try{	
				if(result) {
					if(first.getWxOpenId() != null && !"".equals(first.getWxOpenId())) {
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.success);
						if(oversea == 1){
							json.put(JsonConst.emailAccount, JsonConst.emailAccount);
							json.put("phoneNum", first.getEmail());
							
							//下发邮件
							//	doctor//{doctorId:\\d+}/verifyEmail
						/*	String sendMsg = 	"<a href=\""+WeixinContext.wxDeployBaseUrl+"/doctor/"+ first.getId()+"/verifyEmail\"/>尊敬的医生:"+first.getDoctorName()+"您好，请点击链接验证您的邮箱完成注册</a>";
							String subject = "e会诊医生注册邮箱验证";
							EmailUtil.send(subject,first.getEmail(), sendMsg);*/
							
							String msg = ServerConfigConst.doctorRegisteVerifyEmailMsgContentTeplate+"<br> <a href=\""+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorRegisteVerifyEmailMsgUrlTeplate+"\"/>"+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorRegisteVerifyEmailMsgUrlTeplate+"</a><br><br>这是一封系统邮件，请勿直接回复。";
							msg = msg.replace(ServerConfigConst.DOCTORID,first.getId()+"").replace(ServerConfigConst.DOCTORNAME, first.getDoctorName());
							EmailUtil.sendRegisteEmail(ServerConfigConst.doctorRegisteVerifyEmailMsgSubjectTeplate,
									first.getEmail(), msg);
						}else {
							json.put("phoneNum", phoneNum);
						}
						
						return json.toString();
					}
					Object openId = session.getAttribute(ServerConfigConst.wxOpenIdSessionAttr);
					/*if(openId != null) {
						first.setWxOpenId((String)openId);
						doctorAuditService.update(first);
					}*/
					if(openId == null) {
						String agent = request.getHeader("User-Agent");
						if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
							String code = request.getParameter("code");
							String state = request.getParameter("state");
							if(code!=null&&state!=null&&state.equals("1")) {
								String openid = WeixinBasicKit.queryOpenidByCode(code);
								if(null != openid) {
									first.setWxOpenId(openid);
									doctorAuditService.update(first);
									
									JSONObject json = new JSONObject();
									json.put(JsonConst.result, JsonConst.success);
									if(oversea == 1){
										json.put(JsonConst.emailAccount, JsonConst.emailAccount);
										json.put("phoneNum", first.getEmail());
										
										/*//下发邮件
										String sendMsg = "<a href=\""+WeixinContext.wxDeployBaseUrl+"/doctor/"+ first.getId()+"/verifyEmail\"/>尊敬的医生:"+first.getDoctorName()+"您好，请点击链接验证您的邮箱完成注册</a>";
										String subject = "e会诊医生注册邮箱验证";
										EmailUtil.send(subject,first.getEmail(), sendMsg);*/
										
										String msg = ServerConfigConst.doctorRegisteVerifyEmailMsgContentTeplate+"<br>  <a href=\""+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorRegisteVerifyEmailMsgUrlTeplate+"\"/>"+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorRegisteVerifyEmailMsgUrlTeplate+"</a><br><br>这是一封系统邮件，请勿直接回复。";
										msg = msg.replace(ServerConfigConst.DOCTORID,first.getId()+"").replace(ServerConfigConst.DOCTORNAME, first.getDoctorName());
										EmailUtil.sendRegisteEmail(ServerConfigConst.doctorRegisteVerifyEmailMsgSubjectTeplate,
												first.getEmail(), msg);
										
									}else {
										json.put("phoneNum", phoneNum);
									}
									return json.toString();
								}
							}
						}else {
							JSONObject json = new JSONObject();
							json.put(JsonConst.result, JsonConst.openInWeixinPlease);
							if(oversea == 1){
							//	json.put(JsonConst.emailAccount, JsonConst.emailAccount);
								json.put("phoneNum", first.getEmail());
							}else {
								json.put("phoneNum", phoneNum);
							}
							return json.toString();
						}
					}else {
						first.setWxOpenId((String)openId);
						doctorAuditService.update(first);
						
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.success);
						if(oversea == 1){
							json.put(JsonConst.emailAccount, JsonConst.emailAccount);
							json.put("phoneNum", first.getEmail());
							
							//下发邮件
							/*String sendMsg = "<a href=\""+WeixinContext.wxDeployBaseUrl+"/doctor/"+ first.getId()+"/verifyEmail\"/>尊敬的医生:"+first.getDoctorName()+"您好，请点击链接验证您的邮箱完成注册</a>";
							String subject = "e会诊医生注册邮箱验证";
							EmailUtil.send(subject,first.getEmail(), sendMsg);*/
							
							String msg = ServerConfigConst.doctorRegisteVerifyEmailMsgContentTeplate+"<br>  <a href=\""+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorRegisteVerifyEmailMsgUrlTeplate+"\"/>"+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorRegisteVerifyEmailMsgUrlTeplate+"</a><br><br>这是一封系统邮件，请勿直接回复。";
							msg = msg.replace(ServerConfigConst.DOCTORID,first.getId()+"").replace(ServerConfigConst.DOCTORNAME, first.getDoctorName());
							EmailUtil.sendRegisteEmail(ServerConfigConst.doctorRegisteVerifyEmailMsgSubjectTeplate,
									first.getEmail(), msg);
							
						}else {
							json.put("phoneNum", phoneNum);
						}
						return json.toString();
					}
					
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.success);
				if(oversea == 1){
					json.put(JsonConst.emailAccount, JsonConst.emailAccount);
					json.put("phoneNum", first.getEmail());
					
					//下发邮件
					/*String sendMsg = "<a href=\""+WeixinContext.wxDeployBaseUrl+"/doctor/"+ first.getId()+"/verifyEmail\"/>尊敬的医生:"+first.getDoctorName()+"您好，请点击链接验证您的邮箱完成注册</a>";
					String subject = "e会诊医生注册邮箱验证";
					EmailUtil.send(subject,first.getEmail(), sendMsg);*/
					
					String msg = ServerConfigConst.doctorRegisteVerifyEmailMsgContentTeplate+"<br>  <a href=\""+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorRegisteVerifyEmailMsgUrlTeplate+"\"/>"+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorRegisteVerifyEmailMsgUrlTeplate+"</a><br><br>这是一封系统邮件，请勿直接回复。";
					msg = msg.replace(ServerConfigConst.DOCTORID,first.getId()+"").replace(ServerConfigConst.DOCTORNAME, first.getDoctorName());
					EmailUtil.sendRegisteEmail(ServerConfigConst.doctorRegisteVerifyEmailMsgSubjectTeplate,
							first.getEmail(), msg);
				}else {
					json.put("phoneNum", phoneNum);
				}
				return json.toString();
			} else {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.fail);
				return json.toString();
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}		
		JSONObject json = new JSONObject();
		json.put(JsonConst.result, JsonConst.fail);
		return json.toString();

		/*} else {
			JSONObject json = new JSONObject();
			json.put(JsonConst.result, JsonConst.verifyError);
			return json.toString();
		}*/
	}
	
	
	
	
	
	private ModelAndView handleDoctorWxBind(Object oid) {
		ModelAndView model = new ModelAndView("/weixin/doctor/signInPage");
		try {
			DoctorModel doctor = doctorService.getDoctorByWxOpenId((String)oid);
			if( doctor == null ) {
				DoctorAuditModel doctorAudit = doctorAuditService.getDoctorAuditByWxId((String)oid);
				if(doctorAudit != null) {
					if(doctorAudit.getStatus() == 1) {//只完成第1步提交
						model = new ModelAndView("/weixin/doctor/signUpPage");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.addObject("msg", "您注册的账号:【"+which+"】资料不全，请继续完善资料");
						
						model.addObject("firstOverSea", doctorAudit.getOversea());
						model.addObject("firstDoctorName",doctorAudit.getDoctorName());
						model.addObject("firstWhich", which);
						
						model.addObject(JsonConst.result, "onlyFinishFirstStep");
						return model;
					}
					
					if(doctorAudit.getStatus() == 2) {//邮箱注册用户待验证邮箱
						model = new ModelAndView("/weixin/common/signUpWaitValidEmailModalDialog");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.addObject("msg", "您已绑定账号:【"+which+"】等待验证邮箱");
						model.addObject(JsonConst.result, JsonConst.waitForValidEmail);
						model.addObject("which", which);
						return model;
					}
					
					if(doctorAudit.getStatus() == 3) {//待审核
						model = new ModelAndView("/weixin/common/signUpWaitAuditModalDialog");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.addObject("msg", "您已绑定账号:【"+which+"】待审核中");
						model.addObject(JsonConst.result, JsonConst.waitAudit);
						return model;
					}
					if(doctorAudit.getStatus() == 4) {//审核未通过
						model = new ModelAndView("/weixin/common/signUpAuditNoSuccessModalDialog");
						String which = "";
						if(doctorAudit.getOversea() == 0) {
							which = doctorAudit.getPhoneNum();
						}else {
							which = doctorAudit.getEmail();
						}
						model.addObject("msg", "您绑定的账号:【"+which+"】审核未通过，如有问题，请联系客服400-888-3918");
						model.addObject(JsonConst.result, JsonConst.auditNoSuccess);
						return model;
					}
				}else {
					//不存在 可以绑定
					//跳转到绑定页面
					model = new ModelAndView("/weixin/doctor/signInPage");//
					model.addObject(JsonConst.result, JsonConst.success);//可以继续下一步
					return model;
				}
			}else {
				//已经绑定过
				model = new ModelAndView("/weixin/common/alreadyBindSuccessModalDialog");
				String which = "";
				if(doctor.getOverSea() == 0) {
					which = doctor.getPhoneNum();
				}else {
					which = doctor.getEmail();
				}
				String msg = "您已经绑定账号:【"+which+"】请勿重复执行";
				model.addObject("msg", msg);
				model.addObject(JsonConst.result, JsonConst.alreadyBind);//已经绑定
				return model;
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return model;
		}
		return model;
	}
	
	/** 跳转到医生登录页面-当前登录为绑定页面*/
	@RequestMapping(value = "/doctor/signInPage", method = RequestMethod.GET)
	public ModelAndView signInPage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
//TODO删除这里
//session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr, "ox39CwaThU6vFJA6zwtrFfzirGWA");
		ModelAndView model = new ModelAndView("/weixin/doctor/signInPage");
		Object oid = session.getAttribute(ServerConfigConst.wxOpenIdSessionAttr);
		if(oid == null) {
			String agent = request.getHeader("User-Agent");
			if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				if(code!=null&&state!=null&&state.equals("1")) {
					//通过Code获取openid来进行授权
					String openid = WeixinBasicKit.queryOpenidByCode(code);
					if(null != openid) {
						session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr,openid);
						return handleDoctorWxBind(openid);
					}
				}
			}
		}else {
			return handleDoctorWxBind(oid);
		}
		return model;
	}
	
	
	/**绑定请求 */
	@RequestMapping(value = "doctor/signIn", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String signIn(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "patientNameOrPhoneNum", required = true) String phoneNum,
			@RequestParam(value = "passWord", required = true) String passWord,
			HttpSession session) {
		try {
		if(!ValidationTools.isOverSeaMobile(phoneNum)) {
			JSONObject result = new JSONObject();
			result.put(JsonConst.result, JsonConst.formatErr);//格式错误
			return result.toString();
		}
		
		DoctorModel model = null;
		try {
			model = doctorService.getDoctorByPhoneNum(phoneNum);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		
		if((model == null || model.getStatus() != '1')) {//正式库中没有，看审核库
			DoctorAuditModel p1 = null;
			try {
				p1 = doctorAuditService.getDoctorAuditByPhoneNum(phoneNum);
			} catch (Exception e1) {
				log.error(e1.getMessage(),e1);
			}
			if(p1 != null && p1.getStatus() == 3) {//3:待审核,4:审核未通过
				JSONObject result = new JSONObject();
				result.put(JsonConst.result, JsonConst.waitAudit);//待审核
				return result.toString();
			}
			if(p1 != null && p1.getStatus() == 4) {//4审核未通过
				JSONObject result = new JSONObject();
				result.put(JsonConst.result, JsonConst.auditNoSuccess);//审核未通过
				return result.toString();
			}
			JSONObject json = new JSONObject();
			json.put(JsonConst.result, JsonConst.notExist);
			return json.toString();
		}else {//存在
			if(! model.getPassword().equalsIgnoreCase(MD5.encodeByMD5(passWord))) {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.accountPasswdError);
				return json.toString();
			}
			/*if(model.getWeixinAppId() != null && !"".equals(model.getWeixinAppId())) {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.alreadyBind);//已经绑定过了
				return json.toString();
			}*/
			Object openId = session.getAttribute(ServerConfigConst.wxOpenIdSessionAttr);
			try {
				if(openId == null) {
					String agent = request.getHeader("User-Agent");
					if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
						String code = request.getParameter("code");
						String state = request.getParameter("state");
						if(code!=null&&state!=null&&state.equals("1")) {
							String openid = WeixinBasicKit.queryOpenidByCode(code);
							if(null != openid) {
								session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr,openid);
								log.error("绑定openId="+openid+","+model.toString());
								model.setWeixinAppId(openid);
								doctorService.update(model);
								session.setAttribute(ServerConfigConst.doctorInfoSessionAttr, model);
								JSONObject json = new JSONObject();
								json.put(JsonConst.result, JsonConst.success);
								return json.toString();
							}
						}
					}else {
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.openInWeixinPlease);
						return json.toString();
					}
				}else {
					log.error("绑定openId="+openId+","+model.toString());
					model.setWeixinAppId((String)openId);
					doctorService.update(model);
					session.setAttribute(ServerConfigConst.doctorInfoSessionAttr, model);
					JSONObject json = new JSONObject();
					json.put(JsonConst.result, JsonConst.success);
					return json.toString();
				}
			}catch(Exception e) {
				log.error("绑定失败"+model.toString(),e);
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.fail);
				return json.toString();
			}
		}
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		JSONObject json = new JSONObject();
		json.put(JsonConst.result, JsonConst.fail);
		return json.toString();
	}
	
	/**医生详情页面 */
	@RequestMapping(value = "doctor/detail/{doctorId:\\d+}", method = RequestMethod.GET)
	public ModelAndView doctorDetailPage(@PathVariable("doctorId") int doctorId,HttpServletRequest request,HttpServletResponse response
		) throws Exception {
		ModelAndView model = new ModelAndView("/weixin/doctor/doctorDetail");
		try {
			net.sf.json.JSONObject json = doctorService.getDoctorDetail(doctorId);
			if(json != null) {
				model.addObject("photo",json.getString("photo"));
				model.addObject("doctorName",json.getString("doctorName"));
				model.addObject("departmentName",json.getString("departmentName"));
				model.addObject("positionName",json.getString("positionName"));//职称
				model.addObject("hospitalName",json.getString("hospitalName"));//所在医院
				model.addObject("orderCount",json.getString("orderCount"));//已会诊次数
				model.addObject("evaluateCount",json.getString("evaluateCount"));//评价次数
				model.addObject("description",json.getString("description"));//医生简介
				model.addObject("skilledField",json.getString("skilledField"));//擅长领域
				model.addObject("avgScore",Integer.parseInt(json.getString("avgScore").substring(0, json.getString("avgScore").indexOf("."))));//得分
			}	
			return model;
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		return model;
	}
	
	
	/**重置密码页面 */
	@RequestMapping(value = "doctor/resetPasswdPage", method = RequestMethod.GET)
	public ModelAndView resetPasswdPage(HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@RequestParam(value = "phoneNum", required = false) String phoneNum) throws Exception {
//TODO删除这里
//session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr, "ox39CwaThU6vFJA6zwtrFfzirGWA");
				
		//resetPasswdByEmail
		String token = VerifyCodeUtils.generateVerifyCode(6,"0123456789");
		session.setAttribute(ServerConfigConst.resetDoctorPasswdTokenSessionAttr, token);
		ModelAndView model = new ModelAndView("/weixin/doctor/resetPasswd");
		model.addObject("token",token);
		/*ModelAndView resetPasswdByEmailModel = new ModelAndView("/weixin/doctor/resetPasswdByEmail");
		Object openId = session.getAttribute(ServerConfigConst.wxOpenIdSessionAttr);
		try {
			if(openId == null) {
				String agent = request.getHeader("User-Agent");
				if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
					String code = request.getParameter("code");
					String state = request.getParameter("state");
					if(code!=null&&state!=null&&state.equals("1")) {
						String openid = WeixinBasicKit.queryOpenidByCode(code);
						if(null != openid) {
							session.setAttribute(ServerConfigConst.wxOpenIdSessionAttr,openid);
							DoctorModel doctor = doctorService.getDoctorByWxOpenId((String)openId);
							if(doctor == null) {
								resetPasswdByEmailModel.addObject(JsonConst.result,JsonConst.notExist);//账号不存在 或者审核未通过，不支持
								return resetPasswdByEmailModel;
							}
							String token = VerifyCodeUtils.generateVerifyCode(6,"0123456789");
							session.setAttribute(ServerConfigConst.resetDoctorPasswdTokenSessionAttr, token);
							
							if(doctor.getOverSea() == 0) {//国内用户
								model.addObject("token",token);
								return model;
							}else{
								resetPasswdByEmailModel.addObject("token",token);
								return resetPasswdByEmailModel;
							}
						}
					}
				}else {
					resetPasswdByEmailModel.addObject(JsonConst.result,JsonConst.openInWeixinPlease);
					return resetPasswdByEmailModel;
				}
			}else {
				DoctorModel doctor = doctorService.getDoctorByWxOpenId((String)openId);
				if(doctor == null) {
					resetPasswdByEmailModel.addObject(JsonConst.result,JsonConst.notExist);//账号不存在 或者审核未通过，不支持
					return resetPasswdByEmailModel;
				}
				String token = VerifyCodeUtils.generateVerifyCode(6,"0123456789");
				session.setAttribute(ServerConfigConst.resetDoctorPasswdTokenSessionAttr, token);
				
				if(doctor.getOverSea() == 0) {//国内用户
					model.addObject("token",token);
					return model;
				}else{
					resetPasswdByEmailModel.addObject("token",token);
					return resetPasswdByEmailModel;
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}*/
		return model;
	}
	

	/**医生重置密码获验证码*/
	@RequestMapping(value = "doctor/getVerifyCodeForResetPasswd/{phoneNum}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String getVerifyCodeForResetPasswd(@PathVariable("phoneNum") String phoneNum,HttpServletRequest request,HttpServletResponse response, HttpSession httpSession) {
		if(!ValidationTools.isMobile(phoneNum)) {
			JSONObject result = new JSONObject();
			result.put(JsonConst.result, JsonConst.formatErr);//格式错误
			return result.toString();
		}
		try {
			DoctorModel model =  doctorService.getDoctorByPhoneNum(phoneNum);
			if(model != null) {
				String verifyCode = VerifyCodeUtils.generateVerifyCode(6,"0123456789");
				boolean result = smsService.getVerifyCode(phoneNum, verifyCode);
				httpSession.setAttribute(phoneNum+ServerConfigConst.doctorVerifyCodeForResetPasswd, verifyCode);
				if(result) {
					JSONObject json = new JSONObject();
					json.put(JsonConst.result, JsonConst.success);
					json.put("verifyCode", verifyCode);
					return json.toString();	
				}else {
					JSONObject json = new JSONObject();
					json.put(JsonConst.result, JsonConst.getVerifyCodeLimit);
					return json.toString();	
				}
			}else {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.notExist);
				return json.toString();	
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		JSONObject json2 = new JSONObject();
		json2.put(JsonConst.result, JsonConst.fail);
		return json2.toString();	
	}
	
	
	/**重置密码时判断医生手机号是否存在*/
	@RequestMapping(value = "doctor/existPhoneNumForResetPasswd/{phoneNum}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String existPhoneNumForResetPasswd(@PathVariable("phoneNum") String phoneNum,HttpServletRequest request,HttpServletResponse response) {
		if(!ValidationTools.isMobile(phoneNum)) {
			JSONObject result = new JSONObject();
			result.put(JsonConst.result, JsonConst.formatErr);
			return result.toString();
		}
		
		try {
			DoctorModel model = doctorService.getDoctorByPhoneNum(phoneNum);
			if(model != null) {
				JSONObject result = new JSONObject();
				result.put(JsonConst.result, JsonConst.exist);
				return result.toString();
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		JSONObject result = new JSONObject();
		result.put(JsonConst.result, JsonConst.notExist);
		return result.toString();
	}
	

	/**修改密码*/
	@RequestMapping(value = "doctor/resetPasswd", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String resetPasswd(HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@RequestParam(value = "phoneNum", required = true) String phoneNum,
			@RequestParam(value = "verifyCode", required = true) String verifyCode,
			@RequestParam(value = "passWord", required = true) String passWord,
			@RequestParam(value = "passWordConfirm", required = true) String passWordConfirm,
			@RequestParam(value = "token", required = true,defaultValue = "") String token,
			HttpSession httpSession) {
		try {
			Object tokenAttr = session.getAttribute(ServerConfigConst.resetDoctorPasswdTokenSessionAttr);
			if(tokenAttr == null) {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.tokenInvalid);//token失效
				return json.toString();
			}else {
				if(!token.equals((String)tokenAttr)) {//token失效
					JSONObject json = new JSONObject();
					json.put(JsonConst.result, JsonConst.tokenInvalid);//token失效
					return json.toString();
				}else {
					Object code = httpSession.getAttribute(phoneNum+ServerConfigConst.doctorVerifyCodeForResetPasswd);
					if(code == null || !verifyCode.equals((String)code)) {
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.verifyError);//确认密码错误
						return json.toString();
					}
					if(!passWord.equals(passWordConfirm)) {
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.confirmPasswdError);//确认密码错误
						return json.toString();
					}
					//token正常
					String md5Ps = MD5.encodeByMD5(passWord);
					DoctorModel model = doctorService.getDoctorByPhoneNum(phoneNum);
					if(model != null) {
						model.setPassword(md5Ps);
						doctorService.update(model);
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.success);//修改成功
						session.setAttribute(ServerConfigConst.resetDoctorPasswdTokenSessionAttr,null);
						return json.toString();
					}else {
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.notExist);
					}
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		JSONObject json = new JSONObject();
		json.put(JsonConst.result, JsonConst.fail);
		return json.toString();
	}
	
	
	
	/**邮箱用户修改密码*/
	@RequestMapping(value = "doctor/resetPasswdByEmail", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String resetPasswdByEmail(HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "verifyCode", required = true) String verifyCode,
			@RequestParam(value = "passWord", required = true) String passWord,
			@RequestParam(value = "passWordConfirm", required = true) String passWordConfirm,
			@RequestParam(value = "token", required = true,defaultValue = "") String token,
			HttpSession httpSession) {
		try {
			Object tokenAttr = session.getAttribute(ServerConfigConst.resetDoctorPasswdTokenSessionAttr);
			if(tokenAttr == null) {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.tokenInvalid);//token失效
				return json.toString();
			}else {
				if(!token.equals((String)tokenAttr)) {//token失效
					JSONObject json = new JSONObject();
					json.put(JsonConst.result, JsonConst.tokenInvalid);//token失效
					return json.toString();
				}else {
					Object code = httpSession.getAttribute(email+ServerConfigConst.doctorVerifyCodeForResetPasswd);
					if(code == null || !verifyCode.equals((String)code)) {
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.verifyError);//确认密码错误
						return json.toString();
					}
					if(!passWord.equals(passWordConfirm)) {
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.confirmPasswdError);//确认密码错误
						return json.toString();
					}
					//token正常
					String md5Ps = MD5.encodeByMD5(passWord);
					DoctorModel model = doctorService.getDoctorByFieldValue("Email", email);
					if(model != null) {
						model.setPassword(md5Ps);
						doctorService.update(model);
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.success);//修改成功
						session.setAttribute(ServerConfigConst.doctorVerifyCodeForResetPasswd,null);
						return json.toString();
					}else {
						JSONObject json = new JSONObject();
						json.put(JsonConst.result, JsonConst.notExist);
					}
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		JSONObject json = new JSONObject();
		json.put(JsonConst.result, JsonConst.fail);
		return json.toString();
	}
	
	
	
	
	
	
	/**邮箱用户验证邮箱 */ 
	@RequestMapping(value = "doctor//{doctorId:\\d+}/verifyEmail", method = RequestMethod.GET)
	public ModelAndView verifyEmail(@PathVariable("doctorId") int doctorId,HttpServletRequest request,HttpServletResponse response
		) throws Exception {
		ModelAndView model = new ModelAndView("/weixin/doctor/verifyEmail");
		try{
			DoctorAuditModel doctor = doctorAuditService.getDoctorAuditById(doctorId);
			if(doctor == null) {
				model.addObject(JsonConst.result, JsonConst.notExist);
				model.addObject("msg", "您所验证的账号不存在");
				return model;
			}
			if(doctor.getStatus() == 2) {
				doctor.setStatus(3);
				doctorAuditService.update(doctor);
				model.addObject("msg", "您所注册的账号【"+doctor.getEmail()+"】验证成功");
				model.addObject(JsonConst.result, JsonConst.success);
				return model;
			}else if(doctor.getStatus() == 3) {
				model.addObject("msg", "您所注册的账号【"+doctor.getEmail()+"】已通过验证，请勿重复操作");
				model.addObject(JsonConst.result, JsonConst.alreadyVerify);
				return model;
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		return model;
	}
	

	@RequestMapping(value = "doctor/{doctorId:\\d+}/resetPasswdOverseaAccount", method = RequestMethod.POST)
	public @ResponseBody String resetPasswdOverseaAccount(@PathVariable("doctorId") int doctorId,HttpServletRequest request,HttpServletResponse response,
			HttpSession session,
			@RequestParam(value = "passWord", required = true) String passWord,
			@RequestParam(value = "passWordConfirm", required = true) String passWordConfirm,
			@RequestParam(value = "token", required = true) String token) {
		
		/*Object sessionToken = session.getAttribute(ServerConfigConst.doctorModifyByEmailTokenSessionAttr);
		if(sessionToken == null) {
			JSONObject json = new JSONObject();
			json.put(JsonConst.result, JsonConst.tokenInvalid);//该链接超时 失效，请重新获取
			return json.toString();
		}
		if(! ((String)sessionToken).equals(token)) {
			JSONObject json = new JSONObject();
			json.put(JsonConst.result, JsonConst.tokenInvalid);//该链接超时 token不正确
			return json.toString();
		}*/
		
		PasswdModifyLinkVerify msg = PasswdModifyLinkValidPeriodUtil.getInstance().get(token);
		if(	msg == null) {
			JSONObject json = new JSONObject();
			json.put(JsonConst.result, JsonConst.tokenInvalid);//该链接超时 失效，请重新获取
			json.put("msg","此链接已失效请重新获取");
			return json.toString();
		}
		
		try {
			DoctorModel doctor = doctorService.getDoctorById(doctorId);
			if(doctor == null) {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.notExist);//
				return json.toString();
			}else {
				if(! passWord.equals(passWordConfirm)) {
					JSONObject json = new JSONObject();
					json.put(JsonConst.result, JsonConst.confirmPasswdError);//
					return json.toString();
				}
				doctor.setPassword(MD5.encodeByMD5(passWord));
				doctorService.update(doctor);
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.success);
				return json.toString();
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		JSONObject json = new JSONObject();
		json.put(JsonConst.result, JsonConst.fail);
		return json.toString();
	}
	
	
     
	/**
	 * token,doctorId  合并在一块
	 * @RequestMapping(value = "doctor/{doctorId:\\d+}/overseaAccountResetPasswdPage", method = RequestMethod.GET)
	 * 改为 doctor/overseaAccountResetPasswdPage
	 * 参数 token,doctorId
	 * 
	 */
	@RequestMapping(value = "doctor/overseaAccountResetPasswdPage", method = RequestMethod.GET)
	public ModelAndView resetPasswdOverseaAccountPage(HttpServletRequest request,HttpServletResponse response,
			HttpSession session,
			@RequestParam(value = "token", required = true) String token) {
		ModelAndView view = new ModelAndView("/weixin/doctor/resetPasswdOverseaAccountPage");
		Object sessionToken = session.getAttribute(ServerConfigConst.doctorModifyByEmailTokenSessionAttr);
		/*if(sessionToken == null) {                                  
			view.addObject(JsonConst.result, JsonConst.tokenInvalid);//该链接超时 失效，请重新获取
			return view;
		}
		if(! ((String)sessionToken).equals(token)) {
			view.addObject(JsonConst.result, JsonConst.tokenInvalid);//该链接超时 token不正确
			return view;
		}*/
		try {
			//token = java.net.URLDecoder.decode(token, "utf-8");
			String encryptToken = SecurityHelper.decrypt(SecurityHelper.key, token);
			String[] tokens = encryptToken.split(",");
			PasswdModifyLinkVerify msg = PasswdModifyLinkValidPeriodUtil.getInstance().get(tokens[0]);
			if(	msg == null) {
				view = new ModelAndView("/weixin/doctor/passwdModifyLinkInValid");
				view.addObject(JsonConst.result, JsonConst.tokenInvalid);//该链接超时 失效，请重新获取
				view.addObject("msg","此链接已失效请重新获取");
				return view;
			}
			DoctorModel doctor = doctorService.getDoctorById(Integer.parseInt(tokens[1]));
			if(doctor == null) {
				view = new ModelAndView("/weixin/doctor/passwdModifyLinkInValid");
				view.addObject("msg","您的账号不存在");
				view.addObject(JsonConst.result, JsonConst.notExist);//
				return view;
			}else {
				view.addObject("doctorId",doctor.getId());
				view.addObject("token",tokens[0]);
				view.addObject("doctorName",doctor.getDoctorName());
				return view;
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		view.addObject(JsonConst.result, JsonConst.fail);//该链接超时 token不正确
		return view;
	}
			
	
	
	/**判断医生email是否已存在*/
	@RequestMapping(value = "doctor/existEmail", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String existEmail(HttpServletRequest request,HttpServletResponse response,
			HttpSession session,
			@RequestParam(value = "token", required = false,defaultValue = "") String token,
			@RequestParam(value = "email", required = true) String email
			) {

		Object tokenAttr = session.getAttribute(ServerConfigConst.resetDoctorPasswdTokenSessionAttr);
		if(tokenAttr == null) {
			JSONObject json = new JSONObject();
			json.put(JsonConst.result, JsonConst.tokenInvalid);//token失效
			return json.toString();
		}else {
			if(!token.equals((String)tokenAttr)) {//token失效
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.tokenInvalid);//token失效
				return json.toString();
			}
		}
		DoctorModel model = null;
		try {
			model = doctorService.getDoctorByFieldValue("Email",email);
					
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		try {
			if(model != null) {
				JSONObject result = new JSONObject();
				String subject = "医生修改密码";
				long minute = System.currentTimeMillis()/1000l;
				//String verifyCode = VerifyCodeUtils.generateVerifyCode(8,"0123456789");
				String verifyCode = ""+minute+VerifyCodeUtils.generateVerifyCode(8,"0123456789");
				PasswdModifyLinkVerify task = new PasswdModifyLinkVerify(verifyCode,model.getId());
				DoctorPasswdModifyLinkToken linkToken = new DoctorPasswdModifyLinkToken(verifyCode,1);
				doctorService.savePasswdLinkToken(linkToken);
				
				PasswdModifyLinkValidPeriodUtil.getInstance().add(verifyCode, task);
				ServerConfigConst.threadPool.schedule(task, ServerConfigConst.doctorPasswdModifyByEmailLinkValidPeriod, TimeUnit.MINUTES);
				
				session.setAttribute(ServerConfigConst.doctorModifyByEmailTokenSessionAttr, verifyCode);
			//	String sendMsg = "<a href=\""+WeixinContext.wxDeployBaseUrl+"/doctor/"+ model.getId()+"/overseaAccountResetPasswdPage?token="+verifyCode+"\"/>尊敬的医生:"+model.getDoctorName()+"您好，请点击链接完成密码修改</a>";
				String tokenSuffix = verifyCode+","+ model.getId();
				String encryptToken = SecurityHelper.encrypt(SecurityHelper.key, tokenSuffix);
				encryptToken = java.net.URLEncoder.encode(encryptToken, "utf-8"); 
				
				/*String sendMsg = "<a href=\""+WeixinContext.wxDeployBaseUrl+"/doctor/overseaAccountResetPasswdPage?token="+encryptToken+"\"/>尊敬的医生:"+model.getDoctorName()+"您好，请点击链接完成密码修改</a>";
				EmailUtil.send(subject, email, sendMsg);*/
				
				/*String msg = "<a href=\""+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorResetPasswdVerifyEmailMsgUrlTeplate+"\"/>"+ServerConfigConst.doctorResetPasswdVerifyEmailMsgContentTeplate+"</a>";
				msg = msg.replace(ServerConfigConst.TOKEN, encryptToken).replace(ServerConfigConst.DOCTORNAME, model.getDoctorName());
				EmailUtil.send(ServerConfigConst.doctorResetPasswdVerifyEmailMsgSubjectTeplate,
						email, msg);*/
				
				String msg = ServerConfigConst.doctorResetPasswdVerifyEmailMsgContentTeplate+"<br>   <a href=\""+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorResetPasswdVerifyEmailMsgUrlTeplate+"\"/>"+WeixinContext.wxDeployBaseUrl+ServerConfigConst.doctorResetPasswdVerifyEmailMsgUrlTeplate+"</a> <br><br>这是一封系统邮件，请勿直接回复。";
				msg = msg.replaceAll(ServerConfigConst.TOKEN, encryptToken).replace(ServerConfigConst.DOCTORNAME, model.getDoctorName()==null?"":model.getDoctorName());
				EmailUtil.sendResetPasswdEmail(ServerConfigConst.doctorResetPasswdVerifyEmailMsgSubjectTeplate,
						email, msg);
				
				result.put(JsonConst.result, JsonConst.exist);
				return result.toString();
			}else {
				JSONObject result = new JSONObject();
				result.put(JsonConst.result, JsonConst.notExist);
				return result.toString();
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		} 
		JSONObject result = new JSONObject();
		result.put(JsonConst.result, JsonConst.fail);
		return result.toString();
		
	}
	
	
	/**医生修改密码通过邮箱获取验证码*/
	@RequestMapping(value = "doctor/getEmailVerifyCodeForModifyPasswd", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String getEmailVerifyCodeForModifyPasswd(
			@RequestParam(value = "email", required = true) String email,
			HttpServletRequest request,HttpServletResponse response, HttpSession httpSession) {
		
		DoctorModel model = null;
		try {
			model = doctorService.getDoctorByFieldValue("Email", email);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		if(model == null) {
			JSONObject result = new JSONObject();
			result.put(JsonConst.result, JsonConst.notExist);//该邮箱不存在
			return result.toString();
		}
	
		String verifyCode = VerifyCodeUtils.generateVerifyCode(6,"0123456789");
		String emailContent = "您的验证码为："+verifyCode;
		String subject = "e会诊医生找回密码";
		try {
			EmailUtil.sendEmail(subject,email, emailContent);
			httpSession.setAttribute(email+ServerConfigConst.doctorVerifyCodeForResetPasswd, verifyCode);
			
			JSONObject json = new JSONObject();
			json.put(JsonConst.result, JsonConst.success);
			json.put("verifyCode", verifyCode);
			return json.toString();	
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		JSONObject json = new JSONObject();
		json.put(JsonConst.result, JsonConst.fail);
		return json.toString();
	}
	
	
	
	/**待审核页面 */
	@RequestMapping(value = "doctor/waitAudit", method = RequestMethod.GET)
	public ModelAndView waitAudit(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "phoneNum", required = false) String phoneNum) throws Exception {
		
		ModelAndView model = new ModelAndView("/weixin/doctor/waitAudit");
		/*DoctorAuditModel doctorAudit = doctorAuditService.getDoctorAuditByPhoneNum(phoneNum);
		if(doctorAudit != null) {
			model.addObject("phoneNum", doctorAudit.getPhoneNum());
			model.addObject("doctorName", doctorAudit.getDoctorName());
		}
		model.addObject("msg", "您的资料提交成功,等待审核中。");*/
		return model;
	}
	
	
	/**医生注册信息*/
	@RequestMapping(value = "doctor/signUp", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String signUp(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "patientName", required = true) String patientName,
			@RequestParam(value = "phoneNum", required = true) String phoneNum,
			@RequestParam(value = "verifyCode", required = true) String verifyCode,
			@RequestParam(value = "passWord", required = true) String passWord,
			 HttpSession httpSession) {
		
		/*String _verifyCode = (String)httpSession.getAttribute(phoneNum+ServerConfigConst.doctorVerifyCodeSessionAttr);
		if(_verifyCode != null && _verifyCode.equals(verifyCode)) {//验证码正确
			DoctorAuditModel entity = new DoctorAuditModel(patientName, phoneNum, passWord, new Timestamp(System.currentTimeMillis()));
			boolean result = false;
			try {
				result = doctorService.save(entity);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			if(result) {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.success);
				return json.toString();
			} else {
				JSONObject json = new JSONObject();
				json.put(JsonConst.result, JsonConst.fail);
				return json.toString();
			}
		} else {
			JSONObject json = new JSONObject();
			json.put(JsonConst.result, JsonConst.verifyError);
			return json.toString();
		}*/
		return null;
	}
	
	
	
	/**
	 * 获取资讯浏览量
	 * 
	 * @param postId
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/post/{postId:\\d+}/info")
	public void getVisitCount(@PathVariable("postId") int postId, HttpServletResponse response,
			HttpServletRequest request) {
		JSONObject ret = new JSONObject();
		
		ret.put("visitCount", 2);
		String callback = request.getParameter("callback");
		try {
			java.io.PrintWriter out = response.getWriter();
			out.print(callback + "(" + ret.toString() + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
