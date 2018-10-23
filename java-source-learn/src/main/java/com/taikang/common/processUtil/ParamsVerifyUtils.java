package com.taikang.common.processUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taikang.redis.RedisManager;




/**
 * 前端入参校验
 * @author itw_lijm
 *
 */
public class ParamsVerifyUtils {
	private static Logger log=LoggerFactory.getLogger(ParamsVerifyUtils.class);
	private static final RedisManager redisManager = new RedisManager(
			"netcore2c_port", "prpall");
	/**
	 * 正则表达式:验证港澳同胞证
	 */
	private static final String REGEX_COMPATRIOTS_CARD = "^(H|M)?\\d{10}$";
	/**
	 * 正则表达式:验证移动电话
	 */
	private static final String REGEX_MOBILE = "^1[34578]\\d{9}$";
	/**
	 * 正则表达式：验证包含特殊字符(只能为中文，字母和数字)
	 */
	private static final  String REG_EX_SPECIAL_INPUT="^[a-zA-Z0-9\u4E00-\u9FA5]+$";
	/**
	 * 正则表达式：验证证件类型为军官证或身份证的姓名
	 */
	private static final String REG_EX_ID_ARMY_NAME="^([\u4e00-\u9fa5_\\•*]){2,10}$";
	/**
	 * 正则表达式：验证邮箱
	 */
	private static final String REG_EMAIL="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	/**
	/**
	 * 正则表达式：验证护照
	 */
	private static final String REG_PASSPORT="^(G|E)\\d{8}$";
	/**
	/**
	 * 正则表达式：验证军官证
	 */
	private static final String REG_ARMYID="^(([\\u4E00-\\u9FA5\\uF900-\\uFA2D]{3,5})?\\d{6,12})$";
	/**
	 * 身份证号码验证
	 * @param id
	 * @return
	 */
	public static boolean VerifyId(String id){
		log.debug(">>verifyId身份证号校验==开始"+id);
		if(id.trim().length()==15){
			return IdcardUtils.validateIdCard15(id);
		}
		if(id.trim().length()==18){
			 return IdcardUtils.validateIdCard18(id);
		}
		
		return false;
	}
	/**
	 * 手机号码验证
	 * @param mobile
	 * @return
	 */
	public static boolean verifyMobile(String mobile){
		return Pattern.matches(REGEX_MOBILE, mobile);
	}
	/**
	 * 护照验证
	 * @param mobile
	 * @return
	 */
	public static boolean verifyPassport(String passport){
		return Pattern.matches(REG_PASSPORT, passport);
	}
	/**
	 * 军官证验证
	 * @param mobile
	 * @return
	 */
	public static boolean verifyArmyId(String armyId){
		log.debug(">军官证校验==开始"+armyId);
		return Pattern.matches(REG_ARMYID, armyId);
	}
	/**
	 * 身份证 军官证时姓名的校验
	 * @param mobile
	 * @return
	 */
	public static boolean verifyArmyIdNname(String name){
		 return Pattern.matches(REG_EX_ID_ARMY_NAME, name);
		
	}
	/**
	 * 校验验证码
	 * 
	 * @param params
	 * @return
	 */
//	public static boolean verifyCode(String avalidateCode,String Mobile,String serviceKey) {
//		    log.debug(">>verifyCode验证码校验==开始"+avalidateCode);
//		   // 验证验证码是否超时，及输入的是否正确
//		     boolean result=true;
//			String validateCode=null;
//			try {
//				validateCode = redisManager.getByStringKey(serviceKey+"validateCode_"
//						+ Mobile);
//			} catch (JedisConnectionException e) {
//				e.printStackTrace();
//			}catch (Exception e){
//				throw e;
//			}
//			// 从缓存取验证码
//			log.info("===========缓存中的验证码=====" + validateCode);
//			if (validateCode == null) {
//				throw new RuntimeException("验证码失效");
//			} else {
//				String[] avalidateCodes = validateCode.split("_");
//				String userPhone = avalidateCodes[0];
//				String verifyCode = avalidateCodes[1];
//				log.debug("============telphone=====" + userPhone+"====传递的mobile"+Mobile);
//				avalidateCode=Md5.getMD5Mac(avalidateCode, "UTF-8");
//				if (!avalidateCode.equals(verifyCode) || !BusinessUtil.encryptMobile(Mobile).equals(userPhone)) {
//					result=false;
//			}
//		log.debug(">>verifyCode验证码校验==结束。结果是"+result);	
//		return result;
//	}
//	}
	/**
	 * 港澳台同胞证校验证
	 * @param mobile
	 * @return
	 */
	public static boolean verifyHMCard(String card){
		return Pattern.matches(REGEX_COMPATRIOTS_CARD, card);
	}
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input)) {
			return true;
		}
		return false;
	}
	/**
	 * 验证是否有特殊字符(只能为中文、字母、数字)
	 * @param input
	 * @return
	 */
	public static boolean verifySpecialInput(String input){
		return Pattern.matches(REG_EX_SPECIAL_INPUT, input);
	}
	/**
	 * 验证邮箱格式是否正确
	 * @param email
	 * @return
	 */
	public static boolean verifyEmail(String email){
		 boolean flag = false;
	        try{
	                Pattern regex = Pattern.compile(REG_EMAIL);
	                Matcher matcher = regex.matcher(email);
	                flag = matcher.matches();
	            }catch(Exception e){
	                flag = false;
	            }
	        return flag;
	}
	/**
	 * 验证输入的字符串中不能有特殊字符，并且长度小于n
	 * @param name
	 * @param n
	 * @return
	 */
	public static boolean  verifyInsureName(String name){
		
		return verifySpecialInput(name);
	}
	/**
	 * 验证字符串长度在某一个范围内
	 * @param name
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean  verifyInsureLength(String name,int min,int max){
		 return name.length()>=min && name.length()<=max;
	}

}
