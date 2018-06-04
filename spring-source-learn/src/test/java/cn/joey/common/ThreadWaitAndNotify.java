package cn.joey.common;

public class ThreadWaitAndNotify implements Runnable{
	private String name;
	private Object prev;
	private Object self;
	ThreadWaitAndNotify(String name,Object prev,Object self){
		this.name=name;
		this.prev = prev;
		this.self = self;
	}
	
	@Override
	public void run() {
		int count=10;
		while(count>0){
			synchronized(prev){
				synchronized(self){
					System.out.print(name);
					count--;
					self.notify();
				}
				try {
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		ThreadWaitAndNotify tAndNotifyA = new ThreadWaitAndNotify("A", c, a);
		ThreadWaitAndNotify tAndNotifyB = new ThreadWaitAndNotify("B", a, b);
		ThreadWaitAndNotify tAndNotifyC = new ThreadWaitAndNotify("C", b, c);
		new Thread(tAndNotifyA).start();
		Thread.sleep(100);
		new Thread(tAndNotifyB).start();
		Thread.sleep(100);
		new Thread(tAndNotifyC).start();
	}
}
