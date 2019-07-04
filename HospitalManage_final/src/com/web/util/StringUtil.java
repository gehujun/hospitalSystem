package com.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public static Date stringToDate(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = null;
		
		if(StringUtil.isStringEmpty(str)){
			try {
				date = sdf.parse(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	public static String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String string = null;
		try {
			string = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}
	public static boolean isStrNotEmpty(String str) {
		if(str == null) {
			return false;
		}
		return str.length() > 0 ? true : false;
	}
}
