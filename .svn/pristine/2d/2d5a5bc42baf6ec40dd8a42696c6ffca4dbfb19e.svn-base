package com.ehuizhen.weixin.exception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogInterceptor implements MethodInterceptor  {  
	static String replaceStr = "org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation";
    public Object invoke(MethodInvocation invocation) throws Throwable  {
    	
    	Logger loger = LoggerFactory.getLogger(invocation.getClass());
//      loger.error("-------------------------------------------------------------------------------");

    	loger.error(invocation.getMethod() + ":BEGIN!");// 方法前的操作  
        Object obj = invocation.proceed();// 执行需要Log的方法  
        loger.error(invocation.getMethod() + ":END!");// 方法后的操作  
//      loger.error("-------------------------------------------------------------------------------------------------");  
        return obj;  
    }  
  
}  