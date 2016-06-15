package com.ehuizhen.weixin.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationTools {

	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	public static boolean isOverSeaMobile(String str) {
		if(str == null || "".equals(str) || str.length() != 11) {
			return false;
		}
		return true;
	}
}
