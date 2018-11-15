package com.taikang.common.redisUtil.shard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import com.taikang.common.propertyUtil.TaiKangPropertyPlaceholderConfigurer;
/**
 * redis切片集群管理
 * <P>File name : ShardJedisManager.java </P>
 * <P>Author : zouzhihua </P>
 * <P>Date : 2013-4-13 </P>
 */
public class ShardJedisManager {
	private Logger log = LoggerFactory.getLogger(ShardJedisManager.class);
	private static String CONFIG_FILE_NAME = "shardconfig.xml";
	private static class LazyHolder{
		private static final ShardJedisManager INSTANCE = new ShardJedisManager();
	}
	private Map<String,ShardedJedisPool> shardedJedisPoolMap = new HashMap<String, ShardedJedisPool>();

	private ShardJedisManager(){
		try {
			initShardJedis();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static ShardJedisManager getInstance(){
		return LazyHolder.INSTANCE;
	}
	/**
	 * 初始化切片集群
	 * ShardJedisManager.initShardJedis()<BR>
	 * <P>Author : zouzhihua </P>
	 * <P>Date : 2013-4-13 </P>
	 * @throws ConfigurationException
	 */
	private void initShardJedis() throws ConfigurationException{
		HashMap<String,ShardedJedisPool> redisConnectionMap = new HashMap<String,ShardedJedisPool>();

		File directory = new File("");//设定为当前文件夹
		XMLConfiguration routingConfig = null;
		try{
		    log.info(directory.getCanonicalPath());//获取标准的路径
		    log.info(directory.getAbsolutePath());//获取绝对路径
		}catch(IOException e){

		}
		routingConfig = new XMLConfiguration(getConfigFilePath());
		List<Object> serverNodesList = routingConfig.getList("servernode.node.id");
		for(int clusterIndex=0;clusterIndex<serverNodesList.size();clusterIndex++){
			String nodeId = (String)serverNodesList.get(clusterIndex);
			//最大活动连接
			int maxActive = routingConfig.getInt("servernode.node("+clusterIndex+").maxActive",20);
			//最大
			int maxIdle = routingConfig.getInt("servernode.node("+clusterIndex+").maxIdle",20);
			//最长等待时间
			int maxWait = routingConfig.getInt("servernode.node("+clusterIndex+").maxWait",20);
			String hosts = routingConfig.getString("servernode.node("+clusterIndex+").hosts");
			JedisPoolConfig config = new JedisPoolConfig();
//			config.setMaxActive(maxActive);
			config.setMaxIdle(maxIdle);
//			config.setMaxWait(maxWait);
			config.setTestOnBorrow(false);
			if (hosts == null) {
				throw new ConfigurationException("RedisPool init():hosts config error!");
			} else {
				List<JedisShardInfo> jedisShardInfos= new ArrayList<JedisShardInfo>();
				String[] hoststr = hosts.split("#");
				JedisShardInfo jsi;
				String[] hostarrt = null;
				for (String host : hoststr) {
					hostarrt = host.split(":");
					jsi = new JedisShardInfo(hostarrt[0].trim(), Integer.parseInt(hostarrt[1].trim()));
					jedisShardInfos.add(jsi);
				}
				ShardedJedisPool pool = new ShardedJedisPool(config, jedisShardInfos);
				redisConnectionMap.put(nodeId, pool);
			}
		}
		this.shardedJedisPoolMap = redisConnectionMap;
	}
	/**
	 * 获取某个节点的切片集群
	 * ShardJedisManager.getShardedJedisPool()<BR>
	 * <P>Author : zouzhihua </P>
	 * <P>Date : 2013-4-14 </P>
	 * @param nodeId
	 * @return 切片集群池
	 */
	public ShardedJedisPool getShardedJedisPool(String nodeId){
		return shardedJedisPoolMap.get(nodeId);
	}
	public static void main(String[] args){
		ShardJedisManager.getInstance();
	}

	private String getConfigFilePath(){
		String configPath="";
		Set<String> pathSet= TaiKangPropertyPlaceholderConfigurer.getResourceFolderLocations();
		for(String path:pathSet){
			String tempPath = null;
			if(isWindows()){
				tempPath =path+"\\"+CONFIG_FILE_NAME;
			}else{
				tempPath = path+"/"+CONFIG_FILE_NAME;
			}

			File file = new File(tempPath);
			if(file.exists()){
				configPath = tempPath;
				break;
			}
		}
		return configPath;
	}
	private boolean isWindows(){
		String os = System.getProperty("os.name");
		if(os.toLowerCase().startsWith("win")){
		  return true;
		}
		return false;
	}
}
