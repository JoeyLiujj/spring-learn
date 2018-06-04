package cn.joey.common;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class Task implements Runnable{
	private static final CountDownLatch start_Latch = new CountDownLatch(1);
	private static final CountDownLatch stop_Latch = new CountDownLatch(100);
	@Override
	public void run() {
		try {
			start_Latch.await();
			System.out.println("do something");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			stop_Latch.countDown();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<100;i++)
			new Thread(new Task()).start();
		System.out.println(new Date().toString());
		start_Latch.countDown();
		stop_Latch.await();
		System.out.println(new Date().toString());
	}
}
