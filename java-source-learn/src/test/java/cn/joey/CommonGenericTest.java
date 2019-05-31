package cn.joey;

import cn.joey.proxy.CustomInvocationHandler;
import cn.joey.proxy.HelloWorld;
import cn.joey.proxy.HelloWorldImpl;
import cn.joey.socket.User;
import cn.joey.thread.Consumer;
import cn.joey.thread.Producer;
import cn.joey.util.SortAlgorithm;
import org.apache.ibatis.io.Resources;
import org.junit.Test;
import sun.misc.Unsafe;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.stream.Stream;

public class CommonGenericTest {

    @Test
    public void testSortAlgorithm() {
        int arr[] = {3, 534, 2, 3, 43, 54, 2123, 43};
        SortAlgorithm.bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "->");
        }
    }

    @Test
    public void testProxy(){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.out.println(System.getProperty("sun.misc.ProxyGenerator.saveGeneratedFiles"));
        CustomInvocationHandler handler = new CustomInvocationHandler(new HelloWorldImpl());
        HelloWorld helloWorld = handler.newInstance();
        //当调用代理类的方法时，实际上在代理类的字节码文件中会调用handler的invoke方法
        //即调用CustomInvocationHandler的invoke方法
        helloWorld.sayHello("Joey");
        System.out.println("打印代理类的Class名称："+helloWorld.getClass().getName());
    }

    @Test
    public void testScheduled() throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("打印");
            }
        },1000,2000);
        // 单元测试不支持多线程，因为单元测试会直接结束，不会等待子线程结束而结束
        Thread.currentThread().join();//     Thread.sleep(1000000);
    }

    @Test
    public void objectOffsetTest() throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        User user = new User("","");
        long nameOffset = unsafe.objectFieldOffset(User.class.getDeclaredField("name"));
        unsafe.putObject(user,nameOffset,"helloworld");
        System.out.println(user.getName());
    }

}
