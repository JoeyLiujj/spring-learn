/**
 * 
 */
package com.taikang.common.processUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zhaopuqing
 * @created 2015年12月24日 上午11:32:03
 */
public class ValidateUtil {
	private static Logger log = LoggerFactory.getLogger(ValidateUtil.class);
	/**
	 * 正则表达式:验证移动电话
	 */
	private static final String REGEX_MOBILE = "^(\\+\\d+)?1[345678]\\d{9}$";
	/**
	 * 正则表达式:验证固定电话
	 */
	private static final String REGEX_PHONE = "^(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
	/**
	 * 正则表达式:验证15位身份证
	 */
	private static final String REGEX_ID_CARD_15 = "^[1-9]\\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\\d{3}$";
	/**
	 * 正则表达式:验证18位身份证
	 */
	private static final String REGEX_ID_CARD_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\\d{3}(\\d|x|X)$";
	/**
	 * 正则表达式:验证护照
	 */
	private static final String REGEX_PASSPORT = "^[a-zA-Z]{5,17}$";
	/**
	 * 正则表达式:验证军官证
	 */
	private static final String REGEX_MILITARY_ID = "^(([\\u4E00-\\u9FA5\\uF900-\\uFA2D]{3,5})?\\d{6,12})$";
	/**
	 * 正则表达式:验证港澳同胞证
	 */
	private static final String REGEX_COMPATRIOT_CARD = "^(H|M)?\\d{10}$";

	/**
	 * 校验手机号
	 * 
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobile(String mobile) {

		return Pattern.matches(REGEX_MOBILE, mobile);
	}
	
	/**
	 * 
	 * 描述：验证18位身份证号
	 * 作者：itw_lijm
	 * 时间：2016年1月14日
	 * @param mobile
	 * @return
	 */
	public static boolean isId18(String id) {

		return Pattern.matches(REGEX_ID_CARD_18, id);
	}

	/**
	 * 根据schema技术验证json格式是否正确
	 * 
	 * @param json
	 *            待校验的json内容
	 * @param jsonSchemaKey
	 *            schema的key
	 * @param jsonSchema
	 *            schem内容
	 * @return 如果验证失败返回错误信息，返回null时表示验证通过
	 * @throws Exception
	 */
	/*public static String validateProposalJson(String json, Activity activity)
			throws Exception {
		log.debug("start to validate json:{}", json);
		JsonSchemaUtil schemaUtil = SpringContextHolder
				.getBean(JsonSchemaUtil.class);
		return schemaUtil.validateJson(json,
				activity.getProposalJsonSchemalKey(),
				activity.getProposalJsonSchemal());
	}*/

	/**
	 * 
	 * 描述：验证某个险种适合的年龄段,包含开始和结束年龄 作者：itw_lijm 时间：2016年1月13日
	 * 
	 * @param brithday
	 *            格式 yyyy-MM-dd
	 * @param startAge
	 * @param endAge
	 * @return
	 * @throws ParseException 
	 */
	
	public static boolean validateApplicantAge(String brithday, int startAge,
			int endAge) throws ParseException {
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.YEAR,-startAge);
		long timeInMillis18 = instance.getTimeInMillis();
		SimpleDateFormat dataFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=dataFormat.parse(brithday);
		Long birthDay=date.getTime();
		Calendar instance2 = Calendar.getInstance();
		instance2.add(Calendar.YEAR,-endAge);
		long timeInMillis80 = instance2.getTimeInMillis();
		if(birthDay <=timeInMillis18 &&birthDay >= timeInMillis80){
			return true;
		}
		return false;
	}
}
