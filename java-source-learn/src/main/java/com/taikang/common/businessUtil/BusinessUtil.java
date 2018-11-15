package com.taikang.common.businessUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSON;
import com.taikang.common.safeUtil.Md5;
import com.taikang.utils.MemberFunc;

public class BusinessUtil {

	private static final Logger logger = Logger.getLogger(BusinessUtil.class.getName());
	
	/**
	 * 生成服务接口返回的标准json串 包含内容: 1、code:服务器状态码，200，正常
	 * 
	 */
	public static String genResourceResult(String status, String msg, Object resourceResult) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null == status || "".equals(status)) {
			status = "500";
		}
		if (null == msg || "".equals(msg)) {
			msg = "unknow message";
		}
		if (null == resourceResult || "".equals(resourceResult)) {
			resourceResult = "{}";
		}
		resultMap.put("code", status);
		resultMap.put("msg", msg);
		resultMap.put("result", resourceResult);
		return JSON.toJSONString(resultMap);
	}
	
	/**
	 * Md5校验
	 */
	public static String Md5Check(String json, String security) {
		String req = json;
		if (null == json || "".equals(json)) {
			throw new IllegalArgumentException("json error");
		}
		try {
			Map<String, Object> param = JSON.parseObject(json);
			String token = (String) param.get("token");
			Object reqObj = param.get("req");
			if (reqObj instanceof Map) {
				req = JSON.toJSONString(reqObj);
			} else {
				req = (String) reqObj;
			}
			if (null == token || "".equals(token) || null == req || "".equals(req)) {
				throw new IllegalArgumentException("Argument error");
			}
			if (!Md5.MACCompare(req + security, token)) {
				throw new SecurityException("Illegal request!");
			}
			
		} catch (IllegalArgumentException e) {
			throw new SecurityException("Argument error");
		} catch (Exception e) {
			throw new RuntimeException("json error");
		}
		return req;
	}
	/**
	 * 解密手机号
	 * @param cipher
	 * @return
	 */
	public static String decryptMobile(String cipher){
		String plainText =  MemberFunc.decryptMobile(cipher);
		//连接不上加解密服务器或者解密失败会返回空
		//这个时候密文原样返回
		try{
			if(plainText==null || "".equals(plainText)){
				plainText = cipher;
			}else{
				//成功解密后，进行转码
				plainText = URLDecoder.decode(plainText, "UTF-8");
			}
		} catch (Exception e) {
			//出现异常时，将密文返回
			plainText = cipher;
			logger.info("加解密出错");
		}
		return plainText;
	}
	/**
	 * 解密证件号
	 * @param cipher
	 * @return
	 */
	public static String decryptCid(String cipher){
		String plainText =  MemberFunc.decryptCid(cipher);
		//连接不上加解密服务器或者解密失败会返回空
		//这个时候密文原样返回
		try{
			if(plainText==null || "".equals(plainText)){
				plainText = cipher;
			}else{
				//成功解密后，进行转码
				plainText = URLDecoder.decode(plainText, "UTF-8");
			}
		} catch (Exception e) {
			//出现异常时，将密文返回
			plainText = cipher;
			logger.info("加解密出错");
		}
		return plainText;
	}
	

	/**
	 * 加密证件号
	 * @param value
	 * @return
	 */
	public static String encryptCid(String value) {
		String result = value;
		if (result == null || result.equals("")) {
			return null;
		}
		// 默认进行加密
		try {
			value = URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("urlencoder编码错误！");
			e.printStackTrace();
		}
		result = MemberFunc.encryptCid(value);
		return result;
	}

	/**
	 * 加密手机号
	 * @param value
	 * @return
	 */
	public static String encryptMobile(String value) {
		String result = value;
		// 默认进行加密
		result = MemberFunc.encryptMobile(value);
		return result;
	}
	
}
