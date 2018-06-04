package cn.joey.common;

import java.util.HashMap;
import java.util.Map;

public class Derived {
    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        Map map = new HashMap();
        for (int i = 0; i < 5000; i++) {
            map.put(i, i + "key");
        }
        System.out.println(map.size());
        long end1 = System.currentTimeMillis();
        System.out.println("没有设置初始容量时的耗时数" + (end1 - start1));

        long start2 = System.currentTimeMillis();
        Map map2 = new HashMap(6000);
        for (int i = 0; i < 5000; i++) {
            map2.put(i, i + "key");
        }
        System.out.println(map2.size());
        long end2 = System.currentTimeMillis();
        System.out.println("设置初始容量时的耗时数" + (end2 - start2));
    }

    public String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

}
