package com.taikang.common.processUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taikang.common.DateUtil.DateUtil;

/**
 * 通用工具类
 * 
 * @author itw_gaoxc
 * 
 */
public class TKCommonUtils {

	private static Logger log = LoggerFactory.getLogger(TKCommonUtils.class);

	/**
	 * 正则表达式：验证军官证
	 */
	private static final String REG_ARMYID = "^(([\\u4E00-\\u9FA5\\uF900-\\uFA2D]{3,5})\\d{6,12})$";

	/**
	 * 正则表达式：验证护照
	 */
	private static final String REG_PASSPORT = "^(G|E)\\d{8}$";

	/**
	 * 正则表达式：验证邮箱
	 */
	private static final String REG_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

	/**
	 * 正则表达式：验证邮箱(渠道提供)
	 */
	private static final String REG_EMAIL_OTHER = "^(\\S+)@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 正则表达式:验证移动电话
	 */
	private static final String REGEX_MOBILE = "^(\\+\\d+)?1[345678]\\d{9}$";
	/**
	 * 正则表达式:验证移动电话
	 */
	public static final String REGEX_MOBILE_Other = "^1[34578]\\d{9}$";
	/**
	 * 正则表达式:验证中文姓名
	 */
	public static final String REGEX_NAME = "^([A-z\u4E00-\u9FA5\uF900-\uFA2D]+[.|·]?)+([A-z\u4E00-\u9FA5\uF900-\uFA2D]+)$";
	/**
	 * 正则表达式:验证钱数
	 */
	public static final String PAY_MONEY = "(^[-+]?[1-9]\\d*(\\.\\d{1,2})?$)|(^[-+]?[0]{1}(\\.\\d{1,2})?$)";
	/**
	 * 正则表达式：验证包含特殊字符(只能为中文，字母和数字)
	 */
	private static final String REG_EX_SPECIAL_INPUT = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
	/**
	 * 中国公民身份证号码最小长度。
	 */
	public static final int CHINA_ID_MIN_LENGTH = 15;

	/**
	 * 中国公民身份证号码最大长度。
	 */
	public static final int CHINA_ID_MAX_LENGTH = 18;
	/**
	 * 省、直辖市代码表
	 */
	public static final String cityCode[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44",
			"45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91" };
	/**
	 * 每位加权因子
	 */
	public static final int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
	/**
	 * 第18位校检码
	 */
	public static final String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
	/**
	 * 最低年限
	 */
	public static final int MIN = 1930;

	public static Map<String, String> cityCodes = new HashMap<String, String>();

	/**
	 * 台湾身份首字母对应数字
	 */
	public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();

	/**
	 * 香港身份首字母对应数字
	 */
	public static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();

	static {
		cityCodes.put("11", "北京");
		cityCodes.put("12", "天津");
		cityCodes.put("13", "河北");
		cityCodes.put("14", "山西");
		cityCodes.put("15", "内蒙古");
		cityCodes.put("21", "辽宁");
		cityCodes.put("22", "吉林");
		cityCodes.put("23", "黑龙江");
		cityCodes.put("31", "上海");
		cityCodes.put("32", "江苏");
		cityCodes.put("33", "浙江");
		cityCodes.put("34", "安徽");
		cityCodes.put("35", "福建");
		cityCodes.put("36", "江西");
		cityCodes.put("37", "山东");
		cityCodes.put("41", "河南");
		cityCodes.put("42", "湖北");
		cityCodes.put("43", "湖南");
		cityCodes.put("44", "广东");
		cityCodes.put("45", "广西");
		cityCodes.put("46", "海南");
		cityCodes.put("50", "重庆");
		cityCodes.put("51", "四川");
		cityCodes.put("52", "贵州");
		cityCodes.put("53", "云南");
		cityCodes.put("54", "西藏");
		cityCodes.put("61", "陕西");
		cityCodes.put("62", "甘肃");
		cityCodes.put("63", "青海");
		cityCodes.put("64", "宁夏");
		cityCodes.put("65", "新疆");
		cityCodes.put("71", "台湾");
		cityCodes.put("81", "香港");
		cityCodes.put("82", "澳门");
		cityCodes.put("91", "国外");
		twFirstCode.put("A", 10);
		twFirstCode.put("B", 11);
		twFirstCode.put("C", 12);
		twFirstCode.put("D", 13);
		twFirstCode.put("E", 14);
		twFirstCode.put("F", 15);
		twFirstCode.put("G", 16);
		twFirstCode.put("H", 17);
		twFirstCode.put("J", 18);
		twFirstCode.put("K", 19);
		twFirstCode.put("L", 20);
		twFirstCode.put("M", 21);
		twFirstCode.put("N", 22);
		twFirstCode.put("P", 23);
		twFirstCode.put("Q", 24);
		twFirstCode.put("R", 25);
		twFirstCode.put("S", 26);
		twFirstCode.put("T", 27);
		twFirstCode.put("U", 28);
		twFirstCode.put("V", 29);
		twFirstCode.put("X", 30);
		twFirstCode.put("Y", 31);
		twFirstCode.put("W", 32);
		twFirstCode.put("Z", 33);
		twFirstCode.put("I", 34);
		twFirstCode.put("O", 35);
		hkFirstCode.put("A", 1);
		hkFirstCode.put("B", 2);
		hkFirstCode.put("C", 3);
		hkFirstCode.put("R", 18);
		hkFirstCode.put("U", 21);
		hkFirstCode.put("Z", 26);
		hkFirstCode.put("X", 24);
		hkFirstCode.put("W", 23);
		hkFirstCode.put("O", 15);
		hkFirstCode.put("N", 14);
	}

	/**
	 * 护照验证
	 * @param passport
	 * @return
	 */
	public static boolean verifyPassport(String passport) {
		return Pattern.matches(REG_PASSPORT, passport);
	}

	/**
	 * 将15位身份证号码转换为18位
	 * @param idCard
	 * @return
	 */
	public static String conver15CardTo18(String idCard) {
		String idCard18 = "";
		if (idCard.length() != CHINA_ID_MIN_LENGTH) {
			return null;
		}
		if (isNum(idCard)) {
			// 获取出生年月日
			String birthday = idCard.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null)
				cal.setTime(birthDate);
			// 获取出生年(完全表现形式,如：2010)
			String sYear = String.valueOf(cal.get(Calendar.YEAR));
			idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
			// 转换字符数组
			char[] cArr = idCard18.toCharArray();
			if (cArr != null) {
				int[] iCard = converCharToInt(cArr);
				int iSum17 = getPowerSum(iCard);
				// 获取校验位
				String sVal = getCheckCode18(iSum17);
				if (sVal.length() > 0) {
					idCard18 += sVal;
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
		return idCard18;
	}

	/**
	 * 验证身份证是否合法
	 * @param idCard
	 * @return
	 */
	public static boolean validateCard(String idCard) {
		String card = idCard.trim();
		if (validateIdCard18(card)) {
			return true;
		}
		if (validateIdCard15(card)) {
			return true;
		}
		String[] cardval = validateIdCard10(card);
		if (cardval != null) {
			if (cardval[2].equals("true")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证18位身份编码是否合法
	 * @param idCard
	 * @return
	 */
	public static boolean validateIdCard18(String idCard) {
		boolean bTrue = false;
		if (idCard.length() == CHINA_ID_MAX_LENGTH) {
			// 前17位
			String code17 = idCard.substring(0, 17);
			// 第18位
			String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
			if (isNum(code17)) {
				char[] cArr = code17.toCharArray();
				if (cArr != null) {
					int[] iCard = converCharToInt(cArr);
					int iSum17 = getPowerSum(iCard);
					// 获取校验位
					String val = getCheckCode18(iSum17);
					if (val.length() > 0) {
						if (val.equalsIgnoreCase(code18)) {
							bTrue = true;
						}
					}
				}
			}
		}
		return bTrue;
	}

	/**
	 * 验证15位身份编码是否合法
	 * @param idCard
	 * @return
	 */
	public static boolean validateIdCard15(String idCard) {
		if (idCard.length() != CHINA_ID_MIN_LENGTH) {
			return false;
		}
		if (isNum(idCard)) {
			String proCode = idCard.substring(0, 2);
			if (cityCodes.get(proCode) == null) {
				return false;
			}
			String birthCode = idCard.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null)
				cal.setTime(birthDate);
			if (!valiDate(cal.get(Calendar.YEAR), Integer.valueOf(birthCode.substring(2, 4)), Integer.valueOf(birthCode.substring(4, 6)))) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 验证10位身份编码是否合法
	 * @param idCard 身份编码
	 * @return 身份证信息数组
	 * [0] - 台湾、澳门、香港 
	 * [1] - 性别(男M,女F,未知N) 
	 * [2] - 是否合法(合法true,不合法false)
	 * 若不是身份证件号码则返回null
	 */
	public static String[] validateIdCard10(String idCard) {
		String[] info = new String[3];
		String card = idCard.replaceAll("[\\(|\\)]", "");
		if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
			return null;
		}
		if (idCard.matches("^[a-zA-Z][0-9]{9}$")) { // 台湾
			info[0] = "台湾";
			log.debug("11111");
			String char2 = idCard.substring(1, 2);
			if (char2.equals("1")) {
				info[1] = "M";
				log.debug("MMMMMMM");
			} else if (char2.equals("2")) {
				info[1] = "F";
				log.debug("FFFFFFF");
			} else {
				info[1] = "N";
				info[2] = "false";
				log.debug("NNNN");
				return info;
			}
			info[2] = validateTWCard(idCard) ? "true" : "false";
		} else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) { // 澳门
			info[0] = "澳门";
			info[1] = "N";
			// TODO
		} else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) { // 香港
			info[0] = "香港";
			info[1] = "N";
			info[2] = validateHKCard(idCard) ? "true" : "false";
		} else {
			return null;
		}
		return info;
	}

	/**
	 *  验证台湾身份证号码
	 * @param idCard
	 * @return
	 */
	public static boolean validateTWCard(String idCard) {
		String start = idCard.substring(0, 1);
		String mid = idCard.substring(1, 9);
		String end = idCard.substring(9, 10);
		Integer iStart = twFirstCode.get(start);
		Integer sum = iStart / 10 + (iStart % 10) * 9;
		char[] chars = mid.toCharArray();
		Integer iflag = 8;
		for (char c : chars) {
			sum = sum + Integer.valueOf(c + "") * iflag;
			iflag--;
		}
		return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
	}

	/**
	 * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查)
	 * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35
	 * 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
	 * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
	 * @param idCard 身份证号码
	 * @return 验证码是否符合
	 */
	public static boolean validateHKCard(String idCard) {
		String card = idCard.replaceAll("[\\(|\\)]", "");
		Integer sum = 0;
		if (card.length() == 9) {
			sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9
					+ (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
			card = card.substring(1, 9);
		} else {
			sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
		}
		String mid = card.substring(1, 7);
		String end = card.substring(7, 8);
		char[] chars = mid.toCharArray();
		Integer iflag = 7;
		for (char c : chars) {
			sum = sum + Integer.valueOf(c + "") * iflag;
			iflag--;
		}
		if (end.toUpperCase().equals("A")) {
			sum = sum + 10;
		} else {
			sum = sum + Integer.valueOf(end);
		}
		return (sum % 11 == 0) ? true : false;
	}

	/**
	 * 将字符数组转换成数字数组
	 * @param ca 字符数组
	 * @return 数字数组
	 */
	public static int[] converCharToInt(char[] ca) {
		int len = ca.length;
		int[] iArr = new int[len];
		try {
			for (int i = 0; i < len; i++) {
				iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return iArr;
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 * @param iArr
	 * @return 身份证编码。
	 */
	public static int getPowerSum(int[] iArr) {
		int iSum = 0;
		if (power.length == iArr.length) {
			for (int i = 0; i < iArr.length; i++) {
				for (int j = 0; j < power.length; j++) {
					if (i == j) {
						iSum = iSum + iArr[i] * power[j];
					}
				}
			}
		}
		return iSum;
	}

	/**
	 * 将power和值与11取模获得余数进行校验码判断
	 * @param iSum
	 * @return 校验位
	 */
	public static String getCheckCode18(int iSum) {
		String sCode = "";
		switch (iSum % 11) {
		case 10:
			sCode = "2";
			break;
		case 9:
			sCode = "3";
			break;
		case 8:
			sCode = "4";
			break;
		case 7:
			sCode = "5";
			break;
		case 6:
			sCode = "6";
			break;
		case 5:
			sCode = "7";
			break;
		case 4:
			sCode = "8";
			break;
		case 3:
			sCode = "9";
			break;
		case 2:
			sCode = "x";
			break;
		case 1:
			sCode = "0";
			break;
		case 0:
			sCode = "1";
			break;
		}
		return sCode;
	}

	/**
	 * 根据身份编号获取年龄
	 * @param idCard 身份编号
	 * @return 年龄
	 */
	public static int getAgeByIdCard(String idCard) {
		int iAge = 0;
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String date = idCard.substring(6, 14);
		iAge = getAgeByBirth(date);
		return iAge;
	}

	/**
	 * 出生日期获取年龄
	 * @param birthday
	 * @return
	 */
	public static int getAgeByBirth(String birthday) {
		int iAge = 0;
		SimpleDateFormat format = null;
		if (birthday.length() == 10) {
			format = new SimpleDateFormat("yyyy-MM-dd");
		} else if (birthday.length() == 8) {
			format = new SimpleDateFormat("yyyyMMdd");
		}
		try {
			Calendar birthDate = Calendar.getInstance();
			birthDate.setTime((Date) format.parse(birthday));
			Calendar now = Calendar.getInstance();
			iAge = now.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
			birthDate.add(Calendar.YEAR, iAge);
			if ((now.getTime()).before(birthDate.getTime())) {
				iAge--;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return iAge;
	}

	/**
	 * 根据身份编号获取生日
	 * @param idCard 身份编号
	 * @return 生日(yyyyMMdd)
	 */
	public static String getBirthByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return idCard.substring(6, 14);
	}

	/**
	 * 根据身份编号获取生日年
	 * @param idCard 身份编号
	 * @return 生日(yyyy)
	 */
	public static Short getYearByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return Short.valueOf(idCard.substring(6, 10));
	}

	/**
	 * 根据身份编号获取生日月
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(MM)
	 */
	public static Short getMonthByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return Short.valueOf(idCard.substring(10, 12));
	}

	/**
	 * 根据身份编号获取生日天
	 * @param idCard 身份编号
	 * @return 生日(dd)
	 */
	public static Short getDateByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return Short.valueOf(idCard.substring(12, 14));
	}

	/**
	 * 根据身份编号获取性别
	 * @param idCard 身份编号
	 * @return 性别(M-男，F-女，U-未知)
	 */
	public static String getGenderByIdCard(String idCard) {
		String sGender = "U";
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String sCardNum = idCard.substring(16, 17);
		if (Integer.parseInt(sCardNum) % 2 != 0) {
			sGender = "M";
		} else {
			sGender = "F";
		}
		return sGender;
	}

	/**
	 * 根据身份编号获取性别
	 * @param idCard 身份编号
	 * @return 性别(1-男，2-女,0-未知)
	 */
	public static String getTKGenderByIdCard(String idCard) {
		String sGender = "0";
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String sCardNum = idCard.substring(16, 17);
		if (Integer.parseInt(sCardNum) % 2 != 0) {
			sGender = "1";
		} else {
			sGender = "2";
		}
		return sGender;
	}

	/**
	 * 根据身份编号获取性别
	 * @param idCard 身份编号
	 * @return 性别(1-男，2-女，3-未知)
	 */
	public static String getWechatGenderByIdCard(String idCard) {
		String sGender = "3";
		try {
			if (idCard.length() == CHINA_ID_MIN_LENGTH) {
				idCard = conver15CardTo18(idCard);
			}
			String sCardNum = idCard.substring(16, 17);
			if (Integer.parseInt(sCardNum) % 2 != 0) {
				sGender = "1";
			} else {
				sGender = "2";
			}
		} catch (Exception e) {
			log.error("根据身份编号获取性别异常", e);
		}
		return sGender;
	}

	/**
	 * 根据身份编号获取户籍省份
	 * @param idCard 身份编码
	 * @return 省级编码。
	 */
	public static String getProvinceByIdCard(String idCard) {
		int len = idCard.length();
		String sProvince = null;
		String sProvinNum = "";
		if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
			sProvinNum = idCard.substring(0, 2);
		}
		sProvince = cityCodes.get(sProvinNum);
		return sProvince;
	}

	/**
	 * 数字验证
	 * @param val
	 * @return 提取的数字。
	 */
	public static boolean isNum(String val) {
		return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
	}

	/**
	 * 验证小于当前日期 是否有效
	 * @param iYear 待验证日期(年)
	 * @param iMonth 待验证日期(月 1-12)
	 * @param iDate 待验证日期(日)
	 * @return 是否有效
	 */
	public static boolean valiDate(int iYear, int iMonth, int iDate) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int datePerMonth;
		if (iYear < MIN || iYear >= year) {
			return false;
		}
		if (iMonth < 1 || iMonth > 12) {
			return false;
		}
		switch (iMonth) {
		case 4:
		case 6:
		case 9:
		case 11:
			datePerMonth = 30;
			break;
		case 2:
			boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0)) && (iYear > MIN && iYear < year);
			datePerMonth = dm ? 29 : 28;
			break;
		default:
			datePerMonth = 31;
		}
		return (iDate >= 1) && (iDate <= datePerMonth);
	}

	/**
	 * 获取出生日期到投保次日的年龄
	 * @param idCard
	 * @return
	 */
	public static int getAgeByIdCardCopy(String idCard) {
		int iAge = 0;
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String date = idCard.substring(6, 14);
		iAge = getAgeByBirthCopy(date);
		return iAge;
	}

	/**
	 * 投保次日年龄
	 * @param birthday
	 * @return
	 */
	public static int getAgeByBirthCopy(String birthday) {
		int iAge = 0;
		SimpleDateFormat format = null;

		if (birthday.length() == 10) {
			format = new SimpleDateFormat("yyyy-MM-dd");
		} else if (birthday.length() == 8) {
			format = new SimpleDateFormat("yyyyMMdd");
		}
		try {
			Calendar birthDate = Calendar.getInstance();
			birthDate.setTime((Date) format.parse(birthday));
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DATE, 1);
			Date time = now.getTime();
			iAge = now.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
			birthDate.add(Calendar.YEAR, iAge);
			if (time.before(birthDate.getTime())) {
				iAge--;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iAge;
	}

	/**
	 * 军官证校验
	 * @param armyId
	 * @return
	 */
	public static boolean verifyArmyId(String armyId) {
		return Pattern.matches(REG_ARMYID, armyId);
	}

	/**
	 * 验证邮箱格式是否正确
	 * @param email
	 * @return
	 */
	public static boolean verifyEmail(String email) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile(REG_EMAIL);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证邮箱格式是否正确(渠道提供)
	 * @param email
	 * @return
	 */
	public static boolean verifyEmailOther(String email) {
		boolean flag = false;
		Pattern pattern = Pattern.compile(REG_EMAIL_OTHER);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches() && email.length() <= 50) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 校验手机号
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

	/**
	 * 校验是否是默认手机号(默认11111111111)
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isDefaultMobile(String mobile) {
		if ("11111111111".equals(mobile)) {
			return true;
		} else {
			return Pattern.matches(REGEX_MOBILE, mobile);
		}

	}

	/**
	 * 校验是否是默认手机号(渠道提供)
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isDefaultMobileOther(String mobile) {
		if ("11111111111".equals(mobile)) {
			return true;
		} else {
			boolean flag = false;
			Pattern pattern = Pattern.compile(REGEX_MOBILE_Other);
			Matcher matcher = pattern.matcher(mobile);
			if (matcher.matches() && !mobile.endsWith("123456789")) {
				flag = true;
			}
			return flag;
		}
	}

	/**
	 * 姓名校验,长度必须大于等于min个字符,小于等于max个字符,无特殊字符
	 * @param name
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean checkName(String name, Integer min, Integer max) {
		boolean flag = false;
		boolean flag2 = false;
		if (name.length() >= min || name.length() <= max) {
			flag = true;
		}
		if (Pattern.matches(REGEX_NAME, name)) {
			flag2 = true;
		}
		return flag && flag2;
	}

	/**
	 * 比较两个double是否相等
	 * @param proposalPremium
	 * @param policyPremium
	 * @return
	 */
	public static boolean premiumCheck(double proposalPremium, double policyPremium) {
		DecimalFormat df = new DecimalFormat("#.00");
		String p1 = df.format(proposalPremium);
		String p2 = df.format(policyPremium);
		return p1.equals(p2);
	}

	/**
	 * 验证是否有特殊字符(只能为中文、字母、数字)
	 * @param input
	 * @return
	 */
	public static boolean verifySpecialInput(String input) {
		return Pattern.matches(REG_EX_SPECIAL_INPUT, input);
	}

	/**
	 * 验证输入的字符串中不能有特殊字符，并且长度小于n
	 * @param name
	 * @param n
	 * @return
	 */
	public static boolean verifyInsureName(String name) {
		return verifySpecialInput(name);
	}

	/**
	 * 验证字符串长度在某一个范围内
	 * @param name
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean verifyInsureLength(String name, int min, int max) {
		return name.length() >= min && name.length() <= max;
	}

	/**
	 * 判断字符串是否为空
	 * @param param
	 * @return
	 */
	public static boolean isEmpty(String param) {
		if (null == param || "".equals(param.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 获取身份证中的生日和性别
	 * @param identifyNumber
	 * @return
	 */
	public static Map<String, Object> getBrithAndSex(String identifyNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date brith = null;
		String sex = "";
		String birthDay = TKCommonUtils.getBirthByIdCard(identifyNumber);
		sex = TKCommonUtils.getWechatGenderByIdCard(identifyNumber);
		try {
			brith = DateUtil.parseDate(birthDay, DateUtil.TODAY);
		} catch (ParseException e1) {
			e1.printStackTrace();
			log.debug("解析出生年月出现异常");
		}
		map.put("brithday", brith);
		map.put("sex", sex);
		return map;
	}

	/**
	 * 比较两个double类型的数据是否相等(保留小数点后两位)
	 * @param staticData
	 * @param dynamicsData
	 * @return
	 */
	public static int compareExpenseTwo(Double staticData, Double dynamicsData) {
		BigDecimal proposalData = new BigDecimal(dynamicsData).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal configData = new BigDecimal(staticData).setScale(2, BigDecimal.ROUND_HALF_UP);
		int result = proposalData.compareTo(configData);
		if (result != 0) {
			result = 1;
		}
		return result;
	}

	/**
	 * 比较两个double类型的数据是否相等(保留小数点后六位)
	 * @param staticData
	 * @param dynamicsData
	 * @return
	 */
	public static int compareExpenseSix(Double staticData, Double dynamicsData) {
		BigDecimal proposalData = new BigDecimal(dynamicsData).setScale(6, BigDecimal.ROUND_HALF_UP);
		BigDecimal configData = new BigDecimal(staticData).setScale(6, BigDecimal.ROUND_HALF_UP);
		int result = proposalData.compareTo(configData);
		if (result != 0) {
			result = 1;
		}
		return result;
	}



	

	
	
	

	/**
	 * 转换投被保人关系 意健险转非意健险
	 * @param proposal
	 */
	public static String convertRelated(String relatedperson) {
		switch (relatedperson) {
		case "01":// 01 本人

			return "0";// 0 本人
		case "10":// 10 配偶

			return "1";// 1 配偶
		case "11":// 11 丈夫

			return "1";// 1 配偶
		case "12":// 12 妻子

			return "1";// 1 配偶
		case "20":// 20 儿子

			return "2";// 2 子女1
		case "30":// 30 女儿

			return "3";// 3 子女2
		case "40":// 40 儿女

			return "4";// 4 子女3
		case "50":// 50 父母

			return "13";// 13 父母
		case "51":// 51 父亲

			return "13";// 13 父母
		case "52":// 52 母亲

			return "13";// 13 父母
		case "53":// 53 继父

			return "13";// 13 父母
		case "54":// 54 继母

			return "13";// 13 父母
		case "55":// 55 叔伯

			return "12";// 12 亲属
		case "56":// 56 阿姨

			return "12";// 12 亲属
		case "57":// 57 兄弟

			return "12";// 12 亲属
		case "58":// 58 姐妹

			return "12";// 12 亲属
		case "59":// 59 外公

			return "12";// 12 亲属
		case "60":// 60 亲属

			return "12";// 12 亲属
		case "61":// 61 兄弟

			return "12";// 12 亲属
		case "62":// 62 奶奶

			return "12";// 12 亲属
		case "67":// 67 姐妹

			return "12";// 12 亲属
		case "80":// 80 单位

			return "14";// 14 其他
		case "81":// 81 雇员

			return "14";// 14 其他
		case "82":// 82 雇主

			return "14";// 14 其他
		case "99":// 99 其他

			return "14";// 14 其他
		default:// 没有改关系代码返回-1

			return "-1";
		}
	}

	/**
	 * 校验钱数
	 * @param value
	 * @return
	 */
	public static boolean checkMoney(String value) {
		Pattern pattern = Pattern.compile(PAY_MONEY);
		Matcher matcher = pattern.matcher(value);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

}
