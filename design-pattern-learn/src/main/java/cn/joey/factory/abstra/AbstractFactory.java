package cn.joey.factory.abstra;

import cn.joey.factory.Milk;

public abstract class AbstractFactory {
    /**
     * 获得蒙牛品牌的牛奶
     * @return
     */
    public abstract Milk getMengNiu();

    public abstract Milk getTeLunSu();
}
