package cn.joey.thread;

public class Test2 {
    public static void main(String[] args){
        for(int i=0;i<5;i++) {
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行线程：" + Thread.currentThread().getName());
                }
            },"thread-"+i);
            thread2.start();
        }
        System.out.println("我什么时候执行到");
    }
}
