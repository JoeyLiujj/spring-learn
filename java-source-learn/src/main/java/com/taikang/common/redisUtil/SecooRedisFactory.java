package com.taikang.common.redisUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 集群应用管理
 * <P>File name : LafasoRedisFactory.java </P>
 * <P>Author : zouzhihua </P> 
 * <P>Date : 2013-1-29 </P>
 */
public class SecooRedisFactory {
	private static class LazyHolder{
		private static final SecooRedisFactory INSTANCE = new SecooRedisFactory();
	}
	private static Map<String, AppRedis> appSecooRedisMap = new HashMap<String, AppRedis>();
	private SecooRedisFactory(){
		try {
			initAppRedis();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	public static SecooRedisFactory getInstance() {
		return LazyHolder.INSTANCE;
	}
	/**
	 * 获取某个应用下的redis连接
	 * SecooRedisFactory.getSecooRedisByAppId()<BR>
	 * <P>Author : zouzhihua </P>  
	 * <P>Date : 2013-4-14 </P>
	 * @param appId
	 * @param token
	 * @return
	 */
	public ISecooRedis getSecooRedisByAppId(String appId,String token){
		AppRedis redisApp = appSecooRedisMap.get(appId);
		return redisApp.getSecooRedis(token);
	}
	/**
	 * 获取某个应用下的所有redis连接
	 * SecooRedisFactory.getAllSecooRedisByAppId()<BR>
	 * <P>Author : zouzhihua </P>  
	 * <P>Date : 2013-4-14 </P>
	 * @param appId
	 * @return
	 */
	public List<ISecooRedis> getAllSecooRedisByAppId(String appId){
		AppRedis redisApp = appSecooRedisMap.get(appId);
		return redisApp.getAllSecooRedis();
	}
	/**
	 * 初始化
	 * SecooRedisFactory.initAppRedis()<BR>
	 * <P>Author : zouzhihua </P>  
	 * <P>Date : 2013-4-14 </P>
	 * @throws ConfigurationException
	 */
	private  void initAppRedis() throws ConfigurationException{
		HashMap<String,AppRedis> redisConnectionMap = new HashMap<String,AppRedis>();
		XMLConfiguration appConfig = new XMLConfiguration("appredis.xml");
		List<Object> appList = appConfig.getList("app.id");
		for(int index=0;index<appList.size();index++){
			String appId = (String)appList.get(index);
			String dispatch = appConfig.getString("app("+index+").dispatch");
			String clusterStrategy = appConfig.getString("app("+index+").clusterStrategy");
			String clusterIds = appConfig.getString("app("+index+").clusterIds");
			List<String> clusterIdList = new ArrayList<String>();
			if(StringUtils.isNotEmpty(clusterIds)){
				String[] clusterIdstrs = clusterIds.split(":");
				for(String clusterId : clusterIdstrs){
					clusterIdList.add(clusterId);
				}
			}
			AppRedis app = new AppRedis(appId, dispatch, clusterStrategy, clusterIdList);
			redisConnectionMap.put(appId, app);
		}
		appSecooRedisMap = redisConnectionMap;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(SecooRedisFactory.class);
		// TODO Auto-generated method stub
		SecooRedisFactory f = new SecooRedisFactory();
		ISecooRedis redis = f.getSecooRedisByAppId("degrade", "11");
		redis.set("zou", "zou");
		log.info(redis.get("zou"));
	}

}