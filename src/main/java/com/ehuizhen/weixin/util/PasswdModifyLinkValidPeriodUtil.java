package com.ehuizhen.weixin.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//医生邮箱修改密码 链接有效时长
public class PasswdModifyLinkValidPeriodUtil {
	private static PasswdModifyLinkValidPeriodUtil instance = new PasswdModifyLinkValidPeriodUtil();
	public static PasswdModifyLinkValidPeriodUtil getInstance() {
		return instance;
	}
	private static Map<String,PasswdModifyLinkVerify> map = new ConcurrentHashMap<String,PasswdModifyLinkVerify>();
	
	public void add(String randomNum,PasswdModifyLinkVerify msg) {
		PasswdModifyLinkValidPeriodUtil.map.put(randomNum, msg);
	}
	
	public void remove(String key) {
		PasswdModifyLinkValidPeriodUtil.map.remove(key);
	}
	
	public void remove(PasswdModifyLinkVerify msg) {
		PasswdModifyLinkValidPeriodUtil.map.remove(msg);
	}
	public PasswdModifyLinkVerify get(String key) {
		return PasswdModifyLinkValidPeriodUtil.map.get(key);
	}
	
	
}
