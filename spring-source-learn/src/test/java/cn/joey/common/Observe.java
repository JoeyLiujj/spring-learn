package cn.joey.common;

import java.util.Observable;

import org.junit.Test;

public class Observe extends Observable{
	
	public static void main(String[] args) {
		Observe observe = new Observe();
		observe.addObserver(new FirstObserver());
		observe.eat();
	}
	@Test
	public void eat(){
		this.setChanged();
		this.notifyObservers();
	}
}
