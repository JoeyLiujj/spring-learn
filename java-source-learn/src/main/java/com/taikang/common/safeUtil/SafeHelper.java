package com.taikang.common.safeUtil;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.taikang.common.propertyUtil.PropertiesUtil;

@SuppressWarnings("unchecked")
public final class SafeHelper {

	private static SafeHelper routingManager = new SafeHelper();

	public static SafeHelper getInstance() {
		return routingManager;
	}

	private static byte[] stringToByte(String str) {
		str = str.replace("A", "D").replace("B", "D").replace("C", "D").replace("E", "-").replace("F", "-");
		String[] keys = str.split("D");
		byte[] b = new byte[keys.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = Byte.valueOf(keys[i]);
		}
		return b;
	}

	public static String dec(String content) {
		try {
			String sk = "80B" + "78C" + "F116CE" + "111AE74" + "AF98D6BF55" + "BF30AF75" + "D76CF123B" + "6AF37CF" + "123AF40";
			byte[] data = stringToByte(content);
			Key key = new SecretKeySpec(stringToByte(sk), "A" + "ES");
			Cipher cipher = Cipher.getInstance("A" + "ES/E" + "CB/P" + "KCS" + "5Pa" + "dding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	private static String byteToString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		String[] mixs = { "A", "B", "C", "D", "E", "F" };
		for (int i = 0; i < b.length; i++) {
			String tmp = String.valueOf(b[i]);
			int number1 = (int) (Math.random() * 2);
			tmp = tmp.replace("-", mixs[number1 + 4]);
			sb.append(tmp);
			if (i != b.length - 1) {
				int number2 = (int) (Math.random() * 4);
				sb.append(mixs[number2]);
			}
		}
		return sb.toString();
	}

	public static String enc(String content) {
		try {
			String sk = "80B" + "78C" + "F116CE" + "111AE74" + "AF98D6BF55" + "BF30AF75" + "D76CF123B" + "6AF37CF" + "123AF40";
			byte[] data = content.getBytes();
			Key key = new SecretKeySpec(stringToByte(sk), "A" + "ES");
			Cipher cipher = Cipher.getInstance("A" + "ES/E" + "CB/P" + "KCS" + "5Pa" + "dding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return byteToString(cipher.doFinal(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(enc("claim123"));
		System.out.println(enc("claim123"));
		System.out.println(dec("F28C72BF7D40C75B44B51BF76CE127DE27C112C41DF115AE20CE2AE51"));
		System.out.println(dec("E28B72BE7A40A75C44B51BF76AE127DF27B112B41CE115AF20BF2CE51"));
	}

	/**
	 * 字符串加密方法 
	 * 加密规则
	 *   要加密的+字符串(配置文件中的)+md5
	 * @return
	 * @author itw_guojx02
	 */
	public static String stringEnc(String req){
		return Md5.getMD5Mac(req + PropertiesUtil.parseStr("SECURITY"));
	}
}
