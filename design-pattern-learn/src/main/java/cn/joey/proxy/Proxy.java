package cn.joey.proxy;

public class Proxy implements Sourceable{
	public Source source;
	
	public Proxy(){
		super();
		this.source = new Source();
	}
	
	@Override
	public void method() {
		before();
		source.method();
		after();
	}
	public void before(){
		System.out.println("before method");
	}
	public void after(){
		System.out.println("after method");
	}
}
