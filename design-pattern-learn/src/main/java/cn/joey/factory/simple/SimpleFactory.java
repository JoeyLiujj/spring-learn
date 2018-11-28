package cn.joey.factory.simple;

import cn.joey.factory.Milk;
import cn.joey.factory.TeLunSu;
import cn.joey.factory.Yili;

public class SimpleFactory {
    public Milk getMilk(String name) {
        if (name.equals("特仑苏")) {
            return new TeLunSu();
        }
        if(name.equals("伊利")){
            return new Yili();
        }
        return null;
    }
}
