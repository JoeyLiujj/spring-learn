package cn.joey.proxy;

public class Source implements Sourceable{

	@Override
	public void method() {
		System.out.println("the original source");
	}
	
}