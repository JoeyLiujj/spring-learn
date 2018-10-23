package com.taikang.common.resultUtil;

import java.io.Serializable;
import java.util.List;

public class EasyUIDataGridResult implements Serializable{

	private long total;
	private List rows ;
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	public static EasyUIDataGridResult error(){
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.state = "550";
		return result;
	}
}
