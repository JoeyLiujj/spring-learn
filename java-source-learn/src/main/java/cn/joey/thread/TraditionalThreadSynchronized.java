package cn.joey.thread;

import java.io.OutputStream;

public class TraditionalThreadSynchronized {
    public static void main(String[] args) {
        final OutPutter output = new OutPutter();
        new Thread(){
            @Override
            public void run() {
                output.output("zhangsan");
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                output.output("lisi");
            }
        }.start();
    }
}
class OutPutter{
    public synchronized void output(String name){
//    public void output(String name){
        for(int i=0;i<name.length();i++){
            System.out.print(name.charAt(i));
        }
    }
}
