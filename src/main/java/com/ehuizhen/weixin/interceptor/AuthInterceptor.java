package com.ehuizhen.weixin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ehuizhen.weixin.init.ServerConfigConst;

public class AuthInterceptor implements HandlerInterceptor  {

	 	private static final String patientSignInPageUrl = "patient/signInPage"; //登录页面 
	 	private static final String doctorSignInPageUrl = "doctor/signInPage"; //注册页面
	 	

	 	private static String[] patientAuthUrls = {//患者需要登录后才能访问的页面
	 			//"doctor/doctorsPage",//医生列表页面
	 			"sms/smsPage",//会诊消息页面
				"api/v1/sms/query",//获取会诊信息
				//"doctor/casehistoryPage"//精彩病历
	 	};
	 	
	 	private static String[] doctorAuthUrls = {//医生需要登录后才能访问的页面
				//"doctor/casehistoryPage"//精彩病历
	 	};
	 	
	 	static {
	 		//authUrls = ServerConfigConst.authUrls;
	 	}
	 	
	    @Override  
	    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
	    	String uri = req.getRequestURI();
	    	
	    	/*String path = req.getContextPath();
	    	String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
		    basePath+=signInPageUrl;*/
	    		
		    
	    	
	    	String[] paths = uri.split("/");
	    	StringBuilder builder = new StringBuilder();
	    	builder.append(paths[paths.length-2]).append("/").append(paths[paths.length-1]);
	    	
	    	/*for(String url : patientAuthUrls) {
	    		if(url.contains(builder.toString())) {
	    			 HttpSession session = req.getSession(true);  
	    		        // 从session 里面获取用户名的信息  
	    		        Object obj = session.getAttribute(ServerConfigConst.patientInfoSessionAttr);  
	    		        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
	    		        if (obj == null || "".equals(obj.toString())) {  
	    		        	res.sendRedirect(noAuthUrlBuilder.toString());  
	    		        }  
	    		        return true;  
	    		}
	    	}*/
	    	HttpSession session = req.getSession(true);  
	    	Object patientSessionAttr = session.getAttribute(ServerConfigConst.patientInfoSessionAttr);  
	        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
	        if (patientSessionAttr == null ) {  
		    	for(String url : patientAuthUrls) {
		    		if(builder.toString().contains(url)) {
		    			StringBuilder patientNoAuthUrlBuilder = new StringBuilder();
		    			patientNoAuthUrlBuilder.append(req.getScheme()).append("://").append(req.getServerName()).append(":").append(req.getServerPort()).append(req.getContextPath()).append("/").append(patientSignInPageUrl);
		    			res.sendRedirect(patientNoAuthUrlBuilder.toString()); 
	    		        return true;  
		    		}
		    	} 
	    	} 
	        
	        
	        Object doctorSessionAttr = session.getAttribute(ServerConfigConst.doctorInfoSessionAttr);  
	        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
	        if (doctorSessionAttr == null ) {  
		    	for(String url : doctorAuthUrls) {
		    		if(builder.toString().contains(url)) {
		    			StringBuilder doctorNoAuthUrlBuilder = new StringBuilder();
	    		        doctorNoAuthUrlBuilder.append(req.getScheme()).append("://").append(req.getServerName()).append(":").append(req.getServerPort()).append(req.getContextPath()).append("/").append(doctorSignInPageUrl);	
		    			res.sendRedirect(doctorNoAuthUrlBuilder.toString()); 
	    		        return true;  
		    		}
		    	} 
	    	} 
	    	return true;
	    }  
	  
	    @Override  
	    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3) throws Exception {  
	    }  
	  
	    @Override  
	    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3) throws Exception {  
	    }  

}
