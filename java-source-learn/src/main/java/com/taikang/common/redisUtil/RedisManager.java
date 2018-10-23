package com.taikang.common.redisUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taikang.common.redisUtil.shard.ShardJedisManager;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;


/**
 * redis工具类
 * 连接默认缓存时，使用com.taikang.common.redisUtil.RedisManager.RedisManager(String, String)构造方法
 * 连接指定
 */
public class RedisManager
{
	private static final Logger curr_logger  = LoggerFactory.getLogger(RedisManager.class);

    private static final int    DEFAULT_SINGLE_EXPIRE_TIME = 3;

    private static final int    DEFAULT_BATCH_EXPIRE_TIME  = 6;

	/**
	 * redis 缓存
	 * @author jty
	 * 2014-12-05
	 */
	
	//private static JedisPool pool;
	private ShardedJedisPool shardedPool;
	
	/**工程名*/
	private String project;
	/**业务名*/
	private String trade;
	
	private String nodeId;
	
	public RedisManager() {
		
	}
	/**
	 * 连接默认缓存
	 * @param project
	 * @param trade
	 */
//	public RedisManager(String project,String trade)
//	{
//		this.project = project;
//		this.trade = trade;
//		shardedPool = ShardJedisManager.getInstance().getShardedJedisPool("ShardRedisCache");
//	}
	
	/**
	 * 连接指定功能缓存
	 * @param project
	 * @param trade
	 * @param nodeId
	 */
	public RedisManager(String project,String trade,String nodeId){
		this.project = project;
		this.trade = trade;
		this.nodeId = nodeId;
		shardedPool = ShardJedisManager.getInstance().getShardedJedisPool(nodeId);
	}
	
	static
	{
		
		
//		try
//        {
//			//开发环境 10.137.46.7:6379
////			String redis_ip = DbFunc.getAppConfig("service", "redis", "redis_ip");
////			int redis_port = Integer.parseInt(DbFunc.getAppConfig("service", "redis", "redis_port"));
////			pool = new JedisPool(new JedisPoolConfig(),redis_ip,redis_port);
//			
//			//读取配置表，IP与端口要一一对应，顺序一致，使用“,”分割
////			String[] ips = DbFunc.getAppConfig("service", "redis", "redis_ip").split(",");
////			String[] ports = DbFunc.getAppConfig("service", "redis", "redis_port").split(",");
//			String[] ips = {"10.137.46.7"};
//			String[] ports = {"6379"};
//			
//			//定义JedisShardInfo的list；
//			List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
//			for(int i = 0;i<ips.length;i++){
//				JedisShardInfo info = new JedisShardInfo(ips[i], Integer.parseInt(ports[i]));
//				shards.add(info);
//			}
//			//创建哈希分片连接池
//			shardedPool = new ShardedJedisPool(new Config(), shards);
//        }
//        catch (Exception e)
//        {
//	        e.printStackTrace();
//        }
	}
	
	public Object getValue(String key)
	{
		Object ret = null;
		//Jedis jedis = pool.getResource(); 
		ShardedJedis jedis = shardedPool.getResource();
		try
        {
//			Map<byte[],byte[]> map = (Map<byte[],byte[]>)jedis.hgetAll(this.project);
//			
//			if(map != null)
//			{
//				byte[] object = map.get(this.trade);
//				if(object != null)
//				{
//					Map<String,Object> trademap = (HashMap<String,Object>)SerializeUtil.unserialize(object);
//					ret = trademap.get(key);
//				}
//			}
			
			//将工程名，业务名和KEY拼成在redis中真实储存的key
			String realKey = this.project+"_"+this.trade+"_"+key;
			//去redis中取回序列化后的对象
			byte[] obj = jedis.get (realKey.getBytes());
			
			//取回的对象反序列化
			if(obj!=null){
				ret = SerializeUtil.unserialize(obj);
			}
			
        }
        catch (Exception e)
        {
	        curr_logger.info("java/src/com/taikang/redis/RedisManager.java getValue " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return ret;
	}
	
	public void setValue(String key, Object value)
	{
		//Jedis jedis = pool.getResource();
		ShardedJedis jedis = shardedPool.getResource();
		try
        {
//			Map<byte[],byte[]> map = (Map<byte[],byte[]>)jedis.hgetAll(this.project);
//			Map<String,Object> trademap = null;
//			if(map == null)
//			{
//				map = new HashMap<byte[],byte[]>();
//				trademap = new HashMap<String, Object>();
//			}
//			else
//			{
//				byte[] object = map.get(this.trade);
//				if(object == null)
//				{
//					trademap = new HashMap<String, Object>();
//				}
//				else
//				{
//					trademap = (HashMap<String,Object>)SerializeUtil.unserialize(object);
//				}
//			}
//			
//			trademap.put(key, value);
//			map.put(this.trade, SerializeUtil.serialize(trademap));
//			jedis.hmset(this.project, map);
			
			
			//将工程名，业务名和KEY拼成在redis中真实储存的key
			String realKey = this.project+"_"+this.trade+"_"+key;
			//将要存储的对象序列化
			byte[] object = SerializeUtil.serialize(value);
			//把序列化后的对象存入redis
			jedis.set(realKey.getBytes(), object);
        }
		catch (Exception e)
        {
	        curr_logger.info("/com/taikang/redis/RedisManager.java setValue " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		
	}
	
	public void setValue(String key, Object value,int seconds)
	{
		long start = System.currentTimeMillis();
		ShardedJedis jedis = shardedPool.getResource();
		try
        {
			//将工程名，业务名和KEY拼成在redis中真实储存的key
			String realKey = this.project+"_"+this.trade+"_"+key;
			//将要存储的对象序列化
			byte[] object = SerializeUtil.serialize(value);
			//把序列化后的对象存入redis
			jedis.set(realKey.getBytes(), object);
			//jedis.sadd(realKey.getBytes(), object);
			jedis.expire(realKey.getBytes(), seconds);//seconds秒过期
        }
		catch (Exception e)
        {
	        System.out.println("/externalsource/java/src/com/taikang/redis/RedisManager.java setValue " + e);
	        e.printStackTrace();
        }
        finally
        {
        	//System.out.println("[redis] setValue " + (System.currentTimeMillis() - start));
        	shardedPool.returnResource(jedis); 
        }
		
	}
	
	public Object delValue(String key){
		Object ret = null;
		ShardedJedis jedis = shardedPool.getResource();
		String realKey = this.project+"_"+this.trade+"_"+key;
		try {
			ret = jedis.expire(realKey.getBytes(), 0);
		} catch (Exception e) {
	        curr_logger.info("/externalsource/java/src/com/taikang/redis/RedisManager.java setValue " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return ret;
	}
	
	public String[] getKeys(String key) throws Exception{
		String[] a = null ;
		ShardedJedis jedis = shardedPool.getResource();
		try {
			Collection<Jedis> js = (Collection<Jedis>) jedis.getAllShards();
		      for(Jedis j:js){
		    	  Set<String> keys = j.keys(""+this.project+"_"+this.trade+"_"+key);
		    	  a=keys.toArray(new String[keys.size()]);
		      }
		} catch (Exception e) {
			curr_logger.info("/com/taikang/redis/RedisManager.java getKeys() " + e);
	        e.printStackTrace();
	        throw e;
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return a;
	}
	
	public Set<String> getShortKeys(String key) throws Exception{
		HashSet<String> a = new HashSet<String>() ;
		ShardedJedis jedis = shardedPool.getResource();
		try {
			Collection<Jedis> js = (Collection<Jedis>) jedis.getAllShards();
		      for(Jedis j:js){
		    	  Set<String> keys = j.keys(""+this.project+"_"+this.trade+"_"+key);
		    	  for(String s:keys){
		    		  a.add(s.replaceFirst(""+this.project+"_"+this.trade+"_", ""));
		    	  }
		      }
		} catch (Exception e) {
			curr_logger.info("/com/taikang/redis/RedisManager.java getShortKeys() " + e);
	        e.printStackTrace();
	        throw e;
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return a;
	}
	
	public void clear() throws Exception{
		ShardedJedis jedis = shardedPool.getResource();
		try {
			Collection<Jedis> js = (Collection<Jedis>) jedis.getAllShards();
		      for(Jedis j:js){
		    	  Set<String> keys = j.keys(""+this.project+"_"+this.trade+"*");
		    	  String[] a = new String[1];
		    	  j.del(keys.toArray(a));
//		    	  j.flushDB();
//		    	  j.flushAll();
		      }
		} catch (Exception e) {
			curr_logger.info("/com/taikang/redis/RedisManager.java clear() " + e);
	        e.printStackTrace();
	        throw e;
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
	}
	
	public ShardedJedisPool getPool(){
		return shardedPool;
	}
	
	public  int getSize(){
		ShardedJedis jedis = null;
	    int result = 0;
	    boolean borrowOrOprSuccess = true;
	    try {
	      jedis   = shardedPool.getResource();
	      List<Jedis> js = (List<Jedis>) jedis.getAllShards();
	      for(Jedis j:js){
	    	  result+= Integer.valueOf(j.dbSize().toString());
	      }
	    } catch (JedisConnectionException e) {
	      borrowOrOprSuccess = false;
	      if (jedis != null)
	    	  shardedPool.returnBrokenResource(jedis);
	    } finally {
	      if (borrowOrOprSuccess)
	    	  shardedPool.returnResource(jedis);
	    }
	    return result;

	}
	
	/*
	 * 设置KEY的过期时间_以Byte[]形式的KEY
	 */
	public void setExpireByte(String key,int seconds){
		ShardedJedis jedis = shardedPool.getResource();
		String realKey = this.project+"_"+this.trade+"_"+key;
		try {
				jedis.expire(realKey.getBytes(), seconds);
		} catch (Exception e) {
	        curr_logger.info("/com/taikang/redis/RedisManager.java setExpireByte() " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
	}
	
	/*
	 * 设置KEY的过期时间_以String形式的KEY
	 */
	public void setExpireString(String key,int seconds){
		ShardedJedis jedis = shardedPool.getResource();
		String realKey = this.project+"_"+this.trade+"_"+key;
		try {
				jedis.expire(realKey,seconds);
		} catch (Exception e) {
	        curr_logger.info("/com/taikang/redis/RedisManager.java setExpireString() " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
	}
	
	public long incr(String key){
		long incNum = 0L;
		ShardedJedis jedis = shardedPool.getResource();
		String realKey = this.project+"_"+this.trade+"_"+key;
		try {
			incNum = jedis.incr(realKey);
		} catch (Exception e) {
	        curr_logger.info("/com/taikang/redis/RedisManager.java incr() " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return incNum;
	}
	/**
	 * 递增key，并且设置过期时间
	 * @param key
	 * @param seconds
	 * @return
	 */
	public long incr(String key,int seconds){
		long incNum = 0L;
		ShardedJedis jedis = shardedPool.getResource();
		String realKey = this.project+"_"+this.trade+"_"+key;
		try {
			incNum = jedis.incr(realKey);
			jedis.expire(realKey, seconds);
		} catch (Exception e) {
	        curr_logger.info("/com/taikang/redis/RedisManager.java incr() " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return incNum;
	}
	
	public String getByStringKey(String key){
		String val = "";
		ShardedJedis jedis = shardedPool.getResource();
		String realKey = this.project+"_"+this.trade+"_"+key;
		try {
			val = jedis.get(realKey);
		} catch (Exception e) {
	        curr_logger.info("/com/taikang/redis/RedisManager.java getByStringKey() " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return val;
	}
	
	public String setByStringKey(String key,String value){
		String val = "";
		ShardedJedis jedis = shardedPool.getResource();
		String realKey = this.project+"_"+this.trade+"_"+key;
		try {
			val = jedis.set(realKey,value);
		} catch (Exception e) {
	        curr_logger.info("/com/taikang/redis/RedisManager.java setByStringKey() " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return val;
	}
	
	public String setByStringKey(String key, String value, int expireTime) {
		String val = "";
		ShardedJedis jedis = shardedPool.getResource();
		String realKey = this.project + "_" + this.trade + "_" + key;
		try {
			val = jedis.set(realKey, value);
			if ("OK".equals(val)) {
				jedis.expire(realKey, expireTime);
			}
		} catch (Exception e) {
			curr_logger.info("/com/taikang/redis/RedisManager.java setByStringKey() " + e);
			e.printStackTrace();
		} finally {
			shardedPool.returnResource(jedis);
		}
		return val;
	}
	
	/**
	 * 获得所有hash 哈希表类型的值
	 * @param hName
	 * @return
	 */
	public Map<String, String> hGetAll(String hName){
		Map<String, String> val = null;
		ShardedJedis jedis = shardedPool.getResource();
		String realHName = this.project+"_"+this.trade+"_"+hName;
		try {
			val = jedis.hgetAll(realHName);
		} catch (Exception e) {
	        curr_logger.info("/com/taikang/redis/RedisManager.java hGetByStringKey() " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return val;
	}
	/**
	 * 获得hash 哈希表类型的值
	 * @param hName
	 * @param key
	 * @return
	 */
	public String hGetByStringKey(String hName,String key){
		String val = null;
		ShardedJedis jedis = shardedPool.getResource();
		String realHName = this.project+"_"+this.trade+"_"+hName;
		try {
			val = jedis.hget(realHName, key);
		} catch (Exception e) {
	        curr_logger.info("/com/taikang/redis/RedisManager.java hGetByStringKey() " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return val;
	}
	/**
	 *  设置hash 哈希表类型的值
	 * @param hName
	 * @param key
	 * @param value
	 * @return
	 */
	public Long hSetByStringKey(String hName,String key,String value){
		Long val = 0L;
		ShardedJedis jedis = shardedPool.getResource();
		String realHName = this.project+"_"+this.trade+"_"+hName;
		try {
			val = jedis.hset(realHName,key, value);
		} catch (Exception e) {
	        curr_logger.info("/com/taikang/redis/RedisManager.java hSetByStringKey() " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return val;
	}
	
	public void del(String key)
	{
		long start = System.currentTimeMillis();
		ShardedJedis jedis = shardedPool.getResource();
		try
        {
			//将工程名，业务名和KEY拼成在redis中真实储存的key
			String realKey = this.project+"_"+this.trade+"_"+key;
			//将要存储的对象序列化
			//把序列化后的对象存入redis
			jedis.del(realKey);
        }
		catch (Exception e)
        {
	        System.out.println("/externalsource/java/src/com/taikang/redis/RedisManager.java del " + e);
	        e.printStackTrace();
        }
        finally
        {
        	//System.out.println("[redis] setValue " + (System.currentTimeMillis() - start));
        	shardedPool.returnResource(jedis); 
        }
	}
	
	public void setStrInAllServer(String key , String value){
		String realKey = this.project+"_"+this.trade+"_"+key;
		ShardedJedisPool pool = getPool();
		ShardedJedis jedis = null;
		 try {
		      jedis   = pool.getResource();
		      Collection<Jedis> js = (Collection<Jedis>) jedis.getAllShards();
		      for(Jedis j:js){
		    	  j.set(realKey, value);
		      }
		    } catch (JedisConnectionException e) {
		      if (jedis != null)
		    	  pool.returnBrokenResource(jedis);
		    } finally {
		    	pool.returnResource(jedis);
		    }
	}
	
	
	public boolean exists(String key){
		boolean val = false;
		ShardedJedis jedis = shardedPool.getResource();
		String realKey = this.project+"_"+this.trade+"_"+key;
		try {
			val = jedis.exists(realKey);
		} catch (Exception e) {
	        curr_logger.info("/com/taikang/redis/RedisManager.java setByStringKey() " + e);
	        e.printStackTrace();
        }
        finally
        {
        	shardedPool.returnResource(jedis); 
        }
		return val;
	}
	

	    //尝试获取锁
	    public boolean tryLock(String lockName) {
	        return tryLock(lockName, 0L, null);
	    }

	    //尝试获取锁
	    public boolean tryLock(String lockName, long timeout, TimeUnit unit) {
	    	ShardedJedis jedis = null;
	        try {
	            jedis = getResource();
	            long nano = System.nanoTime();
	            do {
	                curr_logger.debug("try lock key: " + lockName);
	                Long i = jedis.setnx(lockName, lockName);
	                if (i == 1) {
	                    jedis.expire(lockName, DEFAULT_SINGLE_EXPIRE_TIME);
	                    curr_logger.debug("get lock, key: " + lockName + " , expire in "
	                            + DEFAULT_SINGLE_EXPIRE_TIME + " seconds.");
	                    return Boolean.TRUE;
	                }
	                else { // 存在锁
	                    if (curr_logger.isDebugEnabled()) {
	                        String desc = jedis.get(lockName);
	                        curr_logger.debug("key: " + lockName
	                                + " locked by another business：" + desc);
	                    }
	                }
	                if (timeout == 0) {
	                    break;
	                }
	                Thread.sleep(300);
	            }
	            while ((System.nanoTime() - nano) < unit.toNanos(timeout)) ;
	            return Boolean.FALSE;
	        }
	        catch (JedisConnectionException je) {
	            curr_logger.error(je.getMessage(), je);
	            returnBrokenResource(jedis);
	        }
	        catch (Exception e) {
	            curr_logger.error(e.getMessage(), e);
	        }
	        finally {
	            returnResource(jedis);
	        }
	        return Boolean.FALSE;
	    }

	    /**
	     * 如果锁空闲立即返回 获取失败 一直等待
	     * 
	     */
	    public void lock(String lockName) {
	    	ShardedJedis jedis = null;
	        try {
	            jedis = getResource();
	            do {
	                curr_logger.debug("lock key: " + lockName);
	                Long i = jedis.setnx(lockName, lockName);
	                if (i == 1) {
	                    jedis.expire(lockName, DEFAULT_SINGLE_EXPIRE_TIME);
	                    curr_logger.debug("get lock, key: " + lockName + " , expire in "
	                            + DEFAULT_SINGLE_EXPIRE_TIME + " seconds.");
	                    return;
	                }
	                else {
	                    if (curr_logger.isDebugEnabled()) {
	                        String desc = jedis.get(lockName);
	                        curr_logger.debug("key: " + lockName
	                                + " locked by another business：" + desc);
	                    }
	                }
	                Thread.sleep(300);
	            }
	            while (true);
	        }
	        catch (JedisConnectionException je) {
	            curr_logger.error(je.getMessage(), je);
	            returnBrokenResource(jedis);
	        }
	        catch (Exception e) {
	            curr_logger.error(e.getMessage(), e);
	        }
	        finally {
	            returnResource(jedis);
	        }
	    }

	    /**
	     * 释放锁
	     */
	    public void unLock(String lockName) {
	        List<String> list = new ArrayList<String>();
	        list.add(lockName);
	        unLock(list);
	    }

	    /**
	     * 批量获取锁 如果全部获取 立即返回true, 部分获取失败 返回false
	     */
	    public boolean tryLock(List<String> lockNameList) {
	        return tryLock(lockNameList, 0L, null);
	    }

	    /**
	     * 锁在给定的等待时间内空闲，则获取锁成功 返回true， 否则返回false
	     * @param lockName
	     * @param timeout
	     * @param unit
	     * @return
	     */
	    public boolean tryLock(List<String> lockNameList, long timeout,
	            TimeUnit unit) {
	    	ShardedJedis jedis = null;
	        try {
	            List<String> needLocking = new CopyOnWriteArrayList<String>();
	            List<String> locked = new CopyOnWriteArrayList<String>();
	            jedis = getResource();
	            long nano = System.nanoTime();
	            do {
	                // 构建pipeline，批量提交
	                ShardedJedisPipeline pipeline = jedis.pipelined();
	                for (String lockName : lockNameList) {
	                    needLocking.add(lockName);
	                    pipeline.setnx(lockName, lockName);
	                }
	                curr_logger.debug("try lock keys: " + needLocking);
	                // 提交redis执行计数
	                List<Object> results = pipeline.syncAndReturnAll();
	                for (int i = 0; i < results.size(); ++i) {
	                    Long result = (Long) results.get(i);
	                    String key = needLocking.get(i);
	                    if (result == 1) { // setnx成功，获得锁
	                        jedis.expire(key, DEFAULT_BATCH_EXPIRE_TIME);
	                        locked.add(key);
	                    }
	                }
	                needLocking.removeAll(locked); // 已锁定资源去除

	                if (CollectionUtils.isEmpty(needLocking)) {
	                    return true;
	                }
	                else {
	                    // 部分资源未能锁住
	                    curr_logger.debug("keys: " + needLocking
	                            + " locked by another business：");
	                }

	                if (timeout == 0) {
	                    break;
	                }
	                Thread.sleep(500);
	            }
	            while ((System.nanoTime() - nano) < unit.toNanos(timeout));

	            // 得不到锁，释放锁定的部分对象，并返回失败
	            if (!CollectionUtils.isEmpty(locked)) {
	            	for(String s:locked){
	            		delValue(s);
	            	}
	            }
	            return false;
	        }
	        catch (JedisConnectionException je) {
	            curr_logger.error(je.getMessage(), je);
	            returnBrokenResource(jedis);
	        }
	        catch (Exception e) {
	            curr_logger.error(e.getMessage(), e);
	        }
	        finally {
	            returnResource(jedis);
	        }
	        return true;
	    }

	    //释放锁
	    public void unLock(List<String> lockNameList) {
	        List<String> keys = new CopyOnWriteArrayList<String>();
	        for (String key : lockNameList) {
	            keys.add(key);
	        }
	        ShardedJedis jedis = null;
	        try {
	            for(String s:keys){
            		delValue(s);
            	}
	            curr_logger.debug("release lock, keys :" + keys);
	        }
	        catch (JedisConnectionException je) {
	            curr_logger.error(je.getMessage(), je);
	            returnBrokenResource(jedis);
	        }
	        catch (Exception e) {
	            curr_logger.error(e.getMessage(), e);
	        }
	        finally {
	            returnResource(jedis);
	        }
	    }

	    private ShardedJedis getResource() {
	        return  shardedPool.getResource();
	    }
	    
	    
	    private void returnBrokenResource(ShardedJedis jedis) {
	        if (jedis == null) {
	            return;
	        }
	        try {
	            // 容错
	        	shardedPool.returnBrokenResource(jedis);
	        }
	        catch (Exception e) {
	            curr_logger.error(e.getMessage(), e);
	        }
	    }

	    private void returnResource(ShardedJedis jedis) {
	        if (jedis == null) {
	            return;
	        }
	        try {
	        	shardedPool.returnResource(jedis);
	        }
	        catch (Exception e) {
	            curr_logger.error(e.getMessage(), e);
	        }
	    }
	    /**
		 * 设置set值
		 * @param hName
		 * @param key
		 * @return
		 */
		public  Long sAddByStringKey(String sName,int expireSeconds,String... values){
			Long val = 0L;
			ShardedJedis jedis = shardedPool.getResource();
			String realSName = this.project+"_"+this.trade+"_"+sName;
			try {
				val = jedis.sadd(realSName, values);
				if(expireSeconds!=-1){
					jedis.expire(realSName, expireSeconds);
				}
			} catch (Exception e) {
		        curr_logger.info("/com/taikang/redis/RedisManager.java sAddByStringKey() " + e);
		        e.printStackTrace();
	        }
	        finally
	        {
	        	shardedPool.returnResource(jedis); 
	        }
			return val;
		}
		/**
		 * 判断字符串是否在集合中
		 * @param sName
		 * @return
		 */
		public boolean sIsMemberByStringKey(String sName,String value){
			ShardedJedis jedis = shardedPool.getResource();
			boolean val = Boolean.FALSE;
			String realSName = this.project+"_"+this.trade+"_"+sName;
			try {
				val = jedis.sismember(realSName, value);
			} catch (Exception e) {
		        curr_logger.info("/com/taikang/redis/RedisManager.java sIsMemberByStringKey() " + e);
		        e.printStackTrace();
	        }
	        finally
	        {
	        	shardedPool.returnResource(jedis); 
	        }
			return val;
		}    
		/**
		 * 获得set所有值
		 * @param sName
		 * @return
		 */
		public Set<String> sMemberByStringKey(String sName){
			Set<String> val = Collections.EMPTY_SET;
			ShardedJedis jedis = shardedPool.getResource();
			String realSName = this.project+"_"+this.trade+"_"+sName;
			try {
				val = jedis.smembers(realSName);
			} catch (Exception e) {
		        curr_logger.info("/com/taikang/redis/RedisManager.java sMemberByStringKey() " + e);
		        e.printStackTrace();
	        }
	        finally
	        {
	        	shardedPool.returnResource(jedis); 
	        }
			return val;
		}    
}
