package com.taikang.common.redisUtil;

/**
 * redis路由异常类
 * <P>File name : RedisRoutingException.java </P>
 * <P>Author : zouzhihua </P> 
 * <P>Date : 2013-1-29 </P>
 */
public class RedisException extends RuntimeException {
	/**
	 * 字段或域定义：<code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	public RedisException(){
		super();
	}
	
	public RedisException(String msg){
		super(msg);
	}
}

