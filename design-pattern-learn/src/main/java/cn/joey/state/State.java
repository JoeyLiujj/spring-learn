package cn.joey.state;

public class State {
	private String value1;
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public void method1(){
		System.out.println("状态:"+value1);
	}
	public void method2(){
		System.out.println("状态:"+value1);
	}
}
