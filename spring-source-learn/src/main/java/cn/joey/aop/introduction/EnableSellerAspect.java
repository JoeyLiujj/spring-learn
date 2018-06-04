package cn.joey.aop.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class EnableSellerAspect {

    @DeclareParents(value = "cn.joey.aop.introduction.NativeWaiter",defaultImpl = SmartSeller.class)
    public Seller seller; //要实现的目标接口
}
