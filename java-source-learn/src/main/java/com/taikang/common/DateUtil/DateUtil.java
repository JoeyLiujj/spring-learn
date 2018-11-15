package com.taikang.common.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式化util 2015-11-26 by jlf
 */
public class DateUtil {
	public static final String TO_DAY = "yyyy-MM-dd";
	public static final String TODAY = "yyyyMMdd";
	public static final String TOSEC = "yyyyMMdd HHmmss";
	public static final String CLASSICAL = "yyyy-MM-dd HH:mm:ss";
	public static final String TOSECCLOSER = "yyyyMMddHHmmss";
	private static SimpleDateFormat dateFormat;

	/**
	 * 把传入时间包装为第n天零时,返回
	 * 
	 * @param startDate
	 * @return
	 */
	public static Date packDate00(Date startDate, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DATE, n);
		return c.getTime();
	}
	/**
	 * 把传入时间包装为第n天23点59分59秒,返回
	 * 
	 * @param startDate
	 * @return
	 */
	public static Date packDate23(Date startDate, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		c.add(Calendar.DATE, n);
		return c.getTime();
	}

	/**
	 * 计算传入时间之后在传入天数之后的时间
	 * 
	 * @param startDate
	 * @param countDay
	 * @return
	 */
	public static Date countDate(Date startDate, int countDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		// 计算countDay天之后的时间
		cal.add(Calendar.DATE, countDay);
		Date endDate = cal.getTime();
		return endDate;
	}

	/**
	 * 计算传入时间和当前时间相差的天数
	 * 
	 * @author itw_wangzc
	 * @param startDate
	 * @param countDay
	 * @return
	 */
	public static Integer countDay(Date startDate, Date endDate) {
		if (null == startDate || null == endDate) {
			return null;
		}
		long intervalMilli = endDate.getTime() - startDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	/**
	 * 计算当前时间是否晚于开始时间
	 * 
	 * @author itw_wangzc
	 * @param startDate
	 * @param nowDate
	 * @return
	 */
	public static boolean isLate(Date startDate, Date nowDate) {
		long now = nowDate.getTime();
		long start = startDate.getTime();
		if (start > now) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 计算传入时间在传入月数之后的时间
	 * 
	 * @param startDate
	 * @param countMonth
	 * @return
	 */
	public static Date countMonth(Date startDate, int countMonth) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		// 计算countMonth个月之后的时间
		// 例：开始时间为2016-04-06 00:00:00，保一年，结束时间为2017-04-05 23:59:59
		cal.add(Calendar.MONTH, countMonth);
		cal.add(Calendar.SECOND, -1);
		Date endDate = cal.getTime();
		return endDate;
	}
	/**
	 * 计算传入时间在多少分钟之后
	 * @param startDate
	 * @param minutes
	 * @return
	 */
    public static Date countMinutes(Date startDate,int minutes){
    	Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.MINUTE, minutes);
		Date endDate = cal.getTime();
		return endDate;
    }
	/**
	 * 按照formatType格式化时间
	 */
	public static String fromatDate(Date date, String formatType) {
		return getFormat(formatType).format(date);

	}

	/**
	 * 按照formatType格式化字符串
	 */
	public static Date parseDate(String dateStr, String formatType) throws ParseException {
		return getFormat(formatType).parse(dateStr);

	}

	/**
	 * 按照输入的格式获取当前时间的字符串形式
	 */
	public static String getNowDateStr(String formatType) {
		return fromatDate(new Date(System.currentTimeMillis()), formatType);

	}

	/**
	 * 按照输入的格式获取当前时间
	 */
	public static Date getNowDate(String formatType) throws ParseException {
		String dateStr = fromatDate(new Date(System.currentTimeMillis()), formatType);
		return parseDate(dateStr, formatType);

	}

	/**
	 * 获取支付宝支付时的请求序列号 ����ϵͳ��ǰʱ��(��ȷ������),��Ϊһ��Ψһ�Ķ������
	 * 
	 * @return ��yyyyMMddHHmmssΪ��ʽ�ĵ�ǰϵͳʱ��
	 */
	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(TOSECCLOSER);
		return df.format(date);
	}

	/**
	 * 实例化SimpleDateFormat对象
	 */
	private static SimpleDateFormat getFormat(String formatType) {
		switch (formatType) {
		case TO_DAY: {
			dateFormat = new SimpleDateFormat(TO_DAY);
			break;
		}
		case TODAY: {
			dateFormat = new SimpleDateFormat(TODAY);
			break;
		}
		case TOSEC: {
			dateFormat = new SimpleDateFormat(TOSEC);
			break;
		}
		case CLASSICAL: {
			dateFormat = new SimpleDateFormat(CLASSICAL);
			break;
		}
		case TOSECCLOSER: {
			dateFormat = new SimpleDateFormat(TOSECCLOSER);
			break;
		}
		case "": {
			dateFormat = new SimpleDateFormat(CLASSICAL);
			break;
		}
		}
		return dateFormat;
	}
	
	/**
	 * 把传入时间包装为第n天23点59分59秒   000毫秒,返回
	 * 
	 * @param startDate
	 * @return
	 */
	public static Date packDate23000(Date startDate, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 000);
		c.add(Calendar.DATE, n);
		return c.getTime();
	}
	
	//根据出生日期与投保起始日期获取被保人的年龄
		public static int getAge(String birthday,String startDate)throws Exception{
			
			int iAge = 0;
			//String birthday="1992-09-10";
			SimpleDateFormat birthdayformat = null;
			if (birthday.length() == 10) {
				birthdayformat = new SimpleDateFormat("yyyy-MM-dd");
			} else if (birthday.length() == 8) {
				birthdayformat = new SimpleDateFormat("yyyyMMdd");
			}
			SimpleDateFormat startDateformat = null;
			if (startDate.length() == 10) {
				startDateformat = new SimpleDateFormat("yyyy-MM-dd");
			} else if (startDate.length() == 8) {
				startDateformat = new SimpleDateFormat("yyyyMMdd");
			} else if(startDate.length() == 19){
				startDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			} else if(startDate.length() == 14){
				startDateformat = new SimpleDateFormat("yyyyMMddHHmmss");
			}
			Calendar birthDate = Calendar.getInstance();
			birthDate.setTime((Date) birthdayformat.parse(birthday));
			Calendar now = Calendar.getInstance();
			now.setTime(startDateformat.parse(startDate));
			iAge = now.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
			birthDate.add(Calendar.YEAR, iAge);
			if ((now.getTime()).before(birthDate.getTime())) {
				iAge--;
			}
			return iAge;
			
		}

}
