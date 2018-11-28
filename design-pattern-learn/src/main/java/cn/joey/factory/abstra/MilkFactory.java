package cn.joey.factory.abstra;

import cn.joey.factory.MengNiu;
import cn.joey.factory.Milk;
import cn.joey.factory.TeLunSu;

public class MilkFactory  extends AbstractFactory {
    @Override
    public Milk getMengNiu() {
        return new MengNiu();
    }

    @Override
    public Milk getTeLunSu() {
        return new TeLunSu();
    }

}
