package cn.joey.thread;

import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.annotations.ThreadSafe;

/**
 * @auther liujiji
 * @date 2018/10/26 12:28
 * 双层同步锁
 */
@ThreadSafe
public class SingletonExample4 {
    private SingletonExample4(){

    }

    //单例对象
//    private static SingletonExample4 instance = null;
    //volatile +双层检测机制 ->禁止指令重排
    private volatile static SingletonExample4 instance = null;
    //指令重排
    //1、memory = allocate() 分配内存空间
    //2、ctorInstance() 初始化对象
    //3、设置instance = memory指向刚分配的内存

    //JVM和cpu优化，发生了指令重排，变成了1 3 2 的形式

    //不让指令重排，限制重排，volatile

    //静态的工厂方法
    public static SingletonExample4 getInstance(){
        if (instance == null) { //双层检测机制
            synchronized (SingletonExample4.class) {
                if(instance==null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
