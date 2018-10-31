package cn.joey.thread;

import cn.joey.socket.User;

/**
 * @author liujiji
 */
public class TestThreadLocal {
    private static ThreadLocal<User> tl = new ThreadLocal<User>(){
        @Override
        protected User initialValue() {
            return new User("Joey","1234");
        }
    };
    private static ThreadLocal<String> tl2 = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "main主线程";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new TestThread().start();
        }
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+tl.get());
        System.out.println(Thread.currentThread().getName()+tl2.get());
    }

    static class TestThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<3;i++) {
                User user = tl.get();
                user.setName(Thread.currentThread().getName());
                user.setPassword(i+"");
                tl.set(user);
                tl2.set(Thread.currentThread().getName()+"---");
                System.out.println(tl.get());
                System.out.println(tl2.get());
            }
        }
    }
}
