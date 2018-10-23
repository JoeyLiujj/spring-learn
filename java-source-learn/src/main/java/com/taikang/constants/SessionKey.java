package com.taikang.constants;

public enum SessionKey {
	/**
	 * 用户信息
	 */
	USER_INFO("userInfo"),
	/**
	 * 用户角色信息
	 */
	ROLE_ID_LIST("roleIdList"),
	/**
	 * 用户权限信息
	 */
	USER_RIGHT("userRight");
	
	private final String value;
	
	SessionKey(String value){
		this.value=value;
	}
	
	public String getValue(){
		return this.value;
	}
}
