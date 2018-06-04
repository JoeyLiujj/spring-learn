package cn.joey;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class DataBinderTestModel {
	private String username;
	private boolean bool;
//	private SchoolInfoModel schoolInfo;
	private List hobbyList;
	private Map map;
	private PhoneNumberModel phoneNumber;  // String -> 自定义对象的装换
	private Date date;
//	private UserState state; //String ->enum的装换
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
//	public SchoolInfoModel getSchoolInfo() {
//		return schoolInfo;
//	}
//	public void setSchoolInfo(SchoolInfoModel schoolInfo) {
//		this.schoolInfo = schoolInfo;
//	}
	public List getHobbyList() {
		return hobbyList;
	}
	public void setHobbyList(List hobbyList) {
		this.hobbyList = hobbyList;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public PhoneNumberModel getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(PhoneNumberModel phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
//	public UserState getState() {
//		return state;
//	}
//	public void setState(UserState state) {
//		this.state = state;
//	}
	
}
