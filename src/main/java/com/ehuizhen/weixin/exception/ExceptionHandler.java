package com.ehuizhen.weixin.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

public class ExceptionHandler implements HandlerExceptionResolver {
	//private MappingJacksonJsonView  jsonView = new MappingJacksonJsonView();

	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			
			   // return new ModelAndView( jsonView, "error", new ErrorMessage( ex ) );
			    
			    
			/*PrintWriter writer = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("result", "fail");
			writer.print(json.toString());
			writer.flush();*/
			return new ModelAndView("redirect:/patient/signInPage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/patient/signInPage");
	}


}
