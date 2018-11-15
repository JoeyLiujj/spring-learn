package com.taikang.common.redisUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RedisCacheMock extends RedisManager{
	
	private Map<String,Object> map = new ConcurrentHashMap<String, Object>();
	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return map.get(key);
	}

	@Override
	public void setValue(String key, Object value) {
		// TODO Auto-generated method stub
		map.put(key, value);
	}

	@Override
	public Object delValue(String key) {
		// TODO Auto-generated method stub
		return super.delValue(key);
	}

	@Override
	public String[] getKeys(String key) throws Exception {
		// TODO Auto-generated method stub
		return super.getKeys(key);
	}

	@Override
	public Set<String> getShortKeys(String key) throws Exception {
		// TODO Auto-generated method stub
		return super.getShortKeys(key);
	}

	@Override
	public void clear() throws Exception {
		// TODO Auto-generated method stub
		super.clear();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return super.getSize();
	}

	@Override
	public void setExpireByte(String key, int seconds) {
		// TODO Auto-generated method stub
		super.setExpireByte(key, seconds);
	}

	@Override
	public void setExpireString(String key, int seconds) {
		// TODO Auto-generated method stub
		super.setExpireString(key, seconds);
	}

	@Override
	public long incr(String key) {
		Long value = (Long)map.get(key);
		if(value==null){
			value =1l;
		}
		return value;
	}

	@Override
	public String getByStringKey(String key) {
		// TODO Auto-generated method stub
		return super.getByStringKey(key);
	}

	@Override
	public String setByStringKey(String key, String value) {
		// TODO Auto-generated method stub
		return super.setByStringKey(key, value);
	}

	@Override
	public void setStrInAllServer(String key, String value) {
		// TODO Auto-generated method stub
		super.setStrInAllServer(key, value);
	}

	@Override
	public boolean exists(String key) {
		// TODO Auto-generated method stub
		return super.exists(key);
	}

	@Override
	public boolean tryLock(String lockName) {
		// TODO Auto-generated method stub
		return super.tryLock(lockName);
	}

	@Override
	public boolean tryLock(String lockName, long timeout, TimeUnit unit) {
		// TODO Auto-generated method stub
		return super.tryLock(lockName, timeout, unit);
	}

	@Override
	public void lock(String lockName) {
		// TODO Auto-generated method stub
		super.lock(lockName);
	}

	@Override
	public void unLock(String lockName) {
		// TODO Auto-generated method stub
		super.unLock(lockName);
	}

	@Override
	public boolean tryLock(List<String> lockNameList) {
		// TODO Auto-generated method stub
		return super.tryLock(lockNameList);
	}

	@Override
	public boolean tryLock(List<String> lockNameList, long timeout,
			TimeUnit unit) {
		// TODO Auto-generated method stub
		return super.tryLock(lockNameList, timeout, unit);
	}

	@Override
	public void unLock(List<String> lockNameList) {
		// TODO Auto-generated method stub
		super.unLock(lockNameList);
	}

	
	
}
