package com.ehuizhen.test.annotation;

import java.util.regex.Pattern;

/**

*

* 〈用例〉

*

* @author timeng

*/

public class UseCase {

	/*
	* @DateFormat(dateType=DateType.DateOnly) public void formatDate(Date date,String type){ System.out.println(date);
	* }
	*/

	@Regex(regexRule = RegexRule.EMAIL)
	public void regexEmail(String unCheckedString, String regexRule) {
	
		boolean rs = Pattern.compile(regexRule).matcher(unCheckedString).find();
		// System.out.println(unCheckedString);
		// System.out.println(regexRule);
		
		System.out.println(rs);
	}

}