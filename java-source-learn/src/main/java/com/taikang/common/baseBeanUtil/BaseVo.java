package com.taikang.common.baseBeanUtil;

import com.alibaba.fastjson.JSON;

/**
 * 所有自己封装的类需要继承的父类
 * @author itw_gusen
 *
 */
public class BaseVo {

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
