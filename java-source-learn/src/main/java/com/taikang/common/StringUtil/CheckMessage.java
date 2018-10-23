package com.taikang.common.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验信息
 * @author itw_guojx02
 *
 */
public class CheckMessage {
	
	/**
	 * 校验邮箱
	 * @param str
	 * @return
	 */
	public static boolean checkEmail(String str){
		String regex="^([a-zA-Z0-9_-])+(.?)+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$";
		return match(regex,str);
	}
	
	/**
	 * 校验密码
	 * @param str
	 * @return
	 */
	public static boolean checkPassword(String str){
		String regex="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$";
		return match(regex,str);
	}
	
	/**
	 * 校验手机号
	 * @param str
	 * @return
	 */
	public static boolean checkPhoneNumber(String str){
		String regex="^1[0-9]{10}$";
		return match(regex,str);
	}
	
	
	
	
	public static boolean match(String regex,String str){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
