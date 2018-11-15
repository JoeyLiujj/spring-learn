package com.taikang.common.safeUtil;

import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESencrypt {
	private static Logger log = LoggerFactory.getLogger(DESencrypt.class);

	public static String encryptECB(String key, String value) {
		try {
			SecretKey secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] binaryData = cipher.doFinal(value.getBytes("UTF-8"));
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(binaryData);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			log.info("Invalid DES key, not encrypting");
			return null;
		} catch (Exception e1) {
			e1.printStackTrace();
			log.info("Error in encryption, not encrypting");
			return null;
		}
	}

	public static String decryptECB(String key, String value) {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] binaryValue = decoder.decodeBuffer(value);
			SecretKey secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] data = cipher.doFinal(binaryValue);
			return new String(data, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		// 宠物险联调测试 加解密使用的key test58pet
		String key = "58xinpet";
		String value = "adsfasdfwrasfadfadsfasfda";
		// String value = "投保信息-testcrypt";
		String encryptText = encryptECB(key, value);
		log.info(encryptText);

		String originData = decryptECB(key, encryptText);
		log.info(originData);
	}
}
