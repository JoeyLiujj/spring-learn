package cn.joey.aop.xmlconfig;


import org.aspectj.lang.JoinPoint;

public class LogAspect {

    public void addSuccessLog(JoinPoint jp, String name) {
        Object[] params = jp.getArgs();
        //获取目标方法体参数
        //获取目标类名
        String className = jp.getTarget().getClass().toString();
        //获取目标方法签名
        String signature = jp.getSignature().toString();
        //获取注解值
        String methodName = signature.substring(signature.lastIndexOf(".") + 1, signature.indexOf("("));

//        String desc = fl.value();
        //把调用的细信息写到日常记录信息里面去
        System.out.println("调用方法返回：addSuccessLog()---" + className + "---->");
        System.out.println("打印目标方法的返回值----" + name);
    }

    public void addExceptionLog(JoinPoint jp, Exception e) {
        System.out.println("获取到目标对象抛出的异常：" + e.getMessage());
        System.out.println("获取到目标对象抛出的异常：" + e.getCause());
    }

}
