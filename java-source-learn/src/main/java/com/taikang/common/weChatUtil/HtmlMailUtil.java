package com.taikang.common.weChatUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class HtmlMailUtil {
	
	
	
	/**
	 * 读取文本文件到字符串
	 * @param path
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String readTextFile(String path, HttpServletRequest request) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		InputStream is = null;
		try {
			is = request.getServletContext().getResourceAsStream(path);
			inputStreamReader = new InputStreamReader(is,"UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			char[] chars = new char[1024];
			int len = 0;
			while ((len=bufferedReader.read(chars)) != -1) {
				sb.append(new String(new String(chars,0,len).getBytes("UTF-8")));
			}
			
		} catch (Exception e) {
			System.out.println("读取文件失败！");
			e.printStackTrace();
		}finally{
			if (null != bufferedReader) {
				bufferedReader.close();
			}
			if (null != inputStreamReader) {
				inputStreamReader.close();
			}
			if (null != is) {
				is.close();
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param tokens 替换使用的数据集合
	 * @param template 要替换的字符串
	 * @param patternString 替换规则
	 * @return
	 */
	public static String replaceTemplateParam(Map<String, String> tokens, String template, String patternString){
		
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(template);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * 生成一定位数的验证码
	 * @param length
	 * @return
	 */
	public static String generateVerifyCode(int length){
		char[] chars = {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H',
				'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<length;i++){
			sb.append(chars[random.nextInt(chars.length)]);
		}
		return sb.toString();
	}

}
