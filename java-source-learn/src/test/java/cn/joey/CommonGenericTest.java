package cn.joey;

import cn.joey.proxy.CustomInvocationHandler;
import cn.joey.proxy.HelloWorld;
import cn.joey.proxy.HelloWorldImpl;
import cn.joey.socket.User;
import cn.joey.thread.Consumer;
import cn.joey.thread.Producer;
import cn.joey.util.SortAlgorithm;
import org.junit.Test;
import sun.misc.Unsafe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

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
//        HelloWorld helloWorld = (HelloWorld) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{HelloWorld.class}, handler);
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


    @Test
    public void test(){
        String str = "D:\\USMSSO\\uninst.exe";
        try {
            FileInputStream fis = new FileInputStream(str);
            byte[] datas = new byte[1024];
//            while(fis.read(datas)!=-1){
////                String s = new String(datas,"UTF-8");
////                System.out.println(s);
//                for(int i=0;i<datas.length;i++){
//                    System.out.print(datas[i]);
//                }
//                System.out.println();
//            }
            SequenceInputStream sis = new SequenceInputStream(fis,fis);
            System.out.println(sis.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
