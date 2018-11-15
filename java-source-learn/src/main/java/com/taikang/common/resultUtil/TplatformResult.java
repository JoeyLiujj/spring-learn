package com.taikang.common.resultUtil;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author itw_lirc
 *
 */
public class TplatformResult implements Serializable {
	// 定义jackson对象
	public static final ObjectMapper MAPPER = new ObjectMapper();
	// 响应业务状态
	private Integer status;
	// 响应消息
	private String msg;
	// 响应中的数据
	private Object data;

	public static TplatformResult build(Integer status, String msg, Object data) {
		return new TplatformResult(status, msg, data);
	}

	public static TplatformResult ok(Object data) {
		return new TplatformResult(data);
	}

	public static TplatformResult ok() {
		return new TplatformResult(null);
	}

	public TplatformResult() {

	}

	public static TplatformResult build(Integer status, String msg) {
		return new TplatformResult(status, msg, null);
	}

	public TplatformResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public TplatformResult(Object data) {
		this.status = 200;
		this.msg = "Ok";
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 将json结果对象转化为TplatformResult对象
	 * 
	 * @param jsonData json数据
	 * @param clazz TplatformResult中的object类型
	 * @return
	 */
	public static TplatformResult formatToDto(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, TplatformResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 没有object对象的转化
	 * 
	 * @param json
	 * @return
	 */
	public static TplatformResult format(String json) {
		try {
			return MAPPER.readValue(json, TplatformResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * object是集合转化
	 * 
	 * @param jsonData json数据
	 * @param clazz 集合中的类型
	 * @return
	 */
	public static TplatformResult formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isArray() && data.size() > 0) {
					obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}
}
