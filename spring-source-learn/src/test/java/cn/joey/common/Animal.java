package cn.joey.common;

public abstract class Animal implements TestInterface{
	private String eye;
	public Animal(String eye){
		this.eye = eye;
	}
	public void eat(){
		System.out.println("Animal eating");
	}
	public String toString(){
		return "Eye is "+eye+".Any Animal can eat";
	}
}
