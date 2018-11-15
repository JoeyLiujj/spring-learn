package com.taikang.common;

import java.io.Serializable;

/**
 * 
 * @author wangwc11
 * @date 2016??12??1??
 */
public class ObjectWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String data;
	private String className;
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/** 
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return "ObjectWrapper [data=" + data + ", className=" + className + "]";
	}
	
	
}
