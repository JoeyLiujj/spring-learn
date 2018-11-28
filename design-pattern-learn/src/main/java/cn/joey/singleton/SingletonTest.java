package cn.joey.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @auther liujiji
 * @date 2018/11/27 9:15
 */
public class SingletonTest {
    private static Map<String,Object> map = new HashMap<String, Object>();
    public static void main(String[] args) {
        int count = 200;

        final CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i=0;i<count;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        Object instance = get("cn.joey.singleton.SingletonDemo");
                        System.out.println(instance);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            countDownLatch.countDown();
        }



    }

    public static Object get(String name){
        Object obj=null;
        if(!map.containsKey(name)){
            try {
                Class cl = Class.forName(name);
                obj = cl.newInstance();
                map.put(obj.getClass().getName(),obj);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }else{
            obj = map.get(name);
        }
        return obj;
    }
}
