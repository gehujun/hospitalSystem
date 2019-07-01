package com.web.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {
	
	private static final ObjectMapper jsonMap = new ObjectMapper();
	
	/**
	 * 把对象转换为json字符串
	 * @param object
	 * @return
	 */
	public static String getJson(Object object) {
		try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            jsonMap.setDateFormat(sdf);//日期格式转换
            return jsonMap.writeValueAsString(object);
        } catch (JsonProcessingException  e) {
            e.printStackTrace();
        }
        return null;
	}
	
	/**
	 * json字符串转换为对象
	 * @param json
	 * @return
	 */
	public static<T> T getBean(String json, Class<T> da) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.readValue(json, da);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
