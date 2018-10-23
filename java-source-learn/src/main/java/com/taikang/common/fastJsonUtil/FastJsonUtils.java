package com.taikang.common.fastJsonUtil;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * fastJson工具类
 * @author itw_guojx02
 *
 */
public class FastJsonUtils {
	
	private static Logger log = LoggerFactory.getLogger(FastJsonUtils.class);
	
	/**
	 * 将对象转成json串
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object){
		return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
	}
	
	
	/**
	 * json串转成对象
	 * @param
	 * @return
	 */
	public static <T> T toObject(String jsonStr,Class<T> cla){
		
		return JSON.parseObject(jsonStr, cla);
		
	}
	/**
	 * 响应json字符串
	 * @param response
	 * @param jsonString
	 */
	public static void write_json(HttpServletResponse response,String jsonString){
		response.setContentType("application/json;utf-8");
		log.debug(jsonString);
		try {
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 响应json字符串,把要转换成json的对象传进来
	 * @param response
	 * @param object
	 */
	public static void write_json(HttpServletResponse response,Object object){
		//把对象转换成json
		String jsonString = JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
		//设置响应头
		response.setContentType("application/json;utf-8");
		response.setCharacterEncoding("UTF-8");
		log.debug(jsonString);
		try {
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
