package com.taikang.common;

import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.taikang.common.redisUtil.RedisManager;
import com.taikang.common.safeUtil.Md5;
import com.taikang.constants.SessionKey;

public class SessionManager {

	private static Logger log = LoggerFactory.getLogger(SessionManager.class);
	/** 缓存工具类 */
	private static RedisManager rm = new RedisManager("tkmobile", "session","User");

	private static int DEFAULT_EXPIRATION=7200;
	
	/**
	 * 缓存会话属性
	 * 
	 * @param request 客户端请求
	 * @param attributeName 缓存属性的名称
	 * @param 缓存属性，必须实现Serializable接口
	 */
	public static void setAttribute(HttpServletRequest request, String attributeName, Object attribute) {
		setAttribute(request, attributeName, attribute, 1800);
	}

	/**
	 * 缓存会话属性
	 * 
	 * @param request 客户端请求
	 * @param attributeName 缓存属性的名称
	 * @param 缓存属性，必须实现Serializable接口
	 */
	public static void setAttribute(HttpServletRequest request, String attributeName, Object attribute, int s) {
		String tkmssid = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("tkmssid".equals(c.getName())) {
					tkmssid = c.getValue();
				}
			}
		}

		if (tkmssid == null || "-1".equals(tkmssid)) {
			tkmssid = "" + request.getAttribute("tkmssid");
		}
		if (attribute == null)
			return;
		else if (attribute instanceof Serializable) {
			try {
				rm.setValue(tkmssid + attributeName, attribute, s);
			} catch (RuntimeException e) {
				// 某些对象实现了序列化，但其内部元素没有实现，也会报错，因此将其转为json
				setJson(attributeName, attribute, s, tkmssid);
			}
		} else {
			setJson(attributeName, attribute, s, tkmssid);
		}

	}

	/**
	 * @param attributeName
	 * @param attribute
	 * @param s
	 * @param tkmssid
	 */
	public static void setJson(String attributeName, Object attribute, int s, String tkmssid) {
		System.out.println("set not Serializable!");
		ObjectWrapper objWrapper = new ObjectWrapper();
		String data = JSON.toJSONString(attribute);
		objWrapper.setData(data);
		objWrapper.setClassName(attribute.getClass().getName());
		System.out.println("objWrapper: " + objWrapper);

		rm.setValue(tkmssid + attributeName, objWrapper, s);
	}

	/**
	 * 获取属性
	 * 
	 * @param request 客户端请求
	 * @param attributeName 缓存属性的名称
	 * @return Object
	 */
	public static Object getAttribute(HttpServletRequest request, String attributeName) {
		TkmCookie tCookie = new TkmCookie(request, "check");

		log.info("[读取缓存中的]:" + tCookie.getTkmssid() + attributeName);
		if (tCookie.isCheck) {
			Object value = rm.getValue(tCookie.getTkmssid() + attributeName);
			if (value == null) {
				return null;
			}
			if (value instanceof ObjectWrapper) {
				System.out.println("get not Serializable!");
				System.out.println("objWrapper: " + value);
				String data = ((ObjectWrapper) value).getData();
				String className = ((ObjectWrapper) value).getClassName();
				try {
					Object bean = JSON.parseObject(data, Class.forName(className));
					return bean;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			return value;
		} else {
			return null;
		}
	}

	/**
	 * 获取属性
	 * 
	 * @param 客户端请求
	 * @param attributeName 缓存属性的名称
	 * @return Object
	 */
	public static Object getAttribute(String tkmssid, String tkmtoken, String attributeName) {
		if (tkmssid != null && tkmtoken != null && tkmtoken.equals(Md5.getMD5Mac((tkmssid + TkmCookie.key)))) {
			return rm.getValue(tkmssid + attributeName);
		}
		return null;
	}

	/**
	 * 根据属性名称，清除会话缓存中的属性
	 * 
	 * @param request 客户端请求
	 * @param attributeName 缓存属性的名称
	 * @return Object
	 */
	public static void removeAttribute(HttpServletRequest request, String attributeName) {
		String tkmssid = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("tkmssid".equals(c.getName())) {
					tkmssid = c.getValue();
				}
			}
		}

		rm.del(tkmssid + attributeName);
	}

	/**
	 * 清除所有会话缓存
	 * 
	 * @param request 客户端请求
	 */
	public static void invalidate(HttpServletRequest request) {
		request.getSession().invalidate();
	}
	/**
	 * 延长Session中各个缓存的过期时间
	 * @param request
	 */
	public static void extendSessionExpiration(HttpServletRequest request) {
		String tkmssid = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("tkmssid".equals(c.getName())) {
					tkmssid = c.getValue();
				}
			}
		}
		if (tkmssid == null || "-1".equals(tkmssid)) {
			tkmssid = "" + request.getAttribute("tkmssid");
		}
		for(SessionKey sk:SessionKey.values()){
			rm.setExpireByte(tkmssid +sk.getValue(), DEFAULT_EXPIRATION);
		}

	}
}
