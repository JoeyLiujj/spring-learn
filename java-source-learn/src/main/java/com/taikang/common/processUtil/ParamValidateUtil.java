package com.taikang.common.processUtil;

import java.util.regex.Pattern;

/**
 * 一些简单的正则表达式的匹配
 * @author itw_pengcc
 *
 */
public class ParamValidateUtil {

	private final static String MOBILE="^1[345678]\\d{9}$";
	public static final String REGEX_NAME ="^([A-z\u4E00-\u9FA5\uF900-\uFA2D]+[.|·]?)+([A-z\u4E00-\u9FA5\uF900-\uFA2D]+)$";
	/**
	 * 匹配中文字符的长度
	 * @param input
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean checkChineseLength(String input,int min,int max){
		return Pattern.matches("^[\u4e00-\u9fa5]{"+min+","+max+"}$", input);
	}
	
	public static boolean checkName(String name){
		boolean flag=false;
		boolean flag2=false;
		if(name.length()>=2|| name.length()<=15){
			flag=true;
		}
		
		if(Pattern.matches(REGEX_NAME, name)){
			flag2=true;
		}
		
		return flag && flag2;
			
	}
	public static boolean checkIdentityCard(String identityCard){
		identityCard=identityCard.trim();
		if (IdcardUtils.validateIdCard18(identityCard)) { return true; }
		if (IdcardUtils.validateIdCard15(identityCard)) { return true; }
		return false;	
	}
	
	public static boolean checkPhoneNum(String mobile){
		boolean flag1=false;
		boolean flag2=false;
		if(mobile==null || mobile.equals("")){
			return flag2;
		}
		mobile=mobile.trim();
		if(mobile.matches(MOBILE)){
			flag1=true;
		}
		if(flag1 && !mobile.substring(2).equals("123456789") && !mobile.substring(2).equals("987654321")){
			flag2=true;
		}
		return flag2;

	}
	
}
