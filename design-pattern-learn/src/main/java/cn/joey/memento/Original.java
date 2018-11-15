package cn.joey.memento;

public class Original {
	private String value;
	private User user;
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
	
	public Original(String value,User user){
		this.value=value;
		this.user=user;
	}
	//创建备忘录
	public Memento createMemento(){
		return new Memento(value,user);
	}
	//恢复原始状态
	public void restoreMemento(Memento memento){
		this.value=memento.getValue();
		this.user=memento.getUser();
	}
	
}
