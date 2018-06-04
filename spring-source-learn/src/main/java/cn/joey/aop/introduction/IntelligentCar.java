package cn.joey.aop.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;

/**
   以IntroductionInterceptor为Advice，则可以为目标类实现一个新的接口，给目标对象动态的
    添加属性和方法，eg. 如用以下的方式作为一个advice后，MyCar对象实际上就实现了Interlligent接口，并实现了
    selfDriving方法，同时invoke方法还可以在原有方法执行前添加任何行为
 */

public class IntelligentCar extends DelegatingIntroductionInterceptor implements Intelligent {

    public String addedFiled;

    public String getAddedFiled() {
        return addedFiled;
    }

    public void setAddedFiled(String addedFiled) {
        this.addedFiled = addedFiled;
    }

    public void selfDriving() {
        setAddedFiled("zhangsan");
        System.out.println("打印被添加的字段值，说明此字段被添加->"+getAddedFiled());
        System.out.println("开启无人驾驶模式，别挡路");
    }

    //实际上执行时会在CglibAopProxy类中将DynamicAdvisedInterceptor放入到回调方法中，当执行此拦截器时，会调用
    //CglibMethodInvocation-->ReflectiveMethodInvocation的proceed方法，此方法又会调用各个advice的invoke方法，并将自己作为参数传入
    //即在该方法的MethodInvocation==CglibMethodInvocation,
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //实际方法执行的地方
        Method method = mi.getMethod();
        System.out.println("在实际方法执行前打印执行方法的名字->"+method.getName());
        return super.invoke(mi);
    }
}
