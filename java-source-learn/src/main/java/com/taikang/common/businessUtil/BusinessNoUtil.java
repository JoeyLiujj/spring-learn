package com.taikang.common.businessUtil;

import java.util.Random;

import com.taikang.common.redisUtil.RedisManager;

public class BusinessNoUtil {
	

	private static RedisManager redis = new RedisManager("tplatform","sequence","Sequence");
	/**
	 * 单号第一位固定为1
	 */
	private static long FIXED_PREFIX=1000000000000000000L;
	
	private static int SYSTEM_LOG_PREFIX = 1000000000;
	/**
	 * 报价单号redis序列key
	 */
	private static String QUOTATIONNO_SUFFIX_REDIS_KEY="quotationno_suffix";
	/**
	 * 投保单号redis序列key
	 */
	private static String POLICYNO_SUFFIX_REDIS_KEY="tplatform_sequence_proposalno_suffix";
	/**
	 * 客户号redis序列key
	 */
	private static String CUSTOMERID_SUFFIX_REDIS_KEY="customerid_suffix";
	
	private static String SYSTEM_LOG_KEY = "system_log";
	
	private static String SUFFIX = "_suffix";
	/**
	 * 默认过期时间
	 */
	private static int DEFAULT_EXPIRED_TIME = 300;
	/**
	 * 生成T平台专用报价单号
	 *  0		为固定值1。
	 *	1-3		客户号后三位。
	 *	4-16	当前毫秒值。
	 *	17-18	Redis中的序号，使用的key为“tplatform_sequence_quotationno_suffix”，使用incr方法进行取值，初始值为10。如果redis不能使用，这几位使用随机数。
	 *
	 * @param customerId 客户ID
	 * @return
	 */
	public static Long generateQuotationNo(long customerId){
		//客户号后三位
		long customerIdPart = getLastThreeDigitOfCustomerId(customerId);
		//当前时间
		long currentTimeMillisPart = System.currentTimeMillis();
		//redis序列值
		long redisSeqPart = getRedisSequencePart(QUOTATIONNO_SUFFIX_REDIS_KEY,2);
		//拼接各个部分
		long quotationNo = FIXED_PREFIX + customerIdPart*1000000000000000L + currentTimeMillisPart*100L + redisSeqPart;
		return quotationNo;
	}
	/**
	 * 获得客户号最后三位
	 * @param customerId
	 * @return
	 */
	private static long getLastThreeDigitOfCustomerId(long customerId){
		if(customerId>999L){
			return customerId%1000L;
		}else{
			throw new RuntimeException("客户号长度错误！");
		}
	}
	/**
	 * 获得redis序号
	 * @param redisKey 
	 * @param length 长度
	 * @return
	 */
	private static long getRedisSequencePart(String redisKey,int length){
		long seq = redis.incr(redisKey);
		if(seq==1){
			redis.setExpireString(redisKey, DEFAULT_EXPIRED_TIME);
		}
		//如果位数过长，截短
		long criteria = (long) Math.pow(10D, length);
		if(seq>criteria){
			seq = seq%criteria;
		}else if(seq==0L){
			//redis连接不上时，seq会等于0，这里使用随机数代替redis生成的值
			Random random = new Random();
			long randomLong = random.nextLong();
			seq = randomLong%criteria;
		}
		return seq;
	}
	/**
	 * 生成T平台专用客户号
	 * 0		为固定值1。
	 * 1-13		当前毫秒值。	
	 * 14-18	Redis中的序号，使用的key为“tplatform_sequence_customerid_suffix”，
				使用incr方法进行取值，初始值为10000。如果redis不能使用，这几位使用随机数。
	 * @return
	 */
	public static Long generateNewCustomerId(){
		//当前时间
		long currentTimeMillisPart = System.currentTimeMillis();
		//redis序列值
		long redisSeqPart = getRedisSequencePart(CUSTOMERID_SUFFIX_REDIS_KEY,5);
		long customerId = FIXED_PREFIX + currentTimeMillisPart*100000L + redisSeqPart;
		return customerId;
	}
	
	/**
	 * 生成T平台专用子机构号
	 * 0		为固定值1。
	 * 1-13		当前毫秒值。	
	 * 14-15	Redis中的序号，使用的key为“tplatform_sequence_customerid_suffix”，
				使用incr方法进行取值，初始值为10000。如果redis不能使用，这几位使用随机数。
	 * 16-19	父机构号 upperOrgId 最后三位
	 * @param upperOrgId
	 * @return
	 */
	public static Long generateLowerOrgId(long upperOrgId){
		//当前时间
		long currentTimeMillisPart = System.currentTimeMillis();
		//取得父机构后三位
		long upperOrgIdPart = getLastThreeDigitOfCustomerId(upperOrgId);
		//redis序列值
		long redisSeqPart = getRedisSequencePart(CUSTOMERID_SUFFIX_REDIS_KEY,2);
		long customerId = FIXED_PREFIX + currentTimeMillisPart*100000L + redisSeqPart*1000+upperOrgIdPart;
		return customerId;
	}
	/**
	 * XXX 生成系统日志单号
	 * @return
	 */
	public static int getNextSystemLogId(){
		int fixedPart = SYSTEM_LOG_PREFIX;
		int redisPart = (int) getRedisSequencePart(SYSTEM_LOG_KEY, 9);
		return fixedPart+redisPart;
	}
	
	/**
	 * 生成交易流水号，测试用
	 * @return
	 */
	public static String createTradeNo() {
		long current = System.currentTimeMillis();
		String random = new Random().nextInt(20000000) + "";
		while (true) {
			if (random.length() < 8) {
				random = "0" + random;
			} else {
				break;
			}
		}
		return current + random;
	}
	
	/**
	 * 生成投保单号
	 * @param customerId  客户ID
	 * @return
	 */
	public static Long generateProposaltionNo(long customerId){
		//客户号后三位
		long customerIdPart = getLastThreeDigitOfCustomerId(customerId);
		//当前时间
		long currentTimeMillisPart = System.currentTimeMillis();
		//redis序列值
		long redisSeqPart = getRedisSequencePart(POLICYNO_SUFFIX_REDIS_KEY,2);
		//拼接各个部分
		long quotationNo = FIXED_PREFIX + customerIdPart*1000000000000000L + currentTimeMillisPart*100L + redisSeqPart;
		return quotationNo;
	}
	
	/**
	 * 生成T平台业务号
	 *  0		为固定值1。
	 *	1-3		客户号后三位。
	 *	4-16	当前毫秒值。
	 *	17-18	Redis中的序号，使用的key为“tplatform_sequence_"+businessType+"_suffix”，使用incr方法进行取值，初始值为10。如果redis不能使用，这几位使用随机数。
	 *
	 * @param customerId 客户ID
	 * @param businessType 业务类型
	 * @return
	 */
	public static Long generateBusinessNo(long customerId,String businessType){
		//客户号后三位
		long customerIdPart = getLastThreeDigitOfCustomerId(customerId);
		//当前时间
		long currentTimeMillisPart = System.currentTimeMillis();
		//redis序列值
		long redisSeqPart = getRedisSequencePart(businessType+SUFFIX,2);
		//拼接各个部分
		long businessNo = FIXED_PREFIX + customerIdPart*1000000000000000L + currentTimeMillisPart*100L + redisSeqPart;
		return businessNo;
	}
	
	
	public static void main(String[] args) {
		for(int i=0;i<1000;i++){
			System.out.println(BusinessNoUtil.generateLowerOrgId(1061149526505607405L));
		}
	}
}
