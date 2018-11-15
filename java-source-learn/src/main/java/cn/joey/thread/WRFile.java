package cn.joey.thread;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WRFile {
    boolean flag;

    public WRFile() {

    }
    public synchronized void read1(){
        if(this.flag){
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile ra=null;
        try {
            ra = new RandomAccessFile("love.txt","rw");
            ra.seek(ra.length());
            ra.writeBytes("I love you");
            ra.writeBytes("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ra.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //修改标记 唤醒线程
        this.flag = true;
        this.notify();
        System.out.println("我read1是否还会执行吗");
    }
    public synchronized void read2(){
        if(!this.flag){
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile ra=null;
        try {
            ra = new RandomAccessFile("love.txt","rw");
            ra.seek(ra.length());
            ra.writeBytes("so do I");
            ra.writeBytes("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ra.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //修改标记 唤醒线程
        this.flag = false;
        this.notify();
        System.out.println("我read2是否还会执行吗");
    }
}
