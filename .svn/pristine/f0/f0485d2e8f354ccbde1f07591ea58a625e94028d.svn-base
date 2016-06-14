package com.ehuizhen.weixin.util;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehuizhen.weixin.init.WeixinContext;
import com.ehuizhen.weixin.pojo.Token;
import com.ehuizhen.weixin.tools.CommonUtil;


public class GetTokenTask extends TimerTask {
	private static final Logger log = LoggerFactory.getLogger(GetTokenTask.class);
	private String appID = "wx50755e9abe9fff28";
	private String appsecret = "126bc11043f0d717c950b569babff065"; 
	private long second = 1000l;//秒
	private long minute = second*60l;//1分钟
	
	@Override
	public void run() {
		try {
			Token token = CommonUtil.getToken(WeixinContext.appId, WeixinContext.appSecurt);
			if (null != token) {
				WeixinContext.accessToken = token;
			}else {
				Token token2 = CommonUtil.getToken(WeixinContext.appId, WeixinContext.appSecurt);
				if (null != token2) {
					WeixinContext.accessToken = token2;
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			Token token = CommonUtil.getToken(WeixinContext.appId, WeixinContext.appSecurt);
			if (null != token) {  
				WeixinContext.accessToken = token;
			}
		} 
	}
	
	public void init() {
		Timer  timer=new Timer();  
		GetTokenTask task = new GetTokenTask();
		//timer.schedule(task, 0, minute*1);//1分钟
		timer.schedule(task, 0, minute*100);//100分钟
	}
	
	public static void main(String[] args) {
		GetTokenTask.getInstance().init();
	}
	
	
	private static GetTokenTask task = new GetTokenTask();
	public static GetTokenTask getInstance() {
		return task;
	}
	
}
