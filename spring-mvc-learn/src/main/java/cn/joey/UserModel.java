package cn.joey;

import java.util.List;


public class UserModel {
	private String username;
	private int password;
	private List address;
	public List getAddress() {
		return address;
	}
	public void setAddress(List address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "username="+username+",password="+password;
	}
	
}
