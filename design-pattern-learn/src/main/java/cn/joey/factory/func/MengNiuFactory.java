package cn.joey.factory.func;

import cn.joey.factory.MengNiu;
import cn.joey.factory.Milk;

public class MengNiuFactory implements Factory{
    @Override
    public Milk getMilk() {
        return new MengNiu();
    }
}
