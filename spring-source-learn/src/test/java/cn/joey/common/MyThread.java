package cn.joey.common;

public class MyThread implements Runnable{
	private static ZhangSan zs = new ZhangSan();
	private static LISI ls=new LISI();
	public boolean flag = false;
	@Override
	public void run() {
		if(flag){
			synchronized(zs){
				zs.say();
				synchronized(ls){
					zs.get();
				}
			}
		}else{
			synchronized(ls){
				ls.say();
				synchronized(zs){
					ls.get();
				}
			}
		}
	}
	public static void main(String[] args) {
		MyThread mt1 = new MyThread();
		MyThread mt2 = new MyThread();
		mt1.flag=true;
		mt2.flag = false;
		Thread thread1 = new Thread(mt1);
		Thread thread2 = new Thread(mt2);
		thread1.start();
		thread2.start();
	}
}
