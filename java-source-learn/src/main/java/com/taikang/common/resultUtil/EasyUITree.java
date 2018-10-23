package com.taikang.common.resultUtil;

import java.util.List;

public class EasyUITree {
	String id;
	String text;
	String state;
	 private Integer seq;
	Boolean checked;
	String attributes;
	String picFlag;
	String todoTaskCounts;
	String pid;
	List<EasyUITree> children;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public List<EasyUITree> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUITree> children) {
		this.children = children;
	}
	public String getPicFlag() {
		return picFlag;
	}
	public void setPicFlag(String picFlag) {
		this.picFlag = picFlag;
	}
	public String getTodoTaskCounts() {
		return todoTaskCounts;
	}
	public void setTodoTaskCounts(String todoTaskCounts) {
		this.todoTaskCounts = todoTaskCounts;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
}
