package com.taikang.common.propertyUtil;

/**
 * Spring-PropertiesUtil工具类 -获取属性值
 * 
 */
public class PropertiesUtil {
	private static TaiKangPropertyPlaceholderConfigurer cp;

	/**
	 * 获取配置文件中的内容
	 * 
	 * @param keyName
	 * @return
	 */
	public static String parseStr(String keyName) {
		return cp.getContextProperty(keyName).toString();
	}

	public static String parseStr(String keyName, String defaultValue) {
		Object obj = cp.getContextProperty(keyName);
		if (obj != null) {
			return cp.getContextProperty(keyName).toString();
		} else {
			return defaultValue;
		}
	}

	/**
	 * 获取配置文件中的内容
	 * 
	 * @param keyName
	 * @return
	 */
	public static int parseInt(String keyName) {
		return Integer.parseInt(cp.getContextProperty(keyName).toString().trim());
	}

	/**
	 * 获取配置文件中的内容
	 * 
	 * @param keyName
	 * @return
	 */
	public static int parseInt(String keyName, int defaultValue) {
		Object obj = cp.getContextProperty(keyName);
		if (obj != null) {
			return Integer.parseInt(cp.getContextProperty(keyName).toString());
		} else {
			return defaultValue;
		}
	}

	/**
	 * 获取配置文件中的内容
	 * 
	 * @param keyName
	 * @return
	 */
	public static double parseDouble(String keyName) {
		return Double.parseDouble(cp.getContextProperty(keyName).toString());
	}

	public static TaiKangPropertyPlaceholderConfigurer getCp() {
		return cp;
	}

	public static void setCp(TaiKangPropertyPlaceholderConfigurer cp) {
		PropertiesUtil.cp = cp;
	}

}
