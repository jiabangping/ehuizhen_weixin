package com.ehuizhen.test.annotation;

public enum  RegexRule {
	
	/**email正則表達式規則*/
	EMAIL("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"),

	/**數字正則表達式規則*/
	NUMBER("^[0-9]*$");

	public String value;

	RegexRule(String value) {
		this.value = value;
	}
}
