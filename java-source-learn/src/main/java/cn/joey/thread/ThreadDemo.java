package cn.joey.thread;

import cn.joey.socket.User;

public class ThreadDemo extends Thread{
	private int i = 0;
	User user ;
	@Override
	public void run() {
		user  = new User(this.getName(),this.getName()+this.getId());
		System.out.println(this +":" + ++i);
		user.setName(this.getName()+this.getState());
		System.out.println(user.getName()+"  "+user.getPassword());
	}

	public static void main(String[] args){
		for(int i=0;i<5;i++){
			ThreadDemo t = new ThreadDemo();
			t.start();

		}

	}
}