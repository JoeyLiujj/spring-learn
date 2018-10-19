package cn.joey.observer;

public class MySubject extends AbstractSubject{

	@Override
	public void operation() {
		System.out.println("update self!");  
		notifyObservers();
	}
}
