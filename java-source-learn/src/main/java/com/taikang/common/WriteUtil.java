package com.taikang.common;

import java.util.Random;

import com.taikang.common.StringUtil.StringUtils;
import com.taikang.common.propertyUtil.PropertiesUtil;

public class WriteUtil {

	/**
	 * 获取电子保单写入磁盘的服务器
	 * 
	 * @param
	 * @return
	 */
	public static String getWriteIp() {

		String path = "";

		// 从配置文件中获取服务器的个数
		String count = PropertiesUtil.parseStr("number");

		// 随机出要添加的服务器
		Random rd = new Random();
		if (StringUtils.isNotBlank(count)) {
			int n = rd.nextInt(Integer.parseInt(count));
			path = PropertiesUtil.parseStr("url" + n);
		}

		return path;
	}

	/**
	 * 获取电子保单写入磁盘的随机路径
	 * 
	 * @param policyNo
	 * @return
	 */
	public static String getWritePath(String policyNo) {

		String path = PropertiesUtil.parseStr("servicerpath", "/data/Epolicy1/");

		// 循环三次 取得三级目录
		if (policyNo != null && !"".equals(policyNo)) {
			for (int i = 1; i < 3; i++) {
				String num = policyNo.substring(policyNo.length() - (i * 2), policyNo.length() - (i - 1) * 2);
				path += num + "/";
			}
		}

		return path;
	}
	/*public static void main(String[] args) {
		String a="051613213546136545641";
		String writePath = getWritePath(a);
		System.out.println(writePath);
	}*/

}
