package cn.joey.factory.abstra;

import cn.joey.factory.Milk;

public class AbstractFactoryTest {
    /**
     * 抽象工厂模式，在spring中应用的最为广泛的，公共的逻辑写在一起，易于扩展。
     * @param args
     */
    public static void main(String[] args) {
        MilkFactory factory = new MilkFactory();
        //用户只有选择的权利了，保证了代码的健壮性，
        Milk mengNiu = factory.getMengNiu();
        System.out.println(mengNiu.getName());

        Milk teLunSu = factory.getTeLunSu();
        System.out.println(teLunSu.getName());

    }
}
