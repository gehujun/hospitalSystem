package com.web.util;

public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isStringEmpty(String str){
		if(str == null){
			return false;
		}
		return str.length()>0 ? true : false;
	}
}
