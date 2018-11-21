package cn.joey.thread;

import cn.joey.socket.User;

import java.util.concurrent.CountDownLatch;

public class ThreadDemo{

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(3);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			}
		}).start();

		countDownLatch.await();

		System.out.println("执行完毕");
	}
}