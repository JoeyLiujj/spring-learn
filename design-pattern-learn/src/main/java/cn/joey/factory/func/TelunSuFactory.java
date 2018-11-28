package cn.joey.factory.func;

import cn.joey.factory.Milk;
import cn.joey.factory.TeLunSu;

public class TelunSuFactory implements Factory{

    @Override
    public Milk getMilk() {
        return new TeLunSu();
    }
}
