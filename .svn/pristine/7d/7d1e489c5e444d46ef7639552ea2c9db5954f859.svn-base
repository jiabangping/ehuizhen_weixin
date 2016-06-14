package com.ehuizhen.weixin.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehuizhen.weixin.config.WeixinBasicKit;
import com.ehuizhen.weixin.config.WeixinFinalValue;
import com.ehuizhen.weixin.init.ServerConfigConst;
import com.ehuizhen.weixin.init.WeixinContext;
import com.ehuizhen.weixin.model.PatientModel;


public class WeixinAuthFilter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(WeixinAuthFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hRequest = (HttpServletRequest)request;
		String uril = hRequest.getRequestURL().toString();
		HttpServletResponse hResponse = (HttpServletResponse)response;
		/*if(uril.contains("patient/myConsultationPage")) {
			String path = hRequest.getRequestURL().toString();
			String query = hRequest.getQueryString();
			if(query!=null) {
				path = path+"?"+query;
			}
			Object m = hRequest.getSession().getAttribute(ServerConfigConst.patientInfoSessionAttr);
			if(m == null) {
				hResponse.sendRedirect("signInPage?redirectUrl=/patient/myConsultationPage");
				return;
			}
		}*/
//PatientModel tu = (PatientModel)hRequest.getSession().getAttribute(ServerConfigConst.patientInfoSessionAttr);
//if(tu==null) {
			Object oid = hRequest.getSession().getAttribute(ServerConfigConst.wxOpenIdSessionAttr);
			if(oid == null) {
				String agent = hRequest.getHeader("User-Agent");
				if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
					String code = request.getParameter("code");
					String state = request.getParameter("state");
					if(code!=null&&state!=null&&state.equals("1")) {
						//通过Code获取openid来进行授权
						String openid = WeixinBasicKit.queryOpenidByCode(code);
						if(openid!=null) {
							log.error("wxOpenId="+openid);
							hRequest.getSession().setAttribute(ServerConfigConst.wxOpenIdSessionAttr, openid);
						}
					} else {
						String path = hRequest.getRequestURL().toString();
						String query = hRequest.getQueryString();
						if(query!=null) {
							path = path+"?"+query;
						}
						String uri = WeixinFinalValue.AUTH_URL;
						uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
					
						uri = uri.replace("APPID", WeixinContext.appId)
						   .replace("REDIRECT_URI",URLEncoder.encode(path, "UTF-8"))
						   .replace("SCOPE", "snsapi_base")
						   .replace("STATE", "1");
						hResponse.sendRedirect(uri);
						log.error("uri="+uri);
						return;
					}
				}
			}
//}else {
			/*if(tu.getBindStatus() != 1) {//1是已经绑定
				Object openId = hRequest.getSession().getAttribute("openId");
				if(openId != null) {
					tu.setOpenId((String)openId);
					tu.setBindStatus(1);
					System.out.println("openId="+openId+","+tu);
					try {
						ServerConfigConst.patientService.update(tu);
						hRequest.getSession().setAttribute(ServerConfigConst.patientInfoSessionAttr, tu);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}*/
//}
		chain.doFilter(hRequest, hResponse);
	}

	@Override
	public void destroy() {

	}

}
