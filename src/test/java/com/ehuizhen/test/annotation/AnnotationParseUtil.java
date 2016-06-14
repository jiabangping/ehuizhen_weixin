package com.ehuizhen.test.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**〈注解解析通用類〉*/
public class AnnotationParseUtil {

	/*public void parseMethod(Class clazz) throws IllegalArgumentException, IllegalAccessException,
		InvocationTargetException, SecurityException, NoSuchMethodException, InstantiationException {
		Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
		for (Method method : clazz.getDeclaredMethods()) {
			DateFormat df = method.getAnnotation(DateFormat.class);
			String name = "";
			if (df != null) {
				name = df.dateType().value;
				method.invoke(obj, name);
			}
		}
	}*/
	
	/**
	* 功能描述: <br>
	* 〈调用指定类的指定方法，传参〉
	*/
	
	public void parseMethod(Object proxy, Method method, Object[] args) throws IllegalArgumentException,
		IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException,
		InstantiationException {
		
		// Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
		
		Regex df = method.getAnnotation(Regex.class);
		
		String name = "";
		if (df != null) {
			name = df.regexRule().value;
			method.invoke(proxy, args[0], name);
		}
	}

}