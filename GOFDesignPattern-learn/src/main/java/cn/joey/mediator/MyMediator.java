package cn.joey.mediator;

public class MyMediator implements Mediator{
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	private User user1;
	private User user2;
	
	@Override
	public void createMediator() {
		user1=new User1(this);
		user2=new User2(this);
	}
	@Override
	public void workAll() {
		user1.work();
		user2.work();
	}
	
}
