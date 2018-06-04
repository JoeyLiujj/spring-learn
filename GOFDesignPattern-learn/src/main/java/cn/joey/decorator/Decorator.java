package cn.joey.decorator;

public class Decorator implements Sourceable{

	private Sourceable source;
	public Decorator(Sourceable source){
		this.source = source;
	}
	@Override
	public void method() {
		System.out.println("before cn.joey.decorator");
		source.method();
		System.out.println("after cn.joey.decorator");
	}
}
