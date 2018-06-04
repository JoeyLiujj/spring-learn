package cn.joey.common;

public class TestThread2 {
	public static void main(String[] args) throws InterruptedException {
		Thread2 thread2 = new Thread2(10);
		Thread t1 = new Thread(thread2,"c");
		Thread t2 = new Thread(thread2,"d");
//		t2.start();
		t2.join();
		t1.start();
	}
}


class Thread2 implements Runnable{
	private int count;
	public Thread2(int count) {
		this.count = count;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			count--;
			System.out.println("当前线程是："+Thread.currentThread().getName()+"---"+count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}