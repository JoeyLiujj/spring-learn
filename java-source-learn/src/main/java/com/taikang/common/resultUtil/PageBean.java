package com.taikang.common.resultUtil;

import java.util.List;
import java.util.Map;

public class PageBean<T> implements Comparable{
	/*
	 * 1.当前页
	 * 2.当前页数据
	 * 3.总页数
	 * 4.每页显示条数
	 * 5.总条数
	 */
	private Map<String,Object> map;
	private Object obj;
	private int pageNumber;
	private List<T> data;
	private int totalPage;
	private int pageSize;
	private int totalRecord;
	private String orderByClause;
	//服务器响应码
	private String code;
	//状态
	private String status;
	//信息
	private String message;
	
	
	public String getOrderByClause() {
		return orderByClause;
	}
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public int getTotalPage() {
		return (int) Math.ceil((totalRecord*1.0)/pageSize);
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public PageBean(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	//获取开始索引的方法
	public int getStartIndex(){
		return (pageNumber-1)*pageSize;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
