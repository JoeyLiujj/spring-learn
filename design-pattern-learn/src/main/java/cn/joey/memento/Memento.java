package cn.joey.memento;

public class Memento {

	private String value;
	private User user;

	public Memento(String value, User user) {
		this.value = value;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
